window.app = window.app || angular.module('booksForAll', []);

app.controller('AllUsersCtrl', ['$scope', function ($scope) {

    $scope.user = null;
    checkUserData($scope);
    $scope.logout = Server.logout;

    $scope.users = [];


    $scope.loadData = function () {
        Server.getAllUsers(function (users, error) {
            if (error != null) {
                alert(error);
                return;
            }
            $scope.$apply(function () {
                $scope.users = users;
            });
        });
    }

    // setTimeout(function () {
    //     $scope.$apply(function () {
    //         $scope.users = [{
    //             "username": "david",
    //             "email": "david@email.com",
    //             "password": "dasdasda",
    //             "address": {"street": "admin", "number": 1, "city": "admin", "zip": "1234567", "country": "admin"},
    //             "phoneNumber": "0546597762",
    //             "nickname": "nickname",
    //             "description": "description",
    //             "photoUrl": "photoUrl",
    //             "role": "Admin",
    //             "deleted": "N",
    //             "creationDate": "Feb 24, 2018"
    //         }, {
    //             "username": "ameer",
    //             "email": "david@email.com",
    //             "password": "dasdasda",
    //             "address": {"street": "admin", "number": 1, "city": "admin", "zip": "1234567", "country": "admin"},
    //             "phoneNumber": "0546597762",
    //             "nickname": "nickname",
    //             "description": "description",
    //             "photoUrl": "photoUrl",
    //             "role": "Admin",
    //             "deleted": "N",
    //             "creationDate": "Feb 24, 2018"
    //         }, {
    //             "username": "skskks",
    //             "email": "david@email.com",
    //             "password": "dasdasda",
    //             "address": {"street": "admin", "number": 1, "city": "admin", "zip": "1234567", "country": "admin"},
    //             "phoneNumber": "0546597762",
    //             "nickname": "nickname",
    //             "description": "description",
    //             "photoUrl": "photoUrl",
    //             "role": "Admin",
    //             "deleted": "N",
    //             "creationDate": "Feb 24, 2018"
    //         }];
    //     });
    // }, 500);


    $scope.openUserDetails = function (username) {
        window.location.href = "user.html?username=" + encodeURIComponent(username);
    };

}]);