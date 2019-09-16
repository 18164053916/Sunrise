angular.module('ticketCenterApp')
.controller('attendanceCtrl',function($scope,$rootScope,attendanceService,Utils){
    var pageCount=0;
    //左侧active 样式

    var total_attendance_size=0;

    var pageSizeNum=5;

    $rootScope.siderbarActive="nav04";

    $scope.showAddAttendanceModal=false;

    $scope.showDelAttendanceAlert=false;

    $scope.student_selected = [] ;

    $scope.classList=[];

    $scope.teacherList=[];

    $scope.allClassList=[];

    $scope.studentNameList=[];

    $scope.allStudentNameList=[];

    $scope.addAttendance=function () {
       $scope.$$childHead.attendance={};
        $scope.$$childHead.attendance.id="1";
        $scope.showAddAttendanceModal=true;
        $scope.student_selected = [] ;
        $scope.$$childHead.studenAttendancetList=[];
        $scope.$$childHead.classAttendanceList=[];
    }



    $scope.pager={
        pageSize:pageSizeNum,
        current:1,
        filter:{}
    }

    $scope.modifyAttendance=function (attendance) {
        $scope.pager.filter["addClasslevel"]=attendance.classLevel;
        attendanceService.getQueryConditionList($scope.pager).then(function (resp) {
            $scope.$$childHead.classAttendanceList=resp.classList;
            $scope.$$childHead.studenAttendancetList=resp.studentList
        });
        var studentIds=attendance.studentId;
        var student_array=studentIds.split(",");
        attendance.studentList=student_array;
        $scope.$$childHead.attendance=attendance;
        console.log("modifyAttendance:"+JSON.stringify(attendance));
        $scope.showAddAttendanceModal=true;
    }

    $scope.deleteAttendance=function (attendance) {
        console.log("delete:"+JSON.stringify(attendance));
        $scope.$$childHead.attendance=attendance;
        $scope.showDelAttendanceAlert=true;
    }

    $scope.deleteAlertAttendance=function (id) {
        attendanceService.deleteAttendanceById(id).then(
            function (resp) {
                if(resp.status=='0'){
                    $scope.showDelAttendanceAlert=false;
                    $scope.load($scope.pager);
                }
            }
        )
    }


    /*搜索*/
    $scope.getAttendanceInfo=function () {
        $scope.pager.current=1;
        $scope.load($scope.pager);
    }

    /*重置*/
    $scope.resetAttendanceInfo=function () {
        $scope.pager.current=1;
        $scope.pager.filter.classId="";
        $scope.pager.filter.level="";
        $scope.pager.filter.studentId="";
        $scope.pager.filter.teacherId="";
        $scope.pager.filter.startDate="";
        $scope.pager.filter.addClasslevel="";
        $scope.classList=$scope.allClassList;
        $scope.studentNameList=$scope.allStudentNameList;
    }


    $scope.load=function (pager) {
        $scope.pager.filter.addClasslevel="";
        attendanceService.getQueryConditionList(pager).then(function (resp) {
            if($scope.classList.length==0){
                $scope.classList=resp.classList;
                $scope.allClassList=resp.classList;
            }
            if($scope.teacherList.length==0){
                $scope.teacherList=resp.teacherList;
            }
            if($scope.studentNameList.length==0){
                $scope.studentNameList=resp.studentList;
                $scope.allStudentNameList=resp.studentList;
            }

            $scope.attendanceResultList=resp.attendanceList;
            pageCount=0;
            total_attendance_size=0;
            if(resp.attendanceList.length>0){
                total_attendance_size = resp.attendanceList[0].total;
                pageCount=Math.ceil(total_attendance_size/$scope.pager.pageSize);
            }
            Utils.initPager(pager.current,pageCount,$scope,total_attendance_size,"attendancePage");
        });

    }

    $scope.load($scope.pager);

    $scope.changeClassLevel=function () {
        attendanceService.getCascadeClassAndStudent($scope.pager).then(function (resp) {
           $scope.classList=resp.classList;
            $scope.studentNameList=resp.studentList;
            $scope.pager.filter.classId="";
            $scope.pager.filter.studentId="";
       });
    }



})



.controller("addAttendanceCtrl",function ($scope,attendanceService) {

    $scope.submitAttendanceForm=function () {
        console.log("addAttendanceCtrl:"+JSON.stringify($scope.attendance));
        if($scope.student_selected.length==0){
            alert("必须至少选中一个学员");
            return false;
        }
        $scope.pager.filter["addClasslevel"]="";
        attendanceService.addAttendance($scope.attendance).then(
            function (resp) {
                if(resp.status=='0'){
                    $scope.$parent.showAddAttendanceModal=false;
                    $scope.$parent.pager.pageSize=5;
                    $scope.$parent.pager.current=1;
                    $scope.load($scope.$parent.pager);
                }else{
                    alert(resp.errmsg);
                }
            }
        );
    }

    $scope.changeLevelAndStu=function () {
        $scope.pager.filter["addClasslevel"]=$scope.attendance.classLevel;
        attendanceService.getQueryConditionList($scope.pager).then(function (resp) {
            $scope.classAttendanceList=resp.classList;
            $scope.studenAttendancetList=resp.studentList;
            $scope.attendance.classId="";
        });
    }

    $scope.selectStudent=function ($event,id) {
        var checkbox = $event.target ;
        if(checkbox.checked){
            $scope.student_selected.push(id);
        }else{
            var idx = $scope.student_selected.indexOf(id);
            $scope.student_selected.splice(idx,1);
        }
        $scope.attendance.studentList=$scope.student_selected;
    }

    $scope.checkInArray=function (_array,_id) {
        if(_array.indexOf(_id)>-1){
            return true;
        }
          return false;
    }


})