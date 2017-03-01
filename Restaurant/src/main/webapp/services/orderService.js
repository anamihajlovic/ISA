var orderServices = angular.module('order.services', ['ngResource']);

guestServices.service('orderService', ['$http', function($http) {
	
	
	this.getAllRestaurantOrders = function(restaurantId) {
		return $http.get('/orders/getAllRestaurantOrders/'+restaurantId);
	}
	
	this.getServedRestaurantOrders = function(restaurantId) {
		return $http.get('/orders/getServedRestaurantOrders/'+restaurantId);
	}
	
	this.getPaidRestaurantOrders = function(restaurantId) {
		return $http.get('/orders/getPaidRestaurantOrders/'+restaurantId);
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
		return $http.put("/orders/prepareDish/"+orderId, dish);
	}
	
	this.getRestaurantPreparingDish = function() {
		return $http.get('/orders/getPreparingDishes');
	}
	
	this.serveDish = function(orderId, dish) {
		return $http.put("/orders/serveDish/"+orderId, dish);
	}
	
	this.acceptOrder = function(orderId, tableId) {
		return $http.put("/orders/acceptOrder/"+orderId+"/"+tableId);
	}
	
	this.serveOrder = function(order) {
		return $http.put("/orders/serveOrder", order);
	}
	
	this.compareWaiters = function(orderId) {
		return $http.get('/orders/compareWaiters/'+orderId);
	}
	
	this.finishOrder = function(orderId) {
		return $http.put("/orders/finishOrder/"+ orderId);
	}
	
	this.createBill = function(bill) {
		return $http.post('/bills/createBill', bill);
	}
	
	this.getReservation = function(reservationId) {
		return $http.get("/reservations/getReservation/"+reservationId);
	}
	
	this.getOrder = function(reservationId) {
		return $http.get("/orders/getOrderByReservation/"+reservationId);
	}

}]);