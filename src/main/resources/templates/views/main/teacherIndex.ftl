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
<div class="right_main" ng-controller="teacherCtrl">

    <#include "../saveOrUpdate/update_teacher.ftl">


    <#include "../delete/teacher_delete_alert.ftl">

    <!--右侧主要内容区域-->
    <div class="main_in">
        <div class="table_top"><!---列表操作栏-->
            <ul class="action_bar f_left">
                <li>
                    <a href="javascript:;" ng-click="addTeacher()"  class="additem"  title="添加教师"><img src="${request.contextPath}/themes/imges/icon09.png"><span>添加教师</span></a>
                </li>
            </ul>
            <div class="table_input_box"><!--搜索-->
                <ul class="search_box">
                    <li>
                        <label>教师名称</label>
                        <input ng-model="pager.filter.name" type="text" class="input_search">
                    </li>
                    <li>
                        <label>教师类型</label>
                         <select name="level" ng-model="pager.filter.teacherType" >
                            <option ng-repeat="(key,value) in teacherType" value="{{key}}">{{value}}</option>
                        </select>
                    </li>
                    <li>
                        <input ng-click="getTeachers()" name="提交" type="submit" value="搜索" class="search_btn">
                        <input ng-click="resetTeacher()" name="重置" type="submit" value="重置" class="reset_btn">
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
                        <th title="教师名称">教师名称</th>
                        <th title="教师类型">教师类型</th>
                        <th title="教师手机">教师手机</th>
                        <th title="操作">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="u in teacherList">
                        <td>{{$index+1}}</td>
                        <td>{{::u.name}}</td>
                        <td>{{teacherType[u.type]}}</td>
                        <td>{{::u.mobile}}</td>
                        <td>
                            <div class="table_btn">
                                <a ng-click="modifyTeacher(u)"  class="bnt blue">编辑</a>
                                <a ng-click="deleteTeacher(u)"  class="bnt red delet_user">删除</a>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!---翻页--->
            <div class="tcdPageCode pa" id="teacherPage">
            </div>
        </div>

    </div>
</div>

</@appMain.layout>