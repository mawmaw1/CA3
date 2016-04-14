'use strict';

var app = angular.module('myApp.view6', ['ngRoute'])
    

app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view6', {
            templateUrl: 'app/view6/view6.html',
            controller: 'View6Ctrl',
            controllerAs: 'ctrl'
        });
    }]);

app.controller('View6Ctrl', ["$http", function ($http) {
        var self = this;
        self.getRates = function (){
            $http.get('api/data/dailyrates')
                .success(function (data, status, headers, config) {
                    console.log(data);
                    self.data = data;
                })
                .error(function (data, status, headers, config) {

                });
        };
        self.getRates();       
    }]);