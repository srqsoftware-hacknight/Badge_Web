var app = angular.module('BadgeApplication', ['ngRoute', 'ngMaterial', 'fabLab.BadgeController', 'fabLab.AuthenticationController']);

app.config(function($routeProvider) {
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

  $routeProvider.otherwise(
    {"templateUrl": "app/badges.html",
     "controller" : "BadgeController"
    });
});
