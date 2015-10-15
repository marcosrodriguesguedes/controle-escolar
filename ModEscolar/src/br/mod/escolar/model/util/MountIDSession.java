package br.mod.escolar.model.util;




import java.util.Random;

public class MountIDSession {
	
	
	
	public static String generateIDSession(){
		return generateRamdomNumber() + generateRamdomLetters()
		+ getTime();
	}
	
	
	private static  String generateRamdomLetters() {
		Random intRdn = new Random();
		char chrVetor[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
				'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
				'u', 'v', 'w', 'x', 'y', 'z' };
		int intCont;
		int intPos1;
		int intPos2;
		char chrTmp;
		for (intCont = 0; intCont < 100; intCont++) {
			intPos1 = intRdn.nextInt(52);
			intPos2 = intRdn.nextInt(52);

			if (intPos1 != intPos2) {
				chrTmp = chrVetor[intPos1];
				chrVetor[intPos1] = chrVetor[intPos2];
				chrVetor[intPos2] = chrTmp;
			}
		}
		String out = "";
		for (int i = 0; i < 5; i++) {
			out += chrVetor[i];
		}
		return out;
	}

	private static String generateRamdomNumber() {
		String out = "";
		out += 1 + (int) (Math.random() * 9999) + "";
		return out;
	}

	private static String getTime() {
		return System.nanoTime() + "";
	}

}
