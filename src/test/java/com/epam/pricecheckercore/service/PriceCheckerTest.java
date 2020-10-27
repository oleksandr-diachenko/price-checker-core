package com.epam.pricecheckercore.service;

import com.epam.pricecheckercore.configuration.PriceCheckerConfiguration;
import com.epam.pricecheckercore.exception.PriceCheckerException;
import com.epam.pricecheckercore.model.CheckerInput;
import com.epam.pricecheckercore.model.CheckerOutput;
import com.epam.pricecheckercore.util.WorkbookHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author : Oleksandr Diachenko
 * @since : 10/27/2020
 **/
@ExtendWith(MockitoExtension.class)
class PriceCheckerTest {

    private static byte[] FILE = {1, 2, 3};
    private static byte[] WORKBOOK_BYTES = {4, 5, 6};

    private Checker checker;

    @Mock
    private PriceCheckService service;
    @Mock
    private WorkbookHelper helper;
    @Mock
    private PriceCheckerConfiguration configuration;
    @Mock
    private Workbook workbook;

    @BeforeEach
    void setUp() {
        when(configuration.getPriceCheckService()).thenReturn(service);
        when(configuration.getWorkbookHelper()).thenReturn(helper);
        checker = new PriceChecker(configuration);
    }

    @Test
    void shouldReturnCalculatedWorkbookAsBytes() throws Exception {
        CheckerInput parameter = CheckerInput.builder()
                .file(FILE)
                .urlIndex(1)
                .insertIndex(2)
                .build();
        when(service.checkPrice(FILE, 1, 2)).thenReturn(workbook);
        when(helper.getBytes(workbook)).thenReturn(WORKBOOK_BYTES);

        CheckerOutput checkerOutput = checker.check(parameter);

        assertThat(checkerOutput.getFile()).isEqualTo(WORKBOOK_BYTES);
    }

    @Test
    void shouldThrowExceptionWhenPriceCheckFailed() throws IOException, InvalidFormatException {
        when(service.checkPrice(FILE, 1, 2)).thenThrow(new IOException());

        CheckerInput parameter = CheckerInput.builder()
                .file(FILE)
                .urlIndex(1)
                .insertIndex(2).build();
        assertThatThrownBy(() -> checker.check(parameter))
                .isInstanceOf(PriceCheckerException.class)
                .hasMessageContaining("Error while checking price");
    }
}