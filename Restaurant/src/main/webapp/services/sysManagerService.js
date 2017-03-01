var sysManagerServices = angular.module('sysManager.services', ['ngResource']);

sysManagerServices.service('sysManagerService',['$http', function($http) {
	
	this.checkRights = function(){
		return $http.get("/sysManager/checkRights");
	}
	
	this.saveResManager = function(resManager) {
		//alert("sysService " + resManager)
		return $http.post("/sysManager/newResManager", resManager);
	}
	
	this.saveRestaurant = function(restaurant) {
		//alert("uslo i u service")
		return $http.post("/sysManager/newRestaurant", restaurant);
	}
	
	this.updateSysManager = function(systemManager) {
		//alert("sysService " + systemManager)
		//alert("id je " + systemManager.id)		
		return $http.put("/sysManager/"+systemManager.id, systemManager);
	}
	this.saveSysManager = function(sysManager) {
		//alert("sysService " + sysManager)
		return $http.post("/sysManager/newSysManager", sysManager);
	}
	this.findAllResManagers = function(event){
		//alert("uslo i u service "+event.target.id)
		return $http.get("/sysManager/ResManagers/"+event.target.id);
	}
	this.showAfterDelResMan = function(sifra){
		//alert("uslo i u service "+event.target.id)
		return $http.get("/sysManager/ResManagers/"+sifra.target.id);
	}
	
	this.findAllRestaurants = function(){
		//alert("uslo i u service")
		return $http.get("/sysManager/Restaurants");
	}
	
	this.buttonDeleteResManager = function(event){
		//alert(event.target.id)
	return $http.delete("/sysManager/deleteResMen/"+event.target.id);
	}
	this.buttonDeleteRestaurant = function(event){
		//alert(event.target.id)
	return $http.delete("/sysManager/deleteRestaurant/"+event.target.id);
	}

	
}]); 