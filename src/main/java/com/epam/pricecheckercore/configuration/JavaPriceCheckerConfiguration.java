package com.epam.pricecheckercore.configuration;

import com.epam.pricecheckercore.helper.WorkbookHelper;
import com.epam.pricecheckercore.helper.WorkbookHelperImpl;
import com.epam.pricecheckercore.helper.stringdecorator.*;
import com.epam.pricecheckercore.model.magazine.*;
import com.epam.pricecheckercore.model.product.*;
import com.epam.pricecheckercore.service.checker.*;
import com.epam.pricecheckercore.service.dataprovider.*;
import com.epam.pricecheckercore.service.excel.Excel;
import com.epam.pricecheckercore.service.excel.ExcelImpl;
import com.epam.pricecheckercore.service.parser.ApiParser;
import com.epam.pricecheckercore.service.parser.DocumentParser;
import com.epam.pricecheckercore.service.parser.Parser;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import java.util.Set;

@Slf4j
public class JavaPriceCheckerConfiguration implements PriceCheckerConfiguration {

    private static final String ROZETKA_API_BASE_URL = "https://common-api.rozetka.com.ua/v2/goods/get-price/?id=";

    @Override
    public PriceCheckService getPriceCheckService() {
        Excel excel = new ExcelImpl();
        SiteChecker siteChecker = new SiteCheckerImpl();
        return new PriceCheckServiceImpl(excel, getDataProviders(), siteChecker, new DocumentExtractor());
    }

    @Override
    public WorkbookHelper getWorkbookHelper() {
        return new WorkbookHelperImpl();
    }

    private Set<DataProvider> getDataProviders() {
        StringProcessor decorator = getStringDecorator();
        Parser<Document> documentParser = new DocumentParser();
        return Set.of(
                new DocumentDataProvider(documentParser, new Makeup(), new MakeupProductSelector(), decorator),
                new DocumentDataProvider(documentParser, new Korea(), new KoreaProductSelector(), decorator),
                new DocumentDataProvider(documentParser, new RoseRoseShop(), new RoseRoseShopProductSelector(), decorator),
                new DocumentDataProvider(documentParser, new BeautyNetKorea(), new BeautyNetKoreaProductSelector(), decorator),
                new DocumentDataProvider(documentParser, new NowZenith(), new NowZenithProductSelector(), decorator),
                new DocumentDataProvider(documentParser, new KoreaButik(), new KoreaButikProductSelector(), decorator),
                new DocumentDataProvider(documentParser, new Cosmetea(), new CosmeteaProductSelector(), decorator),
                new DocumentDataProvider(documentParser, new Sweetness(), new SweetnessProductSelector(), decorator),
                new RozetkaDataProvider(new ApiParser(), new Rozetka(), new JsonMapper(), new RozetkaApiUrlConstructor(ROZETKA_API_BASE_URL))
        );
    }

    private StringProcessor getStringDecorator() {
        StringProcessor initialStringProcessor = new LoggerStringProcessor();
        StringProcessor replaceCommaToDotStringDecorator = new ReplaceCommaToDotStringDecorator(initialStringProcessor);
        StringProcessor removeSpacesStringDecorator = new RemoveSpacesStringDecorator(replaceCommaToDotStringDecorator);
        return new NumberFromStringDecorator(removeSpacesStringDecorator);
    }
}
