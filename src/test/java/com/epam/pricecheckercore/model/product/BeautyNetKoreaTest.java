package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.AbstractProductDataProviderTest;
import com.epam.pricecheckercore.model.magazine.BeautyNetKorea;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.USD;
import static org.assertj.core.api.Assertions.assertThat;

class BeautyNetKoreaTest extends AbstractProductDataProviderTest {

    protected BeautyNetKoreaTest() {
        super(new BeautyNetKoreaProductSelector(), new BeautyNetKorea());
    }

    @Test
    void shouldReturnDiscountPrice() throws Exception {
        ProductData price = dataProvider.getProductData("xml/beautyNewKorea/BeautyNetKorea_discount.xml");

        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(6.95, USD.name()));
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(15.80, USD.name()));
    }

    @Test
    void shouldReturnNormalPrice() throws Exception {
        ProductData price = dataProvider.getProductData("xml/beautyNewKorea/BeautyNetKorea_normal.xml");

        assertThat(price.getNormalPrice()).isEqualTo(Money.of(1.50, USD.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, USD.name()));
    }

    @Test
    void shouldReturnOutOfStock() throws Exception {
        ProductData price = dataProvider.getProductData("xml/beautyNewKorea/BeautyNetKorea_outofstock.xml");

        assertThat(price.isInStock()).isFalse();
    }

    @Test
    void shouldReturnNotFound() throws Exception {
        ProductData price = dataProvider.getProductData("xml/beautyNewKorea/BeautyNetKorea_notfound.xml");

        assertThat(price.isInStock()).isTrue();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, USD.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, USD.name()));
    }
}
