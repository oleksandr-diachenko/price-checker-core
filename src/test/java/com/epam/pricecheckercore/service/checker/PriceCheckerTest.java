package com.epam.pricecheckercore.service.checker;

import com.epam.pricecheckercore.configuration.PriceCheckerConfiguration;
import com.epam.pricecheckercore.exception.PriceCheckerException;
import com.epam.pricecheckercore.model.inputoutput.CheckerInput;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class PriceCheckerTest {

    private Checker checker;

    @Mock
    private PriceCheckerConfiguration configuration;
    @Mock
    private PriceCheckService priceCheckService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        when(configuration.getPriceCheckService()).thenReturn(priceCheckService);
        checker = new PriceChecker(configuration);
    }

    @Test
    void shouldThrowExceptionWhenExceptionOccurredInService() throws IOException, InvalidFormatException {
        when(priceCheckService.checkPrice(any(), anyInt(), anyInt())).thenThrow(new IOException());

        assertThrows(PriceCheckerException.class,
                () -> checker.check(CheckerInput.builder().build()));
    }
}