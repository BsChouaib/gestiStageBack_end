package com.MCBS.GestiStage;

import com.MCBS.GestiStage.dtos.request.AdminDto;
import com.MCBS.GestiStage.dtos.request.AppUserDto;
import com.MCBS.GestiStage.dtos.request.StudentDto;
import com.MCBS.GestiStage.dtos.request.TeacherDto;
import com.MCBS.GestiStage.models.AppRole;
import com.MCBS.GestiStage.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import com.MCBS.GestiStage.config.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class GestiStageApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiStageApplication.class, args);
	}


	@Bean
	CommandLineRunner start(AccountService accountService){
		return args -> {
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
			// student account
			AppUserDto student = new StudentDto();
			student.setFirstname("studentApp");
			student.setLastname("studentPi");
			student.setEmail("student@pi.tn");
			student.setGender("male");
			student.setPassword("123456");
			student.setRole("STUDENT");
			// teacher account
			AppUserDto teacher = new TeacherDto();
			teacher.setFirstname("teacherApp");
			teacher.setLastname("teacherPi");
			teacher.setEmail("teacher@pi.tn");
			teacher.setGender("male");
			teacher.setPassword("123456");
			teacher.setRole("TEACHER");
			accountService.createNewUser(admin);
			accountService.createNewUser(student);
			accountService.createNewUser(teacher);
			/*
			accountService.addRoleToUser("user1","USER");
			accountService.addRoleToUser("user2","USER");
			accountService.addRoleToUser("admin","USER");
			accountService.addRoleToUser("admin","ADMIN");
			*/
		};
	}



}
