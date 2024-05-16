package com.dailycodework.sbrDemo.service;

import com.dailycodework.sbrDemo.model.Student;

import java.util.List;

public interface IStudentService {

    Student getStudentById(Long id);

    List<Student> getStudents();
    Student addStudent(Student student);

    Student updateStudent(Student student, Long id);

    void deleteStudent(Long id);
}
