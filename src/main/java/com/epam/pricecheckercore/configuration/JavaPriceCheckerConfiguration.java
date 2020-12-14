package com.epam.pricecheckercore.configuration;

import com.epam.pricecheckercore.helper.WorkbookHelper;
import com.epam.pricecheckercore.helper.WorkbookHelperImpl;
import com.epam.pricecheckercore.helper.stringdecorator.*;
import com.epam.pricecheckercore.model.magazine.*;
import com.epam.pricecheckercore.model.product.*;
import com.epam.pricecheckercore.service.checker.*;
import com.epam.pricecheckercore.service.dataprovider.DataProvider;
import com.epam.pricecheckercore.service.dataprovider.ProductDataProvider;
import com.epam.pricecheckercore.service.excel.Excel;
import com.epam.pricecheckercore.service.excel.ExcelImpl;
import com.epam.pricecheckercore.service.parser.DocumentParser;
import com.epam.pricecheckercore.service.parser.HtmlDocumentParser;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class JavaPriceCheckerConfiguration implements PriceCheckerConfiguration {

    @Override
    public PriceCheckService getPriceCheckService() {
        Excel excel = new ExcelImpl();
        SiteChecker siteChecker = new SiteCheckerImpl();
        DocumentParser documentParser = new HtmlDocumentParser();
        return new PriceCheckServiceImpl(excel, getMagazines(), siteChecker, new DocumentExtractor(documentParser));
    }

    @Override
    public WorkbookHelper getWorkbookHelper() {
        return new WorkbookHelperImpl();
    }

    private Set<DataProvider> getMagazines() {
        StringProcessor decorator = getStringDecorator();
        return Set.of(
                new ProductDataProvider(new HtmlProduct(new MakeupProductSelector(), new Makeup(), decorator)),
                new ProductDataProvider(new HtmlProduct(new KoreaProductSelector(), new Korea(), decorator)),
                new ProductDataProvider(new HtmlProduct(new RoseRoseShopProductSelector(), new RoseRoseShop(), decorator)),
                new ProductDataProvider(new HtmlProduct(new BeautyNetKoreaProductSelector(), new BeautyNetKorea(), decorator)),
                new ProductDataProvider(new HtmlProduct(new NowZenithProductSelector(), new NowZenith(), decorator)),
                new ProductDataProvider(new HtmlProduct(new RozetkaProductSelector(), new Rozetka(), decorator)),
                new ProductDataProvider(new HtmlProduct(new KoreaButikProductSelector(), new KoreaButik(), decorator)),
                new ProductDataProvider(new HtmlProduct(new CosmeteaProductSelector(), new Cosmetea(), decorator)),
                new ProductDataProvider(new HtmlProduct(new SweetnessProductSelector(), new Sweetness(), decorator))
        );
    }

    private StringProcessor getStringDecorator() {
        StringProcessor initialStringProcessor = new LoggerStringProcessor();
        StringProcessor replaceCommaToDotStringDecorator = new ReplaceCommaToDotStringDecorator(initialStringProcessor);
        StringProcessor removeSpacesStringDecorator = new RemoveSpacesStringDecorator(replaceCommaToDotStringDecorator);
        return new NumberFromStringDecorator(removeSpacesStringDecorator);
    }
}
