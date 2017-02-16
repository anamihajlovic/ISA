var sysManagerServices = angular.module('sysManager.services', ['ngResource']);

sysManagerServices.service('sysManagerService',['$http', function($http) {
	
	this.saveResManager = function(resManager) {
		alert("sysService " + resManager)
		return $http.post("/sysManager/newResManager", resManager);
	}
	
	this.findAllFreeRestaurantManagers = function(){
		//alert("uslo i u service")
		return $http.get("/sysManager/freeResManager");
	}
	
	this.saveRestaurant = function(restaurant) {
		alert("uslo i u service")
		return $http.post("/sysManager/newRestaurant", restaurant);
	}
}]); 