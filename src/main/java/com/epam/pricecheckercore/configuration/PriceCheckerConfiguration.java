package com.epam.pricecheckercore.configuration;

import com.epam.pricecheckercore.helper.WorkbookHelper;
import com.epam.pricecheckercore.service.checker.PriceCheckService;

public interface PriceCheckerConfiguration {

    PriceCheckService getPriceCheckService();

    WorkbookHelper getWorkbookHelper();
}
