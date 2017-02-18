var passModule = angular.module('pass.controller', []);


passModule.controller('passController', ['$scope', 'commonService', '$location',
	
	function ($scope, commonService, $location) {
	
	function isLoggedIn() {
		commonService.getActiveUser().then(function (response) {				
			if(response.data !="") 
				$scope.activeUser = response.data;									
			else
				$location.path('login');
		});
	}
			
		isLoggedIn();
		
		$scope.currentVisible = false;
		$scope.newVisible = false;
		$scope.retypeVisible = false;
		
		$scope.newPassFilled = false;
		
		$scope.toggleCurrentVisible = function() {
			$scope.currentVisible = !$scope.currentVisible;
		}
		
		$scope.toggleNewVisible = function() {			
			$scope.newVisible = !$scope.newVisible;
		}
		
		$scope.toggleRetypeVisible = function() {
			$scope.retypeVisible = !$scope.retypeVisible;
		}
		
		$scope.changePassword = function () { 				
			var request = commonService.getActiveUser().then(function(response) {
				if(response.data != "") {
					$scope.activeUser = response.data;									
					return response;
			} else
				$location.path('login');
		
		});
			
			request.then(function(data) {			
				if($scope.current == $scope.newPassword) {
					toastr.error("New password must be different from current.");
					$scope.newPassword = null;
					$scope.retypedPassword = null;
					$scope.passForm.retypedPass.$dirty = false;
					$scope.passForm.newPass.$dirty = false;
					$scope.newPassFilled = false;
					
				
				}
														
				
				else if($scope.current != $scope.activeUser.password)
					toastr.error("Invalid current password");
				
				else {					
					$scope.activeUser.password = $scope.newPassword;
					var request = commonService.changePassword($scope.activeUser).then(function(response){
						$scope.data = response.data;
						alert(response.data);
						return response;
					});
					
					request.then(function (data) {
						if($scope.data != null) {
							toastr.success("Password was successfully changed.");							
							$location.path($scope.activeUser.userRole);
						} else {
							toastr.error("Something wrong");						
						}
						
					});
				}
			});	
		}
		
		$scope.cancel = function() {
			$location.path($scope.activeUser.userRole);
		}
				
		$scope.clearRetyped = function() {
			$scope.retypedPassword = "";
			
			if($scope.newPassword != null) {
				if($scope.newPassword.length == 0) {
					$scope.newPassFilled = false;					
				}												
				else
					$scope.newPassFilled = true;
			
			} else {
				$scope.newPassFilled = false;
				$scope.passForm.retypedPass.$dirty = false;
				$scope.passwordsMismatch = false;
				
			}
				
		
		}	
		
		$scope.comparePasswords = function() {
						
			if($scope.newPassword.length > 0) {
				if($scope.retypedPassword == null) {
					$scope.passForm.retypedPass.$invalid = true;
					$scope.passwordsMismatch = false;						
				
				} else if($scope.retypedPassword != $scope.newPassword) {
					$scope.passForm.retypedPass.$invalid = true;	
					$scope.passwordsMismatch = true;
				} else {
					$scope.passForm.retypedPass.$invalid = false;	
					$scope.passwordsMismatch = false;					
				}																			
			}						
		}
							
							
		
}]);