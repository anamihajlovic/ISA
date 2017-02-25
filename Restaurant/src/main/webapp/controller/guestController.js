var guestModule = angular.module('guest.controller', []);



guestModule.controller('guestController', ['$scope', 'guestService','commonService', '$location','$interval',
	function($scope, guestService, commonService,  $location, $interval) {
	
		$scope.numOfFriendRequest= 0;
		
		function isLoggedIn() {
			commonService.getActiveUser().then(function (response) {				
				if(response.data !="") {
					$scope.guest = response.data;
					$scope.getNumOfFriendRequest();
				}
				//else
					//$scope.callIsLoggedIn = false;
					//$location.path('login');
			}
		);
	}

	isLoggedIn();
	$scope.infoMode = true;
	
	$scope.getNumOfFriendRequest = function() {
		if($scope.guest != undefined) {
			guestService.getNumOfFriendRequest($scope.guest.id).then(function(response) {
				$scope.numOfFriendRequest = response.data;
			});
		}
	}
	
	var myInterval = $interval( function(){ $scope.getNumOfFriendRequest(); }, 30000);
	
	//nakon logout da prekine interval
	$scope.$on('$destroy', function(){
	    $interval.cancel(myInterval)
	});
		
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
		$scope.search = {};
		$scope.search.searchText = "";
		$scope.search.searchRes = "";
		
		$scope.findFriends = function() {
			$scope.search.searchText = "";
			var request = guestService.findFriends($scope.guest.id).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data.length != 0) {
					$scope.possibleFriends = $scope.data;
					$scope.filtered = $scope.possibleFriends;

				} else {
						toastr.info("You're already friend with everyone or someone has to accept friend request! :)");
						$location.path('guest');
					
				}
			});
		}
		
		$scope.addFriend = function(friendId) {
			var request = guestService.addFriend($scope.guest.id, friendId).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data == "OK") {
					$scope.search.searchText = "";
					toastr.success("Successfully sent friend request!");
					$scope.findFriends();
				} else {
						toastr.error("Sending friend request was unsuccessful. Please, try again");
				}
			});
		}
		
		$scope.getMyFriends = function() {
			$scope.search.searchText = "";
			var request = guestService.getMyFriends($scope.guest.id).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data.length != 0) {
					$scope.myFriends = $scope.data;
					$scope.filtered = $scope.myFriends;
				} else {
						toastr.info("You friend list is empty. Go, find you friends and send them friend request! :)");
						$location.path('guest');
				}
			});
		}
		
		$scope.deleteFriend = function(friendId) {
			var request = guestService.deleteFriend($scope.guest.id, friendId).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data == "OK") {
					$scope.search.searchText = "";
					toastr.success("Successfully deleted friend!");
					$scope.getMyFriends();
				} else {
						toastr.error("Deleting friend was unsuccessful. Please, try again");
				}
			});
		}
		
		 $scope.myFilter = function(friendsData) { 
			var friends = friendsData;
			$scope.filtered = [];
		    $scope.reverse = !$scope.reverse;//razmisliti:ili vratiti samo na default vrednosti propertyName i reverse


			var search = $scope.search.searchText.toLowerCase().replace(/\s/g, "");
			
			for (var i=0; i<friends.length; i++) {
					var fullName = friends[i].firstName+friends[i].lastName;
					var fullName2 = friends[i].lastName+friends[i].firstName;
					if (fullName.toLowerCase().indexOf(search) !== -1 || fullName2.toLowerCase().indexOf(search) !== -1  ) {
						$scope.filtered.push(friends[i]);
					}
					

			}
			$scope.sortBy($scope.propertyName);
		}
		 
		 $scope.propertyName = 'lastName';
		 $scope.reverse = false;

		  $scope.sortBy = function(propertyName) {
		    $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
		    $scope.propertyName = propertyName;
		   
		   
		    var request = guestService.sortFriends($scope.filtered, $scope.propertyName, $scope.reverse).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data.length != 0) {
					$scope.filtered = $scope.data;
				}
			});
		    
		  };
		 
		 
		 
		$scope.getFriendRequests = function() {
			$scope.getNumOfFriendRequest();//za slucaj da nije proslo 30sec pa da se update-uje prilikom klika
			var request = guestService.getFriendRequests($scope.guest.id).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data.length != 0) {
					$scope.friendRequests = $scope.data;
				} else {
						toastr.info("There's no friend requests.");
						$location.path('guest');
				}
			});
		}
		
		$scope.confirm = function(friendId) {
			var request = guestService.confirm(friendId, $scope.guest.id).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data == "OK") {
					toastr.success("Successfully accepted friend request!");
					$scope.getNumOfFriendRequest();
				} else {
						toastr.error("Accepting friend request was unsuccessful. Please, try again");
				}
			});
		}
		
		$scope.deleteRequest = function(friendId) {
			var request = guestService.deleteRequest(friendId, $scope.guest.id).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data == "OK") {
					toastr.success("Successfully deleted friend request!");
					$scope.getNumOfFriendRequest();
				} else {
						toastr.error("Deleting friend request was unsuccessful. Please, try again");
				}
			});
		}
		
		//RESTORANI
		
		$scope.getRestaurants = function () {
			$scope.search.searchRes = "";
			guestService.getRestaurants().then(function(response){
				$scope.restaurants = response.data;
				$scope.filteredRes = $scope.restaurants;
				return response;
			});
				
		}
		
		$scope.propertyNameRes = 'name';
		$scope.reverseRes = false;
		
		$scope.sortResBy = function(propertyName) {
		    $scope.reverseRes = ($scope.propertyNameRes === propertyName) ? !$scope.reverseRes : false;
		    $scope.propertyNameRes = propertyName;
		   
		   
		    var request = guestService.sortRestaurants($scope.propertyNameRes, $scope.reverseRes).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data.length != 0) {
					$scope.restaurants = $scope.data;

					if ($scope.searchRes == "")
						$scope.filteredRes = $scope.restaurants;
					else
						$scope.myFilterRes($scope.restaurants);

				}
			});
		    
		  };
		  
		  $scope.myFilterRes = function(resData) { 
				var res = resData;
				$scope.filteredRes = [];

				var search = $scope.search.searchRes.toLowerCase();
				
				for (var i=0; i<res.length; i++) {
						var name = res[i].name;
						var type = res[i].restaurant_type;
						if (name.toLowerCase().indexOf(search) !== -1 || type.toLowerCase().indexOf(search) !== -1  ) {
							$scope.filteredRes.push(res[i]);
						}
						

				}
			}
		  
		  $scope.reservation;
		  
		  $scope.chooseRes = function(restaurant) {
			  $scope.chosenRes = restaurant;
			  console.log($scope.chosenRes.name);
			 // $location.path('startReservation');
		  }
		  
		  $scope.chooseTables = function() {
			  console.log("date" + $scope.reservation.date);
			  console.log("time" + $scope.reservation.time);
			  console.log("duration" + $scope.reservation.duration);
			  var t =  $scope.reservation.time + $scope.reservation.duration;
			  console.log("ostaje do " + t);


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

