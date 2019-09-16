package cn.com.sunrise.dao;

import cn.com.sunrise.mo.Teacher;
import cn.com.sunrise.utils.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITeacherDAO {

    void saveTeacher(@Param("teacher") Teacher teacher);

    void updateTeacher(@Param("teacher") Teacher teacher);

    List<Teacher> getTeacherList(@Param("pager") Pager pager);

    void deleteTeacher(String id);


}
