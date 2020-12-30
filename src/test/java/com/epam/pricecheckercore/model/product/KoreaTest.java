package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.AbstractProductDataProviderTest;
import com.epam.pricecheckercore.model.magazine.Korea;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;
import static org.assertj.core.api.Assertions.assertThat;

class KoreaTest extends AbstractProductDataProviderTest {

    protected KoreaTest() {
        super(new KoreaProductSelector(), new Korea());
    }

    @Test
    void shouldReturnDiscountPrice() throws Exception {
        ProductData price = dataProvider.getProductData("xml/korea/Korea_discount.xml");

        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(200.00, UAH.name()));
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(415.00, UAH.name()));
    }

    @Test
    void shouldReturnNormalPrice() throws Exception {
        ProductData price = dataProvider.getProductData("xml/korea/Korea_normal.xml");

        assertThat(price.getNormalPrice()).isEqualTo(Money.of(360, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnOutOfStock() throws Exception {
        ProductData price = dataProvider.getProductData("xml/korea/Korea_outofstock.xml");

        assertThat(price.isInStock()).isFalse();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnNotFound() throws Exception {
        ProductData price = dataProvider.getProductData("xml/korea/Korea_notfound.xml");

        assertThat(price.isInStock()).isTrue();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }
}
