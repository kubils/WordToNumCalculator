package com.ks.converter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class WordToNumberConv {

	private static String[] words;
	private static int[] values;

	private static String[] numerals = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
			"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "ninteen",
			"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety", "hundred" };
	private static int[] valuesUS = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 30, 40,
			50, 60, 70, 80, 90, 100 };

	private static String[] numeralsTR = { "", "bir", "iki", "üç", "dört", "beş", "altı", "yedi", "sekiz", "dokuz",
			"on", "yirmi", "otuz", "kırk", "elli", "altmış", "yetmiş", "seksen", "doksan", "yüz" };

	private static int[] valuesTR = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };

	private static ArrayList<String> list;

	private int getValueOf(String word) {
		return values[list.indexOf(word)];
	}

	private static String[] wordsUS = { "billion", "million", "thousand" };

	private static String[] wordsTR = { "milyar", "milyon", "bin" };

	private static int[] digits = { 1000000000, 1000000, 1000 };

	public int parseNumerals(String text) throws Exception {
		int value = 0;
		String[] words = text.replaceAll(" and ", " ").split("\\s");
		for (String word : words) {
			if (!list.contains(word)) {
				throw new Exception("Unknown token : " + word);
			}

			int subval = getValueOf(word);
			if (subval == 100) {
				if (value == 0)
					value = 100;
				else
					value *= 100;
			} else
				value += subval;
		}

		return value;
	}

	public int parseWordToNum(String text) throws Exception {
		// change locale language to english
		// Locale.setDefault(Locale.US);
		if (text.equals("")) {
			return -1;
		}
		if (Locale.getDefault().toString().equals("en_US")) {
			list = new ArrayList<String>(Arrays.asList(numerals));
			words = wordsUS;
			values = valuesUS;
		} else {
			list = new ArrayList<String>(Arrays.asList(numeralsTR));
			words = wordsTR;
			values = valuesTR;
		}

		text = text.toLowerCase().replaceAll("[\\-,]", " ").replaceAll(" and ", " ");
		int totalValue = 0;
		boolean processed = false;

		for (int n = 0; n < words.length; n++) {
			int index = text.indexOf(words[n]);
			if (index >= 0) {
				String text1 = text.substring(0, index).trim();
				String text2 = text.substring(index + words[n].length()).trim();

				if (text2.equals("")) {
					totalValue = parseNumerals(text1) * digits[n];

				} else {
					totalValue = parseNumerals(text1) * digits[n] + parseWordToNum(text2);

				}
				processed = true;
				break;
			}
		}

		if (processed)
			return totalValue;
		else
			return parseNumerals(text);
	}
}
