package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.AbstractProductDataProviderTest;
import com.epam.pricecheckercore.model.magazine.Makeup;
import org.javamoney.moneta.Money;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;
import static org.assertj.core.api.Assertions.assertThat;

class MakeupTest extends AbstractProductDataProviderTest {

    protected MakeupTest() {
        super(new MakeupProductSelector(), new Makeup());
    }

    @Test
    void shouldReturnDiscountPrice() throws Exception {
        Document document = getDocument("xml/makeup/Makeup_discount.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(660, UAH.name()));
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(990, UAH.name()));
    }

    @Test
    void shouldReturnNormalPrice() throws Exception {
        Document document = getDocument("xml/makeup/Makeup_normal.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.getNormalPrice()).isEqualTo(Money.of(623, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnNormalMultiplePrice() throws Exception {
        Document document = getDocument("xml/makeup/Makeup_normal_multiple.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.getNormalPrice()).isEqualTo(Money.of(1676, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnOutOfStock() throws Exception {
        Document document = getDocument("xml/makeup/Makeup_outofstock.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.isInStock()).isFalse();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnNotFound() throws FileNotFoundException {
        Document document = getDocument("xml/makeup/Makeup_notfound.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.isInStock()).isTrue();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }
}
