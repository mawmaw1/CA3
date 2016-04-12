'use strict';

angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/view2', {
              templateUrl: 'app/view2/view2.html',
              controller: 'View2Ctrl',
              controllerAs: 'ctrl'
            });
          }])

        .controller('View2Ctrl', ['$http', function ($http) {
                var self = this;
          $http({
            method: 'GET',
            url: 'api/demouser'
          }).then(function successCallback(res) {
            self.data = res.data.message;
          }, function errorCallback(res) {
            self.error = res.status + ": "+ res.data.statusText;
          });

        }]);