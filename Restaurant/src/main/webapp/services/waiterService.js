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
	
	this.removeDrink = function(drinkId, order){
		return $http.put("/orders/removeDrink/"+drinkId, order);
	}
	
	this.removeDish = function(dishId, orderId){
		return $http.delete("/orders/removeDish/" + dishId + "/" + orderId);
	}
	
	this.getTables = function(){
		return $http.get("/waiters/getTables");
	}
	
	
	
}]); 