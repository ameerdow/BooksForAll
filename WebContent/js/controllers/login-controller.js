window.app = window.app || angular.module('booksForAll', []);

app.controller('LoginCtrl', ['$scope', function LoginCtrl($scope) {

    Server.getUserData(function (response, error) {
        if (error != null) {
            // not logged in
            // wait till controller loaded to prevent displayed unstructured angular data
            $("body").css("display", "block");
        } else {
            if (response.role === "User") {
                window.location = "index.html";
            } else {
                window.location = "../../users.html";
            }
        }
    });


    $scope.username = "";
    $scope.password = "";
    $scope.fieldsDisabled = false;
    $scope.loading = false;

    $scope.login = function login() {
        $scope.errorMessage = "";
        $scope.loading = true;

        setTimeout(function () {
            Server.login($scope.username, $scope.password, function (response, error) {
                if (error != null) {
                    $scope.$apply(function () {
                        onLoginFailed("Incorrect username or password");
                    });
                    return
                }
                window.location.href = "index.html";
            });
        }, 1000);
    };

    $scope.isFieldsDisabled = function isFieldsDisabled() {
        return $scope.username.length === 0 || $scope.password.length === 0 || $scope.loading;
    };

    function onLoginFailed(error) {
        $scope.password = "";
        $scope.errorMessage = error;
        $scope.loading = false;
        setTimeout(function () {
            $('#password').focus();
        }, 100);
    }

}]);
