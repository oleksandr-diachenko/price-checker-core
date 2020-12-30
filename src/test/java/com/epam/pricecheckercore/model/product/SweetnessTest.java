package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.AbstractProductDataProviderTest;
import com.epam.pricecheckercore.model.magazine.Sweetness;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;
import static org.assertj.core.api.Assertions.assertThat;

class SweetnessTest extends AbstractProductDataProviderTest {

    protected SweetnessTest() {
        super(new SweetnessProductSelector(), new Sweetness());
    }

    @Test
    void shouldReturnDiscountPrice() throws Exception {
        ProductData price = dataProvider.getProductData("xml/sweetness/Sweetness_discount.xml");

        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(144, UAH.name()));
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(190, UAH.name()));
    }

    @Test
    void shouldReturnNormalPrice() throws Exception {
        ProductData price = dataProvider.getProductData("xml/sweetness/Sweetness_normal.xml");

        assertThat(price.getNormalPrice()).isEqualTo(Money.of(530, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnOutOfStock() throws Exception {
        ProductData price = dataProvider.getProductData("xml/sweetness/Sweetness_outofstock.xml");

        assertThat(price.isInStock()).isFalse();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnNotFound() throws Exception {
        ProductData price = dataProvider.getProductData("xml/sweetness/Sweetness_notfound.xml");

        assertThat(price.isInStock()).isTrue();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }
}
