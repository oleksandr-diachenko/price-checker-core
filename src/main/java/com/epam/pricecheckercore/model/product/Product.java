package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.model.magazine.Magazine;
import org.javamoney.moneta.Money;
import org.jsoup.nodes.Document;

public interface Product {

    Magazine getMagazine();

    boolean isInStock(Document document);

    Money getDiscountedPrice(Document document);

    Money getNormalPrice(Document document);
}
