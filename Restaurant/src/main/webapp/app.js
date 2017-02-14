'use-strict';
angular.module('restaurants', ['ui.router', 'common.services','common.controller'])
.config(function($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/login');
        
        $stateProvider

        .state('login', {
        	url : '/login',
          	templateUrl : 'login.html',
          	controller : 'commonController'
        })
        
        .state('guest', {
        	url : '/guest',
          	templateUrl : 'html/guest/guestHome.html',
            //controller : 'guestController'
         }) 
});