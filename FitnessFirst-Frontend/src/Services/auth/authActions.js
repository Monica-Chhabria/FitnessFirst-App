import * as AT from './authTypes';
import axios from 'axios';
import authToken from '../utils/authToken';
import MyToast from '../../Components/MyToast';
import { apiRequest } from '../../api/utils';

export const authenticateUser = (email, password) => {
    
    const credentials = {
        username: email,
        password: password
    };
    return dispatch => {
        dispatch({
            type: AT.LOGIN_REQUEST
        });
        const params = {

            "username": email,
            
            "password": password,
            
            };
         delete axios.defaults.headers.common["Authorisation"];
         
         apiRequest('POST', `/api/authenticate`,params,'AUTHENTICATE').then( function getData(result) {
            // that.getVal(result);
            let token = result.data.jwt;
            localStorage.setItem('jwtToken', token);
            authToken(token);
            dispatch(success(true,email));
            return true;
           })
           .catch(function getError(error){
             
               // alert(error);
               dispatch(failure());
               return false;
           });

       /* axios.post("http://localhost:8761/FITNESSFIRST-AUTHENTICATION-SERVICE/api/authenticate",   params
        
       )
            .then(response => {
                let token = response.data.jwt;
                localStorage.setItem('jwtToken', token);
                authToken(token);
                dispatch(success(true,email));
                return true;
            })
            .catch(error => {

               // alert(error);
                dispatch(failure());
                return false;
            });*/
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

export const logoutUser = () => {
    return dispatch => {
        dispatch({
            type: AT.LOGOUT_REQUEST
        });
        localStorage.removeItem('jwtToken');
        dispatch(success(false,''));
    };
};

const success = (isLogged,user) => {
    return {
        type: AT.SUCCESS,
        payload: {isLoggedIn: isLogged,
        username:user}
        
    };
};

const failure = () => {
    return {
        type: AT.FAILURE,
        payload: false
    };
};