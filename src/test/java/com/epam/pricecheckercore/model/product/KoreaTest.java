package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.AbstractProductDataProviderTest;
import com.epam.pricecheckercore.model.magazine.Korea;
import org.javamoney.moneta.Money;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;
import static org.assertj.core.api.Assertions.assertThat;

class KoreaTest extends AbstractProductDataProviderTest {

    protected KoreaTest() {
        super(new KoreaProductSelector(), new Korea());
    }

    @Test
    void shouldReturnDiscountPrice() throws Exception {
        Document document = getDocument("xml/korea/Korea_discount.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(200.00, UAH.name()));
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(415.00, UAH.name()));
    }

    @Test
    void shouldReturnNormalPrice() throws Exception {
        Document document = getDocument("xml/korea/Korea_normal.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.getNormalPrice()).isEqualTo(Money.of(360, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnOutOfStock() throws Exception {
        Document document = getDocument("xml/korea/Korea_outofstock.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.isInStock()).isFalse();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnNotFound() throws FileNotFoundException {
        Document document = getDocument("xml/korea/Korea_notfound.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.isInStock()).isTrue();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }
}
