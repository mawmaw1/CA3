'use strict';

var app = angular.module('myApp.view6', ['ngRoute'])


app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view6', {
            templateUrl: 'app/view6/view6.html',
            controller: 'View6Ctrl',
            controllerAs: 'ctrl'
        });
    }]);

app.controller('View6Ctrl', ["$http", "CurrencyFactory", "$filter", function ($http, CurrencyFactory, $filter) {
        var self = this;

        self.getRatesHttp = function () {
            CurrencyFactory.getCurrency().success(function (data, status, headers, config) {
                console.log(data);
                self.data = data;
            })
                    .error(function (data, status, headers, config) {

                    });
        }
        ;
        self.getRatesHttp();

        self.calculate = function () {
            self.result = self.amount * (self.fromSelected / self.toSelected);
        };

    }]);

app.filter("result", [function () {
        return function (input, from, to) {
            if (isNaN(input) === true || isNaN(from) === true || isNaN(to) === true) {
                return "";
            } else {
                var res = input * (from / to);
                //return input * (from / to);
                return res.toFixed(2);
            }
        };
    }]);

app.factory('CurrencyFactory', ['$http', function ($http) {
        var self = this;
        var getCurrency = (function () {

            return getCurrency =
                    $http({
                        method: 'GET',
                        url: 'api/data/dailyrates'
                    });
        });

        return {
            getCurrency: getCurrency

        };

    }]);