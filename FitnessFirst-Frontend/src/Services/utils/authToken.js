import axios from 'axios';
//function for adding jwt token after authentication to each request
export default function authToken(token)
{
    if(token)
    {

        axios.defaults.headers.common['Authorisation'] = `Bearer ${token}`;
    }
    else{

        delete  axios.defaults.headers.common['Authorisation'] ;
    }
}