window.app = window.app || angular.module('booksForAll', []);

const classFunc = "MyBooksCtrl";
app.controller('MyBooksCtrl', ['$scope', function MyBooksCtrl($scope) {

    $scope.user = null;
    checkUserData($scope);
    $scope.logout = Server.logout;
    $scope.books = [];


    // Server.getAllUserBookPurchases($scope.user.username, function (books, error) {
    //     if (error != null) {
    //         alert(error);
    //         return
    //     }
    //     $scope.$apply(function () {
    //         $scope.books = books;
    //     })
    // })


    setTimeout(function () {
        $scope.$apply(function () {
            $scope.books = [{
                "ID": 1,
                "name": "1",
                "price": 120.2,
                "description": "111111111",
                "likesCount": 12,
                "reviewCount": 3,
                "deleted": "N",
                "filePath": "books/1.html",
                "filePrePath": "books/1-pre.html",
                "iconPath": "books/1.jpg",
                "purchased": true,
            }];
        })
    }, 500);

    $scope.openBookById = function (book) {
        window.location.href = "book.html?bookId=" + book.ID;
    }

}]);
