package com.epam.pricecheckercore.service.checker;

import com.epam.pricecheckercore.helper.UrlUtils;
import com.epam.pricecheckercore.model.magazine.Magazine;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;

@Slf4j
public class SiteCheckerImpl implements SiteChecker {

    @Override
    public boolean isThisMagazine(String url, Magazine magazine) {
        try {
            String siteDomain = magazine.getSiteDomain();
            return UrlUtils.isValid(url) && UrlUtils.getDomainName(url).equals(siteDomain);
        } catch (MalformedURLException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }
}
