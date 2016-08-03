package com.sapan.data.redis.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapan.data.model.Student;

import javax.annotation.PostConstruct;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

	@Autowired
	@Qualifier("studentMapper")
	private ObjectMapper studentMapper;
	
    private static final String KEY = "Student";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public StudentRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public void saveStudent(final Student student) {
        hashOperations.put(KEY, student.getId(), student);
    }

    public void updateStudent(final Student student) {
        hashOperations.put(KEY, student.getId(), student);
    }

    public Student findStudent(final String id) {
        return (Student) hashOperations.get(KEY, id);
    }

    public List<Student> findAllStudents() {
    	Map<Object, Object> entries = hashOperations.entries(KEY);
    	return entries.keySet().stream()
        .map(k -> studentMapper.convertValue(entries.get(k), Student.class))
        .collect(Collectors.toList());
    }

    public void deleteStudent(final String id) {
        hashOperations.delete(KEY, id);
    }
}
