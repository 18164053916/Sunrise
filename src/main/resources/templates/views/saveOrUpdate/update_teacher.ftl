<!--添加用户-->
<div class="addxx_box add_user" ng-class="{'is-visible':showAddTeacherModal}" ng-controller="addTeacherCtrl">
    <form name="teacherForm" class="addxx_box_container">
        <div class="tc_top" ng-if="teacher.id=='1'">添加班级</div>
        <div class="tc_top" ng-if="teacher.id!='1'">修改班级</div>
<a class="close_bnt close_addxx cancel" ng-click="$parent.showAddTeacherModal=false" ></a><!--关闭按钮-->
        <div class="tc_main"><!---弹出页面main--->
            <ul class="input_list">
                <li>
                    <label>教师名称</label>
                    <input ng-model="teacher.id" hidden>
                    <input ng-model="teacher.name" name="teacherName" type="text" class="item_input"
                           ng-maxlength="10"   placeholder="必填" required>
                    <span  style="color:red" ng-show="teacherForm.teacherName.$error.required">教师名称是必须的。</span>
                    <span  style="color:red" ng-show="teacherForm.teacherName.$dirty && teacherForm.teacherName.$invalid">长度最多为10</span>
                </li>

                <li>
                    <label>教师类型</label>
                    <select name="type" ng-model="teacher.type" class="item_input" required>
                        <option ng-repeat="(key,value) in teacherType" value="{{key}}">{{value}}</option>
                    </select>
                    <span  style="color:red" ng-show="teacherForm.type.$error.required">教师类型是必须的。</span>
                </li>

                <li>
                    <label>教师手机</label>
                    <input ng-model="teacher.mobile" name="mobile" type="text" ng-maxlength="13"
                           class="item_input" >
                    <span  style="color:red" ng-show="teacherForm.mobile.$dirty && teacherForm.mobile.$invalid">请输入最多13位数字</span>
                </li>

                <li>
                    <label>备注</label>
                     <textarea cols="" ng-model="teacher.info" ng-maxlength="500" name="info"
                               class="text_input w500 h60" rows=""></textarea>
                </li>
            </ul>
        </div>
        <div class="tc_bottom"><!---弹出页面bottom--->
            <div class="tb_btnbox">
                <input  ng-disabled="teacherForm.$invalid" ng-click="submitTeacher()" name="提交" type="submit" class="tbnt blue f_left dete" value="确定">
                <input ng-click="$parent.showAddTeacherModal=false" name="取消" type="reset" class="tbnt black f_right cancel" value="取消">
            </div>
        </div>
    </form>
</div>