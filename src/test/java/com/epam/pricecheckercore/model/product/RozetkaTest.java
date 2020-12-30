package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.service.dataprovider.*;
import com.epam.pricecheckercore.exception.ProductNotFoundException;
import com.epam.pricecheckercore.model.magazine.Rozetka;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;
import static org.assertj.core.api.Assertions.assertThat;

class RozetkaTest {

    private DataProvider dataProvider;

    @BeforeEach
    void setUp() {
        dataProvider = new RozetkaDataProvider(new FileJsonParser(), new Rozetka(),
                new JsonMapper(), new MockApiUrlConstructor());
    }

    @Test
    void shouldReturnDiscountPrice() throws ProductNotFoundException {
        ProductData actual = dataProvider.getProductData("/json/rozetka/Rozetka_discount.json");

        assertThat(actual.isInStock()).isTrue();
        assertThat(actual.getNormalPrice()).isEqualTo(Money.of(1199, UAH.name()));
        assertThat(actual.getDiscountedPrice()).isEqualTo(Money.of(999, UAH.name()));
    }

    @Test
    void shouldReturnNormalPrice() throws ProductNotFoundException {
        ProductData actual = dataProvider.getProductData("/json/rozetka/Rozetka_normal.json");

        assertThat(actual.isInStock()).isTrue();
        assertThat(actual.getNormalPrice()).isEqualTo(Money.of(9099, UAH.name()));
        assertThat(actual.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnOutOfStock() throws ProductNotFoundException {
        ProductData actual = dataProvider.getProductData("/json/rozetka/Rozetka_outofstock.json");

        assertThat(actual.isInStock()).isFalse();
        assertThat(actual.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(actual.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnNotFound() throws ProductNotFoundException {
        ProductData actual = dataProvider.getProductData("/json/rozetka/Rozetka_notfound.json");

        assertThat(actual.isInStock()).isFalse();
        assertThat(actual.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(actual.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }
}
