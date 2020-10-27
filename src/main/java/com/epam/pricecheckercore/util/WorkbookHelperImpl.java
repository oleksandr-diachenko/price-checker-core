package com.epam.pricecheckercore.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
public class WorkbookHelperImpl implements WorkbookHelper {

    @Override
    public byte[] getBytes(Workbook workbook) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            workbook.write(os);
            return os.toByteArray();
        } catch (IOException e) {
            log.error("Can't read bytes from workbook!");
            throw new RuntimeException("Can't read bytes from workbook!", e);
        }
    }
}
