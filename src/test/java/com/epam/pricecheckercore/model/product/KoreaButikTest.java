package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.AbstractProductDataProviderTest;
import com.epam.pricecheckercore.model.magazine.KoreaButik;
import org.javamoney.moneta.Money;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;
import static org.assertj.core.api.Assertions.assertThat;

class KoreaButikTest extends AbstractProductDataProviderTest {

    protected KoreaButikTest() {
        super(new KoreaButikProductSelector(), new KoreaButik());
    }

    @Test
    void shouldReturnDiscountPrice() throws Exception {
        Document document = getDocument("xml/koreaButik/KoreaButik_discount.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(740, UAH.name()));
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(925, UAH.name()));
    }

    @Test
    void shouldReturnNormalPrice() throws Exception {
        Document document = getDocument("xml/koreaButik/KoreaButik_normal.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.getNormalPrice()).isEqualTo(Money.of(849, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }

    @Test
    void shouldReturnOutOfStock() throws Exception {
        Document document = getDocument("xml/koreaButik/KoreaButik_outofstock.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.isInStock()).isFalse();
    }

    @Test
    void shouldReturnNotFound() throws FileNotFoundException {
        Document document = getDocument("xml/koreaButik/KoreaButik_notfound.xml");

        ProductData price = dataProvider.getProductData(document);

        assertThat(price.isInStock()).isTrue();
        assertThat(price.getNormalPrice()).isEqualTo(Money.of(0, UAH.name()));
        assertThat(price.getDiscountedPrice()).isEqualTo(Money.of(0, UAH.name()));
    }
}
