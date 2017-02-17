var commonServices = angular.module('common.services', ['ngResource']);

commonServices.service('commonService',['$http', function($http) {
	
	this.login = function(user) {
		return $http.post("/users/login", user);
	}
	
	this.getActiveUser = function() {
		alert("U servisu sam");
		return $http.get("/users/getActiveUser");
	}
	
	this.changePassword = function(user) {
		
		if(user.userRole == 'waiter')
			return $http.put("/waiters/changePassword", user);
			
		else if(user.userRole == 'cook')
			return $http.put("/cooks/changePassword", user);
		
		else if(user.userRole == 'bartender')
			return $http.put("/bartenders/changePassword", user);
		
	}
	
	
	/*this.logout = function() {
		alert("service logout");
		return $http.get("/users/logout");
	}*/
	
}]); 