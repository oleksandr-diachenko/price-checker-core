package com.epam.pricecheckercore.helper;

import org.apache.poi.ss.usermodel.Workbook;

public interface WorkbookHelper {

    byte[] getBytes(Workbook workbook);
}
