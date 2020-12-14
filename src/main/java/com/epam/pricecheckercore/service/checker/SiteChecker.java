package com.epam.pricecheckercore.service.checker;

import com.epam.pricecheckercore.model.magazine.Magazine;

public interface SiteChecker {

    boolean isThisMagazine(String url, Magazine magazine);
}
