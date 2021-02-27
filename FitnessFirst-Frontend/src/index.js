import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Router , Link, Switch, Route} from 'react-router-dom';
import history from './Components/historytest';
import {Provider} from 'react-redux';
import store from './Services/store';
import 'react-toastify/dist/ReactToastify.css';

{/*ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);*/}
//Added for redux and provider for redux and router for routing spa
ReactDOM.render(
  <Provider store={store}>
  <Router history = {history}> 
    <App />
  </Router>
  </Provider>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
