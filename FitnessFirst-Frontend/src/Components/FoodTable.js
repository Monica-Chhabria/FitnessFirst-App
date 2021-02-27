import React,{useState} from 'react';
import { Table,Button ,Container} from 'reactstrap';


import fetchdataurl from './Constants';
import history from './historytest';
import {connect} from 'react-redux';
import axios from 'axios';


function FoodTable(props) {
const getDetails = (foodname,servingunit,servingquantity) =>
{
 
callNutrientsApi(foodname);
/*return(
<Route path="/nutrition" 
    render={(props) => <FoodNutrients/>} 
/>


);*/
}

const[nutrientsarr,setNutrientArr] = useState();

const callNutrientsApi = (searchval) =>
{


      
    //   const axios = require('axios');
       axios.get(`http://localhost:8761/FITNESSFIRST-CALORIES-SERVICE/api/nutrients/${searchval}`,
       
       {  headers: {
       
        'Access-Control-Allow-Origin': '*'
      }}
       )
       .then(res => {
         const data = res.data;
        // alert(data.foods);
        // console.log(data);
        let arr = {};
         //let text = `<div class = 'list-group'>`;
         var x= data.foods;
        // console.log(x);
         const map =  x.map(i => {
        
        arr.foodname= searchval;
        arr.calories = i.nf_calories;
        arr.total_fat = i.nf_total_fat;
        arr.cholesterol = i.nf_cholesterol;
        arr.sugar = i.nf_sugars;
        arr.protein = i.nf_protein;
        arr.potassium = i.nf_potassium;
        arr.carbohydrate = i.nf_total_carbohydrate;
        //alert(i.nf_calories);
         //arr.push({foodname:searchval,calories:i.nf_calories});

        // setfooditem([...fooditem,{foodname:i.food_name}]);
      
         /*for(var i = 0; i < fooditem.length ; i++){
           console.log("array"+fooditem[i])
       }*/

         /*for(let key in i) {
           if(i.hasOwnProperty(key)) {
               var value = i[key];
               console.log("food_name "+i.food_name);
               console.log('value '+value);
               //do something with value;
           }
       }*/
       });
       setNutrientArr(arr.foodname);
     //  alert(arr.foodname);
       //alert("inside arr"+arr.size);
     //  alert(nutrientsarr);
     //  toggle();
    

     //const history = createBrowserHistory();
history.push({ pathname:"/nutrition",
calories:arr.calories,food_name:arr.foodname,total_fat:arr.total_fat,cholesterol:arr.cholesterol,sugar:arr.sugar,protein:arr.protein,potassium:arr.potassium,carbohydrate:arr.carbohydrate});
/*
food_name
total_fat
cholesterol
sugar
protein
potassium
carbohydrate*/
       //alert("arr.length"+arr.length);
      // setfooditem(arr);
     //  alert("length in search"+arr.length);
       //callback(arr);
       //alert("fooditem length"+fooditem.length);

     //  console.log();
       /*  for (let key in data) {
           if(key == "common")
           {
             console.log("data "+data[key]);
       
           }
       }
       
    
         for (let key in data) {
             if(key == "common")
             {
               arr[key]  =data[key];

             }
        }*/
        //console.log("arr value "+arr);
        //console.log(arr);
        /*for (var item in arr) {
       //    console.log(item.common);
       for(var inneritem in arr[item])
       {
       const obj =   JSON.parse(inneritem);
       console.log(inneritem[0].food_name);
    //   console.log(JSON.parse(obj[]));
       }

     }*/
         /*res.forEach((contact) =>
   {
     alert('insdie foreach');
   //	text+=`<a href = '/user/${contact.cid}/contact' class = 'list-group-item list-group-item-action'>${contact.name}</a>`;
   })*/

   //text+=`</div>`;
   //$(".search-result").html(text);
   //console.log(data.length);
   /*if(data.length>0)
     {
   $(".search-result").show();
     }*/
       });
  
}

    function renderTableData(props) {
     //   alert('inside rendertable');
        return props.fooditem.map((item, pos) => {
           //const { id, name, age, email } = student //destructuring
           return (
              <tr key={pos}>
                   <td>{pos+1}</td>
                 <td>{item.foodname}</td>
                 <td>{item.servingunit}</td>
                 <td>{item.servingquantity}</td>
                  <td>
                  
     {/*             <Link to={{
  pathname: '/nutrient'
}}></Link>*/}
                    <Button onClick={()=>getDetails(item.foodname,item.servingunit,item.servingquantity)}>View Details</Button>
                


<Button color="primary">Add Food</Button>


{/*modalshow?<FoodNutrients nutrient = {nutrientsarr} showModal={true} />:null*/}
{/*modalshow?[<ModalExample nutrient = {nutrientsarr} showModal={true} />,buttonclick()]
:null}*/}
                    </td>  
              </tr>
           )
        })
     }
    return (
        <div>
             

            {   <Table className = "mt-5">
      <thead>
        <tr>
          <th>#</th>
          <th>Food Name</th>
          <th>Serving Unit</th>
          <th>Serving Quantity</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
     
        {props.fooditem.length>0?renderTableData(props):null}
   
      </tbody>
    </Table>}
    
        </div>
    )
}
export default connect()(FoodTable);
