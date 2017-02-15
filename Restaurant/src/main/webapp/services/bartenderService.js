var bartenderServices = angular.module('bartender.services', ['ngResource']);

bartenderServices.service('bartenderService',['$http', function($http) {
	
	this.updateInfo = function() {
		alert("Bartender Update Info Service");
		
	}
}]); 