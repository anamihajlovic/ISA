var resManagerServices = angular.module('resManager.services', ['ngResource']);

resManagerServices.service('resManagerService',['$http', function($http) {	
	
	this.checkRights = function(){
		return $http.get("/resManager/checkRights");
	}
	this.saveWaiter = function(employee) {
		return $http.post("/resManager/newWaiter", employee);
	}
	this.saveCook = function(employee) {	
		return $http.post("/resManager/newCook", employee);
	}
	this.saveBartender = function(employee) {
		return $http.post("/resManager/newBartender", employee);
	}
	this.saveBidder = function(bidder) {
		return $http.post("/resManager/newBidder", bidder);
	}
	this.findAllWaiters= function(){
		return $http.get("/resManager/Waiters");
	}
	this.findAllCooks = function(){
		return $http.get("/resManager/Cooks");
	}
	this.findAllBartenders= function(){
		return $http.get("/resManager/Bartenders");
	}
	this.findAllBidders = function(){
		return $http.get("/resManager/Bidders");
	}
	this.buttonDeleteWaiter = function(event){
		//alert(event.target.id)
	return $http.delete("/resManager/deleteWaiter/"+event.target.id);
	}
	this.buttonDeleteCook = function(event){
		//alert(event.target.id)
	return $http.delete("/resManager/deleteCook/"+event.target.id);
	}
	this.buttonDeleteBartender = function(event){
		//alert(event.target.id)
	return $http.delete("/resManager/deleteBartender/"+event.target.id);
	}
	this.buttonDeleteBidder = function(event){
		//alert(event.target.id)
	return $http.delete("/resManager/deleteBidder/"+event.target.id);
	}
	this.updateResManager = function(restaurantManager) {		
		return $http.put("/resManager/"+restaurantManager.id, restaurantManager);
	}
}]); 