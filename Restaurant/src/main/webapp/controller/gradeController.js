var gradeModule = angular.module('grade.controller', []);

gradeModule.controller('gradeController', ['$scope', 'employeeService', 'gradeService', 'waiterService', 'orderService', '$location', '$state',
	
	function ($scope, employeeService, gradeService, waiterService, orderService, $location, $state) {
	
	function isLoggedIn() {		
		$scope.mode = $location.url().split('/')[2];
		$scope.order = gradeService.getOrder();
		
		employeeService.getEmployee().then(function (response) {				
				if(response.data !="") {
					$scope.guest = response.data;						
					getRestaurant();
					getWaiter();	
					
					
					if($scope.mode == 'seeRating')
						setGrade();
				}						
				else
					$location.path('login');
			}
		);
	}
	
	function getRestaurant() {
		var request = gradeService.getRestaurant($scope.order.restaurantId).then(function(response) {
			$scope.data = response.data;
			return response;		
		});
		
		request.then(function (data) {
			if($scope.data != "")				
				$scope.restaurant = $scope.data;
			else
				toastr.info("Restaurant does not exist");
		});	
	}
	
	function getWaiter() {
		var request = waiterService.getWaiter($scope.order.waiterId).then(function(response) {
			$scope.data = response.data;
			return response;
		
		});
		
		request.then(function (data) {
			if($scope.data != "")
				$scope.waiter = $scope.data;
			else
				toastr.info("Waiter has been fired.");
		});	
	}
	
	function isValid(grade) {
		if(grade.orderGrade == 0.00 || grade.waiterGrade == 0.00 || grade.restaurantGrade == 0) {
			document.getElementById("modalInvalid").click();
			$scope.gradeOk = false;
		} else
			$scope.gradeOk = true;					
	}
	

	function setGrade() {
		var reservation = gradeService.getReservation();

		var request = gradeService.getRatedReservation(reservation.id, $scope.guest.id).then(function(response) {
			$scope.data = response.data;
			return response;			
		});			
		request.then(function (data) {					
			if($scope.data != null)	 {
				$scope.grade = $scope.data;	
							
				if($scope.grade.waiterGrade == 5.00) 
					$scope.waiterFiveClick();
				else if($scope.grade.waiterGrade == 4.00)
					$scope.waiterFourClick();
				else if($scope.grade.waiterGrade == 3.00)
					$scope.waiterThreeClick();
				else if($scope.grade.waiterGrade == 2.00)
					$scope.waiterTwoClick();
				else if($scope.grade.waiterGrade == 1.00)
					$scope.waiterOneClick();
				else if($scope.grade.waiterGrade == 0.00) {
					$scope.waiterOne = false;
					$scope.waiterTwo = false;
					$scope.waiterThree = false;
					$scope.waiterFour = false;
					$scope.waiterFive = false;
				}
				
				if($scope.grade.orderGrade == 5.00) 
					$scope.mealFiveClick();
				else if($scope.grade.orderGrade == 4.00)
					$scope.mealFourClick();
				else if($scope.grade.orderGrade == 3.00)
					$scope.mealThreeClick();
				else if($scope.grade.orderGrade == 2.00)
					$scope.mealTwoClick();
				else if($scope.grade.orderGrade == 1.00)
					$scope.mealOneClick();
				else if($scope.grade.orderGrade == 0.00) {
					$scope.mealOne = false;
					$scope.mealTwo = false;
					$scope.mealThree = false;
					$scope.mealFour = false;
					$scope.mealFive = false;
				}
				
				if($scope.grade.restaurantGrade == 5.00) 
					$scope.restaurantFiveClick();
				else if($scope.grade.restaurantGrade == 4.00)
					$scope.restaurantFourClick();
				else if($scope.grade.restaurantGrade == 3.00)
					$scope.restaurantThreeClick();
				else if($scope.grade.restaurantGrade == 2.00)
					$scope.restaurantTwoClick();
				else if($scope.grade.restaurantGrade == 1.00)
					$scope.restaurantOneClick();
				else if($scope.grade.restaurantGrade == 0.00) {
					$scope.restaurantOne = false;
					$scope.restaurantTwo = false;
					$scope.restaurantThree = false;
					$scope.restaurantFour = false;
					$scope.restaurantFive = false;
				}
				
			}
						
		});								
	}
	
	
	isLoggedIn();
	
	$scope.waiterOne = false;
	$scope.waiterTwo = false;
	$scope.waiterThree = false;
	$scope.waiterFour = false;
	$scope.waiterFive = false;
	
	
	$scope.mealOne = false;
	$scope.mealTwo = false;
	$scope.mealThree = false;
	$scope.mealFour = false;
	$scope.mealFive = false;
	
	$scope.restaurantOne = false;
	$scope.restaurantTwo = false;
	$scope.restaurantThree = false;
	$scope.restaurantFour = false;
	$scope.restaurantFive = false;
	
	
	$scope.waiterOneClick = function () {
		$scope.waiterOne = true;
		$scope.waiterTwo = false;
		$scope.waiterThree = false;
		$scope.waiterFour = false;
		$scope.waiterFive = false;
	}
	
	$scope.waiterTwoClick = function() {
		$scope.waiterOne = true;
		$scope.waiterTwo = true;
		$scope.waiterThree = false;
		$scope.waiterFour = false;
		$scope.waiterFive = false;
	}
	
	$scope.waiterThreeClick = function() {
		$scope.waiterOne = true;
		$scope.waiterTwo = true;
		$scope.waiterThree = true;
		$scope.waiterFour = false;
		$scope.waiterFive = false;
	}
	
	$scope.waiterFourClick = function() {
		$scope.waiterOne = true;
		$scope.waiterTwo = true;
		$scope.waiterThree = true;
		$scope.waiterFour = true;
		$scope.waiterFive = false;
	}
	
	$scope.waiterFiveClick = function() {
		$scope.waiterOne = true;
		$scope.waiterTwo = true;
		$scope.waiterThree = true;
		$scope.waiterFour = true;
		$scope.waiterFive = true;
	}
	
	$scope.resetWaiter = function() {
		$scope.waiterOne = false;
		$scope.waiterTwo = false;
		$scope.waiterThree = false;
		$scope.waiterFive = false;
		$scope.waiterFour = false;
	}
	
	//*************************************
	$scope.mealOneClick = function () {
		$scope.mealOne = true;
		$scope.mealTwo = false;
		$scope.mealThree = false;
		$scope.mealFour = false;
		$scope.mealFive = false;
	}
	
	$scope.mealTwoClick = function() {
		$scope.mealOne = true;
		$scope.mealTwo = true;
		$scope.mealThree = false;
		$scope.mealFour = false;
		$scope.mealFive = false;
	}
	
	$scope.mealThreeClick = function() {
		$scope.mealOne = true;
		$scope.mealTwo = true;
		$scope.mealThree = true;
		$scope.mealFour = false;
		$scope.mealFive = false;
	}
	
	$scope.mealFourClick = function() {
		$scope.mealOne = true;
		$scope.mealTwo = true;
		$scope.mealThree = true;
		$scope.mealFour = true;
		$scope.mealFive = false;
	}
	
	$scope.mealFiveClick = function() {
		$scope.mealOne = true;
		$scope.mealTwo = true;
		$scope.mealThree = true;
		$scope.mealFour = true;
		$scope.mealFive = true;
	}
	
	$scope.resetMeal = function() {
		$scope.mealOne = false;
		$scope.mealTwo = false;
		$scope.mealThree = false;
		$scope.mealFour = false;
		$scope.mealFive = false;
	}
	
	//*************************************
	$scope.restaurantOneClick = function () {
		$scope.restaurantOne = true;
		$scope.restaurantTwo = false;
		$scope.restaurantThree = false;
		$scope.restaurantFour = false;
		$scope.restaurantFive = false;
	}
	
	$scope.restaurantTwoClick = function() {
		$scope.restaurantOne = true;
		$scope.restaurantTwo = true;
		$scope.restaurantThree = false;
		$scope.restaurantFour = false;
		$scope.restaurantFive = false;
	}
	
	$scope.restaurantThreeClick = function() {
		$scope.restaurantOne = true;
		$scope.restaurantTwo = true;
		$scope.restaurantThree = true;
		$scope.restaurantFour = false;
		$scope.restaurantFive = false;
	}
	
	$scope.restaurantFourClick = function() {
		$scope.restaurantOne = true;
		$scope.restaurantTwo = true;
		$scope.restaurantThree = true;
		$scope.restaurantFour = true;
		$scope.restaurantFive = false;
	}
	
	$scope.restaurantFiveClick = function() {
		$scope.restaurantOne = true;
		$scope.restaurantTwo = true;
		$scope.restaurantThree = true;
		$scope.restaurantFour = true;
		$scope.restaurantFive = true;
	}
	
	$scope.resetRestaurant = function() {
		$scope.restaurantOne = false;
		$scope.restaurantTwo = false;
		$scope.restaurantThree = false;
		$scope.restaurantFour = false;
		$scope.restaurantFive = false;
	}
	
	//*****************************************************************************************
	
	$scope.submit = function() {	
		var grade = new Object();
		var reservation = gradeService.getReservation();
		
		grade.reservationId = reservation.id;
		grade.date = $scope.order.orderDate;
		grade.guestId = $scope.guest.id;		
		grade.orderId = $scope.order.id;
		grade.waiterId = $scope.waiter.id;		
		grade.restaurantId = $scope.restaurant.id;

		if($scope.waiterFive)
			grade.waiterGrade = 5.00;
		else if($scope.waiterFour)
			grade.waiterGrade = 4.00;
		else if($scope.waiterThree)
			grade.waiterGrade = 3.00;
		else if($scope.waiterTwo)
			grade.waiterGrade = 2.00;
		else if($scope.waiterOne)
			grade.waiterGrade = 1.00;
		else 			
			grade.waiterGrade = 0.00;
		
		if($scope.mealFive)
			grade.orderGrade = 5.00;
		else if($scope.mealFour)
			grade.orderGrade = 4.00;
		else if($scope.mealThree)
			grade.orderGrade = 3.00;
		else if($scope.mealTwo)
			grade.orderGrade = 2.00;
		else if($scope.mealOne)
			grade.orderGrade = 1.00;
		else 			
			grade.orderGrade = 0.00;
		
		
		if($scope.restaurantFive)
			grade.restaurantGrade = 5.00;
		else if($scope.restaurantFour)
			grade.restaurantGrade = 4.00;
		else if($scope.restaurantThree)
			grade.restaurantGrade = 3.00;
		else if($scope.restaurantTwo)
			grade.restaurantGrade = 2.00;
		else if($scope.restaurantOne)
			grade.restaurantGrade = 1.00;
		else 			
			grade.restaurantGrade = 0.00;		
		
		isValid(grade);
			
		if($scope.gradeOk) {
			var request = gradeService.submitGrade(grade).then(function(response){
				$scope.data = response.data;
				return response;
			});
			
			request.then(function(data){
				if($scope.data != "") {
					toastr.success("Thank you for grading.")
					$state.go('guest.myVisits');				
				}						
			});
		}
		
	}
	
	
	$scope.cancel = function() {
		$state.go('guest.myVisits');
	}
	
	//*****************************************************************************************
	$scope.showWaiter = function(order) {			
		document.getElementById("modalWaiter").click();		
	}
	
	$scope.showOrder = function() {		
		var reservation = gradeService.getReservation();
		$scope.order = gradeService.getOrder();
		document.getElementById("modalOrder").click();			
	}
	
	$scope.showRestaurant = function() {		
		document.getElementById("modalRestaurant").click();
	}
}]);