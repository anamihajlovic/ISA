var guestServices = angular.module('guest.services', ['ngResource']);

guestServices.service('guestService', ['$http', function($http) {
	
	this.register = function(guest) {
		return $http.post("/guests/register", guest);
	}
	
	this.updateProfile = function(guest) {
		return $http.put("/guests/updateProfile", guest);
	}
	
	this.findFriends = function(id) {
		return $http.get("/friendships/findFriends/" + id);
	}
	
	this.getMyFriends = function(id) {
		return $http.get("/friendships/myFriends/" + id);
	}
	
	this.addFriend = function(guestId, addId) {
		return $http.post("/friendships/addFriend/" + addId, guestId);
	}
	
	this.deleteFriend = function(guestId, friendId) {
		return $http.delete("/friendships/deleteFriend/" + guestId + "/" + friendId);
	}
	
	this.getNumOfFriendRequest = function(id) {
		return $http.get("/friendships/getNumOfFriendRequest/" + id);
	}
	
	this.getFriendRequests = function(id) {
		return $http.get("/friendships/getFriendRequests/" + id);
	}
	
	this.confirm = function(senderId, receiverId) {
		return $http.put("/friendships/confirm/" + senderId + "/" + receiverId);
	}
	
	this.deleteRequest= function(senderId, receiverId) {
		return $http.delete("/friendships/deleteRequest/" + senderId + "/" + receiverId);
	}
	
	this.sortFriends = function(friendsForSort, sortBy, reverse) {
		return $http.post("/friendships/sortFriends/" + sortBy + "/" + reverse, friendsForSort);
	}
	
	this.getRestaurants = function() {
		return $http.get("/restaurants/getAll");
	}
	
	this.sortRestaurants = function(sortBy, reverse) {
		return $http.get("/restaurants/sort/" + sortBy + "/" + reverse);

	}
	
	//za rezervacije
	
	this.addReservation = function(reservation, tablesIdVerSize) {
		console.log("service " + tablesIdVerSize);
		return $http.post("/reservations/add/" + tablesIdVerSize, reservation);
	}
	
	this.getTables = function(id, date, startTime, endTime){
		return $http.get("/resTables/getTables/" + id +"/" + date + "/" + startTime + "/" + endTime);
	}
	
	this.getChosenTable = function(id) {
		return $http.get("/resTables/getTable/" + id);

	}
	
	this.sendInvitation = function(friendId, reservationId) {
		return $http.post("/invitations/sendInvitation/" + friendId, reservationId);
	}
	
	this.getReservation = function(reservationId) {
		return $http.get("/reservations/getReservation/" + reservationId);
	}
	
	this.getDrinks= function(id){
		return $http.get("/restaurants/drinks/" + id);
	}
	
	this.getDishes= function(id){
		return $http.get("/restaurants/dishes/" + id);
	}
		
	this.order = function(reservationId, guestId, dishesAndDrinks) {
		return $http.post("/orders/addOrder/" + reservationId + "/" + guestId, dishesAndDrinks);
	}
	
	//za istoriju poseta
	
	this.getMyVisits = function(id){
		return $http.get("/reservations/getMyVisits/" + id);
	}
	
	this.getInvitedFriends = function(reservationId, guestId) {
		return $http.get("/invitations/getInvitedFriends/" + reservationId + "/" + guestId);
	}
	
	//brisanje rezervacija

	this.getActiveReservations = function(guestId) {
		return $http.get("/reservations/getActiveReservations/" + guestId);
	}

	this.deleteReservation = function(id) {
		return $http.delete("/reservations/deleteReservation/" + id)
	}
	
	
	
}]);