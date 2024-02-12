package com.bookstore.com.controller.studentController;

import com.bookstore.com.domain.student.Student;
import com.bookstore.com.dto.studentDto.StudentDto;
import com.bookstore.com.services.studentService.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "student")
public class StudentController {
    private static final Logger  logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @GetMapping( path = "/fetchAll")
    public ResponseEntity<List<StudentDto>> fetchAllStudent(@RequestParam(value = "pageNo",defaultValue = "0") Integer pageNo,
                                                           @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize,
                                                           @RequestParam(value = "sort",defaultValue = "age") String sort){

        return ResponseEntity.ok(studentService.fetchAllStd(pageNo,pageSize,sort));
    }


    @GetMapping(path = "/fetchByName/{name}")
    public ResponseEntity<StudentDto> fetchByTitle(@PathVariable String name){
        return ResponseEntity.ok(studentService.fetchByName(name));

    }


    @GetMapping(path = "/fetchById/{id}")
    public ResponseEntity<StudentDto> fetchById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.fetchById(id));
    }

    @DeleteMapping(path = "removeStudent")
    public ResponseEntity<Boolean> delStudent(@RequestBody StudentDto studentDto){
        logger.info("removing student...");
        return ResponseEntity.ok(studentService.removeStudent(studentDto));

    }

    @PostMapping(path = "createStudent")
    public ResponseEntity<Student> createStudent(@RequestBody StudentDto studentDto){
        logger.info("creating new student...");
        return ResponseEntity.ok(studentService.createStudent(studentDto));

    }

}
