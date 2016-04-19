
var app = angular.module('myApp.view2', ['ngRoute', 'ui.bootstrap']);

app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view2', {
            templateUrl: 'app/view2/view2.html',
            controller: 'View2Ctrl',
            controllerAs: 'ctrl'
        });

    }]);

app.controller('View2Ctrl', ['GetFactory', '$http', function (GetFactory, $http) {
        var self = this;
        self.getCompany = function (input, type) {
            if (type === "cvr") {
                self.getCvr(input);
            }
            if (type === "name") {
                self.getName(input);
            }
            if (type === "phone") {
                self.getPhone(input);
            }

        };
        self.getCvr = (function (cvr) {
            GetFactory.getCvr(cvr).then(function successCallback(res) {
                self.data = res.data;
                console.log(self.data);
            }, function errorCallback(res) {
                self.error = res.status + ": " + res.data.statusText;
            });
        });

        self.getName = (function (name) {
            GetFactory.getName(name).then(function successCallback(res) {
                self.data = res.data;
                console.log(self.data);
            }, function errorCallback(res) {
                self.error = res.status + ": " + res.data.statusText;
            });
        });
        self.getPhone = (function (phone) {
            GetFactory.getPhone(phone).then(function successCallback(res) {
                self.data = res.data;
                console.log(self.data);
            }, function errorCallback(res) {
                self.error = res.status + ": " + res.data.statusText;
            });
        });



    }]);

app.factory('GetFactory', ['$http', function ($http) {
        var self = this;
        var getCvr = (function (cvr) {
            return getCvr =
                    $http({
                        method: 'GET',
                        url: 'https://cvrapi.dk/api?vat=' + cvr + '&country=dk',
                        skipAuthorization: true
                    });
        });
        var getName = (function (name) {
            return getName =
                    $http({
                        method: 'GET',
                        url: 'https://cvrapi.dk/api?name=' + name + '&country=dk',
                        skipAuthorization: true
                    });
        });
        var getPhone = (function (phone) {
            return getPhone =
                    $http({
                        method: 'GET',
                        url: 'https://cvrapi.dk/api?phone=' + phone + '&country=dk',
                        skipAuthorization: true
                    });
        });
        return {
            getName: getName,
            getPhone: getPhone,
            getCvr: getCvr
        };

    }]);