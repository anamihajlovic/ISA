var resManagerModule = angular.module('resManager.controller', []);
var visina ;
var sirina;
var ID;
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
	
//////////////////////////////////////	//////////////////////////////////////////////////////////////////////
	$scope.newConfiguration = function(){
		document.getElementById("modalBtnCreateConfiguration").click();	
	}
	$scope.makeConfig = function(){
		var width = $("input[name='width']").val();
		sirina = width;
		//alert(width)
		var height = $("input[name='height']").val();
		visina = height;
		//alert(height)
		resManagerService.makeConfiguration(width, height).then(
			function(response){	
				toastr.success("Success!");	
				
				var request = resManagerService.findAllSegments().then(
						function (response) {
							$scope.segments = response.data;
							document.getElementById("modalBtnCreateSegments").click();
							document.getElementById("cancelConfil").click();
							
						}
					); 	
	
			});
	}
    function AllSegments () {
    	var request = resManagerService.findAllSegments().then(
				function (response) {
					$scope.segments = response.data;
				
				}
			); 
	}

	$scope.addSegment = function(){
		document.getElementById("modalBtnAddSegments").click();	
		$('.modal-table').css('width', '230px');
	}
	$scope.makeSegment = function(){	
		//alert ($scope.segment.segType)
		 
		$scope.segment.color = getRandomColor();
		resManagerService.addSegment($scope.segment).then(
			function(response){
				if(response.data == "dodato") {
				toastr.success("Success!");	
				AllSegments ();
				document.getElementById("cancelMakeSegment").click();
				
				}else {
					toastr.error("Something wrong!");	
				}
			});
	}
	$scope.showTables = function(){
		resManagerService.getTables().then(
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
	  function Tables(){
		  resManagerService.getTables().then(
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
  $scope.finishConf = function (){  
	  document.getElementById("cancelFinishSegment").click();
	  Tables();
	
	 
  }
  
  $scope.loadTables = function(){
	  Tables();
		
	}

  $scope.buttonShowTable = function (event){
	  
	  var request = resManagerService.findTable(event).then(function(response) {
			$scope.table = response.data;
			//alert($scope.foodstuff.quantity)
			
			 $('.modal-admin').css('width', '280px');
			document.getElementById("modalBtnShowTable").click();	
			AllSegments();
			return response;
		});	
  }
   $scope.updateTable = function (){
	   var request = resManagerService.updateTable($scope.table).then(function(response) { 
		$scope.data = response.data;
		return response;
	});			
		request.then(function (data) {
			if($scope.data != null) {
				toastr.success("Success!");	
				document.getElementById("cancelUpdateTable").click();	
				Tables();
			} else {
				toastr.error("Something wrong");
			
			}

	});
	
   }
   
   function getRandomColor() {
	    var letters = '0123456789ABCDEF';
	    var color = '#';
	    for (var i = 0; i < 6; i++ ) {
	        color += letters[Math.floor(Math.random() * 16)];
	    }
	    return color;
	}
   /////////////////////////////////////////RESTAURANT ORDERS/////////////////////////////////////////
  $scope.restaurantOrders= function () {
   	var request = resManagerService.findAllResOrders().then(
				function (response) {
					$scope.resOrders = response.data;
				
				}
			); 
	}
  $scope.buttonShowResOrderUnit = function(event){
	  var request = resManagerService.findShowResOrderUnits(event).then(
				function (response) {
					$scope.resOrderResOrderUnits = response.data;
					document.getElementById("modalBtnShowResOrderUnit").click();
					 $('.modal-showOrderUnit').css('width', '350px');
				}
			); 
	}
	$scope.buttonNewResOrder = function(){
		document.getElementById("modalBtnAddResOrder").click();
			var request = resManagerService.saveFirstTimeResOrder().then(function(response) {	
			$scope.newID = response.data;
			//alert($scope.newID );
			ID =$scope.newID;
		//alert(ID)
			return response;
		});			
			
	
		
	}	 
		 
  $scope.addResOrderUnit = function(){
	  //alert("uslo")
	   $('.modal-showOrderUnit').css('width', '350px');
	  document.getElementById("modalBtnOrderUnit").click();
  }

  $scope.makeOrderUnit = function(){
	  alert($scope.resOrderUnit.orderFoodstuff)
	  alert($scope.resOrderUnit.orderQuantity)
	  $scope.resOrderUnit.ResOrder = ID;
	   alert($scope.resOrderUnit.ResOrder)
	  var request = resManagerService.saveResOrderUnit($scope.resOrderUnit).then(function(response) {	
			$scope.newResOrderUnits = response.data;
			
			
		});			
			
	  
  }
   $scope.endMakeResOrder = function(){
	   $scope.resOrder.id = ID;
		var request = resManagerService.makeResOrder($scope.resOrder).then(function(response) {
			$scope.data = response.data;
			//alert(response.data)
			return response;
		});			
			request.then(function (data) {
				if($scope.data != null) {
					toastr.success("Success!");	
					
				
				 var request = resManagerService.findAllResOrders().then(
							function (response) {
								$scope.resOrders = response.data;
							
							}
						); 
				 document.getElementById("cancelResOrder").click();
						
				} else {
					toastr.error("Something wrong");
				
				}

		});
	   
   }
   $scope.cancelMakeResOrder =function(){
	   var request = resManagerService.deleteResOrderUnits().then(function(response) {
			$scope.data = response.data;
			//alert(response.data)
			return response;
		});
	   
   }

   
   
}]);
