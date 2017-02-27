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
	
		
		
		
		
}]);
