var bidderModule = angular.module('bidder.controller', []);
 

bidderModule.controller('bidderController', ['$scope', 'bidderService','$location',
  	function ($scope,bidderService, $location) {
	function checkRights() {
		bidderService.checkRights().then(
			function (response) {
				$scope.bidder= response.data;
				if(response.data!="") {	
				} else {					
					 $location.path('login');
				}
			}
		);
	}
	checkRights();
	$scope.updateBidder = function(){
		checkRights();	
	}
	$scope.viewAll = function(){
		checkRights();	
	}
	$scope.changePas = function(){
		checkRights();	
	}
	
	$scope.update= function () {    
		var request = bidderService.updateBidder($scope.bidder).then(function(response) {
		$scope.data = response.data;
		return response;
	});			
		request.then(function (data) {
			if($scope.data != null) {
				toastr.success("Success!");	
					
			} else {
				toastr.error("Something wrong");
			
			}

	});
}

  function AllOrders(){
		   bidderService.showBidderOrders().then(
					function (response) {
						$scope.forBidderOrders = response.data;
						
					}
			);
		  
			 
	   }
	AllOrders()
	   AllOffers();
	   function AllOffers(){
		  
				 bidderService.showAllOffers().then(
							function (response) {
								$scope.listOffers = response.data;
							
							}
					);   
		   
	   }
	   var potreban_event ;
	   $scope.buttonShowOfferDialog = function(event){
		   potreban_event = event;
		   var request = bidderService.showBidderOffer(event).then(
					function (response) {
						$scope.forBidderOfferUnits = response.data;
						document.getElementById("modalBtnShowOffer").click();
					}
			);
		   var request = bidderService.showBidderOfferForUpdate(event).then(
					function (response) {
						$scope.offer = response.data;
					}
			);
 
		   
	   }
	
	   $scope.buttonUpdateOfferUnit = function(event){
		
			var request = bidderService.showOfferUnit(event).then(function(response) {
				  $scope.offerUnit = response.data;
				  $('.modal-showOrderUnit').css('width', '350px');
				  document.getElementById("modalBtnUpdateOfferUnit").click();
				return response;
			});
		   
	   }
	   
	   
	   $scope.updateOfferUnitPrice = function(){
		   var request = bidderService.updateOfferUnit($scope.offerUnit).then(function(response) {
				$scope.data = response.data;
				return response;
			});			
				request.then(function (data) {
					if($scope.data != null) {
						toastr.success("Success!");	
						document.getElementById("cancelUnitOffer").click();
						 var request = bidderService.showBidderOfferAfterUpdate(potreban_event).then(
									function (response) {
										$scope.forBidderOfferUnits = response.data;
									}
							);  
		
					} else {
						toastr.error("Something wrong");
					
					}

			});
	  
	   }
	  $scope.AddOffer=function(){		  
		  if($scope.offer==null) {
			  toastr.error("Offer is not complete !");
			  
		  }else{
			var request = bidderService.saveOffer($scope.offer).then(function(response) {	
				$scope.data = response.data;
				return response;
			});			
				request.then(function (data) {
					if($scope.data == "dodato") {
						toastr.success("Success!");
						document.getElementById("cancelOffer").click();
						AllOffers();
						
					} else {
						
						toastr.error("Offer is not complete !");
					
					}
				});
		  }
	  }
	  $scope.UpdateOffer =function(){
			var request = bidderService.updateOffer($scope.offer).then(function(response) {
				$scope.data = response.data;
				return response;
			});			
				request.then(function (data) {
					if($scope.data != null) {
						toastr.success("Success!");	
						document.getElementById("cancelOffer").click();
						AllOffers();
							
					} else {
						toastr.error("Something wrong");
					
					}

			});
		  
		  
	  }

	
	
}]);
