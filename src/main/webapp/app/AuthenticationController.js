angular.module('fabLab.AuthenticationController',[])
  .controller('AuthenticationController', function($scope, $http, $location, $mdToast, $rootScope) {
    console.log("Auth ctrl");

    var rootScope = $rootScope;

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
          rootScope.$broadcast('loggedIn', $scope.username);
        	$location.path("badges");
        }, function(err) {
        	$scope.showResults("User authentication failed");
        }
      );
    };
    
    $scope.navToAuth = function() {
      $location.path("authentication");
    };
  });
