var employeeServices = angular.module('employee.services', ['ngResource']);

employeeServices.service('employeeService',['$http', function($http) {
		
	
	this.getEmployee = function() {			
		return $http.get("/users/getActiveUser");
	}
	
	this.updateInfo = function(employee) {
		
		if(employee.userRole == 'waiter')
			return $http.put("/waiters/"+employee.id, employee);
			
		else if(employee.userRole == 'cook')
			return $http.put("/cooks/"+employee.id, employee);
		
		else if(employee.userRole == 'bartender')
			return $http.put("/bartenders/"+employee.id, employee);
	}
	
	
	
}]); 