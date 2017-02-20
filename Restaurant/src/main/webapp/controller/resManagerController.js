var resManagerModule = angular.module('resManager.controller', []);
 

resManagerModule.controller('resManagerController', ['$scope', 'resManagerService','$location',
  	function ($scope,resManagerService, $location) {
	
	function checkRights() {
		resManagerService.checkRights().then(
			function (response) {
				$scope.restaurantManager = response.data;
				if(response.data!="") {	
				} else {					
					 $location.path('login');
				}
			}
		);
	}
	function Restaurant() {
		resManagerService.getRestaurant().then(
			function (response) {
				$scope.restaurant = response.data;
				if(response.data!="") {	
				} else {					
					 $location.path('login');
				}
			}
		);
	}
	//Restaurant();
	checkRights();	
	$scope.updateResManager = function(){
		checkRights();		
	}
	$scope.updateRestaurant = function(){
		Restaurant();
		checkRights();		
	}
	$scope.newEmpolyee = function(){
		checkRights();	
		/*$scope.employee.firstName = '';
		$scope.employee.lastName = '';
		$scope.employee.email = '';
		$scope.employee.password = '';
		$scope.employee.birthday = '';
		$scope.employee.clothesSize= '';
		$scope.employee.shoesSize = '';
		$scope.employee.userRole = '';*/
	}
	$scope.newBidder = function(){
		checkRights();	
		/*$scope.bidder.firstName = '';
		$scope.bidder.lastName = '';
		$scope.bidder.email = '';
		$scope.bidder.password = '';*/
	}
	
	
	$scope.saveEmployee= function () {    
		
		//alert("pre requesta " +$scope.employee.userRole);
		if($scope.employee.userRole=='waiter'){
		resManagerService.saveWaiter($scope.employee).then(function(response) {		
			$scope.data = response.data;
			return response;
			});			
			request.then(function (data) {
				if($scope.data == "dodato") {
					toastr.success("Success!");	
					/*$scope.employee.firstName = '';
					$scope.employee.lastName = '';
					$scope.employee.email = '';
					$scope.employee.password = '';
					$scope.employee.birthday = '';
					$scope.employee.clothesSize= '';
					$scope.employee.shoesSize = '';
					$scope.employee.userRole = '';*/
						
				} else {
					toastr.error("Something wrong");			
				}
	
			});
		}else if ($scope.employee.userRole=="cook"){
			
			var request = resManagerService.saveCook($scope.employee).then(function(response) {				
				$scope.data = response.data;
				return response;
				});			
				request.then(function (data) {
					if($scope.data != null) {
						toastr.success("Success!");	
							
					} else {
						toastr.error("Something wrong");
					
					}

				});
			
			
		}else {
			var request = resManagerService.saveBartender($scope.employee).then(function(response) {				
				$scope.data = response.data;
				return response;
				});			
				request.then(function (data) {
					if($scope.data != null) {
						toastr.success("Success!");	
							
					} else {
						toastr.error("Something wrong");
					
					}

				});

		}

	}
	
////////////////////////////////////////	
	$scope.saveBidder= function () {    
		
		//alert("pre requesta " + $scope.bidder);
		var request = resManagerService.saveBidder($scope.bidder).then(function(response) {	
		$scope.data = response.data;
		//alert($scope.data );
		return response;
	});			
		request.then(function (data) {
			if($scope.data != null) {
				toastr.success("Success!");	
				/*$scope.bidder.firstName = '';
				$scope.bidder.lastName = '';
				$scope.bidder.email = '';
				$scope.bidder.password = '';*/
					
			} else {
				toastr.error("Something wrong");
			
			}

	});
	}
	$scope.viewAll = function(){
		checkRights();
		 resManagerService.findAllWaiters().then(
				function (response) {
					$scope.waiters = response.data;
				}
			); 	
		 resManagerService.findAllCooks().then(
					function (response) {
						$scope.cooks = response.data;
					}
				);
		 resManagerService.findAllBartenders().then(
					function (response) {
						$scope.bartenders = response.data;
					}
				);
		 resManagerService.findAllBidders().then(
					function (response) {
						$scope.bidders = response.data;
					}
				); 
		 resManagerService.findAllFoodstuffs().then(
					function (response) {
						$scope.foodstuffs = response.data;
					}
				);
		
		
	}
	$scope.menu = function(){
		checkRights();
		
		 resManagerService.findAllDishes().then(
				function (response) {
					$scope.dishes = response.data;
				}
			); 	
	}
	$scope.drinkCard = function(){
		checkRights();
		 resManagerService.findAllDrinks().then(
				function (response) {
					$scope.drinks = response.data;
				}
			); 	
	}
	
	function AllDishes(){
		 resManagerService.findAllDishes().then(
					function (response) {
						$scope.dishes = response.data;
					}
				); 
	}
	function AllDrinks(){
		resManagerService.findAllDrinks().then(
				function (response) {
					$scope.drinks = response.data;
				}
			);
		
	}
	function AllFoodstuffs() {  
		resManagerService.findAllFoodstuffs().then(
				function (response) {
					$scope.foodstuffs = response.data;
				}
			);
	};
	function AllWaiters() {  
		//alert("uslo u controller")		
		 resManagerService.findAllWaiters().then(
			function (response) {
				$scope.waiters = response.data;
			}
		); 	
	};
	
	function AllCooks() {  
		//alert("uslo u controller")		
		var request = resManagerService.findAllCooks().then(
			function (response) {
				$scope.cooks = response.data;
			}
		); 	
	};

	function AllBartenders() {  
		//alert("uslo u controller")		
		var request = resManagerService.findAllBartenders().then(
			function (response) {
				$scope.bartenders = response.data;
			}
		); 	
	};

	function AllBidders() {  
		//alert("uslo u controller")		
		var request = resManagerService.findAllBidders().then(
			function (response) {
				$scope.bidders = response.data;
			}
		); 	
	};
////////////////////////////////////
	
	
	$scope.buttonDeleteWaiter= function (event) { 
		var request = resManagerService.buttonDeleteWaiter(event).then(function(response) {
		$scope.data = response.data;
		return response;
	});			
		request.then(function (data) {
			if($scope.data != "no") {
				toastr.success("Success!");	
				AllWaiters();
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	$scope.buttonDeleteCook= function (event) { 
		var request = resManagerService.buttonDeleteCook(event).then(function(response) {
		$scope.data = response.data;

		return response;
	});			
		request.then(function (data) {
			if($scope.data != "no") {
				toastr.success("Success!");	
				AllCooks();
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	$scope.buttonDeleteBartender= function (event) { 
		var request = resManagerService.buttonDeleteBartender(event).then(function(response) {
		$scope.data = response.data;
		return response;
	});			
		request.then(function (data) {
			if($scope.data != "no") {
				toastr.success("Success!");	
				AllBartenders();
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	$scope.buttonDeleteBidder= function (event) { 
		var request = resManagerService.buttonDeleteBidder(event).then(function(response) {
		$scope.data = response.data;
		return response;
	});			
		request.then(function (data) {
			if($scope.data != "no") {
				toastr.success("Success!");	
				AllBidders();
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	
	$scope.buttonDeleteFoodstuff= function (event) { 
		var request = resManagerService.buttonDeleteFoodstuff(event).then(function(response) {
		$scope.data = response.data;
		return response;
	});			
		request.then(function (data) {
			if($scope.data != "no") {
				toastr.success("Success!");	
				AllFoodstuffs();
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	$scope.buttonDeleteDrink= function (event) { 
		var request = resManagerService.buttonDeleteDrink(event).then(function(response) {
		$scope.data = response.data;
		return response;
	});			
		request.then(function (data) {
			if($scope.data != "no") {
				toastr.success("Success!");	
				resManagerService.findAllDrinks().then(
						function (response) {
							$scope.drinks = response.data;
						}
					); 
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	
	$scope.buttonDeleteDish= function (event) { 
		var request = resManagerService.buttonDeleteDish(event).then(function(response) {
		$scope.data = response.data;
		return response;
	});			
		request.then(function (data) {
			if($scope.data != "no") {
				toastr.success("Success!");	
				 resManagerService.findAllDishes().then(
							function (response) {
								$scope.dishes = response.data;
							}
						); 	
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	$scope.update= function () {    
		var request = resManagerService.updateResManager($scope.restaurantManager).then(function(response) {
		$scope.data = response.data;
		//alert(response.data)
		return response;
	});			
		request.then(function (data) {
			if($scope.data != null) {
				toastr.success("Success!");	
					
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	$scope.updateRes= function () {  
		var request = resManagerService.updateRestaurant($scope.restaurant).then(function(response) {
		$scope.data = response.data;
	//	alert(response.data)
		return response;
	});			
		request.then(function (data) {
			if($scope.data != null) {
				toastr.success("Success!");	
					
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	$scope.buttonShowFoodstuff = function (event) { 
		var request = resManagerService.findFoodstuff(event).then(function(response) {
		$scope.foodstuff = response.data;
		//alert($scope.foodstuff.quantity)
		document.getElementById("modalBtnShowFoodstuff").click();
		return response;
	});	
	}

	$scope.buttonUpdateFoodstuff= function () {  
		var request = resManagerService.buttonUpdateFoodstuff($scope.foodstuff).then(function(response) {
		$scope.data = response.data;
	//	alert(response.data)
		return response;
	});			
		request.then(function (data) {
			if($scope.data != null) {
				toastr.success("Success!");	
				AllFoodstuffs();
				document.getElementById("closeFoodstuff").click();
				
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
	////////////////////////////////////////////////////
	$scope.buttonShowDish = function (event) { 
		var request = resManagerService.findDish(event).then(function(response) {
		$scope.dish = response.data;
		document.getElementById("modalBtnShowDish").click();
		return response;
	});	
	}

	$scope.buttonUpdateDish= function () {  
		var request = resManagerService.buttonUpdateDish($scope.dish).then(function(response) {
		$scope.data = response.data;
	//	alert(response.data)
		return response;
	});			
		request.then(function (data) {
			if($scope.data != null) {
				toastr.success("Success!");	
				AllDishes();
				document.getElementById("closeDish").click();
				
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
////////////////////////////////////////////////////
	$scope.buttonShowDrink = function (event) { 
		var request = resManagerService.findDrink(event).then(function(response) {
		$scope.drink = response.data;
		document.getElementById("modalBtnShowDrink").click();
		return response;
	});	
	}

	$scope.buttonUpdateDrink= function () {  
		var request = resManagerService.buttonUpdateDrink($scope.drink).then(function(response) {
		$scope.data = response.data;
	//	alert(response.data)
		return response;
	});			
		request.then(function (data) {
			if($scope.data != null) {
				toastr.success("Success!");	
				AllDrinks();
				document.getElementById("closeDrink").click();
				
			} else {
				toastr.error("Something wrong");
			
			}

	});
}
/////////////////////////////////////////
	$scope.buttonAddFoodstuff = function(){
		document.getElementById("modalBtnAddFoodstuff").click();	
	}
	$scope.saveFoodstuff = function(){
		var request = resManagerService.saveFoodstuff($scope.food).then(function(response) {	
		$scope.data = response.data;
		return response;
	});			
		request.then(function (data) {
			if($scope.data != null) {
				toastr.success("Success!");	
				AllFoodstuffs();
				document.getElementById("cancelFoodstuff").click();
			
			} else {
				toastr.error("Something wrong");
			
			}

	});
		
}//////////////////////////////////////////
	$scope.buttonAddDish = function(){
		document.getElementById("modalBtnAddDish").click();	
	}
	
	
	$scope.saveDish = function(){
		var request = resManagerService.saveDish($scope.newdish).then(function(response) {	
		$scope.data = response.data;
		return response;
	});			
		request.then(function (data) {
			if($scope.data != null) {
				toastr.success("Success!");	
				AllDishes();
				document.getElementById("cancelDish").click();
			
			} else {
				toastr.error("Something wrong");
			
			}

	});
		
}
	//////////////////////////////////
	$scope.buttonAddDrink = function(){
		document.getElementById("modalBtnAddDrink").click();	
	}
	
	
	$scope.buttonsaveDrink = function(){
		var request = resManagerService.saveDrink($scope.newdrink).then(function(response) {	
		$scope.data = response.data;
		return response;
	});			
		request.then(function (data) {
			if($scope.data != null) {
				toastr.success("Success!");	
				AllDrinks();
				document.getElementById("cancelDrink").click();
			
			} else {
				toastr.error("Something wrong");
			
			}

	});
		
}
//////////////////////////////////////	
	
}]);
