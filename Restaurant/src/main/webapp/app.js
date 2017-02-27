'use-strict';
angular.module('restaurants', [ 'ui.router','ui.calendar' ,'chart.js',
	  							'common.services','common.controller',
								'sysManager.services','sysManager.controller',
								'bartender.services', 'bartender.controller',
								'guest.services', 'guest.controller',
								'resManager.services','resManager.controller',
								'bidder.services','bidder.controller',
								'employee.services',
								'cook.services', 'cook.controller',
								'waiter.services', 'waiter.controller',
								'guest.services', 'guest.controller',
								'pass.controller',
								'calendar.controller',
								'order.services', 'order.controller'
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
        .state('firstLogIn', {
        	url: '/firstLogIn',
        	templateUrl: 'changePassword.html',
        	controller: 'commonController',
        	controller:'passController'
        		
        })
        
        .state('activateAccount', {
        	url: '/activateAccount/:activationCode',
        	templateUrl: 'html/guest/activationMessage.html',
        	resolve: {
        		promiseObj:  function($http, $stateParams){
                return $http.post("/guests/activateAccount/"+ $stateParams.activationCode);
             }}
        })
        
        .state('guest', {
        	url : '/guest',
          	templateUrl : 'html/guest/guestHome.html',
            controller : 'guestController'
         }) 
         
         .state('guest.updateProfile', {
        	url: '/updateProfile',
        	templateUrl: 'html/guest/guestProfile.html'
        })
        
        .state('guest.changePassword', {
        	   url : '/changePassword',
        	   templateUrl : 'changePassword.html',
        	   controller: 'passController'
           })
        
        .state('guest.findFriends', {
        	url: '/findFriends',
        	templateUrl: 'html/guest/addFriends.html'
        })
        
        .state('guest.myFriends', {
        	url:'/myFriends',
        	templateUrl: 'html/guest/myFriends.html'
        })
        
        .state('guest.restaurants', {
        	url:'/restaurants',
        	templateUrl: 'html/guest/restaurantsList.html'
        })
        
        .state('guest.startReservation', {
        	url:'/startReservation',
        	templateUrl: 'html/guest/reservationDateTime.html'
        })
        
        .state('guest.chooseTables', {
        	url: '/chooseTables',
        	templateUrl: 'html/guest/tablesConf.html'
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
          
          .state('sysManager.newSysManager', {
              url : '/newSysManager',
             templateUrl : 'html/sysManager/sysManagerNewSysManager.html',
          })
             
        .state('sysManager.list', {
              url : '/list',
             templateUrl : 'html/sysManager/sysManagerList.html',
          })
          
       .state('resManager', {
       	url : '/resManager',
        templateUrl : 'html/resManager/resManagerHome.html',
          controller : 'resManagerController'
        }) 
         .state('resManager.tables', {
       	url : '/tables',
        templateUrl : 'html/resManager/resManagerSeatingConf.html',
        })
        .state('resManager.updateResManager', {
       	url : '/updateResManager',
        templateUrl : 'html/resManager/resManagerUpdateProfile.html',
        }) 
        .state('resManager.calendar', {
           	url : '/calendar',
            templateUrl : 'html/resManager/resManagerCalendar.html',
            }) 
        .state('resManager.updateRestaurant', {
           	url : '/updateRestaurant',
            templateUrl : 'html/resManager/resManagerUpdateRestaurant.html',
            }) 
            
      .state('resManager.newEmployee', {
       	url : '/newEmployee',
        templateUrl : 'html/resManager/resManagerNewEmployee.html'
        }) 
        
         .state('resManager.newBidder', {
       	url : '/newBidder',
        templateUrl : 'html/resManager/resManagerNewBidder.html'
        }) 
          .state('resManager.list', {
              url : '/list',
             templateUrl : 'html/resManager/resManagerList.html',
          })
          .state('resManager.statistic', {
              url : '/statistic',
             templateUrl : 'html/resManager/resManagerBusiness.html',
          })
          
          
         .state('resManager.menu', {
              url : '/menu',
             templateUrl : 'html/resManager/resManagerMenu.html',
          })
           .state('resManager.purchase', {
              url : '/purchase',
             templateUrl : 'html/resManager/resManagerPurchase.html',
          })
             
         .state('resManager.drinkCard', {
              url : '/drinkCard',
             templateUrl : 'html/resManager/resManagerDrinkCard.html',
          })
          .state('resManager.ratings', {
              url : '/ratings',
             templateUrl : 'html/resManager/resManagerRatings.html',
          })
       
        .state('bidder', {
        	   url : '/bidder',
        	   templateUrl : 'html/bidder/bidderHome.html',
        	   controller: 'bidderController'
        	   
           })
              .state('bidder.showOrders', {
        	   url : '/showOrders',
        	   templateUrl : 'html/bidder/bidderListResOrders.html',
        	   
           })
             .state('bidder.updateBidder', {
        	   url : '/updateBidder',
        	   templateUrl : 'html/bidder/bidderUpdateProfile.html',
        	   
           })
            .state('bidder.changePassword', {
        	   url : '/changePassword',
        	   templateUrl : 'changePassword.html',
        	   controller: 'passController'
        	   
           })
           .state('bartender', {
        	   url : '/bartender',
        	   templateUrl : 'html/employees/bartenderHome.html',
        	   controller: 'bartenderController'
        	   
           })
           
           .state('bartender.updateInfo', {
        	   url : '/updateInfo',
        	   templateUrl : 'html/employees/employeeUpdateInfo.html',        	   
           })
           
           .state('bartender.changePassword', {
        	   url : '/changePassword',
        	   templateUrl : 'changePassword.html',
        	   controller: 'passController'
           })
           
           .state('bartender.workSchedule', {
        	   url : '/workSchedule',
        	   templateUrl : 'html/employees/calendarView.html',
        	   controller: 'calendarController'        	  
           })
           
           .state('bartender.orderedDrinks', {
        	   url : '/orderedDrinks',
        	   templateUrl : 'html/employees/orders.html', 
        	   controller: 'orderController'
           })
          
           
           .state('waiter' , {
        	  url: '/waiter',
        	  templateUrl: 'html/employees/waiterHome.html',
        	  controller: 'waiterController'
        	 
           })
           
           .state('waiter.updateInfo' , {
        	  url: '/updateInfo',
        	  templateUrl: 'html/employees/employeeUpdateInfo.html',        	 
        	 
           })
           
           .state('waiter.changePassword', {
        	   url : '/changePassword',
        	   templateUrl : 'changePassword.html',
        	   controller: 'passController'
           })
           
           .state('waiter.workSchedule', {
        	   url : '/workSchedule',
        	   templateUrl : 'html/employees/calendarView.html',
        	   controller: 'calendarController'        	  
           })                             
           
           .state('cook' , {  
        	  url: '/cook', 
        	  templateUrl: 'html/employees/cookHome.html',
        	  controller: 'cookController'        	          	
           })
           
           .state('cook.updateInfo', {
        	  url: '/updateInfo', 
         	  templateUrl: 'html/employees/employeeUpdateInfo.html',         	    
           })
           
           .state('cook.changePassword', {
        	   url : '/changePassword',
        	   templateUrl : 'changePassword.html',
        	   controller: 'passController'
           })
           
           .state('cook.workSchedule', {
        	   url : '/workSchedule',
        	   templateUrl : 'html/employees/calendarView.html',
        	   controller: 'calendarController'        	  
           })
           
           .state('cook.orderedDish', {
        	   url : '/orderedDish',
        	   templateUrl : 'html/employees/orders.html', 
        	   controller: 'orderController'
           })
           
            .state('cook.preparingDish', {
        	   url : '/preparingDish',
        	   templateUrl : 'html/employees/preparingDish.html', 
        	   controller: 'orderController'
           })
           
           
                     
         
});


