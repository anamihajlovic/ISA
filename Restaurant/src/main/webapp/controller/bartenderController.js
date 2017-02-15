var bartenderModule = angular.module('bartender.controller', []);


bartenderModule.controller('bartenderController', ['$scope', 'bartenderService','$location',
	function ($scope, bartenderService, $location) {
	
		
	$scope.updateInfo = function () {    			
				alert("Update info");

		}
		
				
}]);