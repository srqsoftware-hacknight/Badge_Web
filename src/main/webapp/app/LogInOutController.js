angular.module('fabLab.LogInOutController',[])
  .controller('LogInOutController', function($scope, $location, $rootScope, $http) {

    var rootScope = $rootScope;
    var isLoggedIn = false;

    $scope.curUser = "Anonymous";
    $scope.logInOut = "Log In";

    $scope.toggleAuth = function() {
        if (isLoggedIn) {
            $http.get("/logout?ts=" + new Date().getTime())
                .then(function(res) {
                    rootScope.$broadcast('loggedOut', true);
                }, function(err) {
                    if (err.data.status == 404) {
                        // A spring security redirect (to a 404) is still okay
                        rootScope.$broadcast('loggedOut', true);
                    } else {
                        console.log("Error: " + JSON.stringify(err));
                    }
                });
        } else {
        	$location.path("authentication");
        }
    };

    $scope.$on('loggedIn', function(event, data) {
        isLoggedIn = true;
        $scope.curUser = data;
        $scope.logInOut = "Log Out";
    });

    $scope.$on('loggedOut', function(event, data) {
        isLoggedIn = false;
        $scope.curUser = "Anonymous";
        $scope.logInOut = "Log In";
        $location.path("authentication");
    });

  });
