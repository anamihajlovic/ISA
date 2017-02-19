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

	
	
}]);
