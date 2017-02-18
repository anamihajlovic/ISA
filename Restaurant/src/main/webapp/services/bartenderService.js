var bartenderServices = angular.module('bartender.services', ['ngResource']);

bartenderServices.service('bartenderService',['$http', function($http) {
		
	
	this.getBartender = function() {		
		return $http.get("/bartenders/getBartender");
	}
	
	this.updateBartenderInfo = function(bartender) {		
		return $http.put("/bartenders/updateInfo", bartender);
	}
	
}]); 