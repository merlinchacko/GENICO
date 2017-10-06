var app = angular.module('transferApp', []);
app.controller('GetController', function($scope, $http, $location) {
	
	$scope.resulttable = false;
	$scope.matchtable = false;
	$scope.errMessage = "";
	
	$scope.getAllAccount = function(){
		var url = $location.absUrl() + "getAllAccount()";

		var config = {
				headers : {
					'Content-Type': 'application/json;charset=utf-8;'
				}
		}

		$http.get(url, config).then(function (response) {
			$scope.resulttable = true;
			$scope.matchtable = false;
			$scope.response = response.data;
			$scope.itemsLength = Object.keys($scope.response).length;
		}, function (response) {
			$scope.errMessage = "Invalid input or Internal server error!";
		});
	}
	
	$scope.createAccount = function(){

		$http({
			method: 'POST',
			url: $location.absUrl() + "createAccount()"
		}).then(function (response) {
			$scope.errMessage = "Succes";
		}, function (response) {
			$$scope.errMessage = "Failed";
		});
	}
	
});

