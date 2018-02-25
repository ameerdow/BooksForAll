window.app = window.app || angular.module('booksForAll', []);

app.controller('AllPurchasesCtrl', ['$scope', function ($scope) {

    $scope.user = null;
    checkUserData($scope);
    $scope.logout = Server.logout;

    $scope.purchases = [];

    $scope.loadData = function () {
        Server.getAllBookPurchases(0, function (purchases, error) {
            if (error != null) {
                alert(error);
                return;
            }

            $scope.$apply(function () {
                $scope.purchases = purchases;
            });
        });
    }

    // setTimeout(function () {
    //     $scope.$apply(function () {
    //         $scope.purchases = [{
    //             "username": "david",
    //             "bookId": 12,
    //             "price": 123.44,
    //             "creationDate": "Feb 24, 2018"
    //         }, {
    //             "username": "david",
    //             "bookId": 12,
    //             "price": 123.44,
    //             "creationDate": "Feb 24, 2018"
    //         }, {
    //             "username": "david",
    //             "bookId": 12,
    //             "price": 123.44,
    //             "creationDate": "Feb 24, 2018"
    //         },];
    //     });
    // }, 500);

}]);