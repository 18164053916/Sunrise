package cn.com.sunrise.service;

import cn.com.sunrise.mo.Student;
import cn.com.sunrise.utils.MessageReturn;
import cn.com.sunrise.utils.Pager;

import java.util.List;

public interface IStudentService {


    public List<Student> getStudentList(Pager pager);

    MessageReturn saveOrUpdateStudent(Student student);

    MessageReturn deleteStudent(String id);



}
