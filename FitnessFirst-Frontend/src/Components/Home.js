import React, { Fragment,useState } from "react";
import {useEffect} from 'react';
import { apiRequest } from '../api/utils';
import {connect} from 'react-redux';

//this can also be done by passing {name.title } instead of props as tought by durgesh
//state cn alos be used inside components according to durgesh
const Home = (props) =>{
const [calorieseaten,setcalorieseaten] = useState(0);
const [caloriesspent,setcaloriesspent] = useState(10);

useEffect(() => {
        //console.log('inside useeffect');

       apiRequest('GET', `/api/totalCalBurntPerDay/${props.auth.username}`,null,'EXERCISE').then( function getData(result) {
       console.log('inside totalcalburnt'+result);
        const totalcalburnt=result.data.totalcalburnt;
        setcaloriesspent(totalcalburnt);
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

  //function to get calories eaten
  apiRequest('GET', `/api/totalCalPerDay/${props.auth.username}`,null,'CALORIE').then( function getData(result) {
    console.log('inside totalcalburnt'+result);
     const totalcalperday=result.data.totalcalperday;
     setcalorieseaten(totalcalperday);
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
}, []);
return(



    <Fragment>
        <h1>Calories Eaten:{calorieseaten}</h1>
        <h1>Calories Burnt:{caloriesspent}</h1>
    </Fragment>
);

}
const mapStateToProps = state => {
    return {
        auth: state.auth
      //  food:state.food
    };
  };
  
  
  export default connect(mapStateToProps)(Home);
//export default 