angular.module('fabLab.AdminController',[])
  .controller('AdminController', function($scope, $http, $location, $mdToast, $rootScope) {
    console.log("Admin ctrl");

    var rootScope = $rootScope;

    $scope.username = "admin";
    $scope.password = "";

    $scope.showResults = function(txt) {
    	$mdToast.show(
    			$mdToast.simple()
    				.textContent(txt)
    				.position('top left')
    				.hideDelay(2000));
    };

    $scope.authenticate = function() {
        var curData = {
            "username": "admin",
            "password": $scope.password
        };
        var curDataString = JSON.stringify(curData);
    	$http({"method":"POST", "headers": {'Content-Type': 'application/json'}, "url":"/adminpw",
    	 "data": curDataString})
    	
        .then(function(res) {
        	$scope.showResults("Admin ID was created");
          $location.path("authentication");
        }, function(err) {
        	$scope.showResults("Admin ID was not created");
        }
      );
    };
    
  });
