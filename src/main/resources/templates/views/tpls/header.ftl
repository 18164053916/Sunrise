<!---头部--->
<div id="Top" class="Top" ng-app="ticketCenterApp" ng-controller="studentCtrl">

    <div class="Toolbar1">
        <div class="CentreBox">
            <div class="Logo">Sunrise考勤系统</div>
            <div class="Menu" id="left-panel" ng-controller="navigatorCtrl">
                <ul class="List1">
                    <li><a ng-click="active('${base}/index/toIndex/student','nav01')"  href="" class="nav01" ng-class="{active:siderbarActive=='nav01'}">学员管理</a></li>
                    <li><a ng-click="active('${base}/index/toIndex/class','nav02')"  href="" class="nav02" ng-class="{active:siderbarActive=='nav02'}">班级管理</a></li>
                    <li><a ng-click="active('${base}/index/toIndex/teacher','nav03')"  href="" class="nav03" ng-class="{active:siderbarActive=='nav03'}">教师管理</a></li>
                    <li><a ng-click="active('${base}/index/toIndex/attendance','nav04')"  href="" class="nav04" ng-class="{active:siderbarActive=='nav04'}">考勤管理</a></li>
                </ul>
            </div>
        </div>
    </div>


</div>




