package cn.com.sunrise.service;

import cn.com.sunrise.mo.Teacher;
import cn.com.sunrise.utils.MessageReturn;
import cn.com.sunrise.utils.Pager;

import java.util.List;

public interface ITeacherService {

    public MessageReturn saveOrUpdateTeacher(Teacher teacher);

    public List<Teacher> getTeacherList(Pager pager);

    public MessageReturn deleteTeacher(String id);




}
