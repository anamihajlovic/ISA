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
	
}]);