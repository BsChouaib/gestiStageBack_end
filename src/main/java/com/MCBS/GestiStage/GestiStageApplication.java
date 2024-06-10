package com.MCBS.GestiStage;

import com.MCBS.GestiStage.dtos.request.*;
import com.MCBS.GestiStage.enumerations.InternshipType;
import com.MCBS.GestiStage.models.AppRole;
import com.MCBS.GestiStage.models.Files;
import com.MCBS.GestiStage.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import com.MCBS.GestiStage.config.RsaKeyProperties;

import java.util.Date;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class GestiStageApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiStageApplication.class, args);
	}


	@Bean
	CommandLineRunner start(AccountService accountService,
							ClaimsService claimsService,
							SubjectService subjectService,
							DemandService demandService,
							StudyFieldService studyFieldService){
		return args -> {
			// added StudyFields
			studyFieldService.addNewStudyField(new StudyFieldDto("IRM-Opt. BD-IA"));
			studyFieldService.addNewStudyField(new StudyFieldDto("IRM-Opt. IT finance"));
			studyFieldService.addNewStudyField(new StudyFieldDto("IRM-Opt. Ingénierie des Systèmes d’Information et du Logiciel"));
			studyFieldService.addNewStudyField(new StudyFieldDto("IRM-Opt. Ingénierie des Systèmes Embarqués et Mobiles"));
			studyFieldService.addNewStudyField(new StudyFieldDto("Informatique, Réseaux et Multimédia (IRM)"));
			//
			accountService.newRole(AppRole.builder().roleName("STUDENT").build());
			accountService.newRole(AppRole.builder().roleName("ADMIN").build());
			accountService.newRole(AppRole.builder().roleName("TEACHER").build());
			// admin account
			AppUserDto admin = new AdminDto();
			admin.setFirstname("adminApp");
			admin.setLastname("adminPi");
			admin.setEmail("admin@pi.tn");
			admin.setGender("male");
			admin.setPassword("123456");
			admin.setRole("ADMIN");
			accountService.createNewUser(admin);
			// students account
			AppUserDto student = 	new StudentDto(	"student1Fristname",
					"student1Lastname",
					"123456","123456",
					"student@pi.tn",
					new Date(),
					"+21627823600",
					"2074",
					"Tunisia",
					"EL Mourouj",
					"2074",
					"M",
					"TUNISIAN",
					"STUDENT",
					"1",
					"polytech",
					4L,
					new Date()
			);

			accountService.createNewUser(student);


			AppUserDto student13 = 	new StudentDto(	"student13Fristname",
													"student13Lastname",
													"123456","123456",
													"student13@pi.tn",
													new Date(),
													"+21627823600",
													"2074",
													"Tunisia",
													"EL Mourouj",
												"2074",
													"M",
											"TUNISIAN",
												"STUDENT",
										"1",
										"polytech",
											1L,
													new Date()
													);
			accountService.createNewUser(student13);

			AppUserDto student1 = 	new StudentDto(	"student2Fristname",
					"student2Fristname",
					"123456","123456",
					"student1@pi.tn",
					new Date(),
					"+21627823600",
					"2074",
					"Tunisia",
					"EL Mourouj",
					"2074",
					"M",
					"TUNISIAN",
					"STUDENT",
					"1",
					"polytech",
					5L,
					new Date()
			);

			accountService.createNewUser(student1);
			// teacher account
			AppUserDto teacher = new TeacherDto();
			teacher.setFirstname("teacher1Firstname");
			teacher.setLastname("teacher1Lastname");
			teacher.setEmail("teacher@pi.tn");
			teacher.setGender("M");
			teacher.setPassword("123456");
			teacher.setRole("TEACHER");
			AppUserDto teacher1 = new TeacherDto();
			teacher1.setFirstname("teacher2Lastname");
			teacher1.setLastname("teacher2Lastname");
			teacher1.setEmail("teacher1@pi.tn");
			teacher1.setGender("M");
			teacher1.setPassword("123456");
			teacher1.setRole("TEACHER");
			AppUserDto teacher2 = new TeacherDto();
			teacher2.setFirstname("teacher3Firstname");
			teacher2.setLastname("teacher3Lastname");
			teacher2.setEmail("teacher2@pi.tn");
			teacher2.setGender("F");
			teacher2.setPassword("123456");
			teacher2.setRole("TEACHER");
			accountService.createNewUser(teacher);
			accountService.createNewUser(teacher1);
			accountService.createNewUser(teacher2);
            /*
			accountService.addRoleToUser("user1","USER");
			accountService.addRoleToUser("user2","USER");
			accountService.addRoleToUser("admin","USER");
			accountService.addRoleToUser("admin","ADMIN");
			*/
			ClaimDtoRequest  claim1 = new ClaimDtoRequest("studentCalim1");
			ClaimDtoRequest  claim2 = new ClaimDtoRequest("studentCalim1");
			ClaimDtoRequest  claim3 = new ClaimDtoRequest("studentCalim1");
			ClaimDtoRequest  claim4 = new ClaimDtoRequest("studentCalim1");
			ClaimDtoRequest  claim5 = new ClaimDtoRequest("studentCalim1");
			ClaimDtoRequest  claim6 = new ClaimDtoRequest("studentCalim1");
			ClaimDtoRequest  claim7 = new ClaimDtoRequest("studentCalim1");
			claimsService.createClaim(claim1,"student@pi.tn");
			claimsService.createClaim(claim2,"student1@pi.tn");
			claimsService.createClaim(claim3,"student2@pi.tn");
			claimsService.createClaim(claim4,"student@pi.tn");
			claimsService.createClaim(claim5,"student@pi.tn");
			claimsService.createClaim(claim6,"student1@pi.tn");
			claimsService.createClaim(claim7,"student2@pi.tn");
			ClaimDtoRequest  claim8 = new ClaimDtoRequest("TeacherCalim1");
			ClaimDtoRequest  claim14 = new ClaimDtoRequest("TeacherCalim2");
			ClaimDtoRequest  claim9 = new ClaimDtoRequest("TeacherCalim2");
			ClaimDtoRequest  claim10 = new ClaimDtoRequest("TeacherCalim2");
			ClaimDtoRequest  claim11 = new ClaimDtoRequest("TeacherCalim2");
			ClaimDtoRequest  claim12 = new ClaimDtoRequest("TeacherCalim2");
			ClaimDtoRequest  claim13 = new ClaimDtoRequest("TeacherCalim2");
			claimsService.createClaim(claim8,"teacher@pi.tn");
			claimsService.createClaim(claim14,"teacher@pi.tn");
			claimsService.createClaim(claim9,"teacher@pi.tn");
			claimsService.createClaim(claim10,"teacher@pi.tn");
			claimsService.createClaim(claim11,"teacher1@pi.tn");
			claimsService.createClaim(claim12,"teacher1@pi.tn");
			claimsService.createClaim(claim13,"teacher2@pi.tn");


			// create demands
            /*demandService.createDemand(1L,"student@pi.tn", null, null);
			demandService.createDemand(5L,"student1@pi.tn", null, null);
			demandService.createDemand(6L,"student1@pi.tn", null, null);
			demandService.createDemand(10L,"student@pi.tn", null, null);
			demandService.createDemand(3L,"student2@pi.tn", null, null);
			demandService.createDemand(2L,"student@pi.tn", null, null);
			*/


			// add subject
			SubjectDtoRequest subjectDtoRequest1= new SubjectDtoRequest("dev web","dev web with angular and spring boot framework","teacher2@pi.tn", InternshipType.initiationInternship, 1L);
			SubjectDtoRequest subjectDtoRequest2= new SubjectDtoRequest("dev web","dev web with react and spring boot framework","teacher1@pi.tn", InternshipType.perfectionnementInternship, 4L);
			SubjectDtoRequest subjectDtoRequest3= new SubjectDtoRequest("dev web","dev monolithic app with .Net framework","teacher1@pi.tn", InternshipType.perfectionnementInternship, 2L);
			SubjectDtoRequest subjectDtoRequest4= new SubjectDtoRequest("dev mobile","dev web with angular and spring boot framework","teacher1@pi.tn", InternshipType.EndOfStudiesProject, 3L);
			SubjectDtoRequest subjectDtoRequest5= new SubjectDtoRequest("dev IHM","dev dab with c++","teacher1@pi.tn", InternshipType.initiationInternship, 1L);
			SubjectDtoRequest subjectDtoRequest6= new SubjectDtoRequest("dev sever web","dev web with angular and spring boot framework","teacher@pi.tn", InternshipType.perfectionnementInternship, 1L);
			SubjectDtoRequest subjectDtoRequest7= new SubjectDtoRequest("dev vpn","dev web with angular and spring boot framework","teacher@pi.tn", InternshipType.EndOfStudiesProject, 1L);
			SubjectDtoRequest subjectDtoRequest8= new SubjectDtoRequest("dev retail system","dev web with angular and spring boot framework","teacher@pi.tn", InternshipType.EndOfStudiesProject,4L);
			SubjectDtoRequest subjectDtoRequest9= new SubjectDtoRequest("dev tracking system","dev web with angular and spring boot framework","teacher@pi.tn", InternshipType.EndOfStudiesProject,3L);
			SubjectDtoRequest subjectDtoRequest10= new SubjectDtoRequest("dev payment module","dev web with angular and spring boot framework","teacher2@pi.tn", InternshipType.EndOfStudiesProject, 5L);
			subjectService.createSubject(subjectDtoRequest1);
			subjectService.createSubject(subjectDtoRequest2);
			subjectService.createSubject(subjectDtoRequest3);
			subjectService.createSubject(subjectDtoRequest4);
			subjectService.createSubject(subjectDtoRequest5);
			subjectService.createSubject(subjectDtoRequest6);
			subjectService.createSubject(subjectDtoRequest7);
			subjectService.createSubject(subjectDtoRequest8);
			subjectService.createSubject(subjectDtoRequest9);
			subjectService.createSubject(subjectDtoRequest10);

		};
	}



}
