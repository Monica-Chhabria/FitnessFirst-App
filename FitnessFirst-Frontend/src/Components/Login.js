import React, { Component } from 'react';
import { Form, FormGroup, Label, Input, FormText,Container,Col,Button } from 'reactstrap';
import {connect} from 'react-redux';
import {authenticateUser} from '../Services/index';
import authToken from '../Services/utils/authToken';
import { ToastContainer, toast } from 'react-toastify';

import MyToast from './MyToast';
  class Login extends Component {
    state = {email:'', password:'', error:'',show : false}; 
    notify = (message) => toast.error(message,
    {position :"top-center"});
    validateUser = () => {
        //alert('this.state.email '+this.state.email);
        
       // alert('this.state.password '+this.state.password);
       //to set variable to set popup

 
     let issuccess =    this.props.authenticateUser(this.state.email, this.state.password);
        setTimeout(() => {
            if(this.props.auth.isLoggedIn) {
                if(localStorage.jwtToken) {
                    authToken(localStorage.jwtToken);
                 }
                 if(issuccess)
                 {
                    this.setState({show : true});

                 }
                return this.props.history.push("/");
            } else {
                this.resetLoginForm();
                issuccess= false;
              
                this.setState({show : true});
                this.notify("Invalid Username or Password");
             //   this.setState({"error":"Invalid email and password"});
           
            }
        }, 500);

     
          
    };

    resetLoginForm = () => {
        this.setState(() => this.initialState);
    };
        constructor(props) {
           super(props);
          // this.state = this.initialState;
              // this.state = {email:'', password:'', error:'',show:''};       
        }


 
    credentialChange = event => {
        this.setState({
            [event.target.name] : event.target.value
        });
        //alert(this.props.auth.isLoggedIn);
    };
     loginform = stateval =>(
        <Container className = "mt-5">

        <div>
                   

       <div style={{"display":stateval.show ? "block" : "none"}}>
                {  /*{  <MyToast show = {"true"} message = {" Login Done Succesfully."} type = {"success"}/>}*/}
            
               { /*  <MyToast show = {stateval.show} message = {"Invalid Username or   Password"} type = {"failure"}/>*/}
                
                </div>
                <ToastContainer />
        <Form className = "mt-5">
  <FormGroup row>

<Label for="email" sm={2}>Email</Label>

<Col sm={10}>
 <Input
   type="email"
   name="email"
   id="email"
   onChange={this.credentialChange}
 />
 

</Col>
</FormGroup>
<FormGroup row>
<Label for="password" sm={2}>Password</Label>
<Col sm={10}>

<Input
type="password"
name="password"
id="password"
onChange={this.credentialChange}
/>

</Col>
</FormGroup>
<FormGroup check row>
 <Col sm={{ size: 10, offset: 2 }}>
   <Button className="bg-primary" onClick={this.validateUser}>Submit</Button> <Button className="bg-primary">Reset</Button>
 </Col>{}
 {/*<Col sm={{ size: 10, offset: 2 }}>
   <Button>Reset</Button>
 </Col>*/}

</FormGroup>
</Form>
     
     </div>
</Container>
     );
    render() {
        return (
            <div>
                <Container className= "mt-5">
                {localStorage.getItem('jwtToken')?null:this.loginform(this.state) }
                </Container>
      {/*{this.props.auth.username ? null : this.loginform}*/}
      </div>
        )
    }
}
const mapStateToProps = state => {
    return {
        auth:state.auth
    }
};

const mapDispatchToProps = dispatch => {
    return {
        authenticateUser: (email, password) => dispatch(authenticateUser(email, password))
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Login);