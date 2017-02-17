var sysManagerModule = angular.module('sysManager.controller', []);
 

sysManagerModule.controller('sysManagerController', ['$scope', 'sysManagerService','$location',
  	function ($scope,sysManagerService, $location) {

	function checkRights() {
		sysManagerService.checkRights().then(
			function (response) {
				$scope.systemManager = response.data;
				if(response.data!="") {
					toastr.success("tu jee !");	
					toastr.success($scope.systemManager.preset);	
					if ($scope.systemManager.preset=="no"){
					var dugme = document.getElementById("newSysManager");
					dugme.style.display = "none";
					}
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
	function AllResManagers() {  
		//alert("uslo u controller")		
		var request = sysManagerService.findAllRestaurantManagers().then(
			function (response) {
				$scope.restaurantManagers = response.data;
			}
		); 	
	};
	AllResManagers();
	function AllRestaurants(){  
		//alert("uslo u controller")		
		var request = sysManagerService.findAllRestaurants().then(
			function (response) {
				$scope.restaurantss= response.data;
			}
		); 	
	};
	AllRestaurants();
	
	
	
	////////////////////////////////////////
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
	///////////////////////////////////////////////////
	$scope.saveSysManager= function () {    
		
		alert("pre requesta " + $scope.sysManager);
		var request = sysManagerService.saveSysManager($scope.sysManager).then(function(response) {
	
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
///////////////////////////////////////////////	
	

	
	$scope.buttonDeleteResManager= function (event) {    
		//alert(event.target.id)
		var request = sysManagerService.buttonDeleteResManager(event).then(function(response) {
		$scope.data = response.data;
		//alert(response.data)
		return response;
	});			
		request.then(function (data) {
			if($scope.data != "no") {
				toastr.success("Success!");	
				AllResManagers();
				AllRestaurants();
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	$scope.buttonDeleteRestaurant= function (event) {    
		//alert(event.target.id)
		var request = sysManagerService.buttonDeleteRestaurant(event).then(function(response) {
		$scope.data = response.data;
		//alert(response.data)
		return response;
	});			
		request.then(function (data) {
			if($scope.data != "no") {
				toastr.success("Success!");	
				AllResManagers();
				AllRestaurants();
			} else {
				toastr.error("Something wrong");
			
			}

	});
}

	  $scope.open = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();

	    $scope.opened = true;
	  };

	  $scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	  };


	  $scope.format = 'dd-MMMM-yyyy';
	
	
	
	
	//////////////////////////////////////////////////
}]);