angular.module('ticketCenterApp')
.controller('studentCtrl',function($scope,$rootScope,studentService,Utils){
    var pageCount=0;
    //左侧active 样式

    var total_student_size=0;

    $rootScope.siderbarActive="nav01";

    $scope.showAddStudentModal=false;

    $scope.showDelStudentAlert=false;


    $scope.addStudent=function () {
       $scope.$$childHead.student={};
        $scope.$$childHead.student.id="1";
        $scope.showAddStudentModal=true;
    }



    $scope.pager={
        pageSize:10,
        current:1
    }

    $scope.modifyStudent=function (student) {
        $scope.$$childHead.student=student;
        $scope.showAddStudentModal=true;
    }

    $scope.deleteStudent=function (student) {
        console.log("delete:"+JSON.stringify(student));
        $scope.$$childHead.student=student;
        $scope.showDelStudentAlert=true;
    }

    $scope.deleteAlert4Student=function (id) {
        studentService.deleteStudentById(id).then(
            function (resp) {
                if(resp.status=='0'){
                    $scope.showDelStudentAlert=false;
                    $scope.load($scope.pager);
                }
            }
        )
    }


    /*搜索*/
    $scope.getStudents=function () {
        $scope.pager.current=1;
        $scope.load($scope.pager);
    }

    /*重置*/
    $scope.resetStudent=function () {
        $scope.pager.current=1;
        $scope.pager.filter.studentName="";
        $scope.pager.filter.level="";
        $scope.pager.filter.gender="";
    }


    $scope.load=function (pager) {
        studentService.getStudentList(pager).then(function (resp) {
            $scope.studentList =resp;
            pageCount=0;
            total_student_size=0;
            if(resp.length>0){
                total_student_size = resp[0].total;
                pageCount=Math.ceil(total_student_size/$scope.pager.pageSize);
            }
            Utils.initPager(pager.current,pageCount,$scope,total_student_size,"studentPage");
        });

    }

    $scope.load($scope.pager);



})
.controller("addStudentCtrl",function ($scope,studentService) {

    $scope.submit=function () {
        console.log("addStudent:"+JSON.stringify($scope.student))
        studentService.addStudent($scope.student).then(
            function (resp) {
                console.log("load:"+JSON.stringify(resp));
                if(resp.status=='0'){
                    $scope.$parent.showAddStudentModal=false;
                    $scope.load($scope.$parent.pager);
                }else{
                    alert(resp.errmsg);
                }
            }
        );
    }
})
.controller("navigatorCtrl",function ($scope,$rootScope) {
        //展开使用同一个页面浏览器不跳转。
        $scope.active=function (url,status) {
            $rootScope.siderbarActive=status;
            if($rootScope.absUrl.indexOf(url)==-1){
                window.location.href=url+"?status="+status;
            }else{
                $rootScope.$broadcast("projectLoad");
            }
        }

    })