package com.epam.pricecheckercore;

import com.epam.pricecheckercore.helper.stringdecorator.*;
import com.epam.pricecheckercore.model.magazine.Magazine;
import com.epam.pricecheckercore.model.product.HtmlProduct;
import com.epam.pricecheckercore.model.product.Product;
import com.epam.pricecheckercore.model.product.ProductSelector;
import com.epam.pricecheckercore.service.dataprovider.DataProvider;
import com.epam.pricecheckercore.service.dataprovider.ProductDataProvider;
import com.epam.pricecheckercore.service.parser.DocumentParser;
import org.jsoup.nodes.Document;

import java.io.FileNotFoundException;

public abstract class AbstractProductDataProviderTest {

    protected DataProvider dataProvider;

    protected AbstractProductDataProviderTest(ProductSelector productSelector, Magazine magazine) {
        Product product = new HtmlProduct(productSelector, magazine, getStringDecorator());
        dataProvider = new ProductDataProvider(product);
    }

    private StringProcessor getStringDecorator() {
        StringProcessor initialStringProcessor = new LoggerStringProcessor();
        StringProcessor replaceCommaToDotStringDecorator = new ReplaceCommaToDotStringDecorator(initialStringProcessor);
        StringProcessor removeSpacesStringDecorator = new RemoveSpacesStringDecorator(replaceCommaToDotStringDecorator);
        return new NumberFromStringDecorator(removeSpacesStringDecorator);
    }

    protected Document getDocument(String filePath) throws FileNotFoundException {
        DocumentParser parser = new FileDocumentParser();
        return parser.getDocument(filePath)
                .orElseThrow(FileNotFoundException::new);
    }
}
