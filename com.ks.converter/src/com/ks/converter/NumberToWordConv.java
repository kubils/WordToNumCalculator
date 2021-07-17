package com.ks.converter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class NumberToWordConv {
	private ResourceBundle bundle;

	public NumberToWordConv() {
		// TODO Auto-generated constructor stub
		this.bundle = ResourceBundle.getBundle("MessageBundle", Locale.getDefault());
		System.out.println(Locale.getDefault());

	}

	private static final String[] tensNames_US = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
			" seventy", " eighty", " ninety" };

	private static final String[] numNames_US = { "", " one", " two", " three", " four", " five", " six", " seven",
			" eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
			" seventeen", " eighteen", " nineteen" };

	private static final String[] tensNames_TR = { "", "on", "yirmi", "otuz", "kırk", "elli", "altmış", "yetmiş",
			"seksen", "doksan", "yüz" };

	private static final String[] numNames_TR = { "", "bir", "iki", "üç", "dört", "beş", "altı", "yedi", "sekiz",
			"dokuz", "on" };

	private String[] numNames;
	private String[] tensNames;

	private String convertLessThanOneThousand(int number) {
		String soFar;

		if (number % 100 < 20 && Locale.getDefault().toString().equals("en_US")) {
			soFar = numNames[number % 100];
			number /= 100;
		} else if (number % 100 < 11 && Locale.getDefault().toString().equals("tr_TR")) {
			soFar = numNames[number % 100];
			number /= 100;
		} else {

			soFar = numNames[number % 10];
			number /= 10;

			soFar = tensNames[number % 10] + " " + soFar;
			number /= 10;
		}
		if (number == 0)
			return soFar;
	
		return numNames[number] + " " + bundle.getString("hundred").toLowerCase() + " " + soFar;
	}

	public String convertNumToWord(int number) {

		if (Locale.getDefault().toString().equals("en_US")) {
			numNames = numNames_US;
			tensNames = tensNames_US;
		} else {
			numNames = numNames_TR;
			tensNames = tensNames_TR;
		}

		// 0 to 999 999 999 999
		if (number == 0) {
			return bundle.getString("zero").toLowerCase();
		}

		String snumber = Integer.toString(number);

		// pad with "0"
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);

		// XXXnnnnnnnnn
		int billions = Integer.parseInt(snumber.substring(0, 3));
		// nnnXXXnnnnnn
		int millions = Integer.parseInt(snumber.substring(3, 6));
		// nnnnnnXXXnnn
		int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
		// nnnnnnnnnXXX
		int thousands = Integer.parseInt(snumber.substring(9, 12));

		String tradBillions;
		switch (billions) {
		case 0:
			tradBillions = "";
			break;
		case 1:
			tradBillions = convertLessThanOneThousand(billions) + " " + bundle.getString("billion").toLowerCase() + " ";
			break;
		default:
			tradBillions = convertLessThanOneThousand(billions) + " " + bundle.getString("billion").toLowerCase() + " ";
		}
		String result = tradBillions;

		String tradMillions;
		switch (millions) {
		case 0:
			tradMillions = "";
			break;
		case 1:
			tradMillions = convertLessThanOneThousand(millions) + " " + bundle.getString("million").toLowerCase() + " ";
			break;
		default:
			tradMillions = convertLessThanOneThousand(millions) + " " + bundle.getString("million").toLowerCase() + " ";
		}
		result = result + tradMillions;

		String tradHundredThousands;
		switch (hundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1:
			tradHundredThousands = bundle.getString("thousand").toLowerCase() + " ";
			break;
		default:
			tradHundredThousands = convertLessThanOneThousand(hundredThousands) + " " + bundle.getString("thousand").toLowerCase() + " ";
		}

		result = result + tradHundredThousands;

		String tradThousand;
		tradThousand = convertLessThanOneThousand(thousands);
		result = result + tradThousand;

		// remove extra spaces!
		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}

}
