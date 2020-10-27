package com.epam.pricecheckercore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.epam.pricecheckercore.model.magazine.Magazine;
import com.epam.pricecheckercore.service.excel.Excel;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.trim;

/**
 * @author Alexander Diachenko.
 */
@Slf4j
@RequiredArgsConstructor
public class PriceCheckServiceImpl implements PriceCheckService {

    private final Excel excel;
    private final Set<Magazine> magazines;

    @Override
    public Workbook checkPrice(byte[] bytes, int urlIndex, int insertIndex)
            throws IOException, InvalidFormatException {
        List<List<String>> table = excel.read(bytes);
        if (!isPositives(urlIndex, insertIndex)) {
            return excel.buildWorkBook(table);
        }
        table.parallelStream().forEach(row ->  {
            for (Magazine magazine : emptyIfNull(magazines)) {
                String url = retrieveUrl(row, urlIndex);
                if (magazine.isThisWebsite(url)) {
                    log.info("Searching price for {} in magazine {}", url, magazine.getClass().getSimpleName());
                    String price = magazine.getPrice(magazine.getDocument(url));
                    log.info("Price for url: {} is {}", url, price);
                    insert(row, insertIndex, price);
                }
            }
        });
        log.info("Returning table: {}", table.toString());
        return excel.buildWorkBook(table);
    }

    private boolean isPositives(int urlColumn, int insertColumn) {
        return urlColumn >= 0 && insertColumn >= 0;
    }

    private String retrieveUrl(List<String> row, int index) {
        if (row.size() <= index) {
            return EMPTY;
        }
        return trim(row.get(index));
    }

    private void insert(List<String> row, int index, String price) {
        while (row.size() < index) {
            row.add(EMPTY);
        }
        row.add(index, price);
    }
}

