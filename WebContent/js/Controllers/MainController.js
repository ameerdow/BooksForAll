var app = angular.module('booksForAll');

app.controller('MainController',['$scope', 'MainStorage',  function ($scope, $storage) {
    ngStorage = $storage;
}]);