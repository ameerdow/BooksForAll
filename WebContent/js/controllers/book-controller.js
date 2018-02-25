window.app = window.app || angular.module('booksForAll', []);

app.controller('BookCtrl', ['$scope', function BookCtrl($scope) {

    $scope.user = null;
    checkUserData($scope);
    $scope.logout = Server.logout;

    $scope.bookId = getParameterByName("bookId");
    if ($scope.bookId == null || $scope.bookId.length === 0) {
        window.location = "index.html";
        return;
    }

    $scope.loadData = function () {
        Server.getBookById($scope.bookId, function (book, error) {
            if (error != null) {
                alert(error);
                return
            }
            $scope.$apply(function () {
                $scope.book = book;
            })
        });
    }


    // setTimeout(function () {
    //     $scope.$apply(function () {
    //         $scope.book = {
    //             "ID": 1,
    //             "name": "1",
    //             "price": 120.2,
    //             "description": "111111111",
    //             "likesCount": 12,
    //             "reviewCount": 3,
    //             "deleted": "N",
    //             "filePath": "books/1.html",
    //             "filePrePath": "books/1-pre.html",
    //             "iconPath": "books/1.jpg",
    //             "purchased": true,
    //             "position": 0,
    //             "likers": [{username: "admin", nickname: "admin"}, {username: "ameerdow", nickname: "ameerdow1"}],
    //             "reviews": [{
    //                 "username": "ameerdow",
    //                 "nickname": "ameerdow1",
    //                 "review": "david askdmlasdmalsd als dkals dlkas dklas jksad sadkl"
    //             }, {
    //                 "username": "ameerdow1",
    //                 "nickname": "ameerdow1",
    //                 "review": "david askdmasldmas dlksa dlkasdlasdmalsd als dkals dlkas dklas jksad sadkl"
    //             }, {
    //                 "username": "davidan",
    //                 "nickname": "ameerdow1",
    //                 "review": "sadk las dklsadlksa dlkasdlasdmalsd als dkals dlkas dklas jksad sadkl"
    //             }]
    //         };
    //     })
    // }, 500);

    $scope.bookHtmlLoaded = function () {
        if ($scope.book.purchased) {
            $("html, body").animate({scrollTop: ($('body').height() * (parseFloat($scope.book.position) / 100)) + "px"}, 1000);
        }

        setTimeout(function () {
            window.addEventListener("scroll", function () {
                if ($scope.book.purchased) {
                    $("#progress").css("width", ((window.scrollY * 100) / $('body').height()) + "%");
                }
            });

            if ($scope.book.purchased) {
                setInterval(function () {
                    Server.saveReadPositionBook($scope.bookId, ((window.scrollY * 100) / $('body').height()), function () {
                    });
                }, 1000)
            }
        }, 3000);
    };

    $scope.openUserDetails = function (username) {
        window.location.href = "user.html?username=" + encodeURIComponent(username);
    };

    $scope.isLiked = function () {
        if ($scope.book != null) {
            for (var i = 0; i < $scope.book.likers.length; i++) {
                if ($scope.book.likers[i].username === $scope.user.username) {
                    return true;
                }
            }
        }
        return false;
    };

    $scope.likeBook = function () {
        Server.likeBook($scope.bookId, function (response, error) {
            if (error != null) {
                alert(error);
                return;
            }
            window.location.reload(true);
        });
    };

    $scope.reviewBook = function () {

    };
}]);
