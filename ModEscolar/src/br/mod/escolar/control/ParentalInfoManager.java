package br.mod.escolar.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import br.mod.escolar.model.entities.ParentalInfo;
import br.mod.escolar.model.exception.DBException;
import br.mod.escolar.model.exception.InvalidParameterException;
import br.mod.escolar.model.exception.NoSuchStudentException;
import br.mod.escolar.model.util.HibernateUtil;
import br.mod.escolar.model.util.Messages;
import br.mod.escolar.model.util.Validator;

public class ParentalInfoManager {

	private static ParentalInfoManager manager = null;

	private ParentalInfoManager() {
	}

	public static synchronized ParentalInfoManager getInstance() {
		if (manager == null) {
			manager = new ParentalInfoManager();
		}
		return manager;
	}

	public void validate(String motherName, String motherBirthday,
			String motherCPF, String motherRG, String motherIssuingDate,
			String motherIssuingOrg, String motherJob, String motherWorkplace,
			String motherReligion, String motherTelephoneDDD,
			String motherTelephone, String motherMobileDDD,
			String motherMobile, String motherEmail, String fatherName,
			String fatherBirthday, String fatherCPF, String fatherRG,
			String fatherIssuingDate, String fatherIssuingOrg,
			String fatherJob, String fatherWorkplace, String fatherReligion,
			String fatherTelephoneDDD, String fatherTelephone,
			String fatherMobileDDD, String fatherMobile, String fatherEmail)
			throws InvalidParameterException, DBException {

		if (!Validator.isStringValid(motherName))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Nome da mãe");

		if (!Validator.isStringValid(fatherName))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Nome do pai");

		if (!Validator.isDateValid(motherBirthday))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Data de nascimento da mãe");

		if (!Validator.isDateValid(fatherBirthday))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Data de nascimento do pai");

		if (!Validator.isCPFValid(motherCPF))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "CPF da mãe");

