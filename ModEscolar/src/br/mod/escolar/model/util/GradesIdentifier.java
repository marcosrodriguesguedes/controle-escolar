package br.mod.escolar.model.util;

public class GradesIdentifier {
	
	public static final String G0 = "0";
	public static final String G1 = "1";
	public static final String G2 = "2";
	public static final String G3 = "3";
	public static final String G4 = "4";
	public static final String G5 = "5";
	public static final String G6 = "6";
	public static final String G7 = "7";
	public static final String G8 = "8";
	public static final String G9 = "9";
	public static final String EI= "EI";
	public static final String EF= "EF";
	public static final String EFI = "EFI";
	public static final String EFII = "EFII";
	public static final String EP = "EP";
	public static final String EM = "EM";
	
	public static String makeTitleToDisciplinesReport(String grade, String  grade2){
		String nameGrade = "";
		if(grade2.equals(GradesIdentifier.EI)){
			if(grade.equals(GradesIdentifier.G0)){
				nameGrade =  "MAT";
			}else{
				nameGrade = grade;
			}
		}else if(grade2.equals(GradesIdentifier.EM)){
			if(grade.equals(GradesIdentifier.G4)){
				nameGrade =  "PV"; 
			}else{
				nameGrade = grade;
			}
		}else if(grade2.equals(GradesIdentifier.EFI)){
			nameGrade = grade;
		}else if(grade2.equals(GradesIdentifier.EFII)){
			nameGrade = grade;
		}else if(grade2.equals(GradesIdentifier.EP)){
			if(grade.equals(GradesIdentifier.G1)){
				nameGrade =  "PR";
			}
		}else{
			nameGrade = grade;
		}
		return nameGrade;
	}
	
	public static String makeTitleToStudentsReport(String grade, String  grade2){
		String nameGrade = "";
		if(grade2.equals(GradesIdentifier.EI)){
			if(grade.equals(GradesIdentifier.G0+"º")){
				nameGrade =  "MAT";
			}else{
				nameGrade = grade;
			}
		}else if(grade2.equals(GradesIdentifier.EM)){
			if(grade.equals(GradesIdentifier.G4+"º")){
				nameGrade =  "PV"; 
			}else{
				nameGrade = grade;
			}
		}else if(grade2.equals(GradesIdentifier.EFI)){
			nameGrade = grade;
		}else if(grade2.equals(GradesIdentifier.EFII)){
			nameGrade = grade;
		}else if(grade2.equals(GradesIdentifier.EP)){
			if(grade.equals(GradesIdentifier.G1+"º")){
				nameGrade =  "PR";
			}
		}else{
			nameGrade = grade;
		}
		return nameGrade;
	}


}
