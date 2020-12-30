package com.epam.pricecheckercore.service.dataprovider;

import com.epam.pricecheckercore.exception.MapperException;
import com.epam.pricecheckercore.exception.ProductNotFoundException;
import com.epam.pricecheckercore.model.magazine.Rozetka;
import com.epam.pricecheckercore.model.product.ProductData;
import com.epam.pricecheckercore.service.parser.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.net.MalformedURLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RozetkaDataProviderTest {

    public static final String BASE_URL = "www.base-url.com";
    private DataProvider dataProvider;

    @Mock
    private Parser<String> parser;
    @Mock
    private Mapper mapper;

    @BeforeEach
    void setUp() {
        initMocks(this);
        dataProvider = new RozetkaDataProvider(new FileJsonParser(), new Rozetka(),
                new JsonMapper(), new MockApiUrlConstructor());
    }

    @Test
    void shouldThrowExceptionWhenCantParseFile() {
        when(parser.getContent(anyString())).thenReturn(Optional.empty());
        dataProvider = new RozetkaDataProvider(parser, new Rozetka(), new JsonMapper(), new MockApiUrlConstructor());

        assertThatThrownBy(() -> dataProvider.getProductData("qwe"))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    void shouldReturnEmptyProductDataWhenMapperThrowException() throws Exception {
        when(mapper.map(anyString(), any())).thenThrow(new MapperException());
        dataProvider = new RozetkaDataProvider(new FileJsonParser(), new Rozetka(),
                mapper, new MockApiUrlConstructor());

        ProductData actual = dataProvider.getProductData("/json/qwe.json");

        assertThat(actual).isEqualTo(new ProductData());
    }

    @Test
    void shouldThrowProductNotFoundWhenUrlIncorrect() {
        dataProvider = new RozetkaDataProvider(new FileJsonParser(), new Rozetka(),
                new JsonMapper(), new RozetkaApiUrlConstructor(BASE_URL));

        assertThatThrownBy(() -> dataProvider.getProductData("qwe"))
                .isInstanceOf(ProductNotFoundException.class);
    }
}