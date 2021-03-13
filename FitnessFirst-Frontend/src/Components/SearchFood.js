import { Button } from 'reactstrap';
import React, { Fragment ,useState,useEffect} from 'react'
import { Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import classes from "./search.module.css"
import FoodTable from './FoodTable';
import { apiRequest } from '../api/utils';

export default function SearchFood(props) {
const [fooditem,setfooditem] = useState([]);
const[foodtableshow,setfoodtable] = useState(false);
const[searchval,setSearchVal] = useState('');

const callsearchtest= () =>
{

    let data =  `{
        "common": [
            {
                "food_name": "grilled cheese",
                "serving_unit": "sandwich",
                "tag_name": "grilled cheese",
                "serving_qty": 1,
                "common_type": null,
                "tag_id": "1763",
                "photo": {
                    "thumb": "https://nix-tag-images.s3.amazonaws.com/1763_thumb.jpg"
                },
                "locale": "en_US"
            },
            {
                "food_name": "grilled cheeses",
                "serving_unit": "sandwich",
                "tag_name": "grilled cheese",
                "serving_qty": 1,
                "common_type": null,
                "tag_id": "1763",
                "photo": {
                    "thumb": "https://nix-tag-images.s3.amazonaws.com/1763_thumb.jpg"
                },
                "locale": "en_US"
            },
            {
                "food_name": "Ultimate Grilled Cheese",
                "serving_unit": "serving",
                "nix_brand_id": "513fbc1283aa2dc80c000149",
                "brand_name_item_name": "Friendly's Ultimate Grilled Cheese",
                "serving_qty": 1,
                "nf_calories": 580,
                "photo": {
                    "thumb": "https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png",
                    "highres": null,
                    "is_user_uploaded": false
                },
                "brand_name": "Friendly's",
                "region": 1,
                "brand_type": 1,
                "nix_item_id": "5266a2159f05a39eb300ed5e",
                "locale": "en_US"
            }
        ]
    }`;
//console.log('');
var x= data.common;
//console.log(x);
let arr = [];
//let text = `<div class = 'list-group'>`;




for (let key in data) {
    if(key == "common")
    {
      console.log("data "+data[key]);

    }
}

};
/*function doHomework(subject, callback) {
    alert(`Starting my ${subject} homework.`);
    callback();
  }
  
  doHomework('math', function() {
    alert('Finished my homework');
  });*/
let isLoggedIn = true;
const searchandupdatapi = (searchval)=>
{


    callSearchApi(searchval);
  // setTimeout( setFoodDetails(),2300);


   // setfoodtable((foodtableshow) =>!foodtableshow )
}
const setFoodDetails = (food) =>
{
    //alert('inside setfoodetails '+fooditem.length);
 
    if(food.length > 0)
    {setfoodtable(true)}
    else
    {setfoodtable(false)}
//callback();
}
    const callSearchApi = (searchval,callback) =>
    {


        //setfooditem([...fooditem,{foodname:'Monica'}]);
       // setfooditem([...fooditem,{foodname:'Monica 1'}]);
        
        //setfooditem([...fooditem,{foodname:'Monica 2'}]);
       // 
       apiRequest('GET', `/v2/search/instant?query=${searchval}`,null,'NUTRITIONIX').then( function getData(result) {
        // that.getVal(result);
       /* let data = result.data;

        let arr = [];
               //let text = `<div class = 'list-group'>`;
               var x= data.exercises;
              // console.log(x);
               const map =  x.map(i => {
             
               //arr[] = ;
               arr.push({nf_calories:i.nf_calories});
               that.setState({ ...that.state, calories: i.nf_calories })
        console.log(data);
        })*/

        const data = result.data;
        // console.log(data);
        let arr = [];
         //let text = `<div class = 'list-group'>`;
         var x= data.common;
        // console.log(x);
         const map =  x.map(i => {
           /*  console.log("food name"+i.food_name);
         console.log("serving_unit"+i.serving_unit);
         console.log("serving_qty"+i.serving_qty);*/
         //arr[] = ;
         arr.push({foodname:i.food_name,servingunit:i.serving_unit,servingquantity:i.serving_qty});

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
       setfooditem(arr);
       callback(arr);


       })
       .catch(function getError(error){
         console.log(error);
       });
        const axios = require('axios');
      /*  axios.get(`https://trackapi.nutritionix.com/v2/search/instant?query=${searchval}`,{  headers: {
            'x-app-id': '0be23763',
            'x-app-key':'18a59c6f58cbcd1f4d04948204affa65'
          }})

        


         

        .then(res => {
          const data = res.data;
       
         let arr = [];
          var x= data.common;
      
          const map =  x.map(i => {
          
          arr.push({foodname:i.food_name,servingunit:i.serving_unit,servingquantity:i.serving_qty});
*/




         // setfooditem([...fooditem,{foodname:i.food_name}]);
       

          /*for(let key in i) {
            if(i.hasOwnProperty(key)) {
                var value = i[key];
                console.log("food_name "+i.food_name);
                console.log('value '+value);
                //do something with value;
            }
        }*/



      /*  
        });
        setfooditem(arr);
        callback(arr);
*/


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
     
    /*});*/
      
    }
/*
    const callFoodSearch = ()=>
    {*/
    return (
    <Fragment>
     <Form className = "mt-5">
         <FormGroup>
     <div className = {classes.searchcontainer}>

        <Input
          type="text"
          name="search"
          id="search"
         onChange={(e) =>
            
            {

                setSearchVal(e.target.value);
            }}
        />
        
        {
        
        /*
        onKeyUp= {() =>{callSearchApi()}}
        <div className = {classes.searchresult}>
         search result
        </div>*/}
      
    </div>
    
      </FormGroup>  
      </Form>
      <div className= "mt-5">
   
    


        <Button onClick = {() =>{callSearchApi(searchval,
          setFoodDetails)}}>Search</Button>
       
        <div> {foodtableshow?<FoodTable fooditem= {fooditem} showModal = "true" meal = {props.meal}/>:'no record found'}</div>
        </div>       
    </Fragment>
    )
/*}*/

}