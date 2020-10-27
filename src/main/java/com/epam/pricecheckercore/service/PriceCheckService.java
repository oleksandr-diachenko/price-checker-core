package com.epam.pricecheckercore.service;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

/**
 * @author : Oleksandr Diachenko
 * @since : 10/27/2020
 **/
public interface PriceCheckService {

    Workbook checkPrice(byte[] bytes, int urlIndex, int insertIndex) throws IOException, InvalidFormatException;
}
