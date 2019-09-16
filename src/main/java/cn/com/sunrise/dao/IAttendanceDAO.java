package cn.com.sunrise.dao;

import cn.com.sunrise.mo.Attendance;
import cn.com.sunrise.mo.AttendanceRelation;
import cn.com.sunrise.mo.Student;
import cn.com.sunrise.utils.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAttendanceDAO {

    void saveAttendance(@Param("attendance") Attendance attendance);

    void saveAttendanceRelation(@Param("attendanceRelation") AttendanceRelation attendanceRelation);

    List<Attendance> selectSingleAttendance(@Param("pager") Pager pager,boolean flag);

    void deleteAttendanceById(@Param("id") String id,@Param("flag") boolean flag);

    void updateAttendance(@Param("attendance") Attendance attendance);

}
