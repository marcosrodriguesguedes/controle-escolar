package br.mod.escolar.model.util;




import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.util.Date;

import br.mod.escolar.model.exception.InvalidParameterException;

public class Validator {

	public static boolean isNumberValid(int number) {
		return (number > 0);
	}

	public static boolean isNumberValid(double number) {
		return (number > 0);
	}

	public static boolean isNumberValid(String number) {
		if (number == null) {
			return false;
		} else if (isNumber(number)) {
			int n = Integer.parseInt(number);
			return n > 0;
		}
		return false;
	}

	public static boolean isRGValid(String rg) {
		if (rg == null || rg.length() == 0)
			return false;
		for (int i = 0; i < rg.length(); i++) {
			if (!isNumber(rg.charAt(i) + ""))
				return false;
		}
		return true;
	}

	public static boolean isClassIdValid(String id) {
		if (id.length() == 1) {
			return Character.isLetter(id.toCharArray()[0]);
		} else
			return false;
	}

	public static boolean isURLValid(String url) {
		try {
			@SuppressWarnings("unused")
			URL u = new URL(url);
		} catch (MalformedURLException e) {
			return false;
		}
		return true;
	}

	public static boolean isStringValid(String str) {
		return (str == null) ? false : (str.length() > 0);
	}

	public static boolean isChoiceValid(String value, String choice1,
			String choice2) {
		return isStringValid(value)
				&& isStringValid(choice1)
				&& isStringValid(choice2)
				&& (value.equalsIgnoreCase(choice1) || value
						.equalsIgnoreCase(choice2));
	}

	private static boolean isNumber(String number) {
		try {
			@SuppressWarnings("unused")
			int num = Integer.parseInt(number);
			return true;
		} catch (NumberFormatException e) {
			try {
				@SuppressWarnings("unused")
				double num = Double.parseDouble(number);
				return true;
			} catch (NumberFormatException e2) {
				return false;
			}
		}
	}

	public static boolean isDDDValid(String number) {
		return (number.length() == 2) && (isNumber(number));
	}

	public static boolean isPhoneNumberValid(String number) {
		return (number.length() == 8) && (isNumber(number));
	}

	public static boolean isCEPValid(String number) {
		if (number == null)
			return false;
		return (isNumber(number)) && (number.length() == 8);
	}

	public static Date generateDate(String date)
			throws InvalidParameterException {
		if (!isDateValid(date))
			return null;
		else {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			long mili = 0;
			try {
				mili = formatter.parse(date).getTime();
			} catch (ParseException e) {
				throw new InvalidParameterException(Messages.INVALID_DATE);
			}

			return new Date(mili);

		}
	}

	public static String formatDate(Date date) {
		String[] d = date.toString().split("-");
		return d[0] + "/" + d[1] + "/" + d[2];
	}
	
	public static String formatDate2(Date date) {
		String[] d = date.toString().split("-");
		return d[2] + "/" + d[1] + "/" + d[0];
	}

	public static String formatTimestamp(Date date) {
		String d1 = date.toString().split(" ")[0];
		String[] d = d1.split("-");
		return d[2] + "/" + d[1] + "/" + d[0];
	}

	public static boolean isDateValid(String date) {
		String[] d = date.split("/");

		if (d.length == 3)
			if ((d[0].length() == 2) && (d[1].length() == 2)
					&& (d[2].length() == 4)) {
				try {
					int day = Integer.parseInt(d[0]);
					int month = Integer.parseInt(d[1]);
					int year = Integer.parseInt(d[2]);

					return ((day >= 1) && (day <= 31) && (month >= 0)
							&& (month <= 11) && (year >= 1800) && (year <= 8099));
				} catch (NumberFormatException e) {
					return false;
				}
			} else
				return false;
		else
			return false;
	}

	public static boolean isEmailValid(String email) {
		if (!isStringValid(email)) {
			return false;
		}
		String[] nameAndDomain = email.split("@");

		if ((nameAndDomain.length <= 1)
				|| !Validator.isStringValid(nameAndDomain[0])
				|| !Validator.isStringValid(nameAndDomain[1])) {
			return false;
		}

		String[] domainSplitted = nameAndDomain[1].split("\\.");
		return isStringValid(nameAndDomain[0])
				&& isStringValid(domainSplitted[0])
				&& isStringValid(domainSplitted[1]);
	}

	public static boolean isSeriesValid(String series) {
		return isStringValid(series);
	}

	private static String calcDigVerif(String num) {
		Integer primDig, segDig;
		int soma = 0, peso = 10;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		if (soma % 11 == 0 | soma % 11 == 1)
			primDig = new Integer(0);
		else
			primDig = new Integer(11 - (soma % 11));

		soma = 0;
		peso = 11;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		soma += primDig.intValue() * 2;
		if (soma % 11 == 0 | soma % 11 == 1)
			segDig = new Integer(0);
		else
			segDig = new Integer(11 - (soma % 11));

		return primDig.toString() + segDig.toString();
	}

	public static boolean isCPFValid(String cpf) {
		if (cpf == null)
			return false;

		if (cpf.length() != 11)
			return false;

		String numDig = cpf.substring(0, 9);
		return calcDigVerif(numDig).equals(cpf.substring(9, 11));
	}

	public static boolean isRaceValid(String arg) {
		if (arg == null)
			return false;

		return (arg.equals("indefinida") || arg.equals("branca")
				|| arg.equals("amarela") || arg.equals("preto")
				|| arg.equals("parda") || arg.equals("indígena")
				|| arg.equals("Indefinida") || arg.equals("Branca")
				|| arg.equals("Amarela") || arg.equals("Preto")
				|| arg.equals("Parda") || arg.equals("Indígena"));

	}

}
