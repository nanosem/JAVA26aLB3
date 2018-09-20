package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String text = removeAllSeparatorChars("hello, world ...world     ; ;lorem:  ,,lll    ;ipsum");

        printNumberOfDifferentWords(splitTextByWords(text));
    }

    // Functions that returns list of words in given text.
    private static List<String> splitTextByWords(String text) {

        // Adding additional space to the end of string to have exit condition for sure.
        text += " ";

        int startIndex = 0;

        // Allocating and initializing empty list of words.
        List<String> words = new ArrayList<>();

        do {
            // Index of first space from startIndex in string
            int index = text.indexOf(' ', startIndex);
            String word = text.substring(startIndex, index);

            if (word.length() == 0) {
                startIndex = index + 1;
                continue;
            }

            // Appending word to list.
            words.add(word.toLowerCase());

            // Setting startIndex to last found space position(incremented, because we have to skip whitespace char that we've already found).
            startIndex = index + 1;
        } while (startIndex < text.length());

        return words;
    }

    // Function that removes all separator characters from string.
    private static String removeAllSeparatorChars(String text) {
        // List of characters.
        List<Character> ignoringCharsList = Arrays.asList(',', '.', ';', ':');

        // Buffer fom given text.
        StringBuffer sBuffer = new StringBuffer(text);

        for (int index = 0; index < text.length(); index++) {
            Character currentChar = text.charAt(index);

            // If list of ignoring characters contains currentChar then replacing it with whitespace.
            if (ignoringCharsList.contains(currentChar)) {
                sBuffer.replace(index, index + 1, " ");
            }
        }
        return sBuffer.toString();
    }

    // Function that counts and prints number of different words in given array.
    private static void printNumberOfDifferentWords(List<String> words) {

        // Map (dictionary), where key is word and value is count.
        Map<String, Integer> map = new HashMap<>();

        // word - variable that equals to current word from given array of words.
        for (String word: words) {

            // Checking if map contains key with value of word
            if (map.containsKey(word)) {
                // If it does - getting current value for this word(key)
                int count = map.get(word);
                // And putting back incremented value
                map.put(word, count + 1);
            } else {
                // If it doesn't - putting number one(1) as value for key that equals to current word.
                map.put(word, 1);
            }
        }

        // Printing the result.
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            int value = entry.getValue(); // Count of words.

            String entryText;
            String wordText;

            if (value == 1) {
                entryText = "There is ";
                wordText = " word - ";
            } else {
                entryText = "There are ";
                wordText = " words - ";
            }

            System.out.println(entryText + value + wordText + entry.getKey() + " in given text.");
        }
    }

}
