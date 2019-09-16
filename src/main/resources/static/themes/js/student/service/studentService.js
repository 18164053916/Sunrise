angular.module('ticketCenterApp')
.factory("studentService",function ($http,Utils) {
    var studentService={};

    studentService.addStudent =function(studentData){
        return Utils.postDataSync(baseUrl+"/student/saveOrUpdateStudent",studentData,"PUT");
    }

    studentService.getStudentList=function(pager){
        return Utils.postDataSync(baseUrl+"/student/getStudentList",pager,"POST");
    }

    studentService.deleteStudentById=function(id){
        return Utils.postDataSync(baseUrl+"/student/deleteStudent/"+id,'',"DELETE");
    }

    return studentService;
})