var guestServices = angular.module('guest.services', ['ngResource']);

guestServices.service('guestService', ['$http', function($http) {
	
	this.register = function(guest) {
		return $http.post("/guests/register", guest);
	}
	
	this.updateProfile = function(guest) {
		return $http.post("/guests/updateProfile", guest);
	}
	
	this.findFriends = function(id) {
		return $http.get("/guests/findFriends/" + id);
	}
	
	this.getMyFriends = function(id) {
		return $http.get("/guests/myFriends/" + id);
	}
	
	this.addFriend = function(guestId, addId) {
		return $http.post("/friendships/addFriend/" + addId, guestId);
	}
	
	this.deleteFriend = function(guestId, deleteId) {
		return $http.post("/friendships/deleteFriend/" + deleteId, guestId);
	}
	
}]);