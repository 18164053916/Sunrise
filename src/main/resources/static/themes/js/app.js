var ticketCenter = angular.module('ticketCenterApp',[]);

ticketCenter.run(function ($rootScope,$location,$timeout) {
    $rootScope.siderbarActive="";

    $rootScope.absUrl=$location.absUrl();

    $rootScope.studentType={
        "0":"幼儿园","1":"小学"
    };

    $rootScope.classType={
        "0":"幼儿园","1":"小学"
    };

    $rootScope.studentGenger={
        "0":"男","1":"女"
    }

    $rootScope.teacherType={
        "0":"外教","1":"中教"
    }

    $rootScope.timeSelect={
        "0":"日","1":"月"
    }

})


ticketCenter.config(['$locationProvider', function($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
}])





