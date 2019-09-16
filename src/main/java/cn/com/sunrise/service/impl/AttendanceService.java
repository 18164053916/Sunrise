package cn.com.sunrise.service.impl;

import cn.com.sunrise.dao.IAttendanceDAO;
import cn.com.sunrise.mo.*;
import cn.com.sunrise.service.IAttendanceService;
import cn.com.sunrise.service.IClassService;
import cn.com.sunrise.service.IStudentService;
import cn.com.sunrise.service.ITeacherService;
import cn.com.sunrise.utils.MessageReturn;
import cn.com.sunrise.utils.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AttendanceService implements IAttendanceService {
    @Autowired
    private IClassService classService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IAttendanceDAO attendanceDAO;

    private  static final Logger logger = LoggerFactory.getLogger(AttendanceService.class);

    @Override
    public AttendancePageInfo getQueryConditionList(Pager pager) {
        if(pager.getFilter().containsKey("addClasslevel")){
            if(StringUtils.hasLength(pager.getFilter().get("addClasslevel"))){
                pager.getFilter().put("level",pager.getFilter().get("addClasslevel"));
            }
        }
        AttendancePageInfo attendancePageInfo=new AttendancePageInfo();
        Pager classPager=new Pager();
        BeanUtils.copyProperties(pager,classPager);
        classPager.setPageSize(0);
        classPager.setCurrent(0);
        List<ClassVO> classVOList=classService.getClassList(classPager);
        attendancePageInfo.setClassList(classVOList);
        List<Teacher> teacherList=teacherService.getTeacherList(pager);
        attendancePageInfo.setTeacherList(teacherList);
        Pager studentPager=new Pager();
        BeanUtils.copyProperties(pager,studentPager);
        studentPager.setPageSize(0);
        studentPager.setCurrent(0);
        List<Student> studentList=studentService.getStudentList(studentPager);
        attendancePageInfo.setStudentList(studentList);
        Map<String,String> studentNameMap=new HashMap<>();
        studentList.forEach(student -> studentNameMap.put(student.getId(),student.getName()));
        boolean isStudentCheck=false;
        if(pager.getFilter().containsKey("studentId")){
            if(StringUtils.hasLength(pager.getFilter().get("studentId"))){
                isStudentCheck=true;
            }
        }
        List<Attendance> attendanceList=attendanceDAO.selectSingleAttendance(pager,isStudentCheck);
        for(Attendance attendance:attendanceList){
            String studentId=attendance.getStudentId();
            if(StringUtils.hasLength(studentId)){
                 StringBuilder studentNameBuilder=new StringBuilder();
                 String [] idArray=studentId.split(",");
                 for(int i=0;i<idArray.length;i++){
                     String studentSingleId=idArray[i];
                     String studentSingleName=studentNameMap.get(studentSingleId);
                     studentNameBuilder.append(studentSingleName);
                     if(i!=idArray.length-1){
                         studentNameBuilder.append(",");
                     }
                 }
                attendance.setStudentName(studentNameBuilder.toString());
            }
        }
        attendancePageInfo.setAttendanceList(attendanceList);


        return attendancePageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MessageReturn saveOrUpdateStudent(Attendance attendance) {
        logger.info("进入新增考勤的方法----saveOrUpdateStudent");
        MessageReturn msg = new MessageReturn();
        try {
            if(attendance.getId().equals("1")){
                attendance.setId(UUID.randomUUID().toString());
                attendanceDAO.saveAttendance(attendance);
                AttendanceRelation attendanceRelation=new AttendanceRelation();
                attendanceRelation.setId(UUID.randomUUID().toString());
                attendanceRelation.setAttendanceId(attendance.getId());
                attendanceRelation.setStudentList(attendance.getStudentList());
                attendanceDAO.saveAttendanceRelation(attendanceRelation);
            }else{
                attendanceDAO.deleteAttendanceById(attendance.getId(),false);
                attendanceDAO.updateAttendance(attendance);
                AttendanceRelation attendanceRelation=new AttendanceRelation();
                attendanceRelation.setId(UUID.randomUUID().toString());
                attendanceRelation.setAttendanceId(attendance.getId());
                attendanceRelation.setStudentList(attendance.getStudentList());
                attendanceDAO.saveAttendanceRelation(attendanceRelation);
            }
            msg.setStatus(0);
        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(-500).setErrmsg(e.getMessage());
            logger.info(msg.toString());
        }
        return msg;
    }

    @Override
    public AttendancePageInfo getCascadeClassAndStudent(Pager pager) {
        pager.setCurrent(0);
        pager.setPageSize(0);
        AttendancePageInfo attendancePageInfo=new AttendancePageInfo();
        List<ClassVO> classVOList=classService.getClassList(pager);
        attendancePageInfo.setClassList(classVOList);
        List<Student> studentList=studentService.getStudentList(pager);
        attendancePageInfo.setStudentList(studentList);
        return attendancePageInfo;
    }

    @Override
    public MessageReturn deleteAttendanceById(String id) {
        MessageReturn msg = new MessageReturn();
        try {
            attendanceDAO.deleteAttendanceById(id,true);
        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(-500).setErrmsg(e.getMessage());
            logger.error(msg.toString());
        }
        return msg;
    }
}
