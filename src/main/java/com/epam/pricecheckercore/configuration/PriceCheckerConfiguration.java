package com.epam.pricecheckercore.configuration;

import com.epam.pricecheckercore.service.PriceCheckService;
import com.epam.pricecheckercore.util.WorkbookHelper;

/**
 * @author : Oleksandr Diachenko
 * @since : 10/27/2020
 **/
public interface PriceCheckerConfiguration {

    PriceCheckService getPriceCheckService();

    WorkbookHelper getWorkbookHelper();
}
