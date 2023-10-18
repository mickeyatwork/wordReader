package com.wordreader.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordRules {

    public String tidyUp(String word) {

        String editedWord = word;

        // In case of any extra whitespace
        editedWord.trim();

        Pattern lastCharacterPattern = Pattern.compile(".*\\W\\Z");
        Matcher lastCharacterMatcher = lastCharacterPattern.matcher(editedWord);

        if (lastCharacterMatcher.matches()){
            // Remove any unwanted special characters at the end of the word
            editedWord = editedWord.substring(0, editedWord.length() - 1);
            // Trailing commas need the following to get removed
            editedWord = editedWord.replaceAll(",$", "");

            Pattern firstCharacterPattern = Pattern.compile("^\\W.*");
            Matcher firstCharacterMatcher = firstCharacterPattern.matcher(editedWord);

            if (firstCharacterMatcher.matches()) {
                editedWord = editedWord.substring(1);

                return editedWord;
            }


            return editedWord;
        }

        return editedWord;
    }

    public ArrayList splitWordsBySpecialCharacters(String[] words) {

        ArrayList splitWords = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {

            Pattern isNumberPattern = Pattern.compile(".*\\d.*");
            Matcher isNumberMatcher = isNumberPattern.matcher(words[i]);

            Pattern specialCharPattern = Pattern.compile(".*\\W.*");
            Matcher specialCharMatcher = specialCharPattern.matcher(words[i]);

            Pattern urlPattern = Pattern.compile(".*http[a-zA-Z\\W]+.*");
            Matcher urlMatcher = urlPattern.matcher(words[i]);

            Pattern urlPattern2 = Pattern.compile(".*www[a-zA-Z\\W]+.*");
            Matcher urlMatcher2 = urlPattern2.matcher(words[i]);

            if (isNumberMatcher.matches()) {

                splitWords.add(words[i]);

            } else if (specialCharMatcher.matches()) {

                if (urlMatcher.matches() || urlMatcher2.matches()){

                    splitWords.add(words[i]);

                } else {

                    String tempWord = words[i].replaceAll("[-'\"*]", "");
                    tempWord = tempWord.replaceAll("[/,*]", ", ");

                    splitWords.add(tempWord);

                }
            }
        }
        return splitWords;
    }
}
