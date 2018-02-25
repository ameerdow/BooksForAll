window.app = window.app || angular.module('booksForAll', []);

app.controller('SearchCtrl', ['$scope', function SearchCtrl($scope) {

    $scope.user = null;
    checkUserData($scope);
    $scope.logout = Server.logout;
    $scope.books = [];
    $scope.toggledReviews = {};

    $scope.query = getParameterByName("q");
    if ($scope.query == null || $scope.query.length === 0) {
        window.location = "index.html";
        return;
    }

    $scope.loadData = function () {
        Server.getAllBooks(function (books, error) {
            if (error != null) {
                alert(error);
                return;
            }

            $scope.$apply(function () {
                $scope.books = books.filter(function (book) {
                    return book.name.indexOf($scope.query) !== -1 || book.description.indexOf($scope.query) !== -1;
                });
            });
        });
    }

    // $scope.books = [{
    //     "ID": 1,
    //     "name": "1",
    //     "price": 120.2,
    //     "description": "111111111",
    //     "likesCount": 12,
    //     "reviewCount": 3,
    //     "deleted": "N",
    //     "filePath": "books/1.html",
    //     "filePrePath": "books/1-pre.html",
    //     "iconPath": "books/1.jpg",
    //     "reviews": [{
    //         "username": "ameerdow",
    //         "nickname": "ameerdow",
    //         "review": "david askdmlasdmalsd als dkals dlkas dklas jksad sadkl"
    //     }, {
    //         "username": "ameerdow1",
    //         "nickname": "ameerdow1",
    //         "review": "david askdmasldmas dlksa dlkasdlasdmalsd als dkals dlkas dklas jksad sadkl"
    //     }, {
    //         "username": "davidan",
    //         "nickname": "davidan",
    //         "review": "sadk las dklsadlksa dlkasdlasdmalsd als dkals dlkas dklas jksad sadkl"
    //     }]
    // }, {
    //     "ID": 2,
    //     "name": "2",
    //     "price": 150.2,
    //     "description": "2121213 l123n kj123 kj12 3klmsadlkas d",
    //     "likesCount": 12,
    //     "reviewCount": 2,
    //     "deleted": "N",
    //     "filePath": "books/2.html",
    //     "filePrePath": "books/2-pre.html",
    //     "iconPath": "books/2.jpg",
    //     "reviews": [{
    //         "username": "aasdk alskd meerdow1",
    //         "nickname": "aasdk alskd meerdow1",
    //         "review": "david askdmasldmas dlksa dlkasdlasdmalsd als dkals dlkas dklas jksad sadkl"
    //     }, {
    //         "username": "davidan ksals",
    //         "nickname": "davidan ksals",
    //         "review": "sadk las dklsadlksa dlkasdlasdmalsd als dkals dlkas dklas jksad sadkl"
    //     }]
    // }];

    $scope.openUserDetails = function (username) {
        window.location.href = "user.html?username=" + encodeURIComponent(username);
    };

}]);
