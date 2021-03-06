package com.epam.pricecheckercore.helper;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class WorkbookHelperTest {

    @InjectMocks
    private WorkbookHelperImpl workbookHelper;

    @Test
    void shouldReturnNotEmptyBytes() {
        byte[] bytes = workbookHelper.getBytes(new XSSFWorkbook());

        assertThat(bytes).isNotEmpty();
    }
}
