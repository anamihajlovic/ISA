var commonServices = angular.module('common.services', ['ngResource']);

commonServices.service('commonService',['$http', function($http) {
	
	this.testFunkcija = function(test) {
		return $http.post("/test/8", test );
	}
}]); 