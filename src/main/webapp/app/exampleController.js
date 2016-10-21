angular.module('fabLab.exampleCtrl',[])

  // Angular auto-injects the scope for this controller
  .controller('ExampleController', function($scope, $location, $http, exampleService) {

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
    // A simplistic example of calling a service from a controller
    $scope.echoContent = function(echoText) {
      var x = exampleService.echo(echoText); 
      alert(x);
    };

    $scope.logUserOut = function() { 
    	$http({"method":"POST", "headers": {'Content-Type': 'application/x-www-form-urlencoded'}, "url":"/logout", "data": ""})
    	
        .then(function(res) {
        	console.log("User was logged out");
        	$location.path("users")
        }, function(err) {
        	console.log("Error logging out: " + JSON.stringify(err.data.response));
        })
    };

  });
