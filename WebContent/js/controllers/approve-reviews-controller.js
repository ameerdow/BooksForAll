window.app = window.app || angular.module('booksForAll', []);

app.controller('ApproveReviewsCtrl', ['$scope', function ($scope) {

    $scope.user = null;
    checkUserData($scope);
    $scope.logout = Server.logout;

    $scope.reviews = null;


    $scope.loadData = function () {
        Server.getPendingReviews(function (reviews, error) {
            if (error != null) {
                alert(error);
                return
            }
            $scope.$apply(function () {
                $scope.reviews = reviews;
            })
        });
    }

    // setTimeout(function () {
    //     $scope.$apply(function () {
    //         $scope.reviews = [{
    //             "ID": 1,
    //             "username": "ameerdow",
    //             "nickname": "ameerdow1",
    //             "review": "david askdmlasdmalsd als dkals dlkas dklas jksad sadkl"
    //         }, {
    //             "ID": 2,
    //             "username": "ameerdow1",
    //             "nickname": "ameerdow1",
    //             "review": "david askdmasldmas dlksa dlkasdlasdmalsd als dkals dlkas dklas jksad sadkl"
    //         }, {
    //             "ID": 3,
    //             "username": "davidan",
    //             "nickname": "ameerdow1",
    //             "review": "sadk las dklsadlksa dlkasdlasdmalsd als dkals dlkas dklas jksad sadkl"
    //         }];
    //     })
    // }, 500);

    $scope.rejectReview = function (review) {
        Server.rejectReview(review.id, function (s, error) {
            window.location.reload(true);
        });
    };
    $scope.approveReview = function (review) {
        Server.approveReview(review.id, function (s, error) {
            window.location.reload(true);
        });
    };

    $scope.openUserDetails = function (username) {
        window.location.href = "user.html?username=" + encodeURIComponent(username);
    };
}]);