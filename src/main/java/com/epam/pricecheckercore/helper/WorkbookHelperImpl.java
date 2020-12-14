package com.epam.pricecheckercore.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
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
            throw new NotOfficeXmlFileException("Can't read bytes from workbook!", e);
        }
    }
}
