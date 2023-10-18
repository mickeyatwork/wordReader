package com.wordreader.utils;

import com.wordreader.controllers.CalculationController;
import com.wordreader.models.Calculations;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculationControllerTest {

    @Test
    public void testMaxCountValues() {
        CalculationController calculationController = new CalculationController();

        Map<Integer, Integer> wordLengths = new HashMap<Integer, Integer>();
        wordLengths.put(1,2);
        wordLengths.put(2,1);
        wordLengths.put(3,2);

        Calculations calcTest = calculationController.getMaxCountValues(wordLengths);
        assertEquals(2, calcTest.getMax());
        assertEquals("1 & 3", calcTest.getResults());

        wordLengths.put(4,1);
        wordLengths.put(5,2);

        Calculations calcTest2 = calculationController.getMaxCountValues(wordLengths);
        assertEquals(2, calcTest2.getMax());
        assertEquals("1 & 3 & 5", calcTest2.getResults());
        assertNotEquals("1 & 3", calcTest2.getResults());

    }

    @Test
    public void calculationsAreCorrect() {
        CalculationController calculationController = new CalculationController();

        Map<Integer, Integer> wordLengths = new HashMap<Integer, Integer>();
        wordLengths.put(1,1);
        wordLengths.put(2,1);
        wordLengths.put(3,2);
        wordLengths.put(4,1);
        wordLengths.put(5,3);
        Integer fullWordCount = 8;

        double avgWordLengthResult = Double.parseDouble(calculationController.getAvgWordLength(wordLengths, fullWordCount));
        assertEquals(3.500 , avgWordLengthResult);
    }

}