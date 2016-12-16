var app = angular.module('BadgeApplication', ['ngRoute', 'ngMaterial',
						'fabLab.BadgeController', 'fabLab.AuthenticationController', 'fabLab.LogInOutController']);

app.config(function($routeProvider) {
  $routeProvider.when("/authentication", 
	      {"templateUrl": "app/authentication.html",
	       "controller" : "AuthenticationController"
	      });

  $routeProvider.when("/authentication",
	      {"templateUrl": "app/authentication.html",
	       "controller" : "AuthenticationController"
	      });

  $routeProvider.when("/badges",
	      {"templateUrl": "app/badges.html",
	       "controller" : "BadgeController"
	      });

  $routeProvider.when("/badges/add",
	      {"templateUrl": "app/addBadge.html",
	       "controller" : "BadgeController"
	      });

  $routeProvider.when("/badges/edit",
	      {"templateUrl": "app/addBadge.html",
	       "controller" : "BadgeController"
	      });

  $routeProvider.otherwise(
    {"templateUrl": "app/badges.html",
     "controller" : "BadgeController"
    });
});

// Redirect all unauthenticated calls to the login page
app.service('authInterceptor', function($q, $location) {
    var service = this;

    service.responseError = function(response) {
        if (response.status == 401){
        	$location.path("authentication");
        }
        return $q.reject(response);
    };
})
.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('authInterceptor');
}]);

