const Server = {};

const GET = "GET";
const POST = "POST";

const proj = "/BooksForAll"

// Get requests

const GET_ALL_USERS = "/users";
const GET_USER_BY_USERNAME = "/user/{username}";
const GET_LOGGED_IN_USER = "user";
const SEARCH_USER = "/search/user/{searchText}";
const GET_ALL_BOOKS = "/books";
const GET_ALL_BOOK_LIKES = "/book/likes/{bookId}";
const GET_ALL_BOOK_REVIEWS = "/book/review/{bookId}";
const GET_ALL_BOOK_PURCHASES = "/book/purchases/{bookId}";
const GET_ALL_BOOK_USER_PURCHASES = "/book/user/purchases/{username}";
const GET_ALL_BOOK_USER_NOT_PURCHASES = "/book/user/not/purchases/{username}";
const SEARCH_BOOKS = "/search/book/{searchText}";
const GET_BOOK_BY_ID = "/book/id/{bookId}";
const GET_BOOK_BY_CATEGORY = "/book/category/{category}";


// Post requests

const APPROVE_REVIEW = "/review";
const SIGN_UP = "/user";
const LOGIN = "/login";
const LOGOUT = "/logout";
const BUY_BOOK_BY_ID = "/book/buy/";
const LIKE_BOOK_BY_ID = "/book/like/";
const REVIEW_BOOK_BY_ID = "/book/review/";
const DELETE_BOOK_BY_ID = "/book/delete";
const SAVE_READ_BOOK_POSITION = "/book/position";


var getUrl = function(url){
  return proj + url;
};

const call = function (method, url, data, callback) {
    console.log("Server", "call |data|:", method, url, data);
    if (method == GET) {
        $.ajax({
            method: GET,
            url: getUrl(url),
            success: function (response) {
                console.log("Server", "call |RESPONSE|:", method, url, response);
                callback(response);
            },
            error: function (error) {
                var err;
                try {
                    err = JSON.parse(error.responseText);
                } catch (e) {
                    err = error.responseText;
                }
                console.log("Server", "call |ERROR|:", method, url, err);
                callback(null, err);
            }
        });
    } else if (method == POST) {
        $.ajax({
            method: POST,
            url: getUrl(url),
            data: JSON.stringify(data),
            success: function (response) {
                console.log("Server", "call |RESPONSE|:", method, url, response);
                callback(response);
            }, error: function (error) {
                var err;
                try {
                    err = JSON.parse(error.responseText);
                } catch (e) {
                    err = error.responseText;
                }
                console.log("Server", "call |ERROR|:", method, url, err);
                callback(null, err);
            }
        })
    } else {
        callback(null, {message: "Invalid ajax method"});
    }
};


/********************************************
 *  Get Functions
 *******************************************/


/**
 * Gets all users
 * @param callback
 */
Server.getAllUsers = function (callback) {
    call(GET, GET_ALL_USERS, {}, callback);
};
/**
 * Get current logged in user data from session
 * @param callback
 */
Server.getUserData = function (callback) {
    call(GET, GET_LOGGED_IN_USER, {}, callback);
};

/**
 * Get user data by username
 * @param username the related username id
 * @param callback
 */
Server.getUserByUsername = function (username, callback) {
    call(GET, GET_USER_BY_USERNAME.replace("{username}", encodeURIComponent(username)), {}, callback);
};

/**
 * Get book data by book id
 * @param bookId the related book id
 * @param callback
 */
Server.getBookById = function (bookId, callback) {
    call(GET, GET_BOOK_BY_ID.replace("{bookId}", encodeURIComponent(bookId)), {}, callback);
};

/**
 * Get book data by category
 * @param category the related category
 * @param callback
 */
Server.getBookByCategory = function (category, callback) {
    call(GET, GET_BOOK_BY_CATEGORY.replace("{category}", encodeURIComponent(username)), {}, callback);
};

/**
 * Search user
 * @param username the search text from client
 * @param callback
 */
Server.searchUser = function (username, callback) {
    call(GET, SEARCH_USER.replace("{searchText}", encodeURIComponent(username)), {}, callback);
};

/**
 * Search book
 * @param text the search text from client
 * @param callback
 */
Server.searchUser = function (text, callback) {
    call(GET, SEARCH_BOOKS.replace("{searchText}", encodeURIComponent(text)), {}, callback);
};

/**
 * Get all books
 * @param callback
 */
Server.getAllBooks = function (callback) {
    call(GET, GET_ALL_BOOKS, {}, callback);
};

/**
 * Get all likes for book
 * @param bookId book id
 * @param callback
 */
Server.getAllBookLikes = function (bookId, callback) {
    call(GET, GET_ALL_BOOK_LIKES.replace("{bookId}", encodeURIComponent(bookId)), {}, callback);
};

/**
 * Get all reviews for book
 * @param bookId book id
 * @param callback
 */
Server.getAllBookReviews = function (bookId, callback) {
    call(GET, GET_ALL_BOOK_REVIEWS.replace("{bookId}", encodeURIComponent(bookId)), {}, callback);
};


/**
 * Get all purchases of book
 * @param bookId book id
 * @param callback
 */
Server.getAllBookPurchases = function (bookId, callback) {
    call(GET, GET_ALL_BOOK_PURCHASES.replace("{bookId}", encodeURIComponent(bookId)), {}, callback);
};

/**
 * Get all purchases of user
 * @param username book id
 * @param callback
 */
Server.getAllUserBookPurchases = function (username, callback) {
    call(GET, GET_ALL_BOOK_USER_PURCHASES.replace("{username}", encodeURIComponent(username)), {}, callback);
};

/**
 * Get all book not purchased by user
 * @param username book id
 * @param callback
 */
Server.getAllNotUserBookPurchases = function (username, callback) {
    call(GET, GET_ALL_BOOK_USER_NOT_PURCHASES.replace("{username}", encodeURIComponent(username)), {}, callback);
};


/********************************************
 *  Set Functions
 *******************************************/

/**
 * approve review
 * @param reviewId review id
 * @param callback
 */
Server.approveReview = function (reviewId, callback) {
    call(POST, APPROVE_REVIEW, {
        reviewId: reviewId
    }, callback);
};

/**
 * Register a new user in the system
 * @param username unique login username credential
 * @param email
 * @param password login password credential
 * @param street
 * @param number house number
 * @param city
 * @param zip
 * @param country
 * @param phoneNumber
 * @param nickname
 * @param description
 * @param photoUrl
 * @param callback
 */
Server.register = function (username, email, password, street, number, city, zip, country, phoneNumber, nickname, description, photoUrl, callback) {
    call(POST, SIGN_UP, {
        username: username,
        email: email,
        password: password,
        street: street,
        number: number,
        city: city,
        zip: zip,
        country: country,
        phoneNumber: phoneNumber,
        nickname: nickname,
        description: description,
        photoUrl: photoUrl
    }, callback);
};

/**
 * Login to the system
 * @param username
 * @param password
 * @param callback
 */
Server.login = function (username, password, callback) {
    call(POST, LOGIN, {
        username: username,
        password: password
    }, callback);
};

/**
 * buy book
 * @param bookId
 * @param price
 * @param callback
 */
Server.buyBook = function (bookId, price, callback) {
    call(POST, BUY_BOOK_BY_ID, {
        bookId: bookId,
        price: price
    }, callback);
};

/**
 * like book
 * @param bookId
 * @param callback
 */
Server.buyBook = function (bookId, callback) {
    call(POST, LIKE_BOOK_BY_ID, {
        bookId: bookId
    }, callback);
};

/**
 * review book
 * @param bookId
 * @param review
 * @param callback
 */
Server.buyBook = function (bookId, review, callback) {
    call(POST, REVIEW_BOOK_BY_ID, {
        bookId: bookId,
        review: review
    }, callback);
};
/**
 * save book read position
 * @param bookId
 * @param position
 * @param callback
 */
Server.buyBook = function (bookId, position, callback) {
    call(POST, SAVE_READ_BOOK_POSITION, {
        bookId: bookId,
        position: position
    }, callback);
};

/**
 * Logout from the server
 * @param callback
 */
Server.logOut = function (callback) {
    call(POST, LOGOUT, {}, callback);
};