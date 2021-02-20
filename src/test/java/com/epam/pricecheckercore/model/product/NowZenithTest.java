package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.AbstractProductDataProviderTest;
import com.epam.pricecheckercore.model.magazine.NowZenith;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;
import static com.epam.pricecheckercore.model.enums.CurrencyCode.USD;
import static org.assertj.core.api.Assertions.assertThat;

class NowZenithTest extends AbstractProductDataProviderTest {

    protected NowZenithTest() {
        super(new NowZenithProductSelector(), new NowZenith());
    }

    @Test
    void shouldReturnDiscountPrice() throws Exception {
        ProductData price = dataProvider.getProductData("xml/nowZenith/NowZenith_discount.xml");

        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(5.29, USD.name()));
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(12.02, USD.name()));
    }

    @Test
    void shouldReturnOutOfStock() throws Exception {
        ProductData price = dataProvider.getProductData("xml/nowZenith/NowZenith_outofstock.xml");

        assertThat(price.isInStock()).isFalse();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnNormalPrice() throws Exception {
        ProductData price = dataProvider.getProductData("xml/nowZenith/NowZenith_normal.xml");

        assertThat(price.getNormalPrice()).isEqualTo(Money.of(7.93, USD.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, USD.name()));
    }

    @Test
    void shouldReturnNotFound() throws Exception {
        ProductData price = dataProvider.getProductData("xml/nowZenith/NowZenith_notfound.xml");

        assertThat(price.isInStock()).isTrue();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, USD.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, USD.name()));
    }
}
