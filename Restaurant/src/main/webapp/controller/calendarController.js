var calendarModule = angular.module('calendar.controller', ['ui.calendar']);

calendarModule.controller("calendarController", ['$scope', '$location', 'employeeService',
	
	function ($scope, $location, employeeService, $filter) {
	

	function readSchedule() {
		employeeService.getEmployee().then(function (response) {				
			if(response.data !="") 	{
				var request = employeeService.readWorkSchedule(response.data).then(function(response){
					alert("AAAAA");
				});
			} else
				$location.path('login');		
		});
	}
				
	
	function fillCalendar() {
		date = new Date();
		y = date.getYear();
		m = date.getMonth();
		d = date.getDay();
		$scope.events = [
	           {title: 'All Day Event',start: new Date()},
	           {title: 'Long Event',start: new Date(y, m, d - 5),end: new Date(y, m, d - 2)},
	           {id: 999,title: 'Repeating Event',start: new Date(y, m, d - 3, 16, 0),allDay: false},
	           {id: 999,title: 'Repeating Event',start: new Date(y, m, d + 4, 16, 0),allDay: false},
	           {title: 'Birthday Party',start: new Date(y, m, d + 1, 19, 0),end: new Date(y, m, d + 1, 22, 30),allDay: false},
	           {title: 'Click for Google',start: new Date(y, m, 28),end: new Date(y, m, 29),url: 'http://google.com/'}
	          ]
		$scope.eventSources = [$scope.events];
		};
	
	
		
		
	
	$scope.uiConfig = {
			calendar: {
			viewRender: function (view) {
			//some statements here
			},
			renderCalender: $scope.renderCalender,
			height: 520,
			editable: true,
			header: {
			//left: 'month basicWeek basicDay agendaWeek agendaDay',
			left: 'month basicWeek basicDay',
			center: 'title',
			right: 'today prev,next'
			},
			eventClick: $scope.alertOnEventClick,
			dayClick: $scope.alertDayOnClick,
			eventDrop: $scope.alertOnDrop,
			eventResize: $scope.alertOnResize,
			eventRender: $scope.eventRender
			}
			};
	

	
	$scope.eventSources = [];	
	readSchedule();
	
	
	

}]);