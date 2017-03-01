var gradeServices = angular.module('grade.services', ['ngResource']);

gradeServices.service('gradeService', ['$http', function($http) {
	
	var currentReservation = new Object();
	var currentOrder = new Object();
	
	this.getRestaurant = function(id) {
		return $http.get("/restaurants/getRestaurant/"+id);
	}
	
	this.submitGrade = function(grade) {
		return $http.post("/grades/addNew", grade);
	}
	
	this.setReservation = function(reservation) {
		currentReservation = reservation;
	}
	
	this.getReservation = function() {
		return currentReservation;
	}
	
	this.setOrder = function(order) {
		currentOrder = order;		
	}
	this.getOrder = function() {
		return currentOrder;
	}
	
	this.getRatedReservation = function(reservationId, guestId) {
		return $http.get("/grades/getRatedReservation/"+reservationId+"/"+guestId);
	}
	
}]);