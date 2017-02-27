var sysManagerModule = angular.module('sysManager.controller', []);
var emailRegex = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");

sysManagerModule.controller('sysManagerController', ['$scope', 'sysManagerService','$location',
  	function ($scope,sysManagerService, $location) {
	
	
	function checkRights() {
		sysManagerService.checkRights().then(
			function (response) {
				$scope.systemManager = response.data;
				if(response.data!="") {	
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
	$scope.newSysManager = function(){
		checkRights();	
	/*$scope.sysManager.firstName = '';
		
		$scope.sysManager.lastName = '';
		$scope.sysManager.email = '';
		$scope.sysManager.password = '';*/
	}
	$scope.newRestaurant = function(){
		checkRights();
	/*
		$scope.restaurant.name = '';
		$scope.restaurant.street = '';
		$scope.restaurant.country = '';
		$scope.restaurant.restaurant_type = '';
		$scope.restaurant.city = '';*/
		
	}
	$scope.newResManager = function(){
		checkRights();	
		/*$scope.resManager.firstName = null;
		$scope.resManager.lastName = null;
		$scope.resManager.email = null;
		$scope.resManager.password = null;
		$scope.resManager.idRestaurant = null;*/
		
	}
	$scope.list= function(){
		checkRights();	
	}
	
	$scope.saveManager= function () {

				var request = sysManagerService.saveResManager($scope.resManager).then(function(response) {
				$scope.data = response.data;
				
				return response;
			});			
				request.then(function (data) {
					
					if($scope.data != null) {
						toastr.success("Success!");	
						
					
					} else {
						toastr.error("Something wrong");
					
					}
					window.location.reload();
			});
				
		}
		
	var sifra;
	$scope.showManagers = function (event) { 
		sifra=event;
		var request = sysManagerService.findAllResManagers(event).then(function(response) {
		$scope.restaurantManagers = response.data;
		document.getElementById("modalBtnShow").click();
		return response;
	});			
	
	};

	function AllRestaurants(){  
		//alert("uslo u controller")
		
		var request = sysManagerService.findAllRestaurants().then(
			function (response) {
				$scope.restaurantss= response.data;
			}
		); 	
	};
	AllRestaurants();
	

	
	$scope.saveRestaurant= function () {    
		alert("uslo u controller")
		var request = sysManagerService.saveRestaurant($scope.restaurant).then(function(response) {
	
		$scope.data = response.data;
		alert(response.data)
		return response;
	});			
		request.then(function (data) {
			if($scope.data != null) {
				AllRestaurants();
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
				//$("#tbody").empty();
				
				var request1 = sysManagerService.showAfterDelResMan(sifra).then(
						function (response) {						
							$scope.restaurantManagers = response.data;
							//alert($scope.restaurantManagers)
						}
					); 
				//document.getElementById("modalBtnShow").click();
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	$scope.buttonDeleteRestaurant= function (event) { 
		var request = sysManagerService.buttonDeleteRestaurant(event).then(function(response) {
		$scope.data = response.data;
		return response;
	});			
		request.then(function (data) {
			if($scope.data != "no") {
				toastr.success("Success!");	
				AllRestaurants();
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	


	
	
	
	
	//////////////////////////////////////////////////
}]);