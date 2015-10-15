package br.mod.escolar.model.util;




import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateFormater {

	public static final String formatoParaData = "dd-MM-yyyy";

	public static final Locale brasil = new Locale("pt", "BR");

	public static final SimpleDateFormat formatadorDeDatas = new SimpleDateFormat(
			formatoParaData, brasil);
	
}
