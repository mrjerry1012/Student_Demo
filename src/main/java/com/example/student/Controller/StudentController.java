package com.example.student.Controller;


import com.example.student.Exception.NotFoundException;
import com.example.student.Model.Student;
import com.example.student.Repository.StudentRepository;
import com.example.student.Service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService){
        this.studentService = studentService;
    }


    @GetMapping
    public ResponseEntity<List<Student>> list(){
        System.out.println("GET Students");
        List<Student> students = studentService.listAllStudents();
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<Student> get(@PathVariable Long id){
        System.out.println("GET Students");
        try{
            Student student = studentService.getStudent(id);
            return  new ResponseEntity<Student>(student,HttpStatus.OK);
        }catch (NotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody final Student student){
        System.out.println(student);
        return new ResponseEntity<Student>(studentService.addStudent(student),HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public ResponseEntity<Student> update(@PathVariable Long id,@RequestBody final Student student){
        System.out.println(id);
        try{
            return new ResponseEntity<Student>(studentService.updateStudent(id,student),HttpStatus.CREATED);
        }catch (NotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id){
        System.out.println(id);
        try{
            return new ResponseEntity<String>(studentService.deleteStudnet(id),HttpStatus.OK);
        }catch (NotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

}
