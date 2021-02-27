import {FOODADD_REQUEST,  SUCCESS, FAILURE} from './foodTypes';
const initialState = {
    meal: ''
    
};
const reducer = (state = initialState, action) => {
    switch(action.type) {
        case FOODADD_REQUEST:
      
            return {
                ...state
            };
        case SUCCESS: 
        case FAILURE: 
            return {
                meal: action.payload.meal
              
            };
        default:
            return state;
    }
};

export default reducer;