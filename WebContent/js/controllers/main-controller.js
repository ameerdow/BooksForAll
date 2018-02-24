window.app = window.app || angular.module('booksForAll', []);

app.controller('MainCtrl', ['$scope', 'MainStorage', function LoginCtrl($scope, $storage) {
    Server.getUserData(function (response, error) {
        if (error != null) {
            // not logged in
            window.location = "login.html";
        } else {
            // wait till controller loaded to prevent displayed unstructured angular data
            $("body").css("display", "block");
        }
    });

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
