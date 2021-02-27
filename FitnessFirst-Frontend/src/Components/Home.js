import React, { Fragment,useState } from "react"
//this can also be done by passing {name.title } instead of props as tought by durgesh
//state cn alos be used inside components according to durgesh
const Home = () =>{
const [calorieseaten,setcalorieseaten] = useState(1000);
const [caloriesspent,setcaloriesspent] = useState(10);

return(



    <Fragment>
        <h1>Calories Eaten:{calorieseaten}</h1>
        <h1>Calories Burnt:{caloriesspent}</h1>
    </Fragment>
);

}

export default Home