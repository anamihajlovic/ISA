var sysManagerServices = angular.module('sysManager.services', ['ngResource']);

sysManagerServices.service('sysManagerService',['$http', function($http) {
	
	this.saveResManager = function(resManager) {
		alert("sysService " + resManager)
		return $http.post("/sysManager/newResManager", resManager);
	}
}]); 