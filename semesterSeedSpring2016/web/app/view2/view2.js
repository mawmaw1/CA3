'use strict';

angular.module('myApp.view2', ['ngRoute', 'ui.bootstrap'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'app/view2/view2.html',
                    controller: 'View2Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View2Ctrl', ['$http', function ($http) {
                var self = this;
                self.getCvr = (function (cvr) {
                    $http({
                        method: 'GET',
                        url: 'http://cvrapi.dk/api?vat=' + cvr + '&country=dk',
                        skipAuthorization: true,
                    }).then(function successCallback(res) {
                        self.data = res.data;
                        console.log(self.data);
                    }, function errorCallback(res) {
                        self.error = res.status + ": " + res.data.statusText;
                    });
                });
                self.getName = (function (name) {
                    $http({
                        method: 'GET',
                        url: 'http://cvrapi.dk/api?name=' + name + '&country=dk',
                        skipAuthorization: true,
                    }).then(function successCallback(res) {
                        self.data = res.data;
                        console.log(self.data);
                    }, function errorCallback(res) {
                        self.error = res.status + ": " + res.data.statusText;
                    });
                });
                self.getPhone = (function (phone) {
                    $http({
                        method: 'GET',
                        url: 'http://cvrapi.dk/api?phone=' + phone + '&country=dk',
                        skipAuthorization: true,
                    }).then(function successCallback(res) {
                        self.data = res.data;
                        console.log(self.data);
                    }, function errorCallback(res) {
                        self.error = res.status + ": " + res.data.statusText;
                    });
                });

            }]);