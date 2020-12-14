package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.AbstractProductDataProviderTest;
import com.epam.pricecheckercore.model.magazine.NowZenith;
import org.javamoney.moneta.Money;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;
import static com.epam.pricecheckercore.model.enums.CurrencyCode.USD;
import static org.assertj.core.api.Assertions.assertThat;

class NowZenithTest extends AbstractProductDataProviderTest {

    protected NowZenithTest() {
        super(new NowZenithProductSelector(), new NowZenith());
    }

    @Test
    void shouldReturnDiscountPrice() throws Exception {
        Document document = getDocument("xml/nowZenith/NowZenith_discount.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(5.29, USD.name()));
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(12.02, USD.name()));
    }

    @Test
    void shouldReturnOutOfStock() throws Exception {
        Document document = getDocument("xml/nowZenith/NowZenith_outofstock.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.isInStock()).isFalse();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnNormalPrice() throws Exception {
        Document document = getDocument("xml/nowZenith/NowZenith_normal.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.getNormalPrice()).isEqualTo(Money.of(7.93, USD.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, USD.name()));
    }

    @Test
    void shouldReturnNotFound() throws FileNotFoundException {
        Document document = getDocument("xml/nowZenith/NowZenith_notfound.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.isInStock()).isTrue();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, USD.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, USD.name()));
    }
}
