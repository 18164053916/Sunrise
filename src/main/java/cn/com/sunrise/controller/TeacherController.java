package cn.com.sunrise.controller;

import cn.com.sunrise.mo.Teacher;
import cn.com.sunrise.service.ITeacherService;
import cn.com.sunrise.utils.MessageReturn;
import cn.com.sunrise.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @PutMapping("/saveOrUpdateTeacher")
    public MessageReturn saveOrUpdateTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveOrUpdateTeacher(teacher);
    }

    @PostMapping("/getTeacherList")
    public List<Teacher> getTeacherList(@RequestBody Pager pager){
        return teacherService.getTeacherList(pager);
    }


    @DeleteMapping("/deleteTeacher/{id}")
    public MessageReturn deleteClass(@PathVariable String id) {
        return teacherService.deleteTeacher(id);
    }

}
