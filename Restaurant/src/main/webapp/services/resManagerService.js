var resManagerServices = angular.module('resManager.services', ['ngResource']);

resManagerServices.service('resManagerService',['$http', function($http) {	
	
	this.checkRights = function(){
		return $http.get("/resManager/checkRights");
	}
	this.saveWaiter = function(employee) {
		return $http.post("/resManager/newWaiter", employee);
	}
	this.saveFoodstuff = function(food) {
		return $http.post("/resManager/newFoodstuff", food);
	}
	this.saveDish = function(newdish) {
		return $http.post("/resManager/newDish", newdish);
	}
	this.saveDrink = function(newdrink) {
		return $http.post("/resManager/newDrink", newdrink);
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
	this.getRestaurant= function(){
		return $http.get("/resManager/restaurant");
	}
	this.findAllDrinks= function(){
		return $http.get("/resManager/drinks");
	}
	this.findAllDishes= function(){
		return $http.get("/resManager/dishes");
	}
	this.findAllFoodstuffs= function(){
		return $http.get("/resManager/foodstuffs");
	}
	this.findAllWaiters= function(){
		return $http.get("/resManager/waiters");
	}
	this.findAllCooks = function(){
		return $http.get("/resManager/cooks");
	}
	this.findAllBartenders= function(){
		return $http.get("/resManager/bartenders");
	}
	this.findAllBidders = function(){
		return $http.get("/resManager/bidders");
	}
	this.buttonDeleteFoodstuff=function(event){
		return $http.delete("/resManager/deleteFoodstuff/"+event.target.id);
	}
	this.buttonDeleteDish=function(event){
		return $http.delete("/resManager/deleteDish/"+event.target.id);
	}
	this.buttonDeleteDrink=function(event){
		return $http.delete("/resManager/deleteDrink/"+event.target.id);
	}
	this.buttonDeleteWaiter = function(event){
	return $http.delete("/resManager/deleteWaiter/"+event.target.id);
	}
	this.buttonDeleteCook = function(event){
	return $http.delete("/resManager/deleteCook/"+event.target.id);
	}
	this.buttonDeleteBartender = function(event){
	return $http.delete("/resManager/deleteBartender/"+event.target.id);
	}
	this.buttonDeleteBidder = function(event){
	return $http.delete("/resManager/deleteBidder/"+event.target.id);
	}
	this.updateResManager = function(restaurantManager) {		
		return $http.put("/resManager/"+restaurantManager.id, restaurantManager);
	}
	this.updateRestaurant = function(restaurant) {	
		return $http.put("/resManager/update/"+restaurant.id, restaurant);
	}
	
	this.findFoodstuff=function(event){
		return $http.get("/resManager/foodstuff/"+event.target.id);
	}
	this.findDish=function(event){
		return $http.get("/resManager/dish/"+event.target.id);
	}
	this.findDrink=function(event){
		return $http.get("/resManager/drink/"+event.target.id);
	}
	this.buttonUpdateFoodstuff = function(foodstuff){
		return $http.put("/resManager/updateFoodstuff", foodstuff);
	}
	this.buttonUpdateDish = function(dish){
		return $http.put("/resManager/updateDish", dish);
	}
	this.buttonUpdateDrink = function(drink){
		return $http.put("/resManager/updateDrink", drink);
	}
	
}]); 