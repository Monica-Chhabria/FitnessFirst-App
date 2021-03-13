
import axios from 'axios';

import React, { Component } from 'react';
import { Form, FormGroup, Label, Input, FormText, Container, Col, Button } from 'reactstrap';
import PropTypes from 'prop-types';  
import { ToastContainer, toast } from 'react-toastify';
import history from './historytest';
import { apiRequest } from '../api/utils';

export default class RegisterForm extends React.Component {
    constructor() {
        super();
        this.state = this.initialState;
    }

//method to display toast
    notify = (messagetype,message) => {
        if(messagetype=="success")
        {toast.success(message,
        {position :"top-center"})};
        if(messagetype=="error")
        {toast.error(message,
        {position :"top-center"})};
    };
    initialState = {
        city: [],  email:'', password:'', error:'',name:'', usercity:'',weight:'',weightmeasure:'',password:'',email:'',dob:''
    };
    
    credentialChange = event => {
        this.setState({
            [event.target.name] : event.target.value
        });
    };
    saveUser=()=>
    {
        var that = this;
        const params = JSON.stringify({

            "password": this.state.password,
            
            "email": this.state.email,
            
            });
           // delete axios.defaults.headers.common["Authorisation"];
           apiRequest('POST', `/saveUser`,params,'USER').then( function getData(result) {
            setTimeout(   that.notify("success","Registration Done Successfully"), 30000000);

            //let data = response.data;
        
           })
          .catch(function getError(error){
            console.log(error);
               
            that.notify("error","Registration Not Done Successfully");
          });
         
        /*axios.post("http://localhost:8085/saveUser",   params,
        {  headers: {
        
         'Access-Control-Allow-Origin': '*',
         'Content-Type': 'application/json'

       }})
            .then(response => {
                setTimeout(   this.notify("success","Registration Done Successfully"), 30000000);

                let data = response.data;
             //   setTimeout( history.push({ pathname:"/"}), 30000000);

               
            })
            .catch(error => {
               console.log(error);
               
               this.notify("error","Registration Not Done Successfully");
              
            });*/
        
    }
    componentDidMount() {
        var that = this;
        let initialcity = [];
        apiRequest('GET', `/fetchCity`,null,'USER').then( function getData(result) {
            let data = result.data;
            initialcity = data.map((cit) => {
                return cit.code
            });
           // console.log(initialcity);
           that.setState({
                city: initialcity,
            });
            /* console.log(result);
             data1 = result.data.calburnt;*/
            // const data = res.data.calburnt;
             //console.log("fetch exercise details"+data);  
          
            //console.log("fetch exercise details"+data);  
          
          
            //dataObj = result;
          //}*/)
           })
          .catch(function getError(error){
            console.log(error);
          });
       /* fetch('http://localhost:8761/FITNESSFIRST-USERDETAILS-SERVICE/fetchCity')
            .then(response => {
                return response.json();
            }).then(data => {
                initialcity = data.map((cit) => {
                return cit.code
            });
           // console.log(initialcity);
            this.setState({
                city: initialcity,
            });
        });*/
    }
    render() {
        let city = this.state.city;
        let optionItems = city.map((city) =>
                <option key={city}>{city}</option>
            );
        return (
            <div>
                <ToastContainer />

                <Container>
                    <Form className="mt-5">
                        <FormGroup row>

                            <Label for="name" sm={2}>Name</Label>

                            <Col sm={10}>
                                <Input
                                    type="name"
                                    name="name"
                                    id="name"
                                    onChange={this.credentialChange}

                                />

                                {

                                }
                            </Col>
                        </FormGroup>



                   
                   
                        <FormGroup row>

                            <Label for="usercity" sm={2}>City</Label>

                            <Col sm={10}>
                               
                            <Input type="select" name="usercity" id="usercity">
          
          <option value="">-Select-</option>
                {optionItems}
                onChange={this.credentialChange}
        </Input>
                                

                                {

                                }
                            </Col>
                        </FormGroup>
                        <FormGroup row>

<Label for="name" sm={2}>Date Of Birth</Label>

<Col sm={10}>
    <Input
        type="dob"
        name="dob"
        id="dob"
        onChange={this.credentialChange}

    />

    {

    }
</Col>
</FormGroup>
<FormGroup row>

<Label for="name" sm={2}>Weight</Label>

<Col sm={10}>
    <Input
        type="weight"
        name="weight"
        id="weight"
        onChange={(e) => {

            //setSearchVal(e.target.value);
        }}
    />

    {

    }
</Col>
</FormGroup>
<FormGroup row>
<Label for="weightmeasure" sm={2}>Weight measure</Label>

<Col sm={10}>
        <Input type="select" name="weightmeasure" id="weightmeasure">
          
          <option value="">-Select-</option>
          <option value="Kg">Kg</option>
          <option value="lbs">lbs</option>
            
        </Input>
        </Col>
        </FormGroup>   
                        <FormGroup row>

                            <Label for="email" sm={2}>Email</Label>

                            <Col sm={10}>
                                <Input
                                    type="email"
                                    name="email"
                                    id="email"
                                    onChange={this.credentialChange}
                                />

                                {

                                }
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
                                <Button onClick= {this.saveUser}>Submit</Button>
                            </Col>
                        </FormGroup>
                    </Form>
                </Container>
            </div>
        )
    }
}

RegisterForm.propTypes = {  

    weight: PropTypes.number,  
  name:PropTypes.string
}  