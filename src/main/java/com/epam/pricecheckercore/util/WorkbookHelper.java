package com.epam.pricecheckercore.util;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author : Oleksandr Diachenko
 * @since : 10/27/2020
 **/
public interface WorkbookHelper {

    byte[] getBytes(Workbook workbook);
}
