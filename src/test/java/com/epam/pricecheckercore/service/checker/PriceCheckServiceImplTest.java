package com.epam.pricecheckercore.service.checker;

import com.epam.pricecheckercore.AbstractProductDataProviderTest;
import com.epam.pricecheckercore.model.magazine.Cosmetea;
import com.epam.pricecheckercore.model.magazine.Korea;
import com.epam.pricecheckercore.model.magazine.Magazine;
import com.epam.pricecheckercore.model.product.CosmeteaProductSelector;
import com.epam.pricecheckercore.model.product.KoreaProductSelector;
import com.epam.pricecheckercore.service.excel.Excel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.List.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class PriceCheckServiceImplTest extends AbstractProductDataProviderTest {

    private static final byte[] BYTES = {1, 2, 3};

    private PriceCheckService priceCheckService;

    @Mock
    private Excel excel;
    @Mock
    private SiteChecker siteChecker;

    protected PriceCheckServiceImplTest() {
        super(new KoreaProductSelector(), new Korea());
    }

    @BeforeEach
    void setUp() {
        initMocks(this);
        priceCheckService = new PriceCheckServiceImpl(excel, Collections.singleton(dataProvider),
                siteChecker, new DocumentExtractor());
        when(siteChecker.isThisMagazine(anyString(), any(Magazine.class))).thenReturn(true);
    }

    @Test
    void shouldReturnTableImmediatelyWhenUrlIndexIsNegative() throws Exception {
        List<List<String>> table = singletonList(singletonList("xml/korea/Korea_discount.xml"));
        when(excel.read(BYTES)).thenReturn(table);

        priceCheckService.checkPrice(BYTES, -1, 1);

        verify(excel).buildWorkBook(table);
    }

    @Test
    void shouldReturnTableImmediatelyWhenInsertIndexIsNegative() throws Exception {
        List<List<String>> table = singletonList(singletonList("xml/korea/Korea_discount.xml"));
        when(excel.read(BYTES)).thenReturn(table);

        priceCheckService.checkPrice(BYTES, 0, -1);

        verify(excel).buildWorkBook(table);
    }

    @Test
    void shouldReturnTableWithPrice() throws Exception {
        List<String> row = new ArrayList<>();
        row.add("xml/korea/Korea_discount.xml");
        List<List<String>> table = new ArrayList<>();
        table.add(row);
        when(excel.read(BYTES)).thenReturn(table);

        priceCheckService.checkPrice(BYTES, 0, 1);

        verify(excel).buildWorkBook(of(of("xml/korea/Korea_discount.xml", "200.0")));
    }
}