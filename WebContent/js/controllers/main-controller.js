window.app = window.app || angular.module('booksForAll', []);

app.controller('MainCtrl', ['$scope', function MainCtrl($scope) {

    $scope.user = null;
    checkUserData($scope);
    $scope.logout = Server.logout;

    $scope.searchText = "";

    $scope.featuredBooks = [];

    // load sample books to display
    $scope.loadData = function () {
        Server.getAllBooks(function (books, error) {
            if (error != null) {
                alert(error);
            } else {
                $scope.$apply(function () {
                    $scope.featuredBooks = books.length > 4 ? books.splice(0, 4) : books;
                })
            }
        });
    }

    // $scope.featuredBooks = [{
    //     ID: 1,
    //     name: "The City That Was",
    //     iconPath: "books/1.jpg"
    // }, {
    //     ID: 2,
    //     name: "David And Ameer",
    //     iconPath: "books/2.jpg"
    // }, {
    //     ID: 3,
    //     name: "Book 3",
    //     iconPath: "books/3.jpg"
    // }, {
    //     ID: 4,
    //     name: "Book 4",
    //     iconPath: "books/4.jpg"
    // }];


    $scope.search = function () {
        if ($scope.searchText.trim().length > 0) {
            window.location.href = "book-results.html?q=" + encodeURIComponent($scope.searchText);
        }
    };

    $scope.openBookById = function (book) {
        window.location.href = "book.html?bookId=" + book.ID;
    }

}]);
