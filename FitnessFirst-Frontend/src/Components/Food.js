import { Fragment } from "react";
import BreakFast from "./BreakFast";
import Dinner from "./Dinner";
import Lunch from "./Lunch";
import SearchFood from "./SearchFood";
import {connect} from 'react-redux';
import {updateMeal} from '../Services/index';
import history from './historytest';
import {faPlusCircle} from '@fortawesome/free-solid-svg-icons';
import { Form, FormGroup, Label, Input, FormText,Container,Col,Row } from 'reactstrap';
import {useState,useEffect} from 'react';
import { Table } from 'reactstrap';
import {
    Card, CardImg, CardText, CardBody,
    CardTitle, CardSubtitle, Button
  } from 'reactstrap';
import {BrowserRouter as Router, Link, Switch, Route} from 'react-router-dom';

import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

const  Food  = (props) =>{
    const style = {

        width:'100%',
      height:'25px'
       };

    const[breakfasttotal,setBreakfasttotal] = useState(0);
    const[lunchtotal,setLunchtotal] = useState(0);
    const[dinnertotal,setDinnertotal] = useState(0);
    const[breakfast,setBreakfast] = useState([]);
    const[dinner,setDinner] = useState([]);
    const[lunch,setLunch] = useState([]);
    function generateTableData(meal){
     
      let res=[]; 
      for(var i =0; i < meal.length;i++)
      {
       
         res.push(
         <tr key={i}>
             
         <td>{i+1}</td>
       <td>{meal[i].foodname}</td>
       <td>{meal[i].calories}</td>
       <td></td>
    </tr>
       );
        
      }
      /*res = meal.map((item, pos) => {
        //const { id, name, age, email } = student //destructuring
        res.push(
           <tr key={pos}>
             
                <td>{pos+1}</td>
              <td>{item.foodname}</td>
              <td>{item.calories}</td>
              <td>{item.meal}</td>
           </tr>);
  });*/
  //for(var i =0; i < tableData.length; i++){
 
//};
 // alert(res.length);
//  alert(res);

      return res;
  }
    function renderTableData(meal) {
    const tabledata = //   alert('inside rendertable');
      <Table className = "mt-5">
      <thead>
        <tr>
          <th>No</th>
          <th>Food Name</th>
          <th>Calories</th>
       
        
        </tr>
      </thead>
      <tbody>
   
      {generateTableData(meal)}
   
   
  
          </tbody>
    </Table>
     ;
     return tabledata;
      }
    useEffect(() => {

               const axios = require('axios');
        
                /* meal.map((item, pos) => {
                //const { id, name, age, email } = student //destructuring
      
                   <tr key={pos}>
                        <td>{pos+1}</td>
                      <td>{item.foodname}</td>
                      <td>{item.calories}</td>
                      <td>{item.meal}</td>
                       <td>
                       
      
                         </td>  
                   </tr>
          })
             
             }*/
          //Breakfast totalcal
          axios.get(`http://localhost:8761/FITNESSFIRST-CALORIES-SERVICE/api/totalCalPerMealPerDay/${props.auth.username}/BreakFast`,
       
          {  headers: {
          
           'Access-Control-Allow-Origin': '*'
         }}
          )
          .then(res => {
                    const data = res.data.totalcalpermeal;
           // alert(data);
                   setBreakfasttotal(data);
          });
          
         //lunch totalcal
         axios.get(`http://localhost:8761/FITNESSFIRST-CALORIES-SERVICE/api/totalCalPerMealPerDay/${props.auth.username}/Lunch`,
       
         {  headers: {
         
          'Access-Control-Allow-Origin': '*'
        }}
         )
         .then(res => {
                   const data = res.data.totalcalpermeal;
            setLunchtotal(data);
         });


         //dinner totalcal
         axios.get(`http://localhost:8761/FITNESSFIRST-CALORIES-SERVICE/api/totalCalPerMealPerDay/${props.auth.username}/Lunch`,
       
         {  headers: {
         
          'Access-Control-Allow-Origin': '*'
        }}
         )
         .then(res => {
            const data = res.data.totalcalpermeal;
          // alert(data);
            setDinnertotal(data);
         });


          //lunch allcal
          axios.get(`http://localhost:8761/FITNESSFIRST-CALORIES-SERVICE/api/calPerMealPerDay/${props.auth.username}/Lunch`,
       
          {  headers: {
          
           'Access-Control-Allow-Origin': '*'
         }}
          )
          .then(res => {
                    const data = res.data.calpermeal;
                   
             setLunch(data);
          });

          
          //breakfast allcal
          axios.get(`http://localhost:8761/FITNESSFIRST-CALORIES-SERVICE/api/calPerMealPerDay/${props.auth.username}/BreakFast`,
       
          {  headers: {
          
           'Access-Control-Allow-Origin': '*'
         }}
          )
          .then(res => {
                    const data = res.data.calpermeal;
                   
             setBreakfast(data);
          });


          //dinner allcal
          axios.get(`http://localhost:8761/FITNESSFIRST-CALORIES-SERVICE/api/calPerMealPerDay/${props.auth.username}/Dinner`,
       
          {  headers: {
          
           'Access-Control-Allow-Origin': '*'
         }}
          )
          .then(res => {
                    const data = res.data.calpermeal;
                  //  alert(data.length);
             setDinner(data);
          });
    }, []);
    
  const setFoodAndRedirect = (meal) => {
   
       props.updatemeal(meal);
        setTimeout(() => {
          history.push({ pathname:"/"+meal});
                //return this.props.history.push("/");
              //  alert(s"inside props isloggedin");
       
        }, 500);
    };
    const divStyle = {
      display: 'flex',
      alignItems: 'center'
    };
return(
    <div>

    <Card className = "mt-2">
      
      <CardBody>
        <CardTitle tag="h5"></CardTitle>
        <CardSubtitle tag="h6" ><span><h1>BreakFast</h1> 
        {breakfasttotal} Cal Eaten today
        <br/>
        <br/>
      
      
     
        {breakfast.length>0?renderTableData(breakfast):null}
   
    


        <Link to="/BreakFast">
  
<Button  color="primary" className = "ml-2"  onClick={()=>setFoodAndRedirect("BreakFast")}>Add</Button> 
</Link>
</span></CardSubtitle>  
       
       
     
      
      </CardBody>
    </Card>
    {/*<Card>
      
      <CardBody>
        <CardTitle tag="h5"></CardTitle>
        <CardSubtitle tag="h6" >
          <Container className = {divStyle}>
          <Row>
            <Col sm={6}><h1  className = "ml-5">BreakFast</h1>
    </Col>
         <Col sm={6}>
          <Button  color="primary"    style={{marginLeft:0}}    onClick={()=>setFoodAndRedirect("BreakFast")}><FontAwesomeIcon icon={faPlusCircle} /> </Button> 
        </Col>
  </Row>
                </Container>
         </CardSubtitle>
      
       
      
      </CardBody>
    </Card>
    */}
    <Card>
      
      <CardBody>
        <CardTitle tag="h5"></CardTitle>
        <CardSubtitle tag="h6" ><span><h1>Lunch</h1> 
        {lunchtotal} Cal Eaten today
        <br/>
        <br/>
      
      
      
     
        {lunch.length>0?renderTableData(lunch):null}
        <Link to="/Lunch">

<Button  color="primary" className = "ml-2"  onClick={()=>setFoodAndRedirect("Lunch")}>Add</Button> 
</Link>
</span></CardSubtitle>  
       
       
     
      
      </CardBody>
    </Card>
    <Card>
      
      <CardBody>
        <CardTitle tag="h5"></CardTitle>
        <CardSubtitle tag="h6" ><span><h1>Dinner</h1>
        {dinnertotal} Cal Eaten today
        <br/>
        <br/>
        {dinner.length>0?renderTableData(dinner):null}
        <Link to="/Dinner">

<Button  color="primary" className = "ml-2"  onClick={()=>setFoodAndRedirect("Dinner")}>Add</Button> 
</Link>
</span>
        
        </CardSubtitle>
        {/*<FontAwesomeIcon icon={faPlus} style ={style} />
        <CardText>Some quick example text to build on the card title and make up the bulk of the card's content.</CardText>
       */}
      
      </CardBody>
    </Card>
  </div>
);


}
const mapStateToProps = state => {
  return {
    food:state.food,
    auth:state.auth
  }
};

const mapDispatchToProps = dispatch => {
  return {
    updatemeal: (meal) => dispatch(updateMeal(meal))
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(Food);
