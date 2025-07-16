package com.develhope.crudTestDemo;

import com.develhope.crudTestDemo.controller.StudentController;
import com.develhope.crudTestDemo.model.Student;
import com.develhope.crudTestDemo.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles(value = "test")
class StudentControllerTest {

	@Autowired
	private StudentController studentController;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
		assertThat(studentController).isNotNull();
	}

	@Test
	void createAStudent() throws Exception {
		Student student = new Student();
		student.setName("Isabella");
		student.setSurname("De Sanctis");
		student.setWorking(true);

		String studentJSON = objectMapper.writeValueAsString(student);

		MvcResult result = this.mockMvc.perform(post("/student")
				.contentType(MediaType.APPLICATION_JSON)
				.content((studentJSON)))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		Student studFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
		assertThat(studFromResponse.getId()).isNotNull();
		assertThat(studFromResponse.getName()).isEqualTo("Isabella");
		assertThat(studFromResponse.getSurname()).isEqualTo("De Sanctis");

	}
	@Test
	void setWorkingStatus() throws Exception {
		Student s = new Student(null,"Giorgio", "Verdi", true);
		Student student = studentRepository.save(s);

		MvcResult result = this.mockMvc.perform(put("/student/{id}/working",student.getId())
								.param("working","false")

						)
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		Optional<Student> student1 = studentRepository.findById(student.getId());
		assertThat(student1.isPresent()).isTrue();
//		assertThat(student1.get().isWorking()).isFalse();
		Boolean valore = student1.get().isWorking();
		assertThat(valore).isFalse();
	}

	@Test
	void readListStudents() throws Exception{
		MvcResult result = this.mockMvc.perform(get("/student/students"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		List<Student> studentsFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
		assertThat(studentsFromResponse.size()).isNotZero();
	}



}
