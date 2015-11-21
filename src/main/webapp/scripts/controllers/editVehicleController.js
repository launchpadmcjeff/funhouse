

angular.module('jbosswildfly').controller('EditVehicleController', function($scope, $routeParams, $location, VehicleResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.vehicle = new VehicleResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Vehicles");
        };
        VehicleResource.get({VehicleId:$routeParams.VehicleId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.vehicle);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.vehicle.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Vehicles");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Vehicles");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.vehicle.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});