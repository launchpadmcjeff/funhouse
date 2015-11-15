angular.module('jbosswildfly').factory('VehicleResource', function($resource){
    var resource = $resource('rest/vehicles/:VehicleId',{VehicleId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});