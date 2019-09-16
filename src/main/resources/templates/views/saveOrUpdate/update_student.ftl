<!--添加用户-->
<div class="addxx_box add_user" ng-class="{'is-visible':showAddStudentModal}" ng-controller="addStudentCtrl">
    <form name="studentForm" class="addxx_box_container">
        <div class="tc_top" ng-if="student.id==1">添加学员</div>
        <div class="tc_top" ng-if="student.id!=1">修改学员</div>
        <a class="close_bnt close_addxx cancel" ng-click="$parent.showAddStudentModal=false" ></a><!--关闭按钮-->
        <div class="tc_main"><!---弹出页面main--->
            <ul class="input_list">
                <li>
                    <label>学员姓名</label>
                    <input ng-model="student.id" hidden>
                    <input ng-model="student.name" name="studentName" type="text" class="item_input"
                           ng-maxlength="10"   placeholder="必填" required>
                    <span  style="color:red" ng-show="studentForm.studentName.$error.required">学员姓名是必须的。</span>
                    <span  style="color:red" ng-show="studentForm.studentName.$dirty && studentForm.studentName.$invalid">长度最多为10</span>
                </li>

                <li>
                    <label>学员性别</label>
                    <select name="gender" ng-model="student.gender" class="item_input" required>
                        <option ng-repeat="(key,value) in studentGenger" value="{{key}}">{{value}}</option>
                    </select>
                    <span  style="color:red" ng-show="studentForm.gender.$error.required">学员性别是必须的。</span>
                </li>

                <li>
                    <label>学员类型</label>
                    <select name="level" ng-model="student.level" class="item_input" required>
                        <option ng-repeat="(key,value) in studentType" value="{{key}}">{{value}}</option>
                    </select>
                    <span  style="color:red" ng-show="studentForm.level.$error.required">学员类型是必须的。</span>
                </li>


                <li>
                    <label>学员年龄</label>
                    <input ng-model="student.age" name="age" type="number" ng-maxlength="3"
                           class="item_input" placeholder="必填" required >
                    <span  style="color:red" ng-show="studentForm.age.$error.required">年龄是必须的。</span>
                    <span  style="color:red" ng-show="studentForm.age.$dirty && studentForm.age.$invalid">请输入最多三位数字</span>
                </li>

                <li>
                    <label>备注</label>
                     <textarea cols="" ng-model="student.info" ng-maxlength="500" name="info"
                               class="text_input w500 h60" rows=""></textarea>
                </li>
            </ul>
        </div>
        <div class="tc_bottom"><!---弹出页面bottom--->
            <div class="tb_btnbox">
                <input  ng-disabled="studentForm.$invalid" ng-click="submit()" name="提交" type="submit" class="tbnt blue f_left dete" value="确定">
                <input ng-click="$parent.showAddStudentModal=false" name="取消" type="reset" class="tbnt black f_right cancel" value="取消">
            </div>
        </div>
    </form>
</div>