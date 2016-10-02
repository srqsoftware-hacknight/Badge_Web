describe("Example Controller", function() {

    var scope;
    var mockExampleService;

    beforeEach(angular.mock.module('fabLab.exampleCtrl'));

    beforeEach(inject(function($rootScope, $controller) {
        scope = $rootScope.$new();
        mockExampleService = jasmine.createSpy('exampleService');

        $controller('ExampleController', {
            $scope : scope,
            exampleService : mockExampleService
        });
    }));

    it("The echoText() message should work", function() {
        mockExampleService.echo = jasmine.createSpy("echo()", "spy").and.returnValue("Hello World");
        scope.echoContent("Hello World");
        expect(mockExampleService.echo).toHaveBeenCalled();
    });

});