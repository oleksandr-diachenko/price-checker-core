package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.AbstractProductDataProviderTest;
import com.epam.pricecheckercore.model.magazine.RoseRoseShop;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;
import static com.epam.pricecheckercore.model.enums.CurrencyCode.USD;
import static org.assertj.core.api.Assertions.assertThat;

class RoseRoseShopTest extends AbstractProductDataProviderTest {

    protected RoseRoseShopTest() {
        super(new RoseRoseShopProductSelector(), new RoseRoseShop());
    }

    @Test
    void shouldReturnDiscountPrice() throws Exception {
        ProductData price = dataProvider.getProductData("xml/roseRoseShop/RoseRoseShop_discount.xml");

        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(5.49, USD.name()));
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(14.49, USD.name()));
    }

    @Test
    void shouldReturnNormalPrice() throws Exception {
        ProductData price = dataProvider.getProductData("xml/roseRoseShop/RoseRoseShop_normal.xml");

        assertThat(price.getNormalPrice()).isEqualTo(Money.of(35.51, USD.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, USD.name()));
    }

    @Test
    void shouldReturnOutOfStock() throws Exception {
        ProductData price = dataProvider.getProductData("xml/roseRoseShop/RoseRoseShop_outofstock.xml");

        assertThat(price.isInStock()).isFalse();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnNotFound() throws Exception {
        ProductData price = dataProvider.getProductData("xml/roseRoseShop/RoseRoseShop_notfound.xml");

        assertThat(price.isInStock()).isTrue();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, USD.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, USD.name()));
    }
}
