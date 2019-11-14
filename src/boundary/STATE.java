package boundary;


/*
Acts as "routes" like in a url that renders pages based on the appState.
appState can be set from different methods in order to render the pages required.
 */
public enum STATE {
    LOGIN,

    // Movie Goer Pages
    MOVIE_GOER_MENU,
    MOVIE_GOER_MOVIE_DETAILS,
    MOVIE_GOER_MOVIE_LIST,
    MOVIE_GOER_LIST_TOP_5,
    MOVIE_GOER_MOVIE_SEARCH,
    MOVIE_GOER_BOOKING_MENU,
    MOVIE_GOER_ADD_REVIEW,
    MOVIE_GOER_MOVIE_SCREENING_LIST,

    // Admin pages
    ADMIN_MENU,
    ADMIN_CREATE_MOVIE,
    ADMIN_UPDATE_MOVIE,
    ADMIN_DELETE_MOVIE,
    ADMIN_CREATE_SCREENING,
    ADMIN_UPDATE_SCREENING,
    ADMIN_DELETE_SCREENING,
    ADMIN_UPDATE_PRICE,
    ADMIN_UPDATE_PH,
    ADMIN_LIST_TOP
}
