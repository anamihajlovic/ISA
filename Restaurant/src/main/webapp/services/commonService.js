var commonServices = angular.module('common.services', ['ngResource']);

commonServices.service('commonService',['$http', function($http) {
	
	this.login = function(user) {
		return $http.post("/users/login", user);
	}
	
	this.getActiveUser = function() {			
		return $http.get("/users/getActiveUser");
	}
	
	
	/*this.logout = function() {
		alert("service logout");
		return $http.get("/users/logout");
	}*/
	
}]); 