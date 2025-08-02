package com.shpp.p2p.cs.vmarchenko.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * The first task: Counting the number of syllables
 * The program accepts a word from the user and outputs how many syllables it has.
 */
public class Assignment5Part1 extends TextProgram {
    /**
     * The main method of application. Allows to test the given program.
     * Repeatedly prompt the user for a word and print out the estimated
     * number of syllables in that word.
     */
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesInWord(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesInWord(String word) {

        // Array of all vowels
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};

        // number of vowels in a word
        int vowelCount = 0;
        word = word.toLowerCase();

        // If the first letter is a vowel
        if (isVowel(vowels, word.charAt(0))) vowelCount++;

        for (int letter = 1; letter < word.length(); letter++) {
            if (isVowel(vowels, word.charAt(letter))) vowelCount++;

            // If the previous letter is a vowel, then the syllable is subtracted
            if (isVowel(vowels, word.charAt(letter - 1)) && isVowel(vowels, word.charAt(letter))) {
                vowelCount--;
            }
        }

        if (endsWithEWithoutPreviousVowel(word, vowels)) vowelCount--;

        // TODO: в будь-якому випадку треба +1 (1 буква - 1 слово)
        if (needOneSyllableAtEndOfWord(word, vowelCount)) vowelCount++;

        return vowelCount;
    }

    /**
     * Checks whether a given letter is a vowel from a given array of letters.
     *
     * @param vowels given array of letters
     * @param letter given letter to be checked
     * @return whether the given letter is a vowel or not
     */
    public boolean isVowel(char[] vowels, char letter) {
        for (char vowel : vowels) {
            if (letter == vowel) {
                return true;
            }
        }
        return false;
    }

    /**
     * If the last letter is "e" and
     * there are no vowels before it, then it is not a syllable
     *
     * @param word   user-supplied word
     * @param vowels array of all vowels
     * @return whether the word ends with the letter "e"
     * and there are no vowels before it
     */
    public boolean endsWithEWithoutPreviousVowel(String word, char[] vowels) {
        return word.charAt(word.length() - 1) == 'e'
                && !isVowel(vowels, word.charAt(word.length() - 2));
    }

    /**
     * If a word consists of more than 2 letters, has no syllables,
     * but has a vowel (e) at the end, then one syllable is added.
     *
     * @param word       user-supplied word
     * @param vowelCount number of syllables in a word
     * @return whether a complete word consisting of
     * 2-3 letters contains the vowel "e"
     */
    public boolean needOneSyllableAtEndOfWord(String word, int vowelCount) {
        return word.length() >= 2
                && vowelCount == 0
                && word.charAt(word.length() - 1) == 'e';
    }
}