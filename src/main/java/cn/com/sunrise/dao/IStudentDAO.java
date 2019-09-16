package cn.com.sunrise.dao;

import cn.com.sunrise.mo.Attendance;
import cn.com.sunrise.mo.Student;
import cn.com.sunrise.utils.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IStudentDAO {

    List<Student> selectStudentsByCond(@Param("pager") Pager pager);

    void addStudent(@Param("student") Student student);

    void updateStudent(@Param("student") Student student);

    void deleteStudent(String id);


}
