var calendarModule = angular.module('calendar.controller', ['ui.calendar']);

calendarModule.controller("calendarController", ['$scope', '$location', 'employeeService',
	
	function ($scope, $location, employeeService, $filter) {
		
		$scope.eventSources = [];
		$scope.eventVisible = false;
	
		
		var request = employeeService.getEmployee().then(function (response) {	
			
			if(response.data !="") {
				$scope.employee = response.data;
				
				var newRequest = employeeService.readWorkSchedule($scope.employee).then(function (response){
					var shifts = response.data;
					$scope.events = [];
					for(i=0; i<shifts.length; i++) {												
									
						if(shifts[i].shiftType == 'firstShift')								
							event = {id: shifts[i].id, title: 'First shift', start: shifts[i].day, color : '#c9c9ff', textColor: 'black'}
						else if(shifts[i].shiftType == 'secondShift')
							event = {id: shifts[i].id, title: 'Second shift', start: shifts[i].day, color : '#e1f7d5', textColor: 'black'}
						else if(shifts[i].shiftType == 'thirdShift')
							event = {id: shifts[i].id, title: 'Third shift', start: shifts[i].day, color : '#f1cbff', textColor: 'black'}
						else
							event = {id: shifts[i].id, title: shifts[i].startTime + '-' + shifts[i].endTime, start: shifts[i].day, color : '#ffbdbd', textColor: 'black'}
								
						$scope.events.push(event);
									
					}
					 $scope.eventSources.push($scope.events); 																	
				});
				
				return response;
			} 			
			else
				$location.path('login');
		});							
	
	 
 
    $scope.eventClick = function(event){    	     
    	 
        var request =  employeeService.getWorkShift(event.id).then(function(response) {     
        	
        	$scope.selectedShift = response.data;
        	
        	if($scope.selectedShift.shiftType == "firstShift")
        		$scope.selectedShift.name = "First shift";
        	
        	else if($scope.selectedShift.shiftType == "secondShift")        	
        		$scope.selectedShift.name = "Second shift";
        	
        	else if($scope.selectedShift.shiftType == "thirdShift")
        		$scope.selectedShift.name = "Third shift";
        	
        	else
        		$scope.selectedShift.name = "Shift";
        	
        	if($scope.employee.userRole == 'bartender') {
        		$scope.assignedEmployees = $scope.selectedShift.bartenders;
        		$scope.employeeType = "Bartenders";
        	
        	} else if($scope.employee.userRole == 'cook') {
        		$scope.assignedEmployees = $scope.selectedShift.cooks;
        		$scope.employeeType = "Cooks";
        		
        	} else if($scope.employee.userRole == 'waiter') {
        		$scope.assignedEmployees = $scope.selectedShift.waiters;
        		$scope.employeeType = "Waiters";
        	}
        	        		        
        	return response;
        });                      
              
    	
    	if($scope.selectedShift == null)
    		$scope.eventVisible = true;
    	else if(event.id == $scope.selectedShift.id)
    		$scope.eventVisible = !$scope.eventVisible;
    	else if(event.id != $scope.selectedShift.id)
    		$scope.eventVisible = true;
    	
    };
    

    $scope.uiConfig = {
			calendar: {
			viewRender: function (view) {
			//some statements here
			},
			renderCalender: $scope.renderCalender,
			height: 520,
			editable: false,
			header: {
			//left: 'month basicWeek basicDay agendaWeek agendaDay',
			left: 'month basicWeek basicDay',
			center: 'title',
			right: 'today prev,next'
			},
			eventClick: $scope.eventClick,
			dayClick: $scope.dayClick,
			eventDrop: $scope.alertOnDrop,
			eventResize: $scope.alertOnResize,
			eventRender: $scope.eventRender
			}
			};
	
    

}]);