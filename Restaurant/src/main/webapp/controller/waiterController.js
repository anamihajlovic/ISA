var waiterModule = angular.module('waiter.controller', []);


waiterModule.controller('waiterController', ['$scope', 'waiterService', 'employeeService', '$location',
	
	function ($scope, waiterService, employeeService, $location) {
	
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
			alert("Update waiter");			
			var request = employeeService.updateInfo($scope.employee).then(function(response){
				$scope.data = response.data;
				alert(response.data);
				return response;
			});
			
			request.then(function (data) {
				if($scope.data != null) {
					toastr.success("Waiter info is updated");
					$location.path('waiter');
						
				} else {
					toastr.error("Update was unsuccessful");
				
				}
			});
		}
		
		$scope.cancel = function() {
			isLoggedIn();
			$location.path('waiter');
		}
		
		
				
}]);