'use strict';

var app = angular.module('myApp.view3', ['ngRoute']);

app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view3', {
            templateUrl: 'app/view3/view3.html',
            controller: 'View3Ctrl',
            controllerAs: 'ctrl'
        });
    }]);

app.controller('View3Ctrl', ["$http", function ($http) {
        var self = this;
        self.getUsers = function (){
            $http.get('api/admin/users')
                .success(function (data, status, headers, config) {
                    console.log(data);
                    self.data = data;
                })
                .error(function (data, status, headers, config) {

                });
        };
        self.getUsers();       
        self.deleteUser = function (username){
            $http.delete("api/admin/user/"+username).then(function (res){
                console.log(res);
                alert(res.data.userName + " has been deleted");
                self.getUsers();
            });
        };
    }]);

//app.filter("roleFilter", [function () {
//        return function (inputItem) {
//            var res = "";
//            for (var i = 0; i < inputItem.length; i++) {
//                if (inputItem[i] === inputItem[inputItem.length-1]) {
//                    res += inputItem[i].role;
//                }
//                else {
//                    res += inputItem[i].role + ", ";
//                }
//                
//            }
//            return res;
//
//        };
//    }]);
