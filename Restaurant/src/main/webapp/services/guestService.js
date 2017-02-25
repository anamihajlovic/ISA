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
	
	this.addReservation = function(reservation) {
		console.log("service addReservation");
		return $http.post("/reservations/add", reservation);
	}
	
	
	
}]);