var waiterServices = angular.module('waiter.services', ['ngResource']);

waiterServices.service('waiterService',['$http', function($http) {

	this.getDrinks= function(id){
		return $http.get("/restaurants/drinks/" + id);
	}
	
	this.getDishes= function(id){
		return $http.get("/restaurants/dishes/" + id);
	}
	
	this.addDrink = function(drinkId, order){
		return $http.put("/orders/addDrink/"+drinkId, order);
	}
	
	this.addDish = function(dishId, order){
		return $http.post("/orders/addDish/"+dishId, order);
	}
	
}]); 