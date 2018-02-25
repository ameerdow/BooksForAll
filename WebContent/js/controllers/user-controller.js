window.app = window.app || angular.module('booksForAll', []);

app.controller('UserCtrl', ['$scope', function ($scope) {

    $scope.user = null;
    checkUserData($scope);
    $scope.logout = Server.logout;
    $scope.selectedUser = null;

    $scope.username = getParameterByName("username");
    if ($scope.username == null || $scope.username.length === 0) {
        window.location = "all-users.html";
        return;
    }

    $scope.loadData = function () {
        Server.getUserByUsername($scope.username, function (selectedUser, error) {
            if (error != null) {
                alert(error);
                return
            }
            $scope.$apply(function () {
                $scope.selectedUser = selectedUser;
            });
            Server.getAllUserBookPurchases($scope.selectedUser.username, function (books, error) {
                if (error != null) {
                    alert(error);
                    return
                }
                $scope.$apply(function () {
                    $scope.books = books;
                })
            })
        });
    }
    // setTimeout(function () {
    //     $scope.$apply(function () {
    //         $scope.selectedUser = {
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
    //         };
    //
    //         setTimeout(function () {
    //             $scope.$apply(function () {
    //                 $scope.books = [{
    //                     "ID": 1,
    //                     "name": "1",
    //                     "price": 120.2,
    //                     "description": "111111111",
    //                     "likesCount": 12,
    //                     "reviewCount": 3,
    //                     "deleted": "N",
    //                     "filePath": "books/1.html",
    //                     "filePrePath": "books/1-pre.html",
    //                     "iconPath": "books/1.jpg",
    //                     "purchased": true,
    //                 }];
    //             })
    //         }, 500);
    //     })
    // }, 500);


    $scope.openBookById = function (book) {
        window.location.href = "book.html?bookId=" + book.ID;
    };

    $scope.deleteUser = function () {
        Server.deleteUser($scope.selectedUser.username, function (response, error) {
            window.location = "all-users.html";
        })
    };
}]);