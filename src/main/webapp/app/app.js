var app = angular.module('ExampleApplication', ['ngRoute', 'ngMaterial', 'fabLab.exampleCtrl', 'fabLab.UserCtrl', 'fabLab.AuthenticationController', 'fabLab.exampleSvc']);

app.config(function($routeProvider) {
  $routeProvider.when("/example", 
      {"templateUrl": "app/example.html",
       "controller" : "ExampleController"
      });

  $routeProvider.when("/authentication", 
	      {"templateUrl": "app/authentication.html",
	       "controller" : "AuthenticationController"
	      });


  $routeProvider.when("/users", 
	      {"templateUrl": "app/users.html",
	       "controller" : "UserController"
	      });

  $routeProvider.otherwise(
    {"templateUrl": "app/example.html",
     "controller" : "ExampleController"
    });
});
