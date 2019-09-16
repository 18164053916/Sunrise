angular.module('ticketCenterApp')
.controller('teacherCtrl',function($scope,$rootScope,teacherService,Utils){

    var pageCount=0;

    var total_teacher_size=0;

    //左侧active 样式
    $rootScope.siderbarActive="nav03";

    $scope.showAddTeacherModal=false;

    $scope.showDelTeacherAlert=false;

    $scope.addTeacher=function () {
       $scope.$$childHead.teacher={};
        $scope.$$childHead.teacher.id="1";
        $scope.showAddTeacherModal=true;
    }



    $scope.pager={
        pageSize:5,
        current:1
    }

    $scope.modifyTeacher=function (teacher) {
        $scope.$$childHead.teacher=teacher;
        $scope.showAddTeacherModal=true;
    }

    $scope.deleteTeacher=function (teacher) {
        $scope.$$childHead.teacher=teacher;
        $scope.showDelTeacherAlert=true;
    }

    $scope.deleteAlert4Teacher=function (id) {
        teacherService.deleteTeacherById(id).then(
            function (resp) {
                if(resp.status=='0'){
                    $scope.showDelTeacherAlert=false;
                    $scope.load($scope.pager);
                }
            },
            function (resp) {
                console.error("load:"+JSON.stringify(resp));
            }
        )
    }


    /*搜索*/
    $scope.getTeachers=function () {
        $scope.pager.current=1;
        $scope.load($scope.pager);
    }


    /*重置*/
    $scope.resetTeacher=function () {
        $scope.pager.current=1;
        $scope.pager.filter.name="";
        $scope.pager.filter.teacherType="";
    }


    $scope.load=function (pager) {
        teacherService.getTeacherList(pager).then(function (resp) {
            $scope.teacherList =resp;
            pageCount=0;
            total_teacher_size=0;
            if(resp.length>0){
                total_teacher_size = resp[0].total;
                pageCount=Math.ceil(total_teacher_size/$scope.pager.pageSize);
            }
            Utils.initPager(pager.current,pageCount,$scope,total_teacher_size,"teacherPage");
        });
    }

    $scope.load($scope.pager);



})
.controller("addTeacherCtrl",function ($scope,teacherService) {
    $scope.submitTeacher=function () {
        teacherService.addTeacher($scope.teacher).then(
            function (resp) {
                if(resp.status=='0'){
                    $scope.$parent.showAddTeacherModal=false;
                    $scope.load($scope.$parent.pager);
                }else{
                    alert(resp.errmsg);
                }
            }
        );
    }
})

