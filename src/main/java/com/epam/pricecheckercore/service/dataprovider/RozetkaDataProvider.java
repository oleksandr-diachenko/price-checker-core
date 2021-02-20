package com.epam.pricecheckercore.service.dataprovider;

import com.epam.pricecheckercore.exception.MapperException;
import com.epam.pricecheckercore.exception.ProductNotFoundException;
import com.epam.pricecheckercore.model.api.RozetkaDTO;
import com.epam.pricecheckercore.model.magazine.Magazine;
import com.epam.pricecheckercore.model.product.ProductData;
import com.epam.pricecheckercore.service.parser.Parser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javamoney.moneta.Money;

import java.net.MalformedURLException;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;
import static com.epam.pricecheckercore.model.enums.ProductStatus.AVAILABLE;

@Slf4j
@RequiredArgsConstructor
public class RozetkaDataProvider implements DataProvider {

    private final Parser<String> apiParser;
    private final Magazine magazine;
    private final Mapper mapper;
    private final ApiUrlConstructor apiUrlConstructor;

    @Override
    public Magazine getMagazine() {
        return magazine;
    }

    @Override
    public ProductData getProductData(String urlToPage) throws ProductNotFoundException {
        String apiUrl = getApiUrl(urlToPage);

        return apiParser.getContent(apiUrl)
                .map(this::mapToProductData)
                .orElseThrow(ProductNotFoundException::new);
    }

    private String getApiUrl(String urlToPage) throws ProductNotFoundException {
        try {
            return apiUrlConstructor.construct(urlToPage);
        } catch (MalformedURLException e) {
            throw new ProductNotFoundException();
        }
    }

    private ProductData mapToProductData(String content) {
        try {
            RozetkaDTO rozetkaDTO = mapper.map(content, RozetkaDTO.class);
            return populateProductData(rozetkaDTO);
        } catch (MapperException e) {
            log.error("Can't map response. Returning default data", e);
            return new ProductData();
        }
    }

    private ProductData populateProductData(RozetkaDTO rozetkaDTO) {
        ProductData productData = new ProductData();

        productData.setInStock(AVAILABLE.getStatus().equals(rozetkaDTO.getStatus()));
        if(!productData.isInStock()) {
            return productData;
        }
        if (rozetkaDTO.getOldPrice() == 0) {
            populateNormalPrice(productData, rozetkaDTO.getPrice());
            populateDiscountPrice(productData, 0);
        } else {
            populateNormalPrice(productData, rozetkaDTO.getOldPrice());
            populateDiscountPrice(productData, rozetkaDTO.getPrice());
        }
        return productData;
    }

    private void populateDiscountPrice(ProductData productData, int discountPrice) {
        productData.setDiscountedPrice(Money.of(discountPrice, UAH.name()));
    }

    private void populateNormalPrice(ProductData productData, int normalPrice) {
        productData.setNormalPrice(Money.of(normalPrice, UAH.name()));
    }
}
