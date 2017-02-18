var passModule = angular.module('pass.controller', []);


passModule.controller('passController', ['$scope', 'commonService', '$location',
	
	function ($scope, commonService, $location) {
					
		$scope.currentVisible = false;
		$scope.newVisible = false;
		$scope.retypeVisible = false;
		
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
				if($scope.current != $scope.activeUser.password)
					toastr.error("Invalid current password");
				
				else {
					toastr.success("SUPEEEEH");
					$scope.activeUser.password = $scope.newPassword;
					var request = commonService.changePassword($scope.activeUser).then(function(response){
						$scope.data = response.data;
						alert(response.data);
						return response;
					});
					
					request.then(function (data) {
						if($scope.data != null) {
							toastr.success("Password was successfully changed.");													
						} else {
							toastr.error("Something wrong");						
						}
						
					});
				}
			});	
		}
						
							
							
		
}]);