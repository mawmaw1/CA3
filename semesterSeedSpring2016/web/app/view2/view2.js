'use strict';

var app = angular.module('myApp.view2', ['ngRoute'])

        app.config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'app/view2/view2.html',
                    controller: 'View2Ctrl',
                    controllerAs: 'ctrl'
                });
            }]);

        app.controller('View2Ctrl', ['$http', function ($http) {
                var self = this;
                self.getCvr = (function (cvr) {
                    $http({
                        method: 'GET',
                        url: 'http://cvrapi.dk/api?vat=' + cvr + '&country=dk',
                        skipAuthorization: true
                    }).then(function successCallback(res) {
                        self.data = res.data;
                        console.log(self.data);
                    }, function errorCallback(res) {
                        self.error = res.status + ": " + res.data.statusText;
                    });
                });


            }]);
app.filter("roleFilter", [function () {
        return function (inputItem) {
            var res = "";
            for (var i = 0; i < inputItem.length; i++) {
                if (inputItem[i] === inputItem[inputItem.length-1]) {
                    res += inputItem[i].role;
                }
                else {
                    res += inputItem[i].role + ", ";
                }
                
            }
            return res;

        };
    }]);