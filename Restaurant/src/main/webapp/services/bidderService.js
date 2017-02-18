var bidderServices = angular.module('bidder.services', ['ngResource']);

bidderServices.service('bidderService',['$http', function($http) {	
	
	this.checkRights = function(){
		return $http.get("/bidder/checkRights");
	}
	this.updateBidder = function(bidder) {		
		return $http.put("/bidder/"+bidder.id, bidder);
	}
}]); 