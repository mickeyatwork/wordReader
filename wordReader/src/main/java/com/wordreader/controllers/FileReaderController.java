package com.wordreader.controllers;

import com.wordreader.utils.WordRules;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

@Controller
public class FileReaderController {

    public void fileFromUrl() throws IOException {

        // User provides a source file URL, which is picked up by the scanner
        System.out.println("Please provide a file source URL: ");
        Scanner scanner = new Scanner(System.in);

        boolean correctInput;
        String userInput;

        // Check that the URL is formatted correctly
        do {
            userInput = scanner.nextLine();
            correctInput = isValidURL(userInput);

        }
        while (correctInput == false);

        URL url = new URL(userInput);
        System.out.println("Processing file...");

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        // Set up a map for words to get their lengths
        Map<Integer, Integer> wordLengths = new HashMap<Integer, Integer>();

        int fullWordCount = 0;
        String inputLine;

        // Using the buffered reader, read the URL line by line
        while ((inputLine = reader.readLine()) != null) {

            // Words are split by any spaces and put into an array
            String[] wordSplitter = inputLine.split(" ");

            WordRules wordRules = new WordRules();

            // Go through and manage any words that have special characters in the middle of them
            ArrayList tempArray = wordRules.splitWordsBySpecialCharacters(wordSplitter);
            Object[] tempObject = tempArray.toArray();
            wordSplitter = Arrays.copyOf(tempObject, tempObject.length, String[].class);

            // For each word in the split array, check against the set word rules
            for (int i = 0; i < wordSplitter.length; i++) {

                String optimisedWord = wordSplitter[i];
                optimisedWord = wordRules.tidyUp(optimisedWord);

                /* Depending on if the word length has been added yet, that length is added as a map key
                and then the value is added as 1. If the key already exists, the value increments by 1
                 */
                if (!optimisedWord.isEmpty()) {
                    if (wordLengths.containsKey(optimisedWord.length())) {

                        Integer currentValue = wordLengths.get(optimisedWord.length());
                        wordLengths.put(optimisedWord.length(), currentValue + 1);
                    } else {

                        wordLengths.put(optimisedWord.length(), 1);
                    }
                }
            }

            fullWordCount = fullWordCount + wordSplitter.length;
        }

        System.out.println("Word count = " + fullWordCount);

        // Calculations are now made to determine the average word length
        CalculationController calculationController = new CalculationController();
        calculationController.getAvgWordLength(wordLengths, fullWordCount);

        // For each length of word, display how many times that length appears
        for (Map.Entry<Integer, Integer> entry : wordLengths.entrySet()) {
            System.out.println("Number of words of length " + entry.getKey() + " is " + entry.getValue());
        }

        // Calculate the word length that occurs most frequently
        calculationController.getMaxCountValues(wordLengths);
    }

    boolean isValidURL(String url) {
        UrlValidator validator = new UrlValidator();

        if (validator.isValid(url)){

            return true;
        } else {

            System.out.println("That URL could not be used, please try again");
            return false;
        }
    }
}

