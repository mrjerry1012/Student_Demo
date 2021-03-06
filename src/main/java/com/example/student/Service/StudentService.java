package com.example.student.Service;

import com.example.student.Model.Student;

import java.util.List;

public interface StudentService {
    List<Student> listAllStudents();
    Student getStudent(long id);

    Student addStudent(Student student);

    Student updateStudent(long id,Student student);

    String deleteStudnet(long id);
}
