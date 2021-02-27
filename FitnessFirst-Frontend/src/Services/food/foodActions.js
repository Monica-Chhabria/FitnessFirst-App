import * as FT from './foodTypes';

export const updateMeal = (meal) => {

    return dispatch => {
        dispatch({
            type: FT.FOODADD_REQUEST
        });
        /*const params = {

            "username": email,
            
            "password": password,
            
            };*/
         /*delete axios.defaults.headers.common["Authorisation"];

        axios.post("http://localhost:8080/api/authenticate",   params,
        {  headers: {
        
         'Access-Control-Allow-Origin': '*'
       }})
            .then(response => {*/
               // let token = response.data.jwt;
              //  localStorage.setItem('jwtToken', token);
               // authToken(token);
                dispatch(success(meal));
           // })
            //.catch(error => {

               // alert(error);
               
               
               
           //    dispatch(failure());
           
           
               // });
        /*
            let initialcity = [];
        //    fetch('http://localhost:8761/FITNESSFIRST-USERDETAILS-SERVICE/fetchCity')
        fetch('http://localhost:8080/api/authenticate')
       
       // fetch('http://localhost:8761/FITNESSFIRST-USERDETAILS-SERVICE/fetchUserByEmail/foo')
       
        .then(response => {
                return response.json();
            }).then(data => {
                initialcity = data.map((cit) => {
                return cit.code
            });
            //console.log(initialcity);
            /*this.setState({
                city: initialcity,
            });*/
       // });*/


    };
};



const success = (mealdata) => {
    return {
        type: FT.SUCCESS,
        payload: {meal: mealdata}
        
    };
};




const failure = () => {
    return {
        type: FT.FAILURE,
        payload: false
    };
};