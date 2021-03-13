
import axios from 'axios';

const exercise = 'EXERCISE';
const nutritionix = 'NUTRITIONIX';
const authenticate = 'AUTHENTICATE';
const user = 'USER';
const calorie = 'CALORIE';
const API_BASE = 'http://localhost:8761/FITNESSFIRST-EXERCISE-SERVICE';
const EXERCISE_BASE = 'http://localhost:8761/FITNESSFIRST-EXERCISE-SERVICE';

const CALORIE_BASE = 'http://localhost:8761/FITNESSFIRST-CALORIES-SERVICE';

const USER_BASE = 'http://localhost:8761/FITNESSFIRST-USERDETAILS-SERVICE';

//const  = ;

const appid = '0be23763';
const appkey = '18a59c6f58cbcd1f4d04948204affa65';
const timeout = 8000;
const AUTH_BASE = 'http://localhost:8761/FITNESSFIRST-AUTHENTICATION-SERVICE';

const NUTRITIONIX_BASE = 'https://trackapi.nutritionix.com';
/**
 * Parses JSON responses for easier consumption.
 *
 * The returned promise behaves as follows:
 * * For "OK" responses (2xx status codes)
 *   * If the body has JSON, it resolves to the JSON itself
 *   * If the body has no JSON (i.e. is empty), it resolves to null
 * * For all other responses, it rejects with an `Error` object that contains
 *   the following properties:
 *   * `isFromServer`: Set to true, indicating it is a server error
 *   * `response`: The complete response, for reference if required
 *   * `responseJson`: The response body pre-converted to JSON for convenience
 *
 * @param {Object} response
 * @returns {Promise<{}>}
 */
export  function parseJsonResponse(response) {
  let json = null;
  try {
  //  json = await response.json();
  } catch (e) {
    // TODO Do something if response has no, or invalid JSON
  }

 if (response.data.message == "success") {
  return response;
} 
 //if(response.dad)
 /* if (response.data.message == "success") {
    return json;
  } else {
    const error = new Error(response.message);
    error.isFromServer = true;
    error.response = response;
    error.responseJson = json;

    throw error;
  }*/
  
}

/**
 * Performs an API request.
 *
 * @param {string} method - 'GET', 'POST' etc.
 * @param {string} path
 * @param {Object} [body]
 * @param {Object} [options] - `fetch` options other than `method` and `body`
 * @returns {Promise<{}>} As returned by {@link parseJsonResponse}
 */
export   function apiRequest(method, path, body = null,source) {
  /*const options = { method };
  if (body && method !== 'GET') {
    options.body = JSON.stringify(body);
  }*/
let header;
let exheader,nuheader,usheader,calheader,auheader;

  let finalPath = API_BASE + path;
  if(source ==user)
{

  usheader = {

    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json'
  };
  finalPath = USER_BASE + path;
  header = usheader;
}
  //const nutritionixPath = 
if(source == exercise)
{
  exheader = {

    'Access-Control-Allow-Origin': '*'
  };
  finalPath = EXERCISE_BASE + path;
  header = exheader;
}


if(source == authenticate)
{
  auheader = {

    'Access-Control-Allow-Origin': '*',
    
  };
  finalPath = AUTH_BASE + path;
  header = auheader;
}
if(source == nutritionix)
{
  nuheader = {

    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json',
    'x-app-id': appid,
    'x-app-key':appkey
  };
  finalPath = NUTRITIONIX_BASE + path;
  header = nuheader;
}
if(source == calorie)
{
  calheader = {

    'Access-Control-Allow-Origin': '*'
  };
  finalPath = CALORIE_BASE + path;
  header = calheader;
}
 // const response = await fetch(finalPath, options, { mode: 'no-cors'});
if(method == 'POST')
{
  //console.log('inside post');
  return axios({
  method: 'POST',
  url: finalPath,
  data: body,
  timeout: timeout,
  headers: header
  
});
}
if(method == 'GET')
{
 return axios({
  url: finalPath,
  method: 'GET',
  timeout: timeout,
  headers:header
});
}
 /*axios.get(finalPath,
       
{  headers: {

 'Access-Control-Allow-Origin': '*'
}}
).then(res => {
  //;
 //return parseJsonResponse(res);
 alert("insdie res hello"+res.data);
return "hello";
  });*/


}
