var bartenderModule = angular.module('bartender.controller', []);


bartenderModule.controller('bartenderController', ['$scope', 'bartenderService', 'employeeService', '$location',
	
	function ($scope, bartenderService, employeeService, $location) {
	
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
			alert("Update bar");			
			var request = employeeService.updateInfo($scope.employee).then(function(response){
				$scope.data = response.data;
				alert(response.data);
				return response;
			});
			
			request.then(function (data) {
				if($scope.data != null) {
					toastr.success("Bartender info is updated");
					$location.path('bartender');
						
				} else {
					toastr.error("Update was unsuccessful");
				
				}
			});
		}
		
						
		
				
}]);