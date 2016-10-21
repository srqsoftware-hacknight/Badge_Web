angular.module('fabLab.UserCtrl',[])

  // Angular auto-injects the scope for this controller
  .controller('UserController', function($scope, $http, $mdToast, exampleService) {
    console.log("User Controller");

    $scope.user = {};
    $scope.user.firstName = "";
    $scope.user.lastName = "";

    $scope.showResults = function(txt) {
    	
    	$mdToast.show(
    			$mdToast.simple()
    				.textContent(txt)
    				.position('top left')
    				.hideDelay(2000));
    };
    
    
    
    $scope.addUser = function() {
    	$http({"method": "POST", "url":"/users", "data": $scope.user, "withCredentials":true})
        .then(function(res) {
        	$scope.showResults("User was successfully added");
        	$scope.user = {};
        }, function(err) {
        	console.log("Bad user add: " + JSON.stringify(err));
        	$scope.showResults("Error adding the user: " + err.data.response);
        }
      );
    };
    
  });
