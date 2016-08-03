package com.sapan.web;

import static com.sapan.data.model.Student.Gender.MALE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sapan.data.model.Student;
import com.sapan.data.model.Student.Gender;
import com.sapan.data.redis.repo.StudentRepository;

@Controller
public class PutController {
	@Autowired
	private StudentRepository studentRepository;

	@RequestMapping(value = "/addData", method=RequestMethod.GET)
	public String getAddDataForm(Model model) {
		model.addAttribute("student", new Student("1", "Test", MALE, 10));
		return "addData";
	}
	
	@RequestMapping(value = "/addData", method=RequestMethod.POST)
	public String addRedisData(
			@Valid Student student, BindingResult result, Model model) {
		try{
			Student existingStudent = studentRepository.findStudent(student.getId());
			if(existingStudent != null) {
				studentRepository.updateStudent(student);
			}
			studentRepository.saveStudent(student);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/addData";
	}
}
