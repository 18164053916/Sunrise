angular.module('ticketCenterApp')
.factory("patientService",function ($http,Utils) {
    var patientService={};


    patientService.addPatient =function(userData){
        return Utils.postDataSync(baseUrl+"/patient/api/v1/addPatient",userData,"PUT");
    }


    patientService.getPatientList=function(pager){
        return Utils.postDataSync(baseUrl+"/patient/api/v1/patient/list/page",pager,"POST");
    }

    patientService.deletePatient=function(id){
        return Utils.postDataSync(baseUrl+"/patient/api/v1/patient/"+id,'',"DELETE");
    }

    return patientService;
})