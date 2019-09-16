angular.module('ticketCenterApp')
.controller('patientCtrl',function($scope,$rootScope,patientService,Utils){

    var pageCount=0;

    var total_patient_size=0;

    //左侧active 样式
    $rootScope.siderbarActive="nav03";

    $scope.showAddPatientModal=false;

    $scope.showDelPatientAlert=false;

    $scope.addPatient=function () {
       $scope.$$childHead.patient={};
        $scope.$$childHead.patient.id=-1;
        $scope.showAddPatientModal=true;
    }



    $scope.pager={
        pageSize:15,
        current:1
    }

    $scope.modifyPatient=function (patient) {
        $scope.$$childHead.patient=patient;
        $scope.showAddPatientModal=true;
    }

    $scope.deletePatient=function (patient) {
        console.log("delete:"+JSON.stringify(patient));
        $scope.$$childHead.patient=patient;
        $scope.showDelPatientAlert=true;
    }

    $scope.delete4PatientTips=function (id) {
        patientService.deletePatient(id).then(
            function (resp) {
                if(resp.status=='0'){
                    $scope.showDelPatientAlert=false;
                    $scope.load($scope.pager);
                }
            },
            function (resp) {
                console.error("load:"+JSON.stringify(resp));
            }
        )
    }


    /*搜索*/
    $scope.search=function () {
        $scope.pager.current=1;
        $scope.load($scope.pager);
    }


    $scope.load=function (pager) {
        console.log(2222);
        patientService.getPatientList(pager).then(function (resp) {
            $scope.patientList =resp;
            var pageCount=0;
            if(resp.length>0){
                total_patient_size = resp[0].total;
                pageCount=Math.ceil(total_patient_size/$scope.pager.pageSize);
            }
            Utils.initPager(pager.current,pageCount,$scope,total_patient_size);
        });
    }

    $scope.load($scope.pager);



})
.controller("addPatientCtrl",function ($scope,patientService) {

    $scope.submit=function () {
        console.log("addPatient:"+JSON.stringify($scope.patient))
        patientService.addPatient($scope.patient).then(
            function (resp) {
                console.log("load:"+JSON.stringify(resp));
                if(resp.status=='0'){
                    $scope.$parent.showAddPatientModal=false;
                    $scope.load($scope.$parent.pager);
                }else{
                    alert(resp.errmsg);
                }
            },
            function (resp) {
                console.error("load:"+JSON.stringify(resp));
            }
        );
    }
}).controller("navigatorCtrl",function ($scope,$rootScope) {
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
