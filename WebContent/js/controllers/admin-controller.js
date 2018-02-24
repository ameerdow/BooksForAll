window.app = window.app || angular.module('booksForAll', []);

const classFunc = "AdminCtrl";
app.controller('AdminCtrl', ['$scope', function LoginCtrl($scope) {

    $scope.user = null;

    // Server.getUserData(function (response, error) {
    //     if (error != null) {
    //         // not logged in
    //         window.location = "login.html";
    //     } else {
    //         if (response.role === "User") {
    //             window.location = "index.html";
    //         }
    //         // wait till controller loaded to prevent displayed unstructured angular data
    //         $("body").css("display", "block");
    //     }
    // });

    $scope.user = {
        "username": "admin",
        "email": "admin@email.com",
        "password": "passw0rd",
        "address": {"street": "admin", "number": 1, "city": "admin", "zip": "1234567", "country": "admin"},
        "phoneNumber": "0546597762",
        "nickname": "nickname",
        "description": "description",
        "photoUrl": "photoUrl",
        "role": "Admin",
        "deleted": "N",
        "creationDate": "Feb 24, 2018"
    };
    $("body").css("display", "block");

    $scope.users = [
        {
            "username": "admin1",
            "email": "admin@email.com",
            "password": "passw0rd",
            "address": {"street": "admin", "number": 1, "city": "admin", "zip": "1234567", "country": "admin"},
            "phoneNumber": "0546597762",
            "nickname": "nickname",
            "description": "description",
            "photoUrl": "photoUrl",
            "role": "Admin",
            "deleted": "N",
            "creationDate": "Feb 24, 2018"
        }, {
            "username": "admin2",
            "email": "admin@email.com",
            "password": "passw0rd",
            "address": {"street": "admin", "number": 1, "city": "admin", "zip": "1234567", "country": "admin"},
            "phoneNumber": "0546597762",
            "nickname": "nickname",
            "description": "description",
            "photoUrl": "photoUrl",
            "role": "Admin",
            "deleted": "N",
            "creationDate": "Feb 24, 2018"
        }, {
            "username": "admin3",
            "email": "admin@email.com",
            "password": "passw0rd",
            "address": {"street": "admin", "number": 1, "city": "admin", "zip": "1234567", "country": "admin"},
            "phoneNumber": "0546597762",
            "nickname": "nickname",
            "description": "description",
            "photoUrl": "photoUrl",
            "role": "Admin",
            "deleted": "N",
            "creationDate": "Feb 24, 2018"
        }
    ];


    function getParameterByName(name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }


    $scope.initUserDetails = function InitUserDetails() {
        var username = getParameterByName("username", window.location.href);
        $scope.userDetails = GetUserDetails(username);
    };

    
    $scope.goToUserDetails = function GoToUserDetails(user) {
        location.href = "user.html?username=" + user.username;
    };


    $scope.logout = function Logout() {
        Server.logOut(function (response, error) {
            if (error != null) {
                alert.errorMessage(error);
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
            } else {
                $scope.$apply(function () {
                    console.log("user search returned object : ", response);
                    return response;
                });
            }
        });
    };

    $scope.getUserDetails = function GetUserDetails(username) {
        console.log(classFunc + ', getUserDetails');
        Server.getUserByUsername(username, function (response, error) {
            if (error != null) {
                alert.errorMessage(error);
            } else {
                $scope.apply(function () {
                    console.log("search user details obj : ", response);
                    return response;
                });
            }
        });
    };


    $scope.getAllBooks = function GetAllBooks() {
        console.log(classFunc + ', getAllBooks');
        Server.getAllBooks(function (response, error) {
            if (error != null) {
                alert.errorMessage(error);
            } else {
                $scope.apply(function () {
                    console.log("search user details obj : ", response);
                    return response;
                });
            }
        })
    };

    $scope.getAllBookReviews = function GetAllBookReviews(bookId) {
        console.log(classFunc, 'getAllBookReviews');
        Server.getAllBookReviews(bookId, function (response, error) {
            if (error != null) {
                alert.errorMessage(error);
            } else {
                console.log("getAllBookReviews", response);
                $storage.store.books.forEach(function (obj) {
                    if (obj.bookId === bookId) {
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
            } else {
                console.log("getAllBookLikes", response);
                $storage.store.books.forEach(function (obj) {
                    if (obj.bookId === bookId) {
                        obj.bookLikes = response["nickname"];
                    }
                });
            }
        })
    };

    $scope.deactivateUser = function DeactivateUser(username) {
        console.log(classFunc, 'deactivateUser');
        Server.deleteUser(username, function (response, error) {
            if (error != null) {
                alert.errorMessage(error);
            } else {
                console.log("deactivateUser", response);
            }
        })
    };

    $scope.approveReview = function ApproveReview(reviewId) {
        console.log(classFunc, "approveReview");
        Server.approveReview(reviewId, function (response, error) {
            if (error != null) {
                alert.errorMessage(error);
            } else {
                console.log("Review " + reviewId + " has been approved");
            }
        })
    };

    $scope.getBooksPurchase = function GetBooksPurchase() {

    }

}]);
