package com.sapan.data.redis.repo;

import java.util.List;
import java.util.Map;

import com.sapan.data.model.Student;


public interface StudentRepository {

    void saveStudent(Student person);

    void updateStudent(Student student);

    Student findStudent(String id);

    List<Student> findAllStudents();

    void deleteStudent(String id);
}
