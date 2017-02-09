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
});