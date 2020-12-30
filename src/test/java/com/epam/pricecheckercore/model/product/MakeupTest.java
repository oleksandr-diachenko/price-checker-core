package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.AbstractProductDataProviderTest;
import com.epam.pricecheckercore.model.magazine.Makeup;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;
import static org.assertj.core.api.Assertions.assertThat;

class MakeupTest extends AbstractProductDataProviderTest {

    protected MakeupTest() {
        super(new MakeupProductSelector(), new Makeup());
    }

    @Test
    void shouldReturnDiscountPrice() throws Exception {
        ProductData price = dataProvider.getProductData("xml/makeup/Makeup_discount.xml");

        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(660, UAH.name()));
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(990, UAH.name()));
    }

    @Test
    void shouldReturnNormalPrice() throws Exception {
        ProductData price = dataProvider.getProductData("xml/makeup/Makeup_normal.xml");

        assertThat(price.getNormalPrice()).isEqualTo(Money.of(623, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnNormalMultiplePrice() throws Exception {
        ProductData price = dataProvider.getProductData("xml/makeup/Makeup_normal_multiple.xml");

        assertThat(price.getNormalPrice()).isEqualTo(Money.of(1676, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnOutOfStock() throws Exception {
        ProductData price = dataProvider.getProductData("xml/makeup/Makeup_outofstock.xml");

        assertThat(price.isInStock()).isFalse();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnNotFound() throws Exception {
        ProductData price = dataProvider.getProductData("xml/makeup/Makeup_notfound.xml");

        assertThat(price.isInStock()).isTrue();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }
}