		if (!Validator.isCPFValid(fatherCPF))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "CPF do pai");

		if (!Validator.isRGValid(motherRG))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "RG da mãe");

		if (!Validator.isRGValid(fatherRG))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "RG do pai");

		if (!Validator.isDateValid(motherIssuingDate))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Data de emissão do RG da mãe");

		if (!Validator.isDateValid(fatherIssuingDate))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Data de emissão do RG do pai");

		if (!Validator.isStringValid(motherIssuingOrg))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Órgão emissor do RG da mãe");

		if (!Validator.isStringValid(fatherIssuingOrg))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Órgão emissor do RG do pai");

		if (!Validator.isStringValid(motherJob))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Profissão da mãe");

		if (!Validator.isStringValid(fatherJob))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Profissão do pai");

		if (!Validator.isStringValid(motherWorkplace))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Local de trabalho da mãe");

		if (!Validator.isStringValid(fatherWorkplace))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Local de trabalho do pai");

		if (!(Validator.isDDDValid(motherTelephoneDDD) || motherTelephoneDDD
				.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "DDD do telefone da mãe");

		if (!(Validator.isPhoneNumberValid(motherTelephone) || motherTelephone.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Telefone da mãe");

		if (!(Validator.isDDDValid(motherMobileDDD) || motherMobileDDD
				.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "DDD do celular da mãe");

		if (!(Validator.isPhoneNumberValid(motherMobile) || motherMobile
				.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Celular da mãe");

		if (!Validator.isDDDValid(fatherTelephoneDDD))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "DDD do telefone do pai");

		if (!Validator.isPhoneNumberValid(fatherTelephone))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Telefone do pai");

		if (!(Validator.isDDDValid(fatherMobileDDD) || fatherMobileDDD
				.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "DDD do celular do pai");

		if (!(Validator.isPhoneNumberValid(fatherMobile) || fatherMobile
				.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "Celular do pai");

		if (!(Validator.isEmailValid(motherEmail) || motherEmail.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "E-mail da mãe");

		if (!(Validator.isEmailValid(fatherEmail) || fatherEmail.isEmpty()))
			throw new InvalidParameterException(Messages.INVALID_FIELD
					+ "E-mail do pai");
	}

	public int createParentalInfo(String motherName, String motherBirthday,
			String motherCPF, String motherRG, String motherIssuingDate,
			String motherIssuingOrg, String motherJob, String motherWorkplace,
			String motherReligion, String motherTelephoneDDD,
			String motherTelephone, String motherMobileDDD,
			String motherMobile, String motherEmail, String fatherName,
			String fatherBirthday, String fatherCPF, String fatherRG,
			String fatherIssuingDate, String fatherIssuingOrg,
			String fatherJob, String fatherWorkplace, String fatherReligion,
			String fatherTelephoneDDD, String fatherTelephone,
			String fatherMobileDDD, String fatherMobile, String fatherEmail)
			throws DBException, NoSuchStudentException,
			InvalidParameterException {

		validate(motherName, motherBirthday, motherCPF, motherRG,
				motherIssuingDate, motherIssuingOrg, motherJob,
				motherWorkplace, motherReligion, motherTelephoneDDD,
				motherTelephone, motherMobileDDD, motherMobile, motherEmail,
				fatherName, fatherBirthday, fatherCPF, fatherRG,
				fatherIssuingDate, fatherIssuingOrg, fatherJob,
				fatherWorkplace, fatherReligion, fatherTelephoneDDD,
				fatherTelephone, fatherMobileDDD, fatherMobile, fatherEmail);

		ParentalInfo p = new ParentalInfo(motherName, Validator
				.generateDate(motherBirthday), motherCPF, motherRG, Validator
				.generateDate(motherIssuingDate), motherIssuingOrg, motherJob,
				motherWorkplace, motherReligion, motherTelephoneDDD,
				motherTelephone, motherMobileDDD, motherMobile, motherEmail,
				fatherName, Validator.generateDate(fatherBirthday), fatherCPF,
				fatherRG, Validator.generateDate(fatherIssuingDate),
				fatherIssuingOrg, fatherJob, fatherWorkplace, fatherReligion,
				fatherTelephoneDDD, fatherTelephone, fatherMobileDDD,
				fatherMobile, fatherEmail);

		return (Integer) HibernateUtil.create(p);
	}

	@SuppressWarnings("unchecked")
	public ParentalInfo getParentalInfo(int id) {
		Collection<SimpleExpression> exps = new ArrayList<SimpleExpression>();
		exps.add(Restrictions.like("id", id));
		List<ParentalInfo> pi = null;
		pi = (List<ParentalInfo>) HibernateUtil.createQuery(ParentalInfo.class,
				exps);
		return pi.isEmpty() ? null : pi.get(0);
	}
	
	public ParentalInfo removeParentalInfo(int idStudent) {
		ParentalInfo pi = getParentalInfo(idStudent);
		HibernateUtil.delete(pi);
		return pi;
	}

	public void updateParentalInfo(int id, String motherName,
			String motherBirthday, String motherCPF, String motherRG,
			String motherIssuingDate, String motherIssuingOrg,
			String motherJob, String motherWorkplace, String motherReligion,
			String motherTelephoneDDD, String motherTelephone,
			String motherMobileDDD, String motherMobile, String motherEmail,
			String fatherName, String fatherBirthday, String fatherCPF,
			String fatherRG, String fatherIssuingDate, String fatherIssuingOrg,
			String fatherJob, String fatherWorkplace, String fatherReligion,
			String fatherTelephoneDDD, String fatherTelephone,
			String fatherMobileDDD, String fatherMobile, String fatherEmail)
			throws DBException, InvalidParameterException {
		ParentalInfo pi = getParentalInfo(id);

		validate(motherName, motherBirthday, motherCPF, motherRG,
				motherIssuingDate, motherIssuingOrg, motherJob,
				motherWorkplace, motherReligion, motherTelephoneDDD,
				motherTelephone, motherMobileDDD, motherMobile, motherEmail,
				fatherName, fatherBirthday, fatherCPF, fatherRG,
				fatherIssuingDate, fatherIssuingOrg, fatherJob,
				fatherWorkplace, fatherReligion, fatherTelephoneDDD,
				fatherTelephone, fatherMobileDDD, fatherMobile, fatherEmail);

		pi.setMotherName(motherName);
		pi.setMotherBirthday(Validator.generateDate(motherBirthday));
		pi.setMotherCPF(motherCPF);
		pi.setMotherRG(motherRG);
		pi.setMotherIssuingDate(Validator.generateDate(motherIssuingDate));
		pi.setMotherIssuingOrg(motherIssuingOrg);
		pi.setMotherJob(motherJob);
		pi.setMotherWorkplace(motherWorkplace);
		pi.setMotherReligion(motherReligion);
		pi.setMotherTelephoneDDD(motherTelephoneDDD);
		pi.setMotherTelephone(motherTelephone);
		pi.setMotherMobileDDD(motherMobileDDD);
		pi.setMotherMobile(motherMobile);
		pi.setMotherEmail(motherEmail);

		pi.setFatherName(fatherName);
		pi.setFatherBirthday(Validator.generateDate(fatherBirthday));
		pi.setFatherCPF(fatherCPF);
		pi.setFatherRG(fatherRG);
		pi.setFatherIssuingDate(Validator.generateDate(fatherIssuingDate));
		pi.setFatherIssuingOrg(fatherIssuingOrg);
		pi.setFatherJob(fatherJob);
		pi.setFatherWorkplace(fatherWorkplace);
		pi.setFatherReligion(fatherReligion);
		pi.setFatherTelephoneDDD(fatherTelephoneDDD);
		pi.setFatherTelephone(fatherTelephone);
		pi.setFatherMobileDDD(fatherMobileDDD);
		pi.setFatherMobile(fatherMobile);
		pi.setFatherEmail(fatherEmail);

		HibernateUtil.update(pi);
	}
}
