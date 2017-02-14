var commonServices = angular.module('common.services', ['ngResource']);

commonServices.service('commonService',['$http', function($http) {
	
	this.login = function(user) {
		return $http.post("/users/login", user);
	}
}]); 