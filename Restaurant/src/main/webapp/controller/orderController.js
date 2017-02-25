var orderModule = angular.module('order.controller', ['ui.calendar']);


orderModule.controller('orderController', ['$scope', 'orderService', 'employeeService', '$location',
	
	function ($scope, orderService, employeeService, $location) {
	
	function getDrinks() {
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
		
	}
	
	function getDishes() {				
		var mode = $location.url().split('/')[2];	
		
		if(mode == 'orderedDish') {
			var request = orderService.getRestaurantDishOrders($scope.employee.restaurantId).then(function(response){
				$scope.data = response.data;	
				return response;						
			}); 
			
			request.then(function(data) {					
				if($scope.data != null) {
					$scope.orders = $scope.data;									
				}
										
				else {
					toastr.info("There are no ordered dishes at the moment");
					$location.path('cook');
				}
			});
			
		} else if(mode == 'preparingDish') {
			
			var request = orderService.getRestaurantPreparingDish($scope.employee).then(function(response){
				$scope.data = response.data;				
				return response;						
			}); 
			
			request.then(function(data) {					
				if($scope.data != null) {
					$scope.orders = $scope.data;	
					
					alert($scope.orders.length)
					if($scope.orders.length == 0)
						$scope.noDishes = true;
					else
						$scope.noDishes = false;
				}
										
				else {
					toastr.info("There are no dishes that are being prepared at the moment");
					$location.path('cook');
				}
			});
		}
		
		
	}

	function isLoggedIn() {			
		
		var request = employeeService.getEmployee().then(function (response) {				
				if(response.data !="") {
					$scope.employee = response.data;
					return response;
				}					
				else
					$location.path('login');
			}
		);				
		
		request.then(function(employee) {
			if($scope.employee.userRole == 'bartender') 
				getDrinks();
				
			else if($scope.employee.userRole == 'cook') 			
				getDishes();
											
		});
	}
	
	
	isLoggedIn();
	
	$scope.checkCook = function(dish) {			
		if($scope.employee != null && dish != null) {										
			if($scope.employee.userRole == 'cook') {
								
				if($scope.employee.cookType != 'undefined') {
					var cookDish = $scope.employee.cookType.split("Cook")[0];
					if(cookDish == dish.dishType)
						return true;
				} else if($scope.employee.cookType == 'undefined')  {
					return true;
				}												
			}						
		}
		
		return false;
	}
	
	$scope.prepareDrinks = function(order) {
		
		var request = orderService.prepareDrinks(order).then(function(response){			
			$scope.data = response.data;
			return response;
		});
		
		request.then(function (data) {
			if($scope.data != null) {
				
				if($scope.employee.userRole == 'bartender') 
					getDrinks();
			}
		});
	}
	
	$scope.prepareDish = function(order, dish) {
		
		var request = orderService.prepareDish(order.id, dish).then(function(response){
			$scope.data = response.data;
			return response;
		});
		
		request.then(function (data) {
			if($scope.data != 'failure') {
				getDishes();			
			} 
				
		});
	}
	
	$scope.serveDish = function(order, dish) {
		var request = orderService.serveDish(order.id, dish).then(function(response){
			$scope.data = response.data;
			alert(order)
			return response;
		});
		
		request.then(function (data) {
			if($scope.data != 'failure') {
				getDishes();			
			} 
				
		});
	}
	
}]);	