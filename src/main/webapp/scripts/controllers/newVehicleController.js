
angular.module('jbosswildfly').controller('NewVehicleController', function ($scope, $location, locationParser, VehicleResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.vehicle = $scope.vehicle || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Vehicles/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        VehicleResource.save($scope.vehicle, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Vehicles");
    };
});