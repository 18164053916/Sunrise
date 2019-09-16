<!--添加用户-->
<div class="addxx_box add_user" ng-class="{'is-visible':showAddAttendanceModal}" ng-controller="addAttendanceCtrl">
    <form name="attendanceForm" class="addxx_box_container">
        <div class="tc_top" ng-if="attendance.id=='1'">添加考勤</div>
        <div class="tc_top" ng-if="attendance.id!='1'">修改考勤</div>
<a class="close_bnt close_addxx cancel" ng-click="$parent.showAddAttendanceModal=false" ></a><!--关闭按钮-->
        <div class="tc_main"><!---弹出页面main--->
            <ul class="input_list">
                <li>
                    <label>考勤时间</label>
                    <input ng-model="attendance.startTime" type="text" placeholder="必填" required
                           class="datepuicker_input from-date" name="startTime">
                    <span  style="color:red" ng-show="attendanceForm.startTime.$error.required">考勤时间是必须的。</span>
                </li>
                <li>
                    <label>班级类型</label>
                    <select name="classLevel" ng-model="attendance.classLevel" class="item_input" required ng-change="changeLevelAndStu()">
                        <option ng-repeat="(key,value) in classType" value="{{key}}">{{value}}</option>
                    </select>
                    <span  style="color:red" ng-show="attendanceForm.classLevel.$error.required">班级类型是必须的。</span>
                </li>
                <li>
                    <label>班级名称</label>
                    <input ng-model="attendance.id" hidden>
                    <input ng-model="attendance.studentList" hidden>
                    <select name="classId" ng-model="attendance.classId" class="item_input" required>
                        <option ng-repeat="u in classAttendanceList" value="{{u.id}}" ng-selected="attendance.classId==u.id">{{u.name}}</option>
                    </select>
                    <span  style="color:red" ng-show="attendanceForm.classId.$error.required">班级名称是必须的。</span>
                </li>
                <li>
                    <label>教师名称</label>
                    <select name="teacherId" ng-model="attendance.teacherId" class="item_input" required>
                        <option ng-repeat="u in teacherList" value="{{u.id}}">{{u.name}}</option>
                    </select>
                    <span  style="color:red" ng-show="attendanceForm.teacherId.$error.required">教师名称是必须的。</span>
                </li>
                <li>
                    <label>学员名称</label>
                    <div  class="member_box">
                     <ul>
                         <li ng-repeat="u in studenAttendancetList">
                            <span >
                                <input ng-click="selectStudent($event,u.id)" type="checkbox" class="radio_a"
                                       value="{{u.id}}" ng-checked="checkInArray(attendance.studentList,u.id)">
                                  <label class="lable_a" for="test1">{{u.name}}</label>
                             </span>
                         </li>
                    </ul>
                    </div>
                </li>

                <li>
                    <label>备注</label>
                     <textarea cols="" ng-model="attendance.info" ng-maxlength="500" name="info"
                               class="text_input w500 h60" rows=""></textarea>
                </li>
            </ul>
        </div>
        <div class="tc_bottom"><!---弹出页面bottom--->
            <div class="tb_btnbox">
                <input  ng-disabled="attendanceForm.$invalid" ng-click="submitAttendanceForm()" name="提交" type="submit" class="tbnt blue f_left dete" value="确定">
                <input ng-click="$parent.showAddAttendanceModal=false" name="取消" type="reset" class="tbnt black f_right cancel" value="取消">
            </div>
        </div>
    </form>
</div>