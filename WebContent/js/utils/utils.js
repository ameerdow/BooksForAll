function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}


function checkUserData($scope) {
    // Server.getUserData(function (response, error) {
    //     if (error != null) {
    //         // not logged in
    //         window.location = "login.html";
    //     } else {
    //         // wait till controller loaded to prevent displayed unstructured angular data
    //         $scope.$apply(function () {
    //             $scope.user = response;
    //             $("body").css("display", "block");
    //         });
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
}