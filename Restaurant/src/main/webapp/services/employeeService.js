var employeeServices = angular.module('employee.services', ['ngResource']);

employeeServices.service('employeeService',['$http', function($http) {
		
	
	this.getEmployee = function() {			
		return $http.get("/users/getActiveUser");
	}
	
	
	this.updateInfo = function(employee) {
		
		if(employee.userRole == 'waiter')
			return $http.put("/waiters/updateInfo", employee);
			
		else if(employee.userRole == 'cook')
			return $http.put("/cooks/updateInfo", employee);
		
		else if(employee.userRole == 'bartender')
			return $http.put("/bartenders/updateInfo", employee);	
	}
	
	
	this.readWorkSchedule = function(employee) {		
		if(employee.userRole == 'bartender')			
			return $http.get("/bartenders/getSchedule/" + employee.id);	
		
		else if(employee.userRole == 'cook')			
			return $http.get("/cooks/getSchedule/" + employee.id);	
		
		else if(employee.userRole == 'waiter')			
			return $http.get("/waiters/getSchedule/" + employee.id);	
		
		
	}
	
	
	this.getWorkShift = function(id) {
		return $http.get("/workShifts/getWorkShift/"+id);
	}
	
	
}]); 