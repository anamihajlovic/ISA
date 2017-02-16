var bartenderServices = angular.module('bartender.services', ['ngResource']);

bartenderServices.service('bartenderService',['$http', function($http) {
		
	
	this.getBartender = function() {		
		return $http.get("/bartenders/getBartender");
	}
	
	this.updateBartenderInfo = function(bartender) {
		alert("bartender " + bartender)
		alert("id je " + bartender.id)		
		return $http.put("/bartenders/"+bartender.id, bartender);
	}
	
}]); 