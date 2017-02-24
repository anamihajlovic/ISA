var orderModule = angular.module('order.controller', ['ui.calendar']);


orderModule.controller('orderController', ['$scope', 'orderService', 'employeeService', '$location',
	
	function ($scope, orderService, employeeService, $location) {
	

	function isLoggedIn() {
		
		var request = employeeService.getEmployee().then(function (response) {				
				if(response.data !="") 
					$scope.employee = response.data;
				else
					$location.path('login');
			}
		);				
		
		request.then(function(employee) {
						
			if($scope.employee.userRole == 'bartender') {
				var request = orderService.getRestaurantDrinkOrders($scope.employee.restaurantId).then(function(response){
					$scope.data = response.data;	
					return response;						
				}); 
				
				request.then(function(data) {
					if($scope.data != null) 
						$scope.orders = $scope.data;
					else {
						toastr.info("There are no ordered drinks at the moment");
						$location.path('bartender');
					}
				});

				
			} else if($scope.employee.userRole == 'cook') {				
				var request = orderService.getRestaurantDishOrders($scope.employee.restaurantId).then(function(response){
					$scope.data = response.data;	
					return response;						
				}); 
				
				request.then(function(data) {
					if($scope.data != null) 
						$scope.orders = $scope.data;
					else {
						toastr.info("There are no ordered dishes at the moment");
						$location.path('bartender');
					}
				});
			}									
		});
	}
	
	
	isLoggedIn();	
	
	$scope.prepareDrinks = function(order) {
		
		var request = orderService.prepareDrinks(order).then(function(response){			
			$scope.data = response.data;
			return response;
		});
		
		request.then(function (data) {
			if($scope.data != null) {
				
				if($scope.employee.userRole == 'bartender') {
					var request = orderService.getRestaurantDrinkOrders($scope.employee.restaurantId).then(function(response){
						$scope.orders = response.data;	
						
					});
					
				} else if($scope.employee.userRole == 'cook') {				
					var request = orderService.getRestaurantDishOrders($scope.employee.restaurantId).then(function(response){
						$scope.orders = response.data;														
					});
				}
			}
		});
	}
	
	$scope.checkCook = function(dishType) {			
		if($scope.employee != null) {			
			if($scope.employee.userRole == 'cook') {
								
				if($scope.employee.cookType != 'undefined') {
					var cookDish = $scope.employee.cookType.split("Cook")[0];
					if(cookDish == dishType)
						return true;
				} else if($scope.employee.cookType == 'undefined')  {
					return true;
				}												
			}						
		}
		
		return false;
	}
	
	$scope.prepareDish = function(order, dish) {
		alert("Start")
		$scope.preparingDish = false;
		var request = orderService.prepareDish(order, dish).then(function(response) {
			$scope.data = response.data;
			return response;
		});
		
		request.then(function (data) {
			alert("AAAA")
			alert(data)
			if($scope.data != null) {
				$scope.preparingDish = true;
			} else
				toastr.error("Sorry, you cannot prepare this meal");
		})
		
	}
	
}]);	