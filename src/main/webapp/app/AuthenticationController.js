angular.module('fabLab.AuthenticationController',[])

  // Angular auto-injects the scope for this controller
  .controller('AuthenticationController', function($scope, $http, $location, $mdToast) {
    console.log("Auth ctrl");
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
          console.log($scope.username);
        	$location.path("rfid");
        }, function(err) {
        	$scope.showResults("Error authenticating user: " + err.data.response);
        }
      );
    };
    
    $scope.navToAuth = function() {
      $location.path("authentication");
    };
    

  });
