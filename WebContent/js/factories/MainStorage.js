var app = angular.module('booksForAll');

app.factory('MainStorage', function () {
    var classFunc = "MainStorage";
    this.store = {
        selectedBook: {
            book: null,
            reviews: [],
            loading: false,
            position: ""
        },
        registerButton: false,
        loginButton: false,
        searchedBook: [],
        searchBookText: "",
        addNewReview: false,
        books: [{
            book:{},
            reviews:[{
            }],
            likes:[{
            }]
        }],
        searchedUsers: {},
        getUserDetails: {}
    };


    this.syncCurrentUser = function (user) {
        console.log(classFunc, "syncCurrentUser:", user);
        this.store.user = user;
        this.store.systemLoading = false;
    };

    this.setSelectedBook = function (book) {
        console.log(classFunc, "setSelectedBook:", book);
        this.store.selectedBook.book = book;
        this.store.selectedBook.loading = true;
    };

    this.unSelectBook = function () {
        console.log(classFunc, "unSelectBook");
        this.store.selectedBook = {
            book: null,
            review: [],
            loading: false
        };
    };

    return {
        doTheThing: function methodThatDoesAThing() {
        }
    };
});