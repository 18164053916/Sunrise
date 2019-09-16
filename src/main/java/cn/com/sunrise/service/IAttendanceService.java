package cn.com.sunrise.service;

import cn.com.sunrise.mo.AttendancePageInfo;
import cn.com.sunrise.mo.Attendance;
import cn.com.sunrise.utils.MessageReturn;
import cn.com.sunrise.utils.Pager;

import java.util.List;

public interface IAttendanceService {

    AttendancePageInfo getQueryConditionList(Pager pager);

    MessageReturn saveOrUpdateStudent(Attendance attendance);

    AttendancePageInfo getCascadeClassAndStudent(Pager pager);

    MessageReturn deleteAttendanceById(String id);

}
