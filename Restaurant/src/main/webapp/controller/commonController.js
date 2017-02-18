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
						toastr.success("Welcome, " + $scope.data.firstName + " !");
						if ($scope.data.userRole == "guest") {
							$location.path('guest');
						} else if ($scope.data.userRole == "sysManager") {
							$location.path('sysManager');
							
						} else if ($scope.data.userRole == "resManager") {
							$location.path('resManager');
						} else if ($scope.data.userRole == "waiter") {
							$location.path('waiter');
							
						} else if ($scope.data.userRole == "cook") {
							$location.path('cook');
							
						} else if ($scope.data.userRole == "bartender") {
							$location.path('bartender');
							
						} else if ($scope.data.userRole == "bidder") {
							$location.path('bidder');
						}
							
					} else {
						if ($scope.data.password == "neuspesno") {
							toastr.error("Incorrect username and/or password!");
						} else {
							var message = "";
							if ($scope.data.password.indexOf("email") != -1)
								message += "Email field can't be empty. <br\>Email pattern must be example@example.com.<br\>";
							if ($scope.data.password.indexOf("password") != -1)
								message +="Password field can't be empty.";
							
							toastr.error(message);
						}
					}

			});
		}
		
		/*$scope.logout = function() {
			commonService.logout().then(function(reponse) {
				alert("log ouut")
				toastr.success("Goodbye");
				$location.path('login');
			});
		}*/					
		
		
}]);