angular.module('fabLab.exampleCtrl',[])

  // Angular auto-injects the scope for this controller
  .controller('ExampleController', function($scope, $location, exampleService) {

    $scope.navToUsers = function() {
    	console.log("Navigation");
    	$location.path( "users" );
    };

    
    $scope.devices = [
      {"id": "123", "isEnabled": true, "owner": "Scott"},
      {"id": "456", "isEnabled": true, "owner": "Chris"},
      {"id": "789", "isEnabled": false, "owner": "Dick"},
      {"id": "987", "isEnabled": true, "owner": "Eric"},
    ];

    // A simplistic example of calling a service from a controller
    $scope.echoContent = function(echoText) {
      var x = exampleService.echo(echoText);
      alert(x);
    };
    

  });
