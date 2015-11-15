'use strict';

angular.module('jbosswildfly',['ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Vehicles',{templateUrl:'views/Vehicle/search.html',controller:'SearchVehicleController'})
      .when('/Vehicles/new',{templateUrl:'views/Vehicle/detail.html',controller:'NewVehicleController'})
      .when('/Vehicles/edit/:VehicleId',{templateUrl:'views/Vehicle/detail.html',controller:'EditVehicleController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
