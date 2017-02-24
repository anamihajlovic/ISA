var orderServices = angular.module('order.services', ['ngResource']);

guestServices.service('orderService', ['$http', function($http) {
	
	this.getAllRestaurantOrders = function(restaurantId) {
		return $http.get('/orders/getAllRestaurantOrders/'+restaurantId);
	}
	
	this.getRestaurantDrinkOrders = function(restaurantId) {
		return $http.get('/orders/getRestaurantDrinkOrders/'+restaurantId);
	}
	
	this.getRestaurantDishOrders = function(restaurantId) {
		return $http.get('/orders/getRestaurantDishOrders/'+restaurantId);
	}
	
	this.prepareDrinks = function(order) {
		return $http.put("/orders/prepareDrinks", order);
	}
	
	this.prepareDish = function(orderId, dish) {
		alert("Service")
		return $http.put("/orders/prepareDish/"+orderId, dish);
	}
	
	
}]);