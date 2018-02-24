window.app = window.app || angular.module('booksForAll', []);

app.controller('AllPurchasesCtrl', ['$scope', function ($scope) {

    $scope.user = null;
    checkUserData($scope);
    $scope.logout = Server.logout;

}]);