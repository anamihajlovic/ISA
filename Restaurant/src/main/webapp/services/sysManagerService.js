var sysManagerServices = angular.module('sysManager.services', ['ngResource']);

sysManagerServices.service('sysManagerService',['$http', function($http) {
	
	this.checkRights = function(){
		return $http.get("/sysManager/checkRights");
	}
	
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
	this.updateSysManager = function(systemManager) {
		alert("sysService " + systemManager)
		alert("id je " + systemManager.id)		
		return $http.put("/sysManager/"+systemManager.id, systemManager);
	}
	this.saveSysManager = function(sysManager) {
		alert("sysService " + sysManager)
		return $http.post("/sysManager/newSysManager", sysManager);
	}
	this.findAllRestaurantManagers = function(){
		//alert("uslo i u service")
		return $http.get("/sysManager/ResManagers");
	}
	
	this.findAllRestaurants = function(){
		//alert("uslo i u service")
		return $http.get("/sysManager/Restaurants");
	}
	
	
}]); 