var calendarModule = angular.module('calendar.controller', ['ui.calendar']);

calendarModule.controller("calendarController", ['$scope', '$location', 'employeeService',
	
	function ($scope, $location, employeeService, $filter) {
		
		$scope.eventSources = [];			
		var request = employeeService.getEmployee().then(function (response) {	
			
			if(response.data !="") {
				$scope.employee = response.data;
				
				var newRequest = employeeService.readWorkSchedule($scope.employee).then(function (response){
					var workDays = response.data;
					$scope.events = [];
					for(i=0; i<workDays.length; i++) {
						event = {title: 'aaa', start: workDays[i].day}
						$scope.events.push(event);
					}
					 $scope.eventSources.push($scope.events); 				
					 alert("Gotovo")					
					
				});
				
				return response;
			} 			
			else
				$location.path('login');
		});							
	
	 
    $scope.dayClick = function( date, allDay, jsEvent, view ){
    	alert($scope.eventSources.length);
		alert("Bbbb");		
        $scope.$apply(function(){
          $scope.alertMessage = ('Day Clicked ' + date);
        });
    };
    
    $scope.eventClick = function(event){
        alert("Click event");
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