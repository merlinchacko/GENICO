var app = angular.module('transferApp', ['ngRoute','ngResource','ui.bootstrap']);

app.controller('GetController', function($scope, $http, $location, $modal) {
	
	$scope.resulttable = false;
	$scope.result = "";
	
	//init
	getAllAccounts();
	
	function getAllAccounts(){
		var url = $location.absUrl() + "getAllAccounts";

		var config = {
				headers : {
					'Content-Type': 'application/json;charset=utf-8;'
				}
		}

		$http.get(url, config).then(function (response) {
			$scope.resulttable = true;
			$scope.response = response.data;
			$scope.itemsLength = Object.keys($scope.response).length;
		}, function (response) {
			$scope.result = "Invalid input or Internal server error!";
		});
	}
	
	
	
	$scope.openCreateModal = function(){
		$modal.open({
            templateUrl: 'account.html',
            backdrop: true, 
            windowClass: 'modal', 
            size: "sm",
            controller: function ($scope, $modalInstance, $log) {
               
                $scope.cancel = function () {
                    $modalInstance.dismiss('cancel'); 
                };
                $scope.createAccount = function(){
            		console.log("url:"+ $location.absUrl() + "createAccount");
            		var account = {
            				account_name: this.accountName,
            				account_balance:this.accountBalance	    
            		};
            		$http({
            			method: 'POST',
            			url: $location.absUrl() + "createAccount",
            			data : account
            		}).then(function (response) {
            			$scope.result = response.data;
            			 $modalInstance.close();
            			 getAllAccounts();
            		}, function (response) {
            			$scope.result = response.data;
            		});
            	}
            }
        });
	}
	
	$scope.openTransferModal = function(){
		$modal.open({
            templateUrl: 'transfer.html',
            backdrop: true, 
            windowClass: 'modal', 
            size: "sm",
            controller: function ($scope, $modalInstance, $log) {
               
                $scope.cancel = function () {
                    $modalInstance.dismiss('cancel'); 
                };
                $scope.transferAccount = function(){
            		console.log("url:"+ $location.absUrl() + "transferAccount");
            		var details = {
            				fromAccount    : this.fromAccount,
            				toAccount      : this.toAccount,
            				transferAmount : this.transferAmount
            		};
            		$http({
            			method: 'POST',
            			url: $location.absUrl() + "transferAccount",
            			data : JSON.stringify(details)
            		}).then(function (response) {
            			if(response.data == "Success"){
            				$scope.result = "Amount transferred succesfully";
            				$modalInstance.close();
               			 	getAllAccounts();
            			}else if(response.data == "Invalid") {
            				$scope.result = "Invalid account details.";
            			}else {
            				$scope.result = "Insufficient balance to transfer."; 
            			}
            		}, function (response) {
            			$scope.result = response.data;
            		});
            	}
            }
        });
	}
	
	
});


