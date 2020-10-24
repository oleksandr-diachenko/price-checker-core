package com.epam.pricecheckercore.service;

import com.epam.pricecheckercore.exception.PriceCheckerException;
import com.epam.pricecheckercore.model.CheckerInput;
import com.epam.pricecheckercore.model.CheckerOutput;

/**
 * @author : Oleksandr Diachenko
 * @since : 10/27/2020
 **/
public interface Checker {

    CheckerOutput check(CheckerInput parameter) throws PriceCheckerException;
}
