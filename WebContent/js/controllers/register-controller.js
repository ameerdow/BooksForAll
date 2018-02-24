window.app = window.app || angular.module('booksForAll', []);

window.app.controller('RegisterCtrl', ['$scope', function ($scope) {

    $scope.countries = Constants.countries;

    $scope.username = "";
    $scope.password = "";
    $scope.confirmPassword = "";
    $scope.nickname = "";
    $scope.email = "";
    $scope.phone = "";
    $scope.country = "";
    $scope.city = "";
    $scope.streetName = "";
    $scope.houseNumber = "";
    $scope.zipcode = "";
    $scope.photoUrl = "";
    $scope.description = "";
    $scope.loading = false;

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

    /**
     * register the user
     */
    $scope.register = function register() {
        $scope.errorMessage = 0;
        var errorMessage = checkValid("username", "- Username must not be empty and up to 10 characters");
        errorMessage += checkValid("email", "- Invalid email address");
        errorMessage += checkValid("password", "- Password must be up to 8 characters");
        if ($scope.password !== $scope.confirmPassword) {
            errorMessage += "\n- Passwords does not matches";
        }
        errorMessage += checkValid("nickname", "- Nickname must be up to 20 characters contains letters and digits");

        if ($('#phone').val().match(/((^(05)\d{8}$)|(^(02|03|04|08|09)\d{7}$))/) == null) {
            errorMessage += "\n- Invalid phone number"
        }
        if ($scope.country.length === 0) {
            errorMessage += "\n- Select your country"
        }
        errorMessage += checkValid("city", "- Invalid city name at least 3 characters");
        errorMessage += checkValid("street", "- Street must not be empty");
        errorMessage += checkValid("houseNumber", "- House number must not be empty and contains only digits");
        errorMessage += checkValid("zipcode", "- ZipCode must be 7 digits");

        if (errorMessage.replace("\n", "").length !== 0) {
            $scope.errorMessage = errorMessage.substring(1);
            $(window).scrollTop(0);
            return;
        }

        $scope.loading = true;

        Server.register($scope.username, $scope.email, $scope.password, $scope.streetName, $scope.houseNumber,
            $scope.city, $scope.zipcode, $scope.country, $scope.phone, $scope.nickname, $scope.description,
            $scope.photoUrl, function (response, error) {
                if (error != null) {
                    $scope.$apply(function () {
                        $scope.errorMessage = error.message;
                    });
                } else {
                    // login after register
                    Server.login($scope.username, $scope.password, function (response, error) {
                        if (error != null) {
                            console.log("RegisterController, Login: ", error.message);
                            window.location = "login.html";
                        } else {
                            window.location = "index.html";
                        }
                    })
                }
            });

    };

    function checkValid(id, message) {
        var input = $("#" + id)[0];
        if (input.value.length === 0 || !input.validity.valid) {
            return "\n" + message;
        }
        return "";
    }
}]);