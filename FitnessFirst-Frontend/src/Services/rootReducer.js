import {combineReducers} from 'redux';
/*import userReducer from './user/userReducer';*/
import authReducer from './auth/authReducer';
import foodReducer from './food/foodReducer';

/*import bookReducer from './book/bookReducer';*/

const rootReducer = combineReducers({
    /*user: userReducer,
    book: bookReducer,*/
    auth: authReducer,
    food:foodReducer
});

export default rootReducer;