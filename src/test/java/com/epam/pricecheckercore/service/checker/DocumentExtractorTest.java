package com.epam.pricecheckercore.service.checker;

import com.epam.pricecheckercore.AbstractProductDataProviderTest;
import com.epam.pricecheckercore.FileDocumentParser;
import com.epam.pricecheckercore.model.magazine.Rozetka;
import com.epam.pricecheckercore.model.product.RozetkaProductSelector;
import com.epam.pricecheckercore.service.parser.DocumentParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.epam.pricecheckercore.model.enums.PageStatus.PAGE_NOT_FOUND;
import static com.epam.pricecheckercore.model.enums.ProductStatus.OUT_OF_STOCK;
import static com.epam.pricecheckercore.model.enums.ProductStatus.PRICE_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DocumentExtractorTest extends AbstractProductDataProviderTest {

    private Extractor extractor;

    protected DocumentExtractorTest() {
        super(new RozetkaProductSelector(), new Rozetka());
    }

    @BeforeEach
    void setUp() {
        extractor = new DocumentExtractor(new FileDocumentParser());
    }

    @Test
    void shouldReturnNormalPrice() {
        String actual = extractor.extract(dataProvider, "xml/rozetka/Rozetka_normal.xml");

        assertThat(actual).isEqualTo("75199");
    }

    @Test
    void shouldReturnDiscountPrice() {
        String actual = extractor.extract(dataProvider, "xml/rozetka/Rozetka_discount.xml");

        assertThat(actual).isEqualTo("60999");
    }

    @Test
    void shouldReturnPriceNotFound() {
        String actual = extractor.extract(dataProvider, "xml/rozetka/Rozetka_notfound.xml");

        assertThat(actual).isEqualTo(PRICE_NOT_FOUND.getStatus());
    }

    @Test
    void shouldReturnOutOfStock() {
        String actual = extractor.extract(dataProvider, "xml/rozetka/Rozetka_outofstock.xml");

        assertThat(actual).isEqualTo(OUT_OF_STOCK.getStatus());
    }

    @Test
    void shouldReturnPageNotFound() {
        DocumentParser documentParser = mock(DocumentParser.class);
        when(documentParser.getDocument(anyString())).thenReturn(Optional.empty());
        extractor = new DocumentExtractor(documentParser);

        String actual = extractor.extract(dataProvider, "xml/rozetka/Rozetka_outofstock.xml");

        assertThat(actual).isEqualTo(PAGE_NOT_FOUND.getStatus());
    }
}