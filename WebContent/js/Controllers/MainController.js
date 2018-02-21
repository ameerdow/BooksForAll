var app = angular.module('booksForAll');

app.controller('MainController', ['$scope', 'MainStorage', function ($scope, $storage) {
    ngStorage = $storage;
}]);


app.controller('RegisterController', ['$scope', function ($scope) {

    /**
     * register the user
     */
    $scope.register = function Register() {
        console.log('register');

        var data = {
            inputUsernameRegister: $scope.inputUsernameRegister,
            inputPasswordRegister1: $scope.inputPasswordRegister1,
            inputPasswordRegister2: $scope.inputPasswordRegister2,
            inputEmailRegister: $scope.inputEmailRegister,
            inputStreetRegister: $scope.inputStreetRegister,
            inputHouseRegister: $scope.inputHouseRegister,
            inputCityRegister: $scope.inputCityRegister,
            inputZipRegister: $scope.inputZipRegister,
            inputCountryRegister: $scope.inputCountryRegister,
            inputPhoneRegister: $scope.inputPhoneRegister,
            inputNicknameRegister: $scope.inputNicknameRegister,
            inputDescriptionRegister: $scope.inputDescriptionRegister
        };

        if (validatePassword(data.inputPasswordRegister1, data.inputPasswordRegister2) && validateUsername(data.inputUsernameRegister)
            && validateNickname(data.inputNicknameRegister) && validateDescription(data.inputDescriptionRegister)) {
            Server.register(data.inputUsernameRegister, data.inputEmailRegister, data.inputEmailRegister, data.inputStreetRegister, data.inputHouseRegister, data.inputCityRegister,
                data.inputZipRegister, data.inputCountryRegister, data.inputPhoneRegister, data.inputNicknameRegister, data.inputDescriptionRegister, "", function (response, error) {
                   if(error != null){
                       alert(error.message);
                       return;
                   }else {
                       // login after register
                       Server.login(data.inputUsernameRegister, data.inputPasswordRegister1, function (response, error) {
                           if(error != null){
                               alert("Error Logging in");
                               console.log("RegisterController, Login: ", error.message);
                           }else{
                                window.location = "index.html";
                           }
                       })
                   }
                });
        }


    };

    var validatePassword = function (password, confirmPassword) {
        if (password != confirmPassword) {
            alert("Confirm password does not match");
            return false;
        }
        if (password == null || password.length > 8 || password.length == 0) {
            alert("Please choose another password, must be less than 8 chars");
            return false;
        }
        return true;
    };

    var validateUsername = function (username) {
        if (username.indexOf(" ") != -1) {
            alert("Username must not contain spaces, try: " + username.replace(/ /g, "_"));
            return false;
        }
        if (username == null || username.length > 10 || username.length == 0) {
            alert("Please choose another username, must be less than 10 chars");
            return false;
        }
        return true;
    };

    var validateNickname = function (nickname) {
        if (nickname.indexOf(" ") != -1) {
            alert("Nickname must not contain spaces, try: " + nickname.replace(/ /g, "_"));
            return false;
        }
        if (nickname == null || nickname.length > 20 || nickname.length == 0) {
            alert("Please choose another nickname, must be less than 10 chars");
            return false;
        }
        return true;
    };

    var validateDescription = function (description) {
        if (description.length > 50) {
            alert("Please choose another description, must be less than 50 chars");
            return false;
        }
        return true;
    }
}]);