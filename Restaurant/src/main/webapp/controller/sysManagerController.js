var sysManagerModule = angular.module('sysManager.controller', []);
 

sysManagerModule.controller('sysManagerController', ['$scope', 'sysManagerService','$location',
  	function ($scope,sysManagerService, $location) {
	
		
	$scope.saveManager= function () {    
			
				var request = sysManagerService.saveResManager($scope.resManager).then(function(response) {
				$scope.data = response.data;
				alert(response.data)
				return response;
			});			
				request.then(function (data) {
					if($scope.data != null) {
						toastr.success("Success!");	
							
					} else {
						toastr.error("Something wrong");
					
					}


			
			});
		}
		
		
		
		
}]);