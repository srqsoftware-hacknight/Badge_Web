angular.module('fabLab.exampleSvc',[])

.factory("exampleService", function($q, $http) {

    var curService = {};

    // A very simple method
    curService.echo = function(echoText) {
        return "ECHO: " + echoText;
    };

    return curService;
});