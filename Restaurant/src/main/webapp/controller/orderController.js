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
	
	function getOrders() {
		$scope.mode = $location.url().split('/')[2];	
		
		if($scope.mode == 'servedOrders') {			
			var request = orderService.getServedRestaurantOrders($scope.employee.restaurantId).then(function(response){					
				$scope.data = response.data;				
				return response;						
			}); 
			
			request.then(function(data) {
				if($scope.data != null) 
					$scope.orders = $scope.data;
				else {
					toastr.info("There are no served orders.");
					$location.path('waiter');
				}
			});
			
		} else if($scope.mode == 'paidOrders') {
			var request = orderService.getPaidRestaurantOrders($scope.employee.restaurantId).then(function(response){					
				$scope.data = response.data;				
				return response;						
			}); 
			
			request.then(function(data) {
				if($scope.data != null) 
					$scope.orders = $scope.data;
				else {
					toastr.info("There are no paid orders for today.");
					$location.path('waiter');
				}
			});
			
		} else {
			var request = orderService.getAllRestaurantOrders($scope.employee.restaurantId).then(function(response){					
				$scope.data = response.data;	
				return response;						
			}); 
			
			request.then(function(data) {
				if($scope.data != null) 
					$scope.orders = $scope.data;
				else {
					toastr.info("There are no orders.");
					$location.path('waiter');
				}
			});						
		}
	}
	
	
	function getDishes() {				
		$scope.mode = $location.url().split('/')[2];	
		
		if($scope.mode == 'orderedDish') {
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
			
		} else if($scope.mode == 'preparingDish') {
			
			var request = orderService.getRestaurantPreparingDish($scope.employee).then(function(response){
				$scope.data = response.data;				
				return response;						
			}); 
			
			request.then(function(data) {					
				if($scope.data != null) {
					$scope.orders = $scope.data;											
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
					checkWorkShift();					
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
			
			else if($scope.employee.userRole == 'waiter')
				getOrders();
											
		});
	}
	
	function checkWorkShift() {		
		var request = employeeService.checkWorkShift($scope.employee).then(function (response) {				
			if(response.data != "") {
				$scope.currentShift = response.data;				
				//$scope.allowAction = true;
				return response;
			}					
			else {
				//$scope.allowAction = false;
				//$location.path('bartender');
			}
				
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
				
				if($scope.employee.userRole == 'bartender') {
					getDrinks();					
					//getOrders();
				}
					
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
			return response;
		});
		
		request.then(function (data) {
			if($scope.data != 'failure') {
				getDishes();
				getOrders();	
			} 			
		});
	}
	
	
	
	$scope.acceptOrder = function() {		
		var request = orderService.acceptOrder($scope.order.id, $scope.selectedTable).then(function(response){
			$scope.data = response.data;			
			return response;
		});
		
		request.then(function (data) {
			if($scope.data != 'failure') {
				getOrders();					
			} 			
		});
		
		
	}
	
	
	$scope.chooseTable = function(order) {
		$scope.order = order;
		document.getElementById("modalTables").click();
		
		var request = orderService.getReservation(order.reservationId).then(function(response) {
			$scope.data = response.data;			
			return response;
		});		
		request.then(function (data) {
			if($scope.data != "") {
				$scope.reservation = $scope.data;	
				$scope.reservedTables = [];
				for(i = 0; i<$scope.reservation.tables.length; i++) {
					var table = $scope.reservation.tables[i];
					if(table != ';')
						$scope.reservedTables.push(table);
				}									
			} 				
		});
	}
	
	
	$scope.serveOrder = function(order) {
		var request = orderService.serveOrder(order).then(function(response){
			$scope.data = response.data;			
			return response;
		});
		
		request.then(function (data) {
			if($scope.data != 'failure') {
				getOrders();					
			} 
				
		});
	}
	
	$scope.checkWaiter = function(order) {		
		if($scope.employee.id == order.waiterId)
			return true;
		return false;
	}
	
	
	$scope.buttonShowBill = function(order){		
		document.getElementById("modalCreateBill").click();													
		$scope.bill = new Object();
		$scope.order = order;				
		getOrders();						
		
		var currentDate = new Date()
		var day = currentDate.getDate()
		var month = currentDate.getMonth() + 1
		var year = currentDate.getFullYear()
		
		var hours = currentDate.getHours()
		var minutes = currentDate.getMinutes();
	
		$scope.bill.billDate = month + "/" + day + "/" + year;
		$scope.bill.time = hours + ":" + minutes;	
		
		var sum = 0;
		for(i=0; i<order.drinks.length;i++) {
			var drink = order.drinks[i];
			var drinkPrice = order.drinkQuantity[drink.id] * drink.price;
			sum += drinkPrice;
		}
		for(j=0; j<order.dishes.length; j++) {
			var dish = order.dishes[j]
			var dishPrice = order.dishQuantity[dish.id] * dish.price;
			sum += dishPrice;
		}
		
		$scope.bill.totalPrice = sum;
		
		if($scope.employee.id != order.waiterId) {
			
			var request = orderService.compareWaiters(order.id).then(function(response){
				$scope.data = response.data;			
				return response;
			});
			
			request.then(function (data) {
				if($scope.data != null) {
					$scope.waiter = response.data;
					$scope.bill.waiterId = $scope.waiter.id;				
				}								
			});
			
		} else {
			$scope.bill.waiterId = $scope.order.waiterId;
		}
				
		
	}
	
	$scope.createBill = function (order, bill) {	
		
		var request = orderService.finishOrder(order.id).then(function(response){
			$scope.data = response.data;			
			return response;
		});
		
		request.then(function (data) {
			if($scope.data != 'failure') {
				
				var request = orderService.createBill(bill).then(function (response){					
					$scope.data = response.data;			
					return response;			
				});
				
				if($scope.data != 'failure') {
					toastr.success("The order has been paid.");
				}
					
			}								
		});
	}
	
	$scope.inspectOrder = function(order, flag) {
		$scope.order = order;
		$scope.seeOrder = true;	
		$scope.changeOrder = flag;
	}
	
	$scope.hideOrder = function() {
		$scope.seeOrder = false;
		$scope.changeOrder = false;
	}
	
	
	
	
}]);	