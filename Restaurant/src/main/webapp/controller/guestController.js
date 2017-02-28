var guestModule = angular.module('guest.controller', []);



guestModule.controller('guestController', ['$scope', 'guestService', 'orderService', 'commonService', '$location','$interval', '$filter', '$state',
	function($scope, guestService, orderService, commonService,  $location, $interval, $filter, $state) {
	
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
		
		$scope.getMyFriends = function(operation) {
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
					if(operation == 'list') {
					toastr.info("You friend list is empty. Go, find you friends and send them friend request! :)");
					$location.path('guest');
					} else {
						toastr.info("You friend list is empty.");
					}
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
					$scope.getMyFriends('list');
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
			$scope.showOrderButton = true;
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
		  }

		  $scope.chooseTables = function() {
			 $scope.chosenTables = [];

			 $scope.reservation.date = $scope.reservation.date.replace("/", "-");//da bi se u bazi sacuvao
			 $scope.reservation.resId = $scope.chosenRes.id;
			 $scope.reservation.resName = $scope.chosenRes.name;
			 $scope.reservation.guestId = $scope.guest.id;
			 
			 showTables();

		  }
		  
		function showTables () {
			console.log("showTables");
				guestService.getTables($scope.chosenRes.id,$scope.reservation.date, $scope.reservation.startTime, $scope.reservation.endTime).then(
						function(response){
							//tables = response.data;
				
							//	alert(value.xPos+" , "+value.yPos)
								var stolovi = [];
								var red = [];
								var lastXPos = 0;
								var counter = 0;
								var maxX = 0;
								var maxY = 0;
								angular.forEach(response.data, function(value, key){	// punjenje matrice stolova
									if(value.xPos == lastXPos){	
										red.push(value);
									}
									counter++;
						
									if(counter==response.data.length && value.yPos==0){
										stolovi.push(red);
										red =[];
										red.push(value);
									}
								
									if((value.xPos != lastXPos) || counter==response.data.length ) {
										stolovi.push(red);
										red =[];
										red.push(value);
									}
									
									lastXPos = value.xPos;
								});
								$scope.tables = stolovi;
							
							
						});
			}
		
		
		$scope.setTableColor = function(id) {
			
			if($scope.chosenTables.indexOf(id) !== -1){
				var index = $scope.chosenTables.indexOf(id);
				  $scope.chosenTables.splice(index, 1); 
				 // $scope.tableToYellow(id);
			} else {
				$scope.chosenTables.push(id);
				 // $scope.tableToYellow(id);

			}
				
		}
		
		$scope.tableToYellow = function(id) {
			if($scope.chosenTables.indexOf(id) !== -1) {
				  return true;
			}
			return false;
		}
		
		$scope.reservate = function() {
			$('#myModalConfirmation').modal('hide');
			$('.modal-backdrop').remove();
			
			console.log("reservate fja");
			console.log($scope.reservation);
			console.log($scope.chosenTables);
			$scope.reservation.tables = makeArrayString($scope.chosenTables);
			
			 var request = guestService.addReservation($scope.reservation).then(function(response){
					$scope.data = response.data;
					//console.log($scope.data);
					//$scope.data.date = $filter('date')($scope.data.date, "dd.MM.yyyy");  // for type="date" binding; moze proizvoljan format datuma da se dobije
					return response;
				});
			 
			 
			 request.then(function (data) {
				 console.log("data " + $scope.data);
					if($scope.data != "") {
						toastr.success("Successful reservation!")
						$scope.createdReservation = $scope.data;
						$scope.createdReservation.date = $filter('date')($scope.createdReservation.date, "yyyy-MM-dd");
						$scope.reservation = new Object();
						$scope.invitedFriends = [];
						$scope.getMyFriends('invite');
						$state.go('guest.inviteFriends');


					} else {
						toastr.error("Unsuccessful reservation. Please, try again.");
						//$state.go('guest.chooseTables');
						$scope.chosenTables = [];//mislim da ce mi ove dve linije biti potrebne
						//da ispravno prikazem koji u stolovi u medjuvremenu postali rezervisani
						//jer je pretpostavka da ce i to biti jedan od razloga sto nece proci
						//dodavanje rezervacije
						showTables();

					}
				});

		}
		
		function makeArrayString(array) {
			var string= "";
			for (x in array)
				string += array[x] + ";";
			
			var size = string.length;
			string = string.substring(0, size-1); //da odsecem poslednji ;
			return string;
		}
		
		$scope.invitedFriends = [];
		$scope.inviteFriend = function(friendId) {
			var request = guestService.sendInvitation(friendId, $scope.createdReservation.id).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data == "OK") {
					$scope.invitedFriends.push(friendId);
					//console.log("invited friends " + $scope.invitedFriends)
					$scope.search.searchText = "";
					toastr.success("Successfully invited friend!");
					$scope.getMyFriends('invite');
				} else {
					toastr.error("Something wrong. Sending invitation failed. Please, try again.")
				}
			});
			
		}
		
		$scope.hideInvitationButton = function(id) {
			if($scope.invitedFriends.indexOf(id) !== -1) {
				  return true;
			}
			return false;
		}
		
		//ZA NARUCIVANJE		
		$scope.getDrinks = function() {
			$scope.showDishes = false;
			$scope.showDrinks = true;
			
			var request = guestService.getDrinks($scope.createdReservation.resId).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data.length != 0) {
					$scope.drinks = $scope.data;
				} else {
						toastr.info("There's no drink for order.");
				}
			});
			
		}
		
		$scope.getDishes = function() {
			$scope.showDrinks = false;
			$scope.showDishes = true;
			
			var request = guestService.getDishes($scope.createdReservation.resId).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data.length != 0) {
					$scope.dishes = $scope.data;
				} else {
						toastr.info("There's no dish for order.");
				}
			});
			
		}
		
		$scope.chosenDrinks = [];
		$scope.addDrink = function(id) {
			$scope.chosenDrinks.push(id);

		}
		
		$scope.chosenDishes = [];
		$scope.addDish = function(id) {
			$scope.chosenDishes.push(id);
		}
		
		$scope.order = function() {
			console.log("order");
			var dishesString = makeArrayString($scope.chosenDishes);
			var drinksString = makeArrayString($scope.chosenDrinks);
			var dishesAndDrinks = dishesString + "-" + drinksString;

			var request = guestService.order($scope.createdReservation.id, $scope.createdReservation.guestId, dishesAndDrinks).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data != "") {
					$scope.showOrderButton = false;
					toastr.success("Successful order!")
				} else {
						toastr.error("Unsuccessful order. Please, try again.");
				}
			});
		}
		
		//ISTORIJA POSETA
		$scope.getMyVisits = function() {
			var request = guestService.getMyVisits($scope.guest.id).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
				if($scope.data.size != 0) {
					$scope.myVisits = $scope.data;
				} else {
						toastr.info("You haven't visited any restaurant.");
				}
			});
		}

		$scope.getInvitedFriends = function(reservationId) {
			$scope.wereInvited = false;
			$scope.noInvitations = false;

			var request = guestService.getInvitedFriends(reservationId, $scope.guest.id).then(function(response){
				$scope.data = response.data;
				return response;
			});
				
			request.then(function (data) {
					if($scope.data != "") {
						$scope.wereInvited = false;
						if($scope.data[0].friendName == "nema poziva" && $scope.data.length == 1) {
							$scope.invitedFriends = [];
							$scope.noInvitations = true;

						} else {
							$scope.noInvitations = false;
							$scope.invitedFriends = $scope.data;
						}
						

					} else {
						
						$scope.invitedFriends = [];
						$scope.wereInvited = true;

					}
					
			});
		}
		
		
		$scope.rateVisit = function(reservation) {			
			var request = orderService.getOrder(reservation.id).then(function(response) {
				$scope.data = response.data;
				return response;
			
			});
			
			request.then(function (data) {
				$scope.order = $scope.data;
				$state.go('guest.rateVisit');
			});
			
			
		}
		
		//KRAJ ISTORIJE POSETA
		


		
		
		
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

