var cookModule = angular.module('cook.controller', []);


cookModule.controller('cookController', ['$scope', 'cookService', 'employeeService', '$location',
	
	function ($scope, cookService, employeeService, $location) {
	
		function isLoggedIn() {
			employeeService.getEmployee().then(function (response) {				
					if(response.data !="") 
						$scope.employee = response.data;									
					else
						$location.path('login');
				}
			);
		}
	
		isLoggedIn();
		
		
		$scope.updateInfo = function () {    							
			var request = employeeService.updateInfo($scope.employee).then(function(response){
				$scope.data = response.data;
				alert(response.data);
				return response;
			});
			
			request.then(function (data) {
				if($scope.data != null) {
					toastr.success("Cook info is updated");
					$location.path('cook');
						
				} else {
					toastr.error("Update was unsuccessful");
				
				}
			});
		}
		
		$scope.changePassword = function() {
			alert("Pass cook change");				
		}
				
}]);