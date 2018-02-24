window.app = window.app || angular.module('booksForAll', []);

app.controller('MainCtrl', ['$scope', function MainCtrl($scope) {

    $scope.user = null;

    // Server.getUserData(function (response, error) {
    //     if (error != null) {
    //         // not logged in
    //         window.location = "login.html";
    //     } else {
    //         if (response.role !== "User") {
    //             window.location = "users.html";
    //         }
    // wait till controller loaded to prevent displayed unstructured angular data
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
    //     }
    // });

    $scope.logout = function Logout() {
        Server.logOut(function (response, error) {
            if (error != null) {
                alert.errorMessage(error);
            } else {
                window.location = "login.html";
            }
        });
    };


}]);
