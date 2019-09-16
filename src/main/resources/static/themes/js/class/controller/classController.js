angular.module('ticketCenterApp')
.controller('classCtrl',function($scope,$rootScope,classService,Utils){

    var pageCount=0;

    var total_class_size=0;

    //左侧active 样式
    $rootScope.siderbarActive="nav02";

    $scope.showAddClassModal=false;

    $scope.showDelClassAlert=false;

    $scope.addOneClass=function () {
       $scope.$$childHead.classVO={};
        $scope.$$childHead.classVO.id="1";
        $scope.showAddClassModal=true;
    }



    $scope.pager={
        pageSize:5,
        current:1
    }

    $scope.modifyClass=function (classVO) {
        $scope.$$childHead.classVO=classVO;
        $scope.showAddClassModal=true;
    }

    $scope.deleteClass=function (classVO) {
        $scope.$$childHead.classVO=classVO;
        $scope.showDelClassAlert=true;
    }

    $scope.deleteAlert4Class=function (id) {
        classService.deleteClassVO(id).then(
            function (resp) {
                if(resp.status=='0'){
                    $scope.showDelClassAlert=false;
                    $scope.load($scope.pager);
                }
            },
            function (resp) {
                console.error("load:"+JSON.stringify(resp));
            }
        )
    }


    /*搜索*/
    $scope.getClassesByCond=function () {
        $scope.pager.current=1;
        $scope.load($scope.pager);
    }


    /*重置*/
    $scope.resetClass=function () {
        $scope.pager.current=1;
        $scope.pager.filter.name="";
        $scope.pager.filter.level="";
    }


    $scope.load=function (pager) {
        classService.getClassList(pager).then(function (resp) {
            $scope.classList =resp;
            pageCount=0;
            total_class_size=0;
            if(resp.length>0){
                total_class_size = resp[0].total;
                pageCount=Math.ceil(total_class_size/$scope.pager.pageSize);
            }
            Utils.initPager(pager.current,pageCount,$scope,total_class_size,"classPage");
        });
    }

    $scope.load($scope.pager);



})
.controller("addClassCtrl",function ($scope,classService) {
    $scope.submit=function () {
        console.log("addClass:"+JSON.stringify($scope.classVO))
        classService.addClassVO($scope.classVO).then(
            function (resp) {
                console.log("load:"+JSON.stringify(resp));
                if(resp.status=='0'){
                    $scope.$parent.showAddClassModal=false;
                    $scope.load($scope.$parent.pager);
                }else{
                    alert(resp.errmsg);
                }
            }
        );
    }
})

