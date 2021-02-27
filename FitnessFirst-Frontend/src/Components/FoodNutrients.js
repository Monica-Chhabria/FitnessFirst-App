import React, { Component,useState,useEffect} from 'react';
import { Table,Button, Container } from 'reactstrap';
import {
  Card, CardImg, CardText, CardBody,
  CardTitle, CardSubtitle
} from 'reactstrap';
import { ToastContainer, toast } from 'react-toastify';

import {connect} from 'react-redux';

import axios from 'axios';

function FoodNutrients  (props)
{

  const[modalshow,setModalShow] = useState({

  });
//method to display toast popup
const notify = (messagetype,message) => {
  if(messagetype=="success")
  {toast.success(message,
  {position :"top-center"})};
  if(messagetype=="error")
  {toast.error(message,
  {position :"top-center"})};
};
  const saveFood = ()=>
  {
    
    const params = {

      "calories": props.location.calories,
      "foodname":props.location.food_name,
      "meal": props.food.meal,
      "username":props.auth.username,
      "total_fat":props.location.total_fat,
      "cholesterol":props.location.cholesterol,
      "sugars":props.location.sugar,
      "protein":props.location.protein,
      "potassium":props.location.potassium,
      "total_carbohydrate":props.location.total_carbohydrate
      };
  axios.post("http://localhost:8761/FITNESSFIRST-CALORIES-SERVICE/api/saveFood",   params,
  {  headers: {
  
   'Access-Control-Allow-Origin': '*',
   'Content-Type': 'application/json'
 }})
      .then(response => {
          let token = response.data;
          setTimeout(   notify("success","Food Added Successfully"), 30000000);

         // localStorage.setItem('jwtToken', token);
          //dispatch(success(true));
      })
      .catch(error => {

          //alert(error);
          setTimeout(   notify("error","Error while adding Food"), 30000000);
         
          // dispatch(failure());
      });

  }

  return(

    <Card className = "mt-2">
                      <ToastContainer />

      <CardBody>
        <CardTitle tag="h5"></CardTitle>
        <CardSubtitle tag="h6" ><span><h1>{props.food.meal}</h1>
       {/*} <Link to="/BreakFast">

        <Button  color="primary" className = "ml-2">Add</Button> 
        </Link>*/}

        <Container className = "text-center">
          <Table>
          <thead>
        <tr>
       
          <th>Food Name</th>
          <th>Nutrient Details</th>
        
        </tr>
      </thead>
      <tbody>
        <tr>
         <td>Food Name</td><td> {props.location.food_name}</td>     {/*} {props.nutrient.foodname}*/}
         </tr>  
         <tr>     
         
          <td>Calories</td><td> {props.location.calories} {/*}{props.nutrient.calories}*/}</td>
          </tr>
         <tr>
          <td>Total Fat</td>  <td> {props.location.total_fat}</td>
          </tr>
          <tr>
          <td>Cholesterol</td>  <td> {props.location.cholesterol}</td>
          </tr>
          <tr>
          <td>Sugar</td>  <td> {props.location.sugar}</td>
          </tr>
          <tr>
          <td>Protein</td>  <td> {props.location.protein}</td>
          </tr>
          <tr>
          <td>Potassium</td>  <td> {props.location.potassium}</td>
          </tr>
          <tr>
          <td>Potassium</td>  <td> {props.location.potassium}</td>
          </tr>
          <tr>
          <td>Carbohydrate</td>  <td> {props.location.carbohydrate}</td>
          </tr>
          {/*<br/>
          Cholesterol: {props.location.cholesterol}
          <br/>
          Sugar: {props.location.sugar}
          <br/>
          Protein: {props.location.protein}
          <br/>
          Potassium: {props.location.potassium}
          <br/>
          Carbohydrate: {props.location.carbohydrate}*/}
         </tbody>
          </Table>

          <Button  color="primary" className = "ml-2" onClick={saveFood}>Add Food</Button> 
        </Container>
        
         </span></CardSubtitle>
      
        {/*<FontAwesomeIcon icon={faPlus} style ={style} />
        <CardText>Some quick example text to build on the card title and make up the bulk of the card's content.</CardText>
       */}
      
      </CardBody>
    </Card>



  );
  {/*
  useEffect(() => {
        
    return () => {
      setModal(true);
    }
}, [])

    const {
        buttonLabel,
        className
      } = props;
    
      const [modal, setModal] = useState(false);
    
      const toggle = () => {setModal(!modal);         
    };
      

      const closeBtn = <button className="close" onClick={()=>setModal(!Modal)}> &times;</button>;
      return (
        <div>
          <Modal isOpen={modal} toggle={toggle} className="modal">
            <ModalHeader toggle={toggle} close={closeBtn}> {props.nutrient.foodname}</ModalHeader>
            <ModalBody>
              {props.nutrient.foodname}
              {props.nutrient.calories}
              {/*props.nutrient.map(i =>{

                  <h1>i.nf_calories</h1>
              })
              *
            </ModalBody>
            <ModalFooter>
             <Container className="text-center"> 
             <Button color="primary" onClick={toggle}>Ok</Button>{' '}
             </Container>
            </ModalFooter>
          </Modal>
        </div>
      );*/}
}
const mapStateToProps = state => {
  return {
      auth: state.auth,
      food:state.food
  };
};



export default connect(mapStateToProps)(FoodNutrients);