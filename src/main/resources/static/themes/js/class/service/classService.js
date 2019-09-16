angular.module('ticketCenterApp')
.factory("classService",function ($http,Utils) {
    var classService={};

    classService.addClassVO =function(userData){
        return Utils.postDataSync(baseUrl+"/class/saveOrUpdateClass",userData,"PUT");
    }

    classService.getClassList=function(pager){
        return Utils.postDataSync(baseUrl+"/class/getClassList",pager,"POST");
    }

    classService.deleteClassVO=function(id){
        return Utils.postDataSync(baseUrl+"/class/deleteClass/"+id,'',"DELETE");
    }

    return classService;
})