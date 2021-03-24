import logo from './logo.svg';
import './App.css';
import Header from './Components/Header';
import Menus from './Components/Menus';
import { Container, Row, Col } from 'reactstrap';
import {Router , Link, Switch, Route} from 'react-router-dom';
import Home from './Components/Home';
import Food from './Components/Food';
import BreakFast from './Components/BreakFast';
import Dinner from './Components/Dinner';
import Lunch from './Components/Lunch';
import {connect} from 'react-redux';
import {logoutUser} from './Services/index';
import RegisterForm from "./Components/RegisterForm";
import FoodNutrients from './Components/FoodNutrients';
import Login from "./Components/Login";
import Exercise from "./Components/Exercise";
import AboutUs from "./Components/About";
function App(props) {
  return (
    <div className="App">
  {/*  <Router history = {history}> */}
    <Container fluid>
      <Row>
      <Col md = "12">
        <Header/>
        </Col>
      </Row>

      <Row>
      <Col md = "12">
      <Route path = "/login" component = {Login} exact />
      <Route path = "/logout" component = {Home} exact />
      <Route path = "/register" component = {RegisterForm} exact />
        </Col>
      </Row>
      <Row>
      <Col md = "3">
      {/*{props.auth.isLoggedIn ? <Menus/> :null}*/}
      {localStorage.getItem('jwtToken')?<Menus/>:null}

       </Col>
       <Col md = "9">
         <Route path = "/" component = {Home} exact/>
         <Route path = "/AddFood" component = {Food} exact/>
         <Route path = "/BreakFast" component = {BreakFast} exact/>
         <Route path = "/nutrition" component = {FoodNutrients} exact />
         <Route path = "/Lunch" component = {Lunch} exact />
         <Route path = "/Dinner" component = {Dinner} exact />
         <Route path = "/AddExercise" component = {Exercise} exact />
         <Route path ="/AboutUs" component={AboutUs} exact />

         {/*<Route exact path="/nutrition/:calories" render={(props) => <FoodNutrients globalStore={globalStore} {...props} />  />*/}

         {/*
         <Route path = "" component = {}/>
         
         <Route path = "" component = {Home}/>*/}
   
       </Col>
       </Row> 
    </Container>
    {/*</Router>*/}
    </div>
   
 
  );
}

const mapStateToProps = state => {
  return {
      auth: state.auth
  };
};

const mapDispatchToProps = dispatch => {
  return {
      logoutUser: () => dispatch(logoutUser())
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(App);
