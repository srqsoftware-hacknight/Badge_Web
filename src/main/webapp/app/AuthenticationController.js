angular.module('fabLab.AuthenticationController',[])

  // Angular auto-injects the scope for this controller
  .controller('AuthenticationController', function($scope, $http, $location, $mdToast) {

    $scope.username = "";
    $scope.password = "";

    $scope.showResults = function(txt) {
    	
    	$mdToast.show(
    			$mdToast.simple()
    				.textContent(txt)
    				.position('top left')
    				.hideDelay(2000));
    };

    $scope.authenticate = function() {
    	$http({"method":"POST", "headers": {'Content-Type': 'application/x-www-form-urlencoded'}, "url":"/login", "data": "username=" +$scope.username + "&password=" + $scope.password})
    	
        .then(function(res) {
        	$scope.showResults("User was authenticated");
        	$location.path("users")
        }, function(err) {
        	$scope.showResults("Error authenticating user: " + err.data.response);
        }
      );
    };
    
  });
