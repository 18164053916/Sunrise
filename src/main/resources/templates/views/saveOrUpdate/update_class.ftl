<!--添加用户-->
<div class="addxx_box add_user" ng-class="{'is-visible':showAddClassModal}" ng-controller="addClassCtrl">
    <form name="classVOForm" class="addxx_box_container">
        <div class="tc_top" ng-if="classVO.id=='1'">添加班级</div>
        <div class="tc_top" ng-if="classVO.id!='1'">修改班级</div>
<a class="close_bnt close_addxx cancel" ng-click="$parent.showAddClassModal=false" ></a><!--关闭按钮-->
        <div class="tc_main"><!---弹出页面main--->
            <ul class="input_list">
                <li>
                    <label>班级名称</label>
                    <input ng-model="classVO.id" hidden>
                    <input ng-model="classVO.name" name="classVOName" type="text" class="item_input"
                           ng-maxlength="10"   placeholder="必填" required>
                    <span  style="color:red" ng-show="classVOForm.classVOName.$error.required">班级名称是必须的。</span>
                    <span  style="color:red" ng-show="classVOForm.classVOName.$dirty && classVOForm.classVOName.$invalid">长度最多为10</span>
                </li>

                <li>
                    <label>班级类型</label>
                    <select name="level" ng-model="classVO.level" class="item_input" required>
                        <option ng-repeat="(key,value) in studentType" value="{{key}}">{{value}}</option>
                    </select>
                    <span  style="color:red" ng-show="classVOForm.level.$error.required">班级类型是必须的。</span>
                </li>

                <li>
                    <label>备注</label>
                     <textarea cols="" ng-model="classVO.info" ng-maxlength="500" name="info"
                               class="text_input w500 h60" rows=""></textarea>
                </li>
            </ul>
        </div>
        <div class="tc_bottom"><!---弹出页面bottom--->
            <div class="tb_btnbox">
                <input  ng-disabled="classVOForm.$invalid" ng-click="submit()" name="提交" type="submit" class="tbnt blue f_left dete" value="确定">
                <input ng-click="$parent.showAddClassModal=false" name="取消" type="reset" class="tbnt black f_right cancel" value="取消">
            </div>
        </div>
    </form>
</div>