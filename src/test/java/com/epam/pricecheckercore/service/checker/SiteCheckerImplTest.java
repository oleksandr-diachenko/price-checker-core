package com.epam.pricecheckercore.service.checker;

import com.epam.pricecheckercore.model.magazine.Magazine;
import com.epam.pricecheckercore.model.magazine.Rozetka;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class SiteCheckerImplTest {

    private SiteChecker siteChecker;
    private Magazine magazine;

    @BeforeEach
    void setUp() {
        siteChecker = new SiteCheckerImpl();
        magazine = new Rozetka();
    }

    @Test
    void shouldReturnTrueWhenUrlMatchesMagazineDomainName() {
        boolean actual = siteChecker.isThisMagazine("https://rozetka.com.ua/qwe", magazine);

        assertThat(actual).isTrue();
    }

    @Test
    void shouldReturnFalseWhenUrlNotMatchesMagazineDomainName() {
        boolean actual = siteChecker.isThisMagazine("https://makeup.com.ua/qwe", magazine);

        assertThat(actual).isFalse();
    }

    @Test
    void shouldReturnFalseWhenUrlIsIncorrect() {
        boolean actual = siteChecker.isThisMagazine("qwe", magazine);

        assertThat(actual).isFalse();
    }
}