package com.epam.pricecheckercore.service.checker;

import com.epam.pricecheckercore.AbstractProductDataProviderTest;
import com.epam.pricecheckercore.configuration.PriceCheckerConfiguration;
import com.epam.pricecheckercore.exception.PriceCheckerException;
import com.epam.pricecheckercore.helper.WorkbookHelperImpl;
import com.epam.pricecheckercore.model.inputoutput.CheckerInput;
import com.epam.pricecheckercore.model.inputoutput.CheckerOutput;
import com.epam.pricecheckercore.model.magazine.Rozetka;
import com.epam.pricecheckercore.model.product.RozetkaProductSelector;
import com.epam.pricecheckercore.service.excel.ExcelImpl;
import com.epam.pricecheckercore.service.excel.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static java.util.Collections.singleton;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class PriceCheckerIntegrationTest extends AbstractProductDataProviderTest {

    private final FileReader reader = new FileReader();

    private Checker checker;

    @Mock
    private PriceCheckerConfiguration configuration;
    @Mock
    private SiteChecker siteChecker;

    protected PriceCheckerIntegrationTest() {
        super(new RozetkaProductSelector(), new Rozetka());
    }

    @BeforeEach
    void setUp() {
        initMocks(this);

        PriceCheckService priceCheckService =
                new PriceCheckServiceImpl(new ExcelImpl(), singleton(dataProvider),
                        siteChecker, new DocumentExtractor());

        when(configuration.getPriceCheckService()).thenReturn(priceCheckService);
        when(configuration.getWorkbookHelper()).thenReturn(new WorkbookHelperImpl());

        checker = new PriceChecker(configuration);
    }

    @Test
    void shouldReturnPrices() throws PriceCheckerException {
        String filePath = "file/xls/xml_url.xlsx";
        when(siteChecker.isThisMagazine(eq(filePath), any(Rozetka.class))).thenReturn(true);

        CheckerInput checkerInput = CheckerInput.builder()
                .file(reader.getBytes(filePath))
                .urlIndex(0)
                .insertIndex(1).build();
        CheckerOutput actual = checker.check(checkerInput);

        assertThat(actual.getFile()).isNotEmpty();
    }
}