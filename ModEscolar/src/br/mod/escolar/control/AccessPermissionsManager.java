package br.mod.escolar.control;

import br.mod.escolar.model.exception.NoSuchPermissionException;
import br.mod.escolar.model.util.Messages;
import br.mod.escolar.model.util.UserTypeDefinition;

public class AccessPermissionsManager {

	private static AccessPermissionsManager manager = null;

	private AccessPermissionsManager() {

	}

	public static synchronized AccessPermissionsManager getInstance() {
		if (manager == null) {
			manager = new AccessPermissionsManager();
		}
		return manager;
	}

	public void verifyPermissionToPlanning(String typeUser) throws NoSuchPermissionException {
		if (!(typeUser.equals(UserTypeDefinition.ADMINISTRATOR)
				|| typeUser.equals(UserTypeDefinition.TEACHER)|| typeUser.equals(UserTypeDefinition.COORDINATOR))) {
			throw new NoSuchPermissionException(Messages.ACCESS_TO_PLANNING_NOT_PERMITED);
		}
	}
	
	
	public void verifyPermissionToLogin(String typeUser) throws NoSuchPermissionException {
		if (!(typeUser.equals(UserTypeDefinition.ADMINISTRATOR)
			|| typeUser.equals(UserTypeDefinition.TREASURER)
			|| typeUser.equals(UserTypeDefinition.COORDINATOR)
			|| typeUser.equals(UserTypeDefinition.SECRETARY)
			|| typeUser.equals(UserTypeDefinition.STUDENT)
			|| typeUser.equals(UserTypeDefinition.TEACHER))) {
			throw new NoSuchPermissionException(Messages.LOGIN_NOT_PERMITED);
		}
	}

	public void verifyPermissionToViewAndSearchStudents(String typeUser) throws NoSuchPermissionException {
		if (!(typeUser.equals(UserTypeDefinition.ADMINISTRATOR)
				|| typeUser.equals(UserTypeDefinition.TREASURER)
				|| typeUser.equals(UserTypeDefinition.COORDINATOR)
				|| typeUser.equals(UserTypeDefinition.SECRETARY))) { 
				throw new NoSuchPermissionException(Messages.SEARCH_VIEW_STUDENTS_NOT_PERMITED);
		}
	}

	public void verifyPermissionToCreateAndEditStudents(String typeUser) throws NoSuchPermissionException {
		if (!(typeUser.equals(UserTypeDefinition.ADMINISTRATOR)
				|| typeUser.equals(UserTypeDefinition.TREASURER)
				|| typeUser.equals(UserTypeDefinition.SECRETARY))) { 
				throw new NoSuchPermissionException(Messages.CREATE_EDIT_STUDENTS_NOT_PERMITED);
		}
		
	}

	public void verifyPermissionToRemoveStudents(String typeUser) throws NoSuchPermissionException {
		if (!(typeUser.equals(UserTypeDefinition.ADMINISTRATOR)
				|| typeUser.equals(UserTypeDefinition.SECRETARY))) { 
				throw new NoSuchPermissionException(Messages.REMOVE_STUDENTS_NOT_PERMITED);
		}
	}

	public void verifyPermissionToAdvanceGradeAndEditStudents(String typeUser) throws NoSuchPermissionException {
		if (!(typeUser.equals(UserTypeDefinition.ADMINISTRATOR)
				|| typeUser.equals(UserTypeDefinition.SECRETARY))) { 
				throw new NoSuchPermissionException(Messages.ADVANCE_GRADE_STUDENTS_NOT_PERMITED);
		}
	}
	
	public void verifyPermissionToGenerateReportsStudents(String typeUser) throws NoSuchPermissionException {
		if (!(typeUser.equals(UserTypeDefinition.ADMINISTRATOR)
				|| typeUser.equals(UserTypeDefinition.TREASURER)
				|| typeUser.equals(UserTypeDefinition.COORDINATOR)
				|| typeUser.equals(UserTypeDefinition.SECRETARY))) {  
				throw new NoSuchPermissionException(Messages.GENERATE_REPORTS_STUDENTS_NOT_PERMITED);
		}
	}

	public void verifyPermissionToSearchAndViewTeachers(String typeUser) throws NoSuchPermissionException {
		if (!(typeUser.equals(UserTypeDefinition.ADMINISTRATOR)
				|| typeUser.equals(UserTypeDefinition.COORDINATOR)
				|| typeUser.equals(UserTypeDefinition.SECRETARY))) {  
				throw new NoSuchPermissionException(Messages.SEARCH_VIEW_TEACHERS_NOT_PERMITED);
		}
	}

	public void verifyPermissionToCreateTeachers(String typeUser) throws NoSuchPermissionException {
		if (!typeUser.equals(UserTypeDefinition.ADMINISTRATOR)){  
				throw new NoSuchPermissionException(Messages.CREATE_TEACHERS_NOT_PERMITED);
		}
	}
	
	public void verifyPermissionToUpdateTeachers(String typeUser) throws NoSuchPermissionException {
		if (!typeUser.equals(UserTypeDefinition.ADMINISTRATOR)){  
				throw new NoSuchPermissionException(Messages.UPDATE_TEACHERS_NOT_PERMITED);
		}
	}
	
	public void verifyPermissionToRemoveTeachers(String typeUser) throws NoSuchPermissionException {
		if (!typeUser.equals(UserTypeDefinition.ADMINISTRATOR)){  
				throw new NoSuchPermissionException(Messages.REMOVE_TEACHERS_NOT_PERMITED);
		}
	}

	public void verifyPermissionToGenerateReportsTeachers(String typeUser) throws NoSuchPermissionException {
		if (!(typeUser.equals(UserTypeDefinition.ADMINISTRATOR)
				|| typeUser.equals(UserTypeDefinition.COORDINATOR)
				|| typeUser.equals(UserTypeDefinition.SECRETARY))) {  
				throw new NoSuchPermissionException(Messages.GENERATE_REPORTS_TEACHERS_NOT_PERMITED);
		}
	}
	
	public void verifyPermissionToSearchAndViewEmployees(String typeUser) throws NoSuchPermissionException {
		if (!(typeUser.equals(UserTypeDefinition.ADMINISTRATOR)
				|| typeUser.equals(UserTypeDefinition.COORDINATOR)
				|| typeUser.equals(UserTypeDefinition.SECRETARY))) {  
				throw new NoSuchPermissionException(Messages.SEARCH_VIEW_EMPLOYEES_NOT_PERMITED);
		}
	}

	public void verifyPermissionToCreateEmployees(String typeUser) throws NoSuchPermissionException {
		if (!typeUser.equals(UserTypeDefinition.ADMINISTRATOR)){  
				throw new NoSuchPermissionException(Messages.CREATE_EMPLOYEES_NOT_PERMITED);
		}
	}
	
	public void verifyPermissionToUpdateEmployees(String typeUser) throws NoSuchPermissionException {
		if (!typeUser.equals(UserTypeDefinition.ADMINISTRATOR)){  
				throw new NoSuchPermissionException(Messages.UPDATE_EMPLOYEES_NOT_PERMITED);
		}
	}
	
	public void verifyPermissionToRemoveEmployees(String typeUser) throws NoSuchPermissionException {
		if (!typeUser.equals(UserTypeDefinition.ADMINISTRATOR)){  
				throw new NoSuchPermissionException(Messages.REMOVE_EMPLOYEES_NOT_PERMITED);
		}
	}

	public void verifyPermissionToGenerateReportsEmployees(String typeUser) throws NoSuchPermissionException {
		if (!(typeUser.equals(UserTypeDefinition.ADMINISTRATOR)
				|| typeUser.equals(UserTypeDefinition.COORDINATOR)
				|| typeUser.equals(UserTypeDefinition.SECRETARY))) {  
				throw new NoSuchPermissionException(Messages.GENERATE_REPORTS_EMPLOYEES_NOT_PERMITED);
		}
	}
	
	public void verifyPermissionToSearchAndViewDisciplines(String typeUser) throws NoSuchPermissionException {
		if (!(typeUser.equals(UserTypeDefinition.ADMINISTRATOR)
				|| typeUser.equals(UserTypeDefinition.COORDINATOR)
				|| typeUser.equals(UserTypeDefinition.SECRETARY))) {  
				throw new NoSuchPermissionException(Messages.SEARCH_VIEW_DISCIPLINES_NOT_PERMITED);
		}
	}

	public void verifyPermissionToCreateDisciplines(String typeUser) throws NoSuchPermissionException {
		if (!(typeUser.equals(UserTypeDefinition.ADMINISTRATOR)
				|| typeUser.equals(UserTypeDefinition.TEACHER)
				|| typeUser.equals(UserTypeDefinition.COORDINATOR))){  
				throw new NoSuchPermissionException(Messages.CREATE_DISCIPLINES_NOT_PERMITED);
		}
	}
	
	public void verifyPermissionToUpdateDisciplines(String typeUser) throws NoSuchPermissionException {
		if (!(typeUser.equals(UserTypeDefinition.ADMINISTRATOR)
				|| typeUser.equals(UserTypeDefinition.COORDINATOR))){  
				throw new NoSuchPermissionException(Messages.UPDATE_DISCIPLINES_NOT_PERMITED);
		}
	}
	
	public void verifyPermissionToRemoveDisciplines(String typeUser) throws NoSuchPermissionException {
		if (!typeUser.equals(UserTypeDefinition.ADMINISTRATOR)){  
				throw new NoSuchPermissionException(Messages.REMOVE_DISCIPLINES_NOT_PERMITED);
		}
	}

	public void verifyPermissionToGenerateReportsDisciplines(String typeUser) throws NoSuchPermissionException {
		if (!(typeUser.equals(UserTypeDefinition.ADMINISTRATOR)
				|| typeUser.equals(UserTypeDefinition.COORDINATOR)
				|| typeUser.equals(UserTypeDefinition.SECRETARY))) {  
				throw new NoSuchPermissionException(Messages.GENERATE_REPORTS_DISCIPLINES_NOT_PERMITED);
		}
	}
	
	public void verifyPermissionToUserOperations(String typeUser) throws NoSuchPermissionException {
		if (!typeUser.equals(UserTypeDefinition.ADMINISTRATOR)) {  
			throw new NoSuchPermissionException(Messages.USER_OPERATIONS_NOT_PERMITED);
		}
	}
}
