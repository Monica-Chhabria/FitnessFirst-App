
import React, {Component} from 'react';
 
import { ListGroup, ListGroupItem } from 'reactstrap';

import {BrowserRouter as Router, Link, Switch, Route} from 'react-router-dom';
import {connect} from 'react-redux';

import {logoutUser} from '../Services/index';
export  class Menus extends Component {
 
 
  
  
  
   style = {

    width:'100%',
  
   };
    logout = () => {
   this.props.logoutUser();
   
   };
   val = "active";
    render() {
        return (
          <div>
         
          <ListGroup className = "mt-2"  >
          
           <Link className="list-group-item list-group-item-action" tag = "a" to = "/" action>
             Home
           </Link>
           <Link className="list-group-item list-group-item-action" tag = "a" to = "/AddFood" action>
           Add Food
           </Link>
           <Link className="list-group-item list-group-item-action" tag = "a" to = "/AddExercise" action>
           Add Exercise
           </Link>
           
           <Link className="list-group-item list-group-item-action" tag = "a" to = "/GetCalorie" action>
           Get Calorie Details
           </Link>
           <Link className="list-group-item list-group-item-action" tag = "a" to = "/AboutUs" action>
           About us
           </Link>
           
           <Link className="list-group-item list-group-item-action" tag = "a" to = "/Profile" action>
           Your Profile
           </Link>
           
           <Link className="list-group-item list-group-item-action" tag = "a" onClick ={this.logout}  action>
          Logout
           </Link>
           {/*}
           
            <ListGroupItem tag="a" href="#" action>Home</ListGroupItem>
            <ListGroupItem  tag="a" href="#" action>Add Food</ListGroupItem>
            <ListGroupItem tag="a" href="#" action>Add Exercise</ListGroupItem>
            <ListGroupItem tag="a" href="#" action>Get Calorie Details</ListGroupItem>
            <ListGroupItem tag="a" href="#" action>About Us</ListGroupItem>
            <ListGroupItem tag="a" href="#" action>Your Profile</ListGroupItem>
            <ListGroupItem  tag="a" href="#" action>Logout</ListGroupItem>
            
            
            /*to = "/logout"*/}
          </ListGroup>
          {/*<p />
          <h3>Buttons </h3>
          <ListGroup>
            <ListGroupItem active tag="button" action>Cras justo odio</ListGroupItem>
            <ListGroupItem tag="button" action>Dapibus ac facilisis in</ListGroupItem>
            <ListGroupItem tag="button" action>Morbi leo risus</ListGroupItem>
            <ListGroupItem tag="button" action>Porta ac consectetur ac</ListGroupItem>
            <ListGroupItem disabled tag="button" action>Vestibulum at eros</ListGroupItem>
          </ListGroup>*/}
        </div>

        );
    }
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
export default connect(mapStateToProps, mapDispatchToProps)(Menus);
