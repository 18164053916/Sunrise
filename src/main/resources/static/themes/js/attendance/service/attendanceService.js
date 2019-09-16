angular.module('ticketCenterApp')
.factory("attendanceService",function ($http,Utils) {
    var attendanceService={};

    attendanceService.getQueryConditionList=function(pager){
        return Utils.postDataSync(baseUrl+"/attendance/getQueryConditionList",pager,"POST");
    }

    attendanceService.getCascadeClassAndStudent=function(pager){
        return Utils.postDataSync(baseUrl+"/attendance/getCascadeClassAndStudent",pager,"POST");
    }

    attendanceService.addAttendance =function(_data){
        return Utils.postDataSync(baseUrl+"/attendance/saveOrUpdateAttendance",_data,"PUT");
    }

    attendanceService.deleteAttendanceById=function(id){
        return Utils.postDataSync(baseUrl+"/attendance/deleteAttendanceById/"+id,'',"DELETE");
    }


    return attendanceService;
})