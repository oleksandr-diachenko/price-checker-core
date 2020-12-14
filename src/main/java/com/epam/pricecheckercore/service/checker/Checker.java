package com.epam.pricecheckercore.service.checker;

import com.epam.pricecheckercore.exception.PriceCheckerException;
import com.epam.pricecheckercore.model.inputoutput.CheckerInput;
import com.epam.pricecheckercore.model.inputoutput.CheckerOutput;

public interface Checker {

    CheckerOutput check(CheckerInput parameter) throws PriceCheckerException;
}
