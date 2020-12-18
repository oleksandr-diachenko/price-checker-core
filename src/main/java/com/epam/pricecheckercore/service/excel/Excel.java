package com.epam.pricecheckercore.service.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.List;

public interface Excel {

    List<List<String>> read(byte[] bytes) throws IOException, InvalidFormatException;

    Workbook buildWorkBook(List<List<String>> table) throws IOException;

    int getColumnCount(Sheet sheet);

    void autoResizeSheet(Sheet sheet);
}
