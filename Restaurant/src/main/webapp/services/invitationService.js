var invitationServices = angular.module('invitation.services', ['ngResource']);

invitationServices.service('invitationService', ['$http', function($http) {
	
	this.getReservation = function(reservationId) {
		return $http.get("/reservations/getReservation/" + reservationId);
	}
	
	this.confirmInvitation = function(invitationId, operation) {
		return $http.post("/invitations/confirmInvitation/" + invitationId + "/" + operation);

	}
	
	this.getInvitation = function(invitationId) {
		return $http.get("/invitations/getInvitation/" + invitationId);

	}
	
}]);