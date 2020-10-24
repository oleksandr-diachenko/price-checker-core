package com.epam.pricecheckercore.model.magazine;

import com.epam.pricecheckercore.util.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @author Alexander Diachenko
 */
@Slf4j
public abstract class AbstractMagazine implements Magazine {

    private static final String PAGE_NOT_FOUND = "Страница не найдена";
    private static final String OUT_OF_STOCK = "Нет в наличии";
    private static final String NOT_FOUND = "Не найдено";
    protected String url;

    @Override
    public Document getDocument(String url) {
        this.url = url;
        try {
            return Jsoup
                    .connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .get();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String getPrice(Document document) {
        if (document == null) {
            return PAGE_NOT_FOUND;
        }
        if (!isAvailable(document)) {
            return OUT_OF_STOCK;
        }
        try {
            return getPriceFrom(document);
        } catch (IllegalStateException e) {
            return NOT_FOUND;
        }
    }

    protected abstract String getPriceFrom(Document document);

    @Override
    public boolean isThisWebsite(String url) {
        try {
            return UrlUtils.isValid(url) && UrlUtils.getDomainName(url).equals(getSiteDomain());
        } catch (MalformedURLException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    protected abstract String getSiteDomain();
}
