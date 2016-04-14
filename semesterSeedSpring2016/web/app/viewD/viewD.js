'use strict';

angular.module('myApp.viewD', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/viewD', {
    templateUrl: 'app/viewD/viewD.html',
    controller: 'ViewDCtrl',
    controllerAs : 'ctrl'
  });
}])

.controller('ViewDCtrl', ["InfoFactory","InfoService",function(InfoFactory,InfoService) {
  this.msgFromFactory = InfoFactory.getInfo();
  this.msgFromService = InfoService.getInfo();
}]);