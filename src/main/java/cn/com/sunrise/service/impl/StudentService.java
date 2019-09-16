package cn.com.sunrise.service.impl;

import cn.com.sunrise.controller.StudentController;
import cn.com.sunrise.dao.IStudentDAO;
import cn.com.sunrise.mo.Student;
import cn.com.sunrise.service.IStudentService;
import cn.com.sunrise.utils.MessageReturn;
import cn.com.sunrise.utils.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService implements IStudentService {

    private  static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private IStudentDAO studentDAO;


    @Override
    public List<Student> getStudentList(Pager pager) {
        logger.info("进入StudentService的getStudentList()-------------");
        List<Student> list = null;
        try {
            list=studentDAO.selectStudentsByCond(pager);
        }catch (Exception e){
            logger.error(new MessageReturn().setErrmsg(e.getMessage()).toString());
        }
        logger.info("退出StudentService的getStudentList()-------------");
        return list;
    }

    @Override
    public MessageReturn saveOrUpdateStudent(Student student) {
        logger.info("进入新增学员的方法----addStudent");
        MessageReturn msg = new MessageReturn();
        try {
            if(student.getId().equals("1")){
                student.setId(UUID.randomUUID().toString());
                studentDAO.addStudent(student);
            }else{
                studentDAO.updateStudent(student);
            }
            msg.setStatus(0);
        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(-500).setErrmsg(e.getMessage());
            logger.info(msg.toString());
            if(e.getMessage()!=null&&e.getMessage().contains("unique_name")){
                msg.setErrmsg("学生姓名重复！");
            }
        }
        return msg;
    }

    @Override
    public MessageReturn deleteStudent(String id) {
        logger.info("进入StudentService的deleteStudent()-------------");
        MessageReturn msg = new MessageReturn();
        try {
            studentDAO.deleteStudent(id);
        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(-500).setErrmsg(e.getMessage());
            logger.error(msg.toString());
        }
        logger.info("退出StudentService的deleteStudent()-------------");
        return msg;
    }
}
