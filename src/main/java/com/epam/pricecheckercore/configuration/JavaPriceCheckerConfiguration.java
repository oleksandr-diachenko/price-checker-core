package com.epam.pricecheckercore.configuration;

import com.epam.pricecheckercore.model.magazine.*;
import com.epam.pricecheckercore.service.PriceCheckService;
import com.epam.pricecheckercore.service.PriceCheckServiceImpl;
import com.epam.pricecheckercore.service.excel.ExcelImpl;
import com.epam.pricecheckercore.util.WorkbookHelper;
import com.epam.pricecheckercore.util.WorkbookHelperImpl;

import java.util.Set;

/**
 * @author : Oleksandr Diachenko
 * @since : 10/27/2020
 **/
public class JavaPriceCheckerConfiguration implements PriceCheckerConfiguration {

    @Override
    public PriceCheckService getPriceCheckService() {
        return new PriceCheckServiceImpl(new ExcelImpl(), getMagazines());
    }

    @Override
    public WorkbookHelper getWorkbookHelper() {
        return new WorkbookHelperImpl();
    }

    private static Set<Magazine> getMagazines() {
        return Set.of(
                new Makeup(),
                new Korea(),
                new RoseRoseShop(),
                new BeautyNetKorea(),
                new NowZenith(),
                new Rozetka(),
                new KoreaButik(),
                new SweetCorea(),
                new Cosmetea(),
                new Sweetness());
    }
}
