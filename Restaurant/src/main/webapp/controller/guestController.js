var guestModule = angular.module('guest.controller', []);



guestModule.controller('guestController', ['$scope', 'guestService','commonService', '$location',
	function($scope, guestService, commonService,  $location) {
	
		function isLoggedIn() {
			commonService.getActiveUser().then(function (response) {				
				if(response.data !="") 
					$scope.guest = response.data;									
				//else
					//$location.path('login');
			}
		);
	}

	isLoggedIn();
	$scope.infoMode = true;

		$scope.submitRegister = function() {
			var request = guestService.register($scope.guest).then(function(response) {
				$scope.data = response.data;
				return response;
			});
			
			request.then(function (data) {

				if($scope.data == "OK") {
					toastr.success("Successful registration! <br/> You'll receive email with activation link. <br/>" +
							"After successful activation, you'll be able to log in.");
					$location.path('login');
				} else if ($scope.data == "FAILURE") {
					toastr.error("Invalid email. Please, try again.");
				} else {
					var message = "";
					
					if ($scope.data.indexOf("first") != -1)
						message +="First name field can't be empty.<br>";
					if ($scope.data.indexOf("last") != -1)
						message +="Last name field can't be empty.<br>";
					if ($scope.data.indexOf("email") != -1)
						message += "Email field can't be empty. <br>Email pattern must be example@example.com.<br>";
					if ($scope.data.indexOf("password") != -1)
						message +="Password field can't be empty.<br>";
					if ($scope.data.indexOf("confirm") != -1)
						message +="Confirm password field can't be empty.<br>";
					if ($scope.data.indexOf("match") != -1)
						message +="Confirm password must be equal to password.<br>";
					
					toastr.error(message);
				}
			});
			

		}
		
		$scope.editProfile = function() {
			$scope.infoMode = false;
		}
		
		$scope.saveChanges = function() {
			$scope.infoMode = true;   			
			var request = guestService.updateProfile($scope.guest).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data != null) {
					isLoggedIn();
					toastr.success("Successfully updated profile data.");
				} else {
						toastr.error("Profile update was unsuccessful. Please, try again.");
					
				}
			});
			
		}
		
		
		$scope.findFriends = function() {
			var request = guestService.findFriends($scope.guest.id).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data.length != 0) {
					$scope.possibleFriends = $scope.data;
				} else {
						toastr.info("You're already friend with everyone or you're waiting for them to accept your friend request! :)");
					
				}
			});
		}
		
		
		
}]);


/*guestModule.directive("matchPassword", function () {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=matchPassword"
        },
        link: function(scope, element, attributes, ngModel) {
            ngModel.$validators.matchPassword = function(modelValue) {
                return modelValue == scope.otherModelValue;
            };

            scope.$watch("otherModelValue", function() {
                ngModel.$validate();
            });
        }
    };
});*/

