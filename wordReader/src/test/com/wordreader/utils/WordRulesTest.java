package com.wordreader.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordRulesTest {

    @Test
    void testTrailingPunctuation() {

        WordRules wr = new WordRules();

        String withFullStop = "test.";
        String withComma = "test,";
        String withSingleQuotes = "\'test\'";
        String withDoubleQuotes = "\"test\"";
        String withQuestionMark = "test?";
        String withExclamationMark = "test!";

        assertEquals("test", wr.tidyUp(withFullStop));
        assertEquals("test", wr.tidyUp(withComma));
        assertEquals("test", wr.tidyUp(withSingleQuotes));
        assertEquals("test", wr.tidyUp(withDoubleQuotes));
        assertEquals("test", wr.tidyUp(withQuestionMark));
        assertEquals("test", wr.tidyUp(withExclamationMark));

    }

    @Test
    void testPunctuatedWords() {

        WordRules wr = new WordRules();

        String[] seperatedWithHyphen = {"test-ing"};
        String[] seperatedWithForwardDash = {"either/or"};
        String[] seperatedWithComma = {"test,test"};
        String[] surroundedWithDoubleQuotes = {"\"testing\""};
        String[] surroundedWithSingleQuotes = {"\'testing\'"};
        String[] seperatedWithFullStop = {"test.com"};
        String[] fullURL = {"https://www.test.com"};
        String[] anotherURL = {"www.test.com"};
        String[] emailAddress = {"me@test.com"};

        assertEquals("[testing]", wr.splitWordsBySpecialCharacters(seperatedWithHyphen).toString());
        assertEquals("[either, or]", wr.splitWordsBySpecialCharacters(seperatedWithForwardDash).toString());
        assertEquals("[test, test]", wr.splitWordsBySpecialCharacters(seperatedWithComma).toString());
        assertEquals("[testing]", wr.splitWordsBySpecialCharacters(surroundedWithDoubleQuotes).toString());
        assertEquals("[testing]", wr.splitWordsBySpecialCharacters(surroundedWithSingleQuotes).toString());
        assertEquals("[test.com]", wr.splitWordsBySpecialCharacters(seperatedWithFullStop).toString());
        assertEquals("[https://www.test.com]", wr.splitWordsBySpecialCharacters(fullURL).toString());
        assertEquals("[www.test.com]", wr.splitWordsBySpecialCharacters(anotherURL).toString());
        assertEquals("[me@test.com]", wr.splitWordsBySpecialCharacters(emailAddress).toString());

    }

    @Test
    void testNumberFormats() {

        WordRules wr = new WordRules();

        String[] number = {"12345"};
        String[] numberWithComma = {"12,345"};
        String[] dateWithForwardDash = {"01/01/2023"};
        String[] dateWithFullStops = {"01.01.2023"};
        String[] dateWithHyphens = {"01-01-2023"};

        assertEquals("[12345]", wr.splitWordsBySpecialCharacters(number).toString());
        assertEquals("[12,345]", wr.splitWordsBySpecialCharacters(numberWithComma).toString());
        assertEquals("[01/01/2023]", wr.splitWordsBySpecialCharacters(dateWithForwardDash).toString());
        assertEquals("[01.01.2023]", wr.splitWordsBySpecialCharacters(dateWithFullStops).toString());
        assertEquals("[01-01-2023]", wr.splitWordsBySpecialCharacters(dateWithHyphens).toString());
    }
}