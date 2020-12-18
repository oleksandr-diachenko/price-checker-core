package com.epam.pricecheckercore.service.checker;

import com.epam.pricecheckercore.configuration.JavaPriceCheckerConfiguration;
import com.epam.pricecheckercore.configuration.PriceCheckerConfiguration;
import com.epam.pricecheckercore.exception.PriceCheckerException;
import com.epam.pricecheckercore.helper.WorkbookHelper;
import com.epam.pricecheckercore.model.inputoutput.CheckerInput;
import com.epam.pricecheckercore.model.inputoutput.CheckerOutput;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class PriceChecker implements Checker {

    private PriceCheckService service;
    private WorkbookHelper helper;

    public PriceChecker() {
        PriceCheckerConfiguration configuration = new JavaPriceCheckerConfiguration();
        init(configuration);
    }

    public PriceChecker(PriceCheckerConfiguration configuration) {
        init(configuration);
    }

    private void init(PriceCheckerConfiguration configuration) {
        service = configuration.getPriceCheckService();
        helper = configuration.getWorkbookHelper();
    }

    @Override
    public CheckerOutput check(CheckerInput parameter) throws PriceCheckerException {
        Workbook workbook = getCompletedWorkbook(parameter);
        byte[] file = helper.getBytes(workbook);
        return CheckerOutput.builder()
                .file(file)
                .build();
    }

    private Workbook getCompletedWorkbook(CheckerInput parameter) throws PriceCheckerException {
        try {
            return service.checkPrice(parameter.getFile(), parameter.getUrlIndex(), parameter.getInsertIndex());
        } catch (IOException | InvalidFormatException e) {
            log.error("Error while checking price", e);
            throw new PriceCheckerException("Error while checking price", e);
        }
    }
}
