package cn.com.sunrise.controller;

import cn.com.sunrise.mo.Student;
import cn.com.sunrise.service.IStudentService;
import cn.com.sunrise.utils.MessageReturn;
import cn.com.sunrise.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;


    @PostMapping("/getStudentList")
    public List<Student> getStudentList(@RequestBody Pager pager){
        return studentService.getStudentList(pager);
    }


    @PutMapping("/saveOrUpdateStudent")
    public MessageReturn addStudent(@RequestBody Student student) {
        return studentService.saveOrUpdateStudent(student);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public MessageReturn deleteStudent(@PathVariable String id) {
        return studentService.deleteStudent(id);
    }



}
