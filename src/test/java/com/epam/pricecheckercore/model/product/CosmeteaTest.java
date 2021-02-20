package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.AbstractProductDataProviderTest;
import com.epam.pricecheckercore.model.magazine.Cosmetea;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;
import static org.assertj.core.api.Assertions.assertThat;

class CosmeteaTest extends AbstractProductDataProviderTest {

    protected CosmeteaTest() {
        super(new CosmeteaProductSelector(), new Cosmetea());
    }

    @Test
    void shouldReturnDiscountPrice() throws Exception {
        ProductData price = dataProvider.getProductData("xml/cosmetea/Cosmetea_discount.xml");

        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(404, UAH.name()));
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(425, UAH.name()));
    }

    @Test
    void shouldReturnNormalPrice() throws Exception {
        ProductData price = dataProvider.getProductData("xml/cosmetea/Cosmetea_normal.xml");

        assertThat(price.getNormalPrice()).isEqualTo(Money.of(800, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnOutOfStock() throws Exception {
        ProductData price = dataProvider.getProductData("xml/cosmetea/Cosmetea_outofstock.xml");

        assertThat(price.isInStock()).isFalse();
    }

    @Test
    void shouldReturnNotFound() throws Exception {
        ProductData price = dataProvider.getProductData("xml/cosmetea/Cosmetea_notfound.xml");

        assertThat(price.isInStock()).isTrue();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }
}
