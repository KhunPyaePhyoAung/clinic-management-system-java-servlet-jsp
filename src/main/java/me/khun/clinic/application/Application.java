package me.khun.clinic.application;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

import me.khun.clinic.model.repo.DoctorRepo;
import me.khun.clinic.model.repo.DoctorSpecialistRepo;
import me.khun.clinic.model.repo.UserRepo;
import me.khun.clinic.model.repo.impl.MySqlDoctorRepoImpl;
import me.khun.clinic.model.repo.impl.MySqlDoctorSpecialistRepoImpl;
import me.khun.clinic.model.repo.jdbc.PropertyReader;
import me.khun.clinic.model.service.DoctorService;
import me.khun.clinic.model.service.DoctorSpecialistService;
import me.khun.clinic.model.service.UserService;
import me.khun.clinic.model.service.impl.DoctorServiceImpl;
import me.khun.clinic.model.service.impl.DoctorSpecialistServiceImpl;
import me.khun.clinic.model.service.impl.UserServiceImpl;

public class Application {

	public static final String LOGIN_URL = "/login";
	public static final String LOGOUT_URL = "/logout";
	public static final String HOME_URL = "/member/home";
	public static final String REPORTS_SEARCH_URL = "/employee/reports/search";
	public static final String CHANGE_PASSWORD_URL = "/member/change-password";
	public static final String PROFILE_URL = "/member/profile";
	public static final String PROFILE_EDIT_URL = "/member/profile/edit";
	public static final String PROFILE_SAVE_URL = "/member/profile/save";
	
	public static final String DOCTOR_SPECIALISTS_SEARCH_URL = "/admin/specialists/search";
	public static final String DOCTOR_SPECIALISTS_SAVE_URL = "/admin/specialists/save";
	public static final String DOCTOR_SPECIALISTS_EDIT_URL = "/admin/specialists/edit";
	public static final String DOCTOR_SPECIALISTS_DELETE_URL = "/admin/specialists/delete";
	
	public static final String DOCTORS_SEARCH_URL = "/member/doctors/search";
	public static final String DOCTOR_EDIT_URL = "/admin/doctors/edit";
	public static final String DOCTOR_SAVE_URL = "/admin/doctors/save";
	public static final String DOCTOR_DELETE_URL = "/admin/doctors/delete";
	public static final String DOCTOR_PROFILE_URL = "/member/doctors/profile";
	
	public static final String DISEASES_SEARCH_URL = "/doctor/diseases/search";
	public static final String DISEASES_SAVE_URL = "/doctor/diseases/save";
	public static final String DISEASE_EDIT_URL = "/doctor/diseases/edit";
	public static final String DISEASE_DELETE_URL = "/doctor/diseases/delete";
	public static final String DISEASE_RESTORE_URL = "/doctor/diseases/restore";
	
	public static final String MEDICINES_SEARCH_URL = "/doctor/medicines/search";
	public static final String MEDICINES_SAVE_URL = "/doctor/medicines/save";
	public static final String MEDICINES_EDIT_URL = "/doctor/medicines/edit";
	public static final String MEDICINES_DELETE_URL = "/doctor/medicines/delete";
	public static final String MEDICINES_RESTORE_URL = "/doctor/medicines/restore";
	
	public static final String PATIENTS_SEARCH_URL = "/doctor/patients/search";
	public static final String PATIENT_EDIT_URL = "/doctor/patients/edit";
	public static final String PATIENT_SAVE_URL = "/doctor/patients/save";
	public static final String PATIENT_DELETE_URL = "/doctor/patients/delete";
	public static final String PATIENT_PROFILE_URL = "/doctor/patients/profile";
	
	public static final String PRESCRIPTIONS_SEARCH_URL = "/doctor/prescriptions/search";
	public static final String PRESCRIPTION_EDIT_URL = "/doctor/prescriptions/edit";
	public static final String PRESCRIPTION_SAVE_URL = "/doctor/prescriptions/save";
	public static final String PRESCRIPTION_DETAILS_URL = "/clinic/prescriptions/details";
	public static final String PRESCRIPTION_DELETE_URL = "/doctor/prescriptions/delete";
	
	
	public static final String VIEW_FOLDER_LOCATION = "/views";
	public static final String INDEX_JSP_LOCATION = "/views/member/index.jsp";
	public static final String SPECIALISTS_JSP_LOCATION = "/views/admin/specialists.jsp";
	public static final String DISEASES_JSP_LOCATION = "/views/doctor/diseases.jsp";
	public static final String MEDICINES_JSP_LOCATION = "/views/doctor/medicines.jsp";
	
	public static final String PATIENTS_JSP_LOCATION = "/views/doctor/patients.jsp";
	public static final String PATIENT_PROFILE_JSP_LOCATION = "/views/doctor/patient-profile.jsp";
	public static final String ADD_PATIENT_JSP_LOCATION = "/views/doctor/add-patient.jsp";
	public static final String EDIT_PATIENT_JSP_LOCATION = "/views/doctor/edit-patient.jsp";
	
	public static final String PRESCRIPTIONS_JSP_LOCATION = "/views/doctor/prescriptions.jsp";
	public static final String EDIT_PRESCRIPTION_JSP_LOCATION = "/views/doctor/edit-prescription.jsp";
	public static final String PRESCRIPTION_DETAILS_JSP_LOCATION = "/views/clinic/prescription-details.jsp";

	public static final String DOCTORS_JSP_LOCATION = "/views/member/doctors.jsp";
	public static final String DOCTOR_PROFILE_JSP_LOCATION = "/views/member/doctor-profile.jsp";
	public static final String ADD_DOCTOR_JSP_LOCATION = "/views/admin/add-doctor.jsp";
	public static final String EDIT_DOCTOR_JSP_LOCATION = "/views/admin/edit-doctor.jsp";
	
	public static final String REPORTS_JSP_LOCATION = "/views/employee/reports.jsp";
	public static final String CHANGE_PASSWORD_JSP_LOCATION = "/views/member/change-password.jsp";
	
	public static final String DATASOURCE_PROPERTIES_FILE_LOCATION = "/datasource.properties";
	
	public static final String SQL_PROPERTIES_FILE_LOCATION = "/sql.properties";
	
	public static DataSource getDataSource() {
		var dataSourceInfo = new PropertyReader(DATASOURCE_PROPERTIES_FILE_LOCATION);
		var dataSource = new MysqlDataSource();
		dataSource.setUrl(dataSourceInfo.getValue("db.url"));
		dataSource.setUser(dataSourceInfo.getValue("db.username"));
		dataSource.setPassword(dataSourceInfo.getValue("db.password"));
		return dataSource;
	}
	
	public static UserRepo getUserRepo() {
		return new MySqlDoctorRepoImpl(getDataSource());
	}
	
	public static UserService getUserService() {
		return new UserServiceImpl(getDoctorRepo());
	}
	
	public static DoctorSpecialistRepo getDoctorSpecialistRepo() {
		return new MySqlDoctorSpecialistRepoImpl(getDataSource());
	}
	
	public static DoctorSpecialistService getDoctorSpecialistService() {
		return new DoctorSpecialistServiceImpl(getDoctorSpecialistRepo());
	}
	
	public static DoctorRepo getDoctorRepo() {
		return new MySqlDoctorRepoImpl(getDataSource());
	}
	
	public static DoctorService getDoctorService() {
		return new DoctorServiceImpl(getDoctorRepo());
	}
}
