package cn.com.sunrise.controller;

import cn.com.sunrise.mo.Attendance;
import cn.com.sunrise.mo.AttendancePageInfo;
import cn.com.sunrise.service.IAttendanceService;
import cn.com.sunrise.utils.MessageReturn;
import cn.com.sunrise.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
     private IAttendanceService attendanceService;

    @PostMapping("/getQueryConditionList")
    public AttendancePageInfo getQueryConditionList(@RequestBody Pager pager){
        AttendancePageInfo attendancePageInfo=attendanceService.getQueryConditionList(pager);
        return attendancePageInfo;
    }

    @PutMapping("/saveOrUpdateAttendance")
    public MessageReturn addStudent(@RequestBody Attendance attendance) {
        return attendanceService.saveOrUpdateStudent(attendance);
    }

    @PostMapping("/getCascadeClassAndStudent")
    public AttendancePageInfo getCascadeClassAndStudent(@RequestBody Pager pager){
       return attendanceService.getCascadeClassAndStudent(pager);
    }

    @DeleteMapping("/deleteAttendanceById/{id}")
    public MessageReturn deleteAttendanceById(@PathVariable String id) {
        return attendanceService.deleteAttendanceById(id);
    }


}
