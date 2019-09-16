package cn.com.sunrise.mo;

import java.util.List;

public class AttendancePageInfo {

    private List<Attendance> attendanceList;

    private AttendanceRelation attendanceRelation;

    private List<Teacher> teacherList;

    private List<ClassVO> classList;

    private List<Student> studentList;

    public AttendanceRelation getAttendanceRelation() {
        return attendanceRelation;
    }

    public void setAttendanceRelation(AttendanceRelation attendanceRelation) {
        this.attendanceRelation = attendanceRelation;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public List<ClassVO> getClassList() {
        return classList;
    }

    public void setClassList(List<ClassVO> classList) {
        this.classList = classList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }
}
