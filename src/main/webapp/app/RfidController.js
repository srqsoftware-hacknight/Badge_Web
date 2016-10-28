angular.module('fabLab.rfidCtrl',[])

  // Angular auto-injects the scope for this controller
  .controller('RfidController', function($scope, $location, $http, $mdToast) {

    $scope.showResults = function(txt) {
    	
    	$mdToast.show(
    			$mdToast.simple()
    				.textContent(txt)
    				.position('top left')
    				.hideDelay(2000));
    };

    $scope.navToUsers = function() {
    	console.log("Navigation");
    	$location.path( "users" );
    };

    $scope.loadData = function() {
    	$http.get("/users/list")
    	.then(function(res) {
    		$scope.devices = res.data;
    	},
    	function(err) {
    		console.log("ERROR: " + JSON.stringify(err));
    	}
    	)
    };
    $scope.loadData();
    /*
    $scope.devices = [
      {"id": "123", "isEnabled": true, "owner": "Scott"},
      {"id": "456", "isEnabled": true, "owner": "Chris"},
      {"id": "789", "isEnabled": false, "owner": "Dick"},
      {"id": "987", "isEnabled": true, "owner": "Eric"},
    ];
*/

    $scope.updateUser = function(x) {
      // Toggle the status of the user
      if (x.status == 1) {
        x.status = 0;
      } else {
        x.status = 1;
      }

    	$http({"method": "PUT", "url":"/users", "data": x, "withCredentials":true})
        .then(function(res) {
        	$scope.showResults("User was successfully updated");
          $scope.loadData();
        }, function(err) {
        	console.log("Bad user update: " + JSON.stringify(err));
        	$scope.showResults("Error adding the user: " + err.data.response);
        }
      );
    };

    $scope.logUserOut = function() { 
    	$http({"method":"POST", "headers": {'Content-Type': 'application/x-www-form-urlencoded'}, "url":"/logout", "data": ""})
    	
        .then(function(res) {
        	console.log("User was logged out");
        	$location.path("users")
        }, function(err) {
        	console.log("Error logging out: " + JSON.stringify(err.data.response));
        });
    };

  });
