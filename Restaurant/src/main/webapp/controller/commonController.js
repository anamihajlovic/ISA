var commonModule = angular.module('common.controller', []);
 
var firstLoginId = null;

commonModule.controller('commonController', ['$scope', 'commonService','$location',
  	function ($scope,commonService, $location) {
	
		$scope.submitLogin = function () {            
			commonService.testFunkcija($scope.test).then(function(response) {
				alert("success");
			});
		}
		
		
}]);