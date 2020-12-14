package com.epam.pricecheckercore.service.checker;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

public interface PriceCheckService {

    Workbook checkPrice(byte[] bytes, int urlIndex, int insertIndex) throws IOException, InvalidFormatException;
}
