<#-- 引入布局指令的命名空间 -->
<#import "application.ftl" as appMain>

<script type="text/javascript">
    <#if RequestParameters["status"]??>
        var status = '${RequestParameters["status"]}';
    </#if>



</script>

<#-- 调用布局指令 -->
<@appMain.layout>

<!---右侧列表页--->
<div class="right_main" ng-controller="attendanceCtrl">

    <#include "../saveOrUpdate/update_attendance.ftl">


    <#include "../delete/attendance_delete_alert.ftl">

    <!--右侧主要内容区域-->
    <div class="main_in">
        <div class="table_top"><!---列表操作栏-->
            <ul class="action_bar f_left">
                <li>
                    <a href="javascript:;" ng-click="addAttendance()"  class="additem"  title="添加考勤记录"><img src="${request.contextPath}/themes/imges/icon09.png"><span>添加考勤记录</span></a>
                </li>
            </ul>
            <div class="table_input_box"><!--搜索-->
                <ul class="search_box">
                    <li>
                        <label>出勤日期</label>
                        <input ng-model="pager.filter.startDate" type="text" class="datepuicker_input from-date" name="from">
                    </li>
                    <li>
                        <label>班级类型</label>
                        <select name="classLevel" ng-model="pager.filter.level" ng-change="changeClassLevel()">
                            <option ng-repeat="(key,value) in studentType" value="{{key}}">{{value}}</option>
                        </select>
                    </li>
                    <li>
                        <label>班级名称</label>
                        <select name="classVOName" ng-model="pager.filter.classId" >
                            <option ng-repeat="u in classList" value="{{u.id}}">{{u.name}}</option>
                        </select>
                    </li>
                    <li>
                        <label>学员姓名</label>
                        <select name="studentId" ng-model="pager.filter.studentId" >
                            <option ng-repeat="u in studentNameList" value="{{u.id}}">{{u.name}}</option>
                        </select>
                    </li>
                    <li>
                        <label>教师</label>
                        <select name="teacherName" ng-model="pager.filter.teacherId" >
                            <option ng-repeat="u in teacherList" value="{{u.id}}">{{u.name}}</option>
                        </select>
                    </li>
                    <li>
                        <input ng-click="getAttendanceInfo()" name="提交" type="submit" value="搜索" class="search_btn">
                        <input ng-click="resetAttendanceInfo()" name="重置" type="submit" value="重置" class="reset_btn">
                    </li>
                </ul>
            </div>

        </div>
        <!--列表-->
        <div class="table_box">
            <div class="table_in  clearfix">
                <table cellpadding="0" cellspacing="0" class="fx_table_con">
                    <thead>
                    <tr class="table_head">
                        <th title="序号">序号</th>
                        <th title="考勤时间">考勤时间</th>
                        <th title="学员姓名">学员姓名</th>
                        <th title="班级类型">班级类型</th>
                        <th title="班级名称">班级名称</th>
                        <th title="教师名称">教师名称</th>
                        <th title="操作">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="u in attendanceResultList">
                        <td>{{$index+1}}</td>
                        <td>{{::u.startTime| date:'yyyy-MM-dd'}}</td>
                        <td>{{::u.studentName}}</td>
                        <td>{{studentType[u.classLevel]}}</td>
                        <td>{{::u.className}}</td>
                        <td>{{::u.teacherName}}</td>
                        <td>
                            <div class="table_btn">
                                <a ng-click="modifyAttendance(u)"  class="bnt blue">编辑</a>
                                <a ng-click="deleteAttendance(u)"  class="bnt red delet_user">删除</a>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!---翻页--->
            <div class="tcdPageCode pa" id="attendancePage">
            </div>
        </div>

    </div>
</div>

</@appMain.layout>