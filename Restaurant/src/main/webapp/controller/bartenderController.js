var bartenderModule = angular.module('bartender.controller', []);


bartenderModule.controller('bartenderController', ['$scope', 'bartenderService','$location',
	
	function ($scope, bartenderService, $location) {
	
		function isLoggedIn() {
			bartenderService.getBartender().then(function (response) {				
					if(response.data !="") 
						$scope.employee = response.data;									
					else
						$location.path('login');
				}
			);
		}
	
		isLoggedIn();
		
		
		$scope.updateInfo = function () {    			
			alert("Update info");			
			var request = bartenderService.updateBartenderInfo($scope.employee).then(function(response){
				$scope.data = response.data;
				alert(response.data);
				return response;
			});
			
			request.then(function (data) {
				if($scope.data != null) {
					toastr.success("Info is updated");
					$location.path('bartender');
						
				} else {
					toastr.error("Something wrong");
				
				}
			});
		}
		
		$scope.changePassword = function() {
			alert("Pass change");				
		}
		
		
				
}]);