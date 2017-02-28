var invitationModule = angular.module('invitation.controller', []);


invitationModule.controller('invitationController', ['$scope', 'invitationService', 'commonService','$location','$stateParams',
  	function ($scope,invitationService, commonService, $location,  $stateParams) {
	
	$scope.showConfirmButtons = true;
	$scope.showOrderButton = false;
	
	function getInvitation() {
		var invitationId = $stateParams.invitationId;
		var request = invitationService.getInvitation(invitationId).then(function(response){
			$scope.data = response.data;
			return response;
		});
		
		request.then(function (data) {
			if ($scope.data != "")
				$scope.invitation = $scope.data;
			
		});
	}
	
	getInvitation();
	
	function isLoggedIn() {

		var request = commonService.getActiveUser().then(function(response){
			$scope.data = response.data;
			return response;
		});
			
		request.then(function (data) {
			if($scope.data !="") {
				if ($scope.data.id == $scope.invitation.friendId) {
					$scope.loggedIn = true;
					$scope.loggedOut = false;

				} else {
					$scope.loggedIn = false;
					$scope.loggedOut = true;
				}
			}
		});
	
	}
	
	function getReservation () {
		$scope.loggedIn = false;
		$scope.loggedOut = true;
		var reservationId = $stateParams.reservationId;
		
		var request = invitationService.getReservation(reservationId).then(function(response){
			$scope.data = response.data;
			return response;
		});
			
		request.then(function (data) {
			if($scope.data != "") {//null?
				$scope.invitationRes = $scope.data;	
				isLoggedIn();

			}
		});
	}
	
	getReservation();
	

	$scope.acceptInvitation = function () {
		var invitationId = $stateParams.invitationId;
		var request = invitationService.confirmInvitation(invitationId, 'accept').then(function(response){
			$scope.data = response.data;
			return response;
		});
			
		request.then(function (data) {
			if($scope.data != "") {//null?
				$scope.showConfirmButtons = false;
				$scope.showOrderButton = true;
				toastr.success("Successfully accepted invitation.")
				$scope.invitation = $scope.data;					
			} else {
				toastr.error("Unsuccessful invitation confirmation.")
			}
		});
	}
	
	$scope.rejectInvitation = function () {
		var invitationId = $stateParams.invitationId;

		var request = invitationService.confirmInvitation(invitationId, 'reject').then(function(response){
			$scope.data = response.data;
			return response;
		});
			
		request.then(function (data) {
			if($scope.data != "") {//null?
				$scope.showConfirmButtons = false;
				toastr.success("Successfully rejected invitation.")
				$scope.invitation = $scope.data;					
			} else {
				toastr.error("Unsuccessful invitation rejection.")
			}
		});
	}
	
	
	//ZA NARUCIVANJE	
	
	function makeArrayString(array) {
		var string= "";
		for (x in array)
			string += array[x] + ";";
		
		var size = string.length;
		string = string.substring(0, size-1); //da odsecem poslednji ;
		return string;
	}
	
	$scope.getDrinks = function() {
		$scope.showDishes = false;
		$scope.showDrinks = true;
		
		var request = invitationService.getDrinks($scope.invitationRes.resId).then(function(response){
			$scope.data = response.data;
			return response;
		});
			
		request.then(function (data) {
			if($scope.data.length != 0) {
				$scope.drinks = $scope.data;
			} else {
					toastr.info("There's no drink for order.");
			}
		});
		
	}
	
	$scope.getDishes = function() {
		$scope.showDrinks = false;
		$scope.showDishes = true;
		
		var request = invitationService.getDishes($scope.invitationRes.resId).then(function(response){
			$scope.data = response.data;
			return response;
		});
			
		request.then(function (data) {
			if($scope.data.length != 0) {
				$scope.dishes = $scope.data;
			} else {
					toastr.info("There's no dish for order.");
			}
		});
		
	}
	
	$scope.chosenDrinks = [];
	$scope.addDrink = function(id) {
		$scope.chosenDrinks.push(id);

	}
	
	$scope.chosenDishes = [];
	$scope.addDish = function(id) {
		$scope.chosenDishes.push(id);
	}
	
	$scope.order = function() {
		var dishesString = makeArrayString($scope.chosenDishes);
		var drinksString = makeArrayString($scope.chosenDrinks);
		var dishesAndDrinks = dishesString + "-" + drinksString;

		var request = invitationService.order($scope.invitationRes.id, dishesAndDrinks).then(function(response){
			$scope.data = response.data;
			return response;
		});
			
		request.then(function (data) {
			if($scope.data != "") {
				$scope.showOrderButton = false;
				toastr.success("Successful order!")
			} else {
					toastr.error("Unsuccessful order. Please, try again.");
			}
		});
	}
	
		
		
		
		
}]);
