package com.dailycodework.sbrDemo.service;

import com.dailycodework.sbrDemo.exception.StudentNotFoundException;
import com.dailycodework.sbrDemo.model.Student;
import com.dailycodework.sbrDemo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{
    private final StudentRepository studentRepository;
    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student could not be found"));
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        if(studentAlreadyExist(student.getEmail())){
            throw new StudentNotFoundException("Sorry, Student already exists");
        }
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student, Long id) {

       return studentRepository.findById(id).map(st -> {
           st.setFirstName(student.getFirstName());
           st.setLastName(student.getLastName());
           st.setEmail(student.getEmail());
           st.setDepartment(student.getDepartment());
           return studentRepository.save(st);
       }).orElseThrow(() -> new StudentNotFoundException("student not found"));
    }

    @Override
    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new StudentNotFoundException("Sorry, Student could not be found");
        }
        studentRepository.deleteById(id);
    }
    private boolean studentAlreadyExist(String email){
       return studentRepository.findByEmail(email).isPresent();
    }
}
