package com.epam.pricecheckercore.service.dataprovider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.assertj.core.api.Assertions.assertThat;

class RozetkaApiUrlConstructorTest {

    private ApiUrlConstructor apiUrlConstructor;
    private final String baseUrl = "www.base-url.com/";

    @BeforeEach
    void setUp() {
        apiUrlConstructor = new RozetkaApiUrlConstructor(baseUrl);
    }

    @Test
    void shouldConstructUrlForApiCallWhenUrlContainsId() throws MalformedURLException {
        String url = "https://rozetka.com.ua/ua/254845311/p254845311/";
        String actual = apiUrlConstructor.construct(url);

        assertThat(actual).isEqualTo(baseUrl + "254845311");
    }

    @Test
    void shouldConstructUrlForApiCallWhenUrlContainsProductName() throws MalformedURLException {
        String url = "https://rozetka.com.ua/ua/playstation_5_digital_edition_2/p223596301/";
        String actual = apiUrlConstructor.construct(url);

        assertThat(actual).isEqualTo(baseUrl + "223596301");
    }
}