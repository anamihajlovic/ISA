var guestModule = angular.module('guest.controller', []);


guestModule.directive("matchPassword", function () {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=matchPassword"
        },
        link: function(scope, element, attributes, ngModel) {
            ngModel.$validators.matchPassword = function(modelValue) {
                return modelValue == scope.otherModelValue;
            };

            scope.$watch("otherModelValue", function() {
                ngModel.$validate();
            });
        }
    };
});

guestModule.controller('guestController', ['$scope', 'guestService', '$location',
	function($scope, guestService, $location) {

		$scope.submitRegister = function() {
			var request = guestService.register($scope.guest).then(function(response) {
				alert ("success submitRegister in controller");
				$scope.data = response.data;
				return response;
			});
			

		}
		
}]);

