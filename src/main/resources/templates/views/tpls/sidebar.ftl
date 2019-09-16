<#assign base=request.contextPath />
<a id="left-panel-link" href="#left-panel" class="left_panel_btn"></a>
<!---左侧导航--->
<!-- 管理员 -->
    <div class="left_navbox" id="left-panel">
        <ul class="topnav topnav-block">
            <li><a ng-click="active('${base}/inventory/index','nav01')"  href="" class="nav01" ng-class="{active:siderbarActive=='nav01'}">库存管理</a></li><!---选中的导航加active样式--->
            <li><a href="" ng-click="active('${base}/finance/index','nav02')" ng-class="{active:siderbarActive=='nav02'}" class="nav02">收入支出管理</a></li>
        </ul>
    </div>

