window.app = window.app || angular.module('booksForAll', []);

app.controller('AllUsersCtrl', ['$scope', function ($scope) {

    $scope.user = null;
    checkUserData($scope);
    $scope.logout = Server.logout;

}]);