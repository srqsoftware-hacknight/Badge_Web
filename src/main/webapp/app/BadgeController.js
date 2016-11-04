angular.module('fabLab.BadgeController',[])
  .controller('BadgeController', function($scope, $location, $http, $mdToast) {

    $scope.showResults = function(txt) {
    	$mdToast.show(
    			$mdToast.simple()
    				.textContent(txt)
    				.position('top left')
    				.hideDelay(2000));
    };

    $scope.navToBadges = function() {
    	console.log("Navigation");
    	$location.path("badges/add");
    };

    $scope.loadData = function() {
    	$http.get("/badges/list")
    	.then(function(res) {
    		$scope.devices = res.data;
    	},
    	function(err) {
    		console.log("ERROR: " + JSON.stringify(err));
    	})
    };

    $scope.badge = {};
    $scope.badge.firstName = "";
    $scope.badge.lastName = "";

    $scope.loadData();

    $scope.updateBadge = function(x) {
      if (x.status == 1) {
        x.status = 0;
      } else {
        x.status = 1;
      }

    	$http({"method": "PUT", "url":"/badges", "data": x, "withCredentials":true})
        .then(function(res) {
        	$scope.showResults("Badge was successfully deactivated");
          $scope.loadData();
        }, function(err) {
        	console.log("Bad badge update: " + JSON.stringify(err));
        	$scope.showResults("Error adding the badge: " + err.data.response);
        }
      );
    };

    $scope.logUserOut = function() { 
    	$http({"method":"POST", "headers": {'Content-Type': 'application/x-www-form-urlencoded'}, "url":"/logout", "data": ""})
    	
        .then(function(res) {
        	console.log("User was logged out");
        	$location.path("badges")
        }, function(err) {
        	console.log("Error logging out: " + JSON.stringify(err.data.response));
        });
    };

    $scope.addBadge = function() {
      $scope.badge.status = 1;
    	$http({"method": "POST", "url":"/badges", "data": $scope.badge, "withCredentials":true})
        .then(function(res) {
        	$scope.showResults("Badge was successfully added");
        	$scope.badge = {};
          $location.path("badges");
        }, function(err) {
        	console.log("Failed badge add: " + JSON.stringify(err));
        	$scope.showResults("Error adding the badge: " + err.data.response);
        }
      );
    };
  });
