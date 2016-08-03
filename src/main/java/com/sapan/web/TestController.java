package com.sapan.web;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapan.data.model.Student;
import com.sapan.data.redis.repo.StudentRepository;

@RestController
public class TestController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@RequestMapping(value="/getAllData",method=GET,
			produces=APPLICATION_JSON_VALUE)
	public @ResponseBody List<Student> getAllStudents() {
		
		return studentRepository.findAllStudents();
	}

}
