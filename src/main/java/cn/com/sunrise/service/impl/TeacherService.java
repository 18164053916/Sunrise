package cn.com.sunrise.service.impl;

import cn.com.sunrise.dao.ITeacherDAO;
import cn.com.sunrise.mo.Teacher;
import cn.com.sunrise.service.ITeacherService;
import cn.com.sunrise.utils.MessageReturn;
import cn.com.sunrise.utils.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeacherService implements ITeacherService {


    private  static final Logger logger = LoggerFactory.getLogger(TeacherService.class);
    @Autowired
    private ITeacherDAO teacherDAO;

    @Override
    public MessageReturn saveOrUpdateTeacher(Teacher teacher) {
        logger.info("进入新增教师的方法----saveOrUpdateTeacher");
        MessageReturn msg = new MessageReturn();
        try {
            if(teacher.getId().equals("1")){
                teacher.setId(UUID.randomUUID().toString());
                teacherDAO.saveTeacher(teacher);
            }else{
                teacherDAO.updateTeacher(teacher);
            }
            msg.setStatus(0);
        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(-500).setErrmsg(e.getMessage());
            logger.info(msg.toString());
            if(e.getMessage()!=null&&e.getMessage().contains("teacher_name")){
                msg.setErrmsg("教师名称重复！");
            }
        }
        return msg;
    }


    @Override
    public List<Teacher> getTeacherList(Pager pager) {
        logger.info("进入TeacherService的getTeacherList()-------------");
        List<Teacher> list = null;
        try {
            list=teacherDAO.getTeacherList(pager);
        }catch (Exception e){
            logger.error(new MessageReturn().setErrmsg(e.getMessage()).toString());
        }
        logger.info("退出TeacherService的getTeacherList()-------------");
        return list;
    }


    @Override
    public MessageReturn deleteTeacher(String id) {
        logger.info("进入TeacherService的deleteTeacher()-------------");
        MessageReturn msg = new MessageReturn();
        try {
            teacherDAO.deleteTeacher(id);
        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(-500).setErrmsg(e.getMessage());
            logger.error(msg.toString());
        }
        logger.info("退出TeacherService的deleteTeacher()-------------");
        return msg;
    }

}
