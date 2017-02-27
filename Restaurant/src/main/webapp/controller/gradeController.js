var gradeModule = angular.module('grade.controller', []);

gradeModule.controller('gradeController', ['$scope', 'cookService', 'employeeService', '$location',
	
	function ($scope, cookService, employeeService, $location) {
	
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
	}
	
	$scope.waiterTwoClick = function() {
		$scope.waiterOne = true;
		$scope.waiterTwo = true;
	}
	
	$scope.waiterThreeClick = function() {
		$scope.waiterOne = true;
		$scope.waiterTwo = true;
		$scope.waiterThree = true;
	}
	
	$scope.waiterFourClick = function() {
		$scope.waiterOne = true;
		$scope.waiterTwo = true;
		$scope.waiterThree = true;
		$scope.waiterFour = true;
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
	}
	
	$scope.mealTwoClick = function() {
		$scope.mealOne = true;
		$scope.mealTwo = true;
	}
	
	$scope.mealThreeClick = function() {
		$scope.mealOne = true;
		$scope.mealTwo = true;
		$scope.mealThree = true;
	}
	
	$scope.mealFourClick = function() {
		$scope.mealOne = true;
		$scope.mealTwo = true;
		$scope.mealThree = true;
		$scope.mealFour = true;
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
	}
	
	$scope.restaurantTwoClick = function() {
		$scope.restaurantOne = true;
		$scope.restaurantTwo = true;
	}
	
	$scope.restaurantThreeClick = function() {
		$scope.restaurantOne = true;
		$scope.restaurantTwo = true;
		$scope.restaurantThree = true;
	}
	
	$scope.restaurantFourClick = function() {
		$scope.restaurantOne = true;
		$scope.restaurantTwo = true;
		$scope.restaurantThree = true;
		$scope.restaurantFour = true;
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
	
}]);