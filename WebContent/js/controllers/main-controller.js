var app = angular.module('booksForAll');

app.controller('MainController', ['$scope', 'MainStorage', function ($scope, $storage) {
    ngStorage = $storage;
    var classFunc = 'MainController';

    $scope.logout = function Logout() {
        Server.logOut(function (response, error) {
            if (error != null) {
                alert.errorMessage(error);
                return;
            } else {
                window.location = "login.html";
            }
        });
    };

    $scope.getAllUsers = function GetAllUsers() {
        console.log(classFunc + ', getAllUsers');
        Server.getAllUsers(function (response, error) {
            if (error != null) {
                alert.errorMessage(error);
                return;
            } else {
                $scope.$apply(function () {
                    console.log("user search returned object : ", response);
                    $storage.store.searchedUsers = response;
                });
            }
        });
    };

    $scope.getUserDetails = function GetUserDetails(username) {
        console.log(classFunc + ', getUserDetails');
        Server.getUserByUsername(username, function (response, error) {
            if (error != null) {
                alert.errorMessage(error);
                return;
            } else {
                $scope.apply(function () {
                    console.log("search user details obj : ", response);
                    $storage.store.getUserDetails = response;
                });
            }
        });
    };


    $scope.getAllBooks = function GetAllBooks() {
        console.log(classFunc + ', getAllBooks');
        Server.getAllBooks(function (response, error) {
            if (error != null) {
                alert.errorMessage(error);
                return;
            } else {
                $scope.apply(function () {
                    console.log("search user details obj : ", response);
                    $storage.store.books = response;
                });
            }
        })
    };

    $scope.getAllBookReviews = function GetAllBookReviews(bookId) {
        console.log(classFunc, 'getAllBookReviews');
        Server.getAllBookReviews(bookId, function (response, error) {
            if (error != null) {
                alert.errorMessage(error);
                return;
            } else {
                console.log("getAllBookReviews", response);
                $storage.store.books.forEach(function (obj) {
                    if (obj.bookId == bookId) {
                        obj.bookReviews = response;
                    }
                });
            }
        })
    };

    $scope.getAllBookLikes = function GetAllBookLikes(bookId) {
        console.log(classFunc, 'getAllBookLikes');
        Server.getAllBookLikes(bookId, function (response, error) {
            if (error != null) {
                alert.errorMessage(error);
                return;
            } else {
                console.log("getAllBookLikes", response);
                $storage.store.books.forEach(function (obj) {
                    if (obj.bookId == bookId) {
                        obj.bookLikes = response["nickname"];
                    }
                });
            }
        })
    };
    
    $scope.deactivateUser = function DeactivateUser(username){
        console.log(classFunc, 'deactivateUser');
        Server
    }
    
    
    
}]);