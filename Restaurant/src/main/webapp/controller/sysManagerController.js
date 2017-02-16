var sysManagerModule = angular.module('sysManager.controller', []);
 

sysManagerModule.controller('sysManagerController', ['$scope', 'sysManagerService','$location',
  	function ($scope,sysManagerService, $location) {
	
	function checkRights() {
		sysManagerService.checkRights().then(
			function (response) {
				$scope.systemManager = response.data;
				if(response.data!="") {
					toastr.success("tu jee !");	
				} else {					
					 $location.path('login');
				
				}
			}
		);
	}
	checkRights();	
	$scope.saveManager= function () {    
			
				alert("pre requesta " + $scope.resManager);
				var request = sysManagerService.saveResManager($scope.resManager).then(function(response) {
			
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

	////////////////////////////////////////////////////	
	$scope.freeRestaurantManagers = function () {  
		//alert("uslo u controller")
		
		var request = sysManagerService.findAllFreeRestaurantManagers().then(
			function (response) {
				$scope.freeRestaurantManagers = response.data;
			}
		); 	
	};
	//////////////////////////////	
	
	$scope.saveRestaurant= function () {    
		alert("uslo u controller")
		$scope.saveChecked =function () {  
			$scope.restaurant.restaurantManagers.push($scope.restaurantManagerChecked);
			alert("dodat "+$scope.restaurant.restaurantManagers)
		} 
		var request = sysManagerService.saveRestaurant($scope.restaurant).then(function(response) {
	
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
	$scope.saveRestaurant= function () {    
		alert("uslo u controller")
		var request = sysManagerService.saveRestaurant($scope.restaurant).then(function(response) {
	
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
/////////////////////////////////////
	$scope.update= function () {    
		
		alert("pre requesta " + $scope.systemManager);
		var request = sysManagerService.updateSysManager($scope.systemManager).then(function(response) {
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////////////////////
}]);