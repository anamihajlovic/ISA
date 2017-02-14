var commonModule = angular.module('common.controller', []);
 

commonModule.controller('commonController', ['$scope', 'commonService','$location',
  	function ($scope,commonService, $location) {
	
		
		$scope.submitLogin = function () {            
				var request = commonService.login($scope.user).then(function(response) {
				$scope.data = response.data;
				return response;
			});
			
				request.then(function (data) {
					if($scope.data.firstName != "neuspesno") {
						toastr.success("Welcome, " + $scope.data.firstName + "!");
						if ($scope.data.userRole == "guest") {
							console.log("gost je");
							$location.path('guest');
							//console.log($location);
						} else if ($scope.data.userRole == "sysManager") {
							console.log("sysManager je");

						}
							
					} else {
						toastr.error("Incorrect username and/or password!");
					}


			
			});
		}
		
		
		
		
}]);