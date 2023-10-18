package com.wordreader.controllers;

import com.wordreader.models.Calculations;
import org.springframework.stereotype.Controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class CalculationController {

    private static final DecimalFormat df = new DecimalFormat("0.000");

    public Calculations getMaxCountValues(Map<Integer, Integer> wordLengths) {

        Calculations calc = new Calculations();
        calc.setMax(Collections.max(wordLengths.values()));
        calc.setResults("");

        List<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : wordLengths.entrySet()) {
            if (entry.getValue()== calc.getMax()) {

                if (calc.getResults().equals("")) {
                    calc.setResults(calc.getResults() + entry.getKey());
                } else {
                    calc.setResults(calc.getResults() + " & " + entry.getKey());
                }
                keys.add(entry.getKey());
            }
        }
        System.out.println("Words with a length of " + calc.getResults() + " appear the most frequently, occurring " + calc.getMax() + " times");

        return calc;

    }

    public String getAvgWordLength(Map<Integer, Integer> wordLengths, Integer fullWordCount) {

        double keyValArray = 0.000;

        for (Integer key : wordLengths.keySet()) {

            keyValArray = keyValArray + (wordLengths.get(key) * key.intValue());
        }

        double average = keyValArray / fullWordCount;
        System.out.println("Average word length = " + df.format(average));

        return df.format(average);
    }
}
