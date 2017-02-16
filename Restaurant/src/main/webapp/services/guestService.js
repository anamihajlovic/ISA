var guestServices = angular.module('guest.services', ['ngResource']);

guestServices.service('guestService', ['$http', function($http) {
	
	this.register = function(guest) {
		alert ("register in guestService");
		return $http.post("/guests/register", guest);
	}
	
}]);