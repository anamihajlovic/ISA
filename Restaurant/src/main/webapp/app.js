'use-strict';
angular.module('restaurants', [ 'ui.router', 
	  							'common.services','common.controller',
								'sysManager.services','sysManager.controller',
								'bartender.services', 'bartender.controller',
								'guest.services', 'guest.controller'
							   ])
.config(function($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/login');
        
        $stateProvider

        .state('login', {
        	url : '/login',
          	templateUrl : 'login.html',
          	controller : 'commonController'
        })
        
        .state('logout', {
        	url :'/logout',
        	//templateUrl : '',
        	resolve: {
        		promiseObj:  function($http,$location){
        			toastr.success("Goodbye");
        			$location.path('login');
                    return $http.get("/users/logout");
                 }}
        })
        
        .state('register', {
        	url: '/register',
        	templateUrl: 'signUp.html',
        	controller: 'guestController'
        })
        
        .state('guest', {
        	url : '/guest',
          	templateUrl : 'html/guest/guestHome.html',
            controller : 'guestController'
         }) 
         .state('sysManager', {
         	url : '/sysManager',
           	templateUrl : 'html/sysManager/sysManagerHome.html',
             controller : 'sysManagerController'
          }) 
          
          .state('sysManager.newResManager', {
           	url : '/newResManager',
            templateUrl : 'html/sysManager/sysManagerNewResManager.html',
            
            }) 
            
            
           .state('sysManager.newRestaurant', {
           	url : '/newRestaurant',
            templateUrl : 'html/sysManager/sysManagerNewRestaurant.html',
            }) 
            
           .state('sysManager.updateSysManager', {
             url : '/updateSysManager',
           templateUrl : 'html/sysManager/sysManagerUpdateProfile.html',
           })
                
                
           .state('bartender', {
        	   url : '/bartender',
        	   templateUrl : 'html/employees/bartenderHome.html',
        	   controller : 'bartenderController'
           })
           
           .state('bartender.updateInfo', {
        	   url : '/bartender',
        	   templateUrl : 'html/employees/employeeUpdateInfo.html',
           })
           
           .state('bartender.changePassword', {
        	   url : '/bartender',
        	   templateUrl : 'html/employees/changePassword.html',
           })
         
});