package com.epam.pricecheckercore;

import com.epam.pricecheckercore.helper.stringdecorator.*;
import com.epam.pricecheckercore.model.magazine.Magazine;
import com.epam.pricecheckercore.model.product.ProductSelector;
import com.epam.pricecheckercore.service.dataprovider.DataProvider;
import com.epam.pricecheckercore.service.dataprovider.DocumentDataProvider;
import com.epam.pricecheckercore.service.dataprovider.FileDocumentParser;
import com.epam.pricecheckercore.service.parser.Parser;
import org.jsoup.nodes.Document;

import java.io.FileNotFoundException;

public abstract class AbstractProductDataProviderTest {

    protected DataProvider dataProvider;

    protected AbstractProductDataProviderTest(ProductSelector productSelector, Magazine magazine) {
        dataProvider = new DocumentDataProvider(new FileDocumentParser(), magazine, productSelector, getStringDecorator());
    }

    private StringProcessor getStringDecorator() {
        StringProcessor initialStringProcessor = new LoggerStringProcessor();
        StringProcessor replaceCommaToDotStringDecorator = new ReplaceCommaToDotStringDecorator(initialStringProcessor);
        StringProcessor removeSpacesStringDecorator = new RemoveSpacesStringDecorator(replaceCommaToDotStringDecorator);
        return new NumberFromStringDecorator(removeSpacesStringDecorator);
    }

    protected Document getDocument(String filePath) throws FileNotFoundException {
        Parser<Document> parser = new FileDocumentParser();
        return parser.getContent(filePath)
                .orElseThrow(FileNotFoundException::new);
    }
}
