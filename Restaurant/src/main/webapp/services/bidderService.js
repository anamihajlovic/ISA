var bidderServices = angular.module('bidder.services', ['ngResource']);

bidderServices.service('bidderService',['$http', function($http) {	
	
	this.checkRights = function(){
		return $http.get("/bidder/checkRights");
	}
	this.updateBidder = function(bidder) {		
		return $http.put("/bidder/"+bidder.id, bidder);
	}
	this.showBidderOrders = function(){
		return $http.get("/bidder/bidderOrders");
	}	
	this.showBidderOfferForUpdate = function(event){
		return $http.get("/bidder/bidderOffer/"+event.target.id);
	}
	
	this.showBidderOffer = function(event){
		return $http.get("/bidder/bidderOfferUnits/"+event.target.id);
	}
	this.showBidderOfferAfterUpdate = function(potreban_event){
		return $http.get("/bidder/bidderOfferUnits/"+potreban_event.target.id);
	}
	
	this.showOfferUnit = function(event){
		return $http.get("/bidder/showOfferUnit/"+event.target.id);
	}

	this.updateOfferUnit = function(offerUnit){
		return $http.put("/bidder/updateOfferUnit",offerUnit);
	}
	this.updateOfferUnitAfteSetting = function(offerUnit){
		return $http.put("/bidder/updateOfferUnitAfteSetting",offerUnit);
	}

	this.saveOffer = function(offer) {
		return $http.post("/bidder/makeOffer",offer);

	}
	this.showAllOffers = function(){
		return $http.get("/bidder/allOffers");
	}
	this.updateOffer = function(offer){
		return $http.put("/bidder/updateOffer",offer);
	}
	
}]); 