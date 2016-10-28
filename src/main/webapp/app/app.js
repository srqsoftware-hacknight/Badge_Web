var app = angular.module('RfidApplication', ['ngRoute', 'ngMaterial', 'fabLab.rfidCtrl', 'fabLab.UserCtrl', 'fabLab.AuthenticationController']);

app.config(function($routeProvider) {
  $routeProvider.when("/rfid", 
      {"templateUrl": "app/rfid.html",
       "controller" : "RfidController"
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
    {"templateUrl": "app/rfid.html",
     "controller" : "RfidController"
    });
});

app.userName = "Happy";
