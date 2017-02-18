var resManagerModule = angular.module('resManager.controller', []);
 

resManagerModule.controller('resManagerController', ['$scope', 'resManagerService','$location',
  	function ($scope,resManagerService, $location) {
	
	function checkRights() {
		resManagerService.checkRights().then(
			function (response) {
				$scope.restaurantManager = response.data;
				if(response.data!="") {	
				} else {					
					 $location.path('login');
				}
			}
		);
	}
	checkRights();	
	
	
	
	$scope.saveEmployee= function () {    
		
		alert("pre requesta " +$scope.employee.userRole);
		if($scope.employee.userRole=='waiter'){
		resManagerService.saveWaiter($scope.employee).then(function(response) {		
			$scope.data = response.data;
			return response;
			});			
			request.then(function (data) {
				if($scope.data == "dodato") {
					toastr.success("Success!");	
						
				} else {
					toastr.error("Something wrong");			
				}
	
			});
		}else if ($scope.employee.userRole=="cook"){
			
			var request = resManagerService.saveCook($scope.employee).then(function(response) {				
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
			
			
		}else {
			var request = resManagerService.saveBartender($scope.employee).then(function(response) {				
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

	}
	
////////////////////////////////////////	
	$scope.saveBidder= function () {    
		
		alert("pre requesta " + $scope.bidder);
		var request = resManagerService.saveBidder($scope.bidder).then(function(response) {	
		$scope.data = response.data;
		alert($scope.data );
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
	$scope.viewAll = function(){
		 resManagerService.findAllWaiters().then(
				function (response) {
					$scope.waiters = response.data;
				}
			); 	
		 resManagerService.findAllCooks().then(
					function (response) {
						$scope.cooks = response.data;
					}
				);
		 resManagerService.findAllBartenders().then(
					function (response) {
						$scope.bartenders = response.data;
					}
				);
		 resManagerService.findAllBidders().then(
					function (response) {
						$scope.bidders = response.data;
					}
				); 	
		
		
	}
	function AllWaiters() {  
		//alert("uslo u controller")		
		 resManagerService.findAllWaiters().then(
			function (response) {
				$scope.waiters = response.data;
			}
		); 	
	};
	
	function AllCooks() {  
		//alert("uslo u controller")		
		var request = resManagerService.findAllCooks().then(
			function (response) {
				$scope.cooks = response.data;
			}
		); 	
	};

	function AllBartenders() {  
		//alert("uslo u controller")		
		var request = resManagerService.findAllBartenders().then(
			function (response) {
				$scope.bartenders = response.data;
			}
		); 	
	};

	function AllBidders() {  
		//alert("uslo u controller")		
		var request = resManagerService.findAllBidders().then(
			function (response) {
				$scope.bidders = response.data;
			}
		); 	
	};
////////////////////////////////////
	
	
	$scope.buttonDeleteWaiter= function (event) { 
		var request = resManagerService.buttonDeleteWaiter(event).then(function(response) {
		$scope.data = response.data;
		return response;
	});			
		request.then(function (data) {
			if($scope.data != "no") {
				toastr.success("Success!");	
				AllWaiters();
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	$scope.buttonDeleteCook= function (event) { 
		var request = resManagerService.buttonDeleteCook(event).then(function(response) {
		$scope.data = response.data;

		return response;
	});			
		request.then(function (data) {
			if($scope.data != "no") {
				toastr.success("Success!");	
				AllCooks();
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	$scope.buttonDeleteBartender= function (event) { 
		var request = resManagerService.buttonDeleteBartender(event).then(function(response) {
		$scope.data = response.data;
		return response;
	});			
		request.then(function (data) {
			if($scope.data != "no") {
				toastr.success("Success!");	
				AllBartenders();
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	$scope.buttonDeleteBidder= function (event) { 
		var request = resManagerService.buttonDeleteBidder(event).then(function(response) {
		$scope.data = response.data;
		return response;
	});			
		request.then(function (data) {
			if($scope.data != "no") {
				toastr.success("Success!");	
				AllBidders();
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	$scope.update= function () {    
		var request = resManagerService.updateResManager($scope.restaurantManager).then(function(response) {
		$scope.data = response.data;
		alert(response.data)
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



//////////////////////////////////////	
	
}]);
