window.app = window.app || angular.module('booksForAll', []);
/**
 * ng-enter used for detect enter keydown/keypress and call the attached func
 */
app.directive('ngEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if (event.which === 13) {
                scope.$apply(function () {
                    scope.$eval(attrs.ngEnter);
                });

                event.preventDefault();
            }
        });
    };
});