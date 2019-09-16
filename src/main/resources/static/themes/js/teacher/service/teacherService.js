angular.module('ticketCenterApp')
.factory("teacherService",function ($http,Utils) {
    var teacherService={};

    teacherService.addTeacher =function(teacherData){
        return Utils.postDataSync(baseUrl+"/teacher/saveOrUpdateTeacher",teacherData,"PUT");
    }

    teacherService.getTeacherList=function(pager){
        return Utils.postDataSync(baseUrl+"/teacher/getTeacherList",pager,"POST");
    }

    teacherService.deleteTeacherById=function(id){
        return Utils.postDataSync(baseUrl+"/teacher/deleteTeacher/"+id,'',"DELETE");
    }

    return teacherService;
})