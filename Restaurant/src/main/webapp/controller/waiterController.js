var waiterModule = angular.module('waiter.controller', []);


waiterModule.controller('waiterController', ['$scope', 'waiterService', 'employeeService', 'resManagerService', '$location',
	
	function ($scope, waiterService, employeeService, resManagerService, $location) {
	
		function isLoggedIn() {
			employeeService.getEmployee().then(function (response) {				
					if(response.data !="") {
						$scope.employee = response.data;
						checkWorkShift($scope.employee);						
					}
						
					else
						$location.path('login');
				}
			);
		}
		
		
		function checkWorkShift(employee) {		
			var request = employeeService.checkWorkShift(employee).then(function (response) {				
				if(response.data != "") {
					$scope.allowAction = true;
					$scope.currentShift = response.data;
					showTables($scope.currentShift);
					return response;
				} else
					$scope.allowAction = false;
				
			});					
		}
		
		
		function showTables(currentShift) {	
			alert($scope.allowAction)
			$scope.assignedResponsabilites = $scope.currentShift.responsabilites;
			var waiterReons = [];
			for(i = 0; i<$scope.assignedResponsabilites.length; i++) {
				var resp = $scope.assignedResponsabilites[i];
				for(j = 0; j<resp.waiters.length; j++) {
					var waiter = resp.waiters[j];
					if(waiter.id == $scope.employee.id)
						waiterReons.push(resp.reon);
				}
			}
			
			resManagerService.getTables().then(					
					function(response){							
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
							
							for(i = 0; $scope.tables.length; i++) {
								var row =$scope.tables[i];
								for(j = 0; j<row.length; j++) {
									var table = row[j];
									if(waiterReons.indexOf(table.reon) != -1)
										table.reonColor = '#c9c9ff';
									else
										table.reonColor = 'white';
									
									if(table.state == 'not_exists')
										table.showTable = false;
									else 
										table.showTable = true;
								}
							}
						
					});	
											
		}
		
	
		
		isLoggedIn();
				
		$scope.updateInfo = function () {    							
			var request = employeeService.updateInfo($scope.employee).then(function(response){
				$scope.data = response.data;				
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
		
		
		$scope.buttonShowTable = function (event){			 
			  var request = resManagerService.findTable(event).then(function(response) {
					$scope.table = response.data;										
					 $('.modal-admin').css('width', '280px');
					document.getElementById("modalBtnShowTable").click();					
					return response;
				});	
		  }
		
}]);