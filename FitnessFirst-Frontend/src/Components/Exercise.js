import React from 'react';
import { Form, FormGroup, Label, Input, FormText,Container,Col,Button } from 'reactstrap';
import { Table } from 'reactstrap';
import { ToastContainer, toast } from 'react-toastify';
import {connect} from 'react-redux';
import { apiRequest } from '../api/utils';
import Loader from './General/Loader';
import Error from './General/Error';
class Exercise extends React.Component {
   
    credentialChange = event => {
        this.setState({
            [event.target.name] : event.target.value
        });
    };
 getVal(result)
  {
    let data1;
    console.log(result);
    data1 = result.data.calburnt;
    this.setState({ ...this.state,exercisedetails:data1
    });
  }
componentDidMount=()=>
{
  var that = this;
let data1=[];

 /* let resp = apiRequest('GET', `/api/calBurntPerDay/${this.props.auth.username}`).then(function (result) {
    console.log(result);
     data1 = result.data.calburnt;
    // const data = res.data.calburnt;
     //console.log("fetch exercise details"+data);  
     this.setState({ ...this.state,exercisedetails:data1
     });
    //console.log("fetch exercise details"+data);  


    //dataObj = result;
})
.catch(function(error){
    console.log(error);
});*/

this.setState({ ...this.state,loading:true
});
 apiRequest('GET', `/api/calBurntPerDay/${this.props.auth.username}`,null,'EXERCISE').then( function getData(result) {
  that.getVal(result);
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
//return <Loader size={50} />;

/*
if (this.state.loading) {
  return <Loader size={50} />;
} else if (this.state.error) {
  return <Error message="Failed to load products"  />;
}*/


/* else if (product) {
  return (
    <div className="product">
      <ImageSlider product={product} />
      <Information product={product} />
    </div>
  );
} else {
  return null;
}*/
//this.setState({ ...this.state,exercisedetails:data1
//});
//this.setState({ ...this.state,exercisedetails:data
//});

//});
/*.then(time => {
    console.log("resp"+resp);
})*/
/*.catch(error => {
    // Handle/report error
});;*/
 // console.log("resp "+resp.data);
 // console.log( "resp"+resp);

  }

 // console.log("inside component did mount");

    constructor() {
        super();
        this.state = this.initialState;
      //  this.getVal = this.getVal.bind(this);

    }
    initialState = {
        exercise:'',
        calories:0,
        exercisedetails:[],
        loading:false,
        error:null
    };
     notify = (messagetype,message) => {
      if(messagetype=="success")
      {toast.success(message,
      {position :"top-center"})};
      if(messagetype=="error")
      {toast.error(message,
      {position :"top-center"})};
    };

     generateTableData(exercise){
     
        let res=[]; 
        for(var i =0; i < exercise.length;i++)
        {
         
           res.push(
           <tr key={i}>
               
           <td>{i+1}</td>
         <td>{exercise[i].exercise}</td>
         <td>{exercise[i].calories}</td>
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
    addExercise = (exercise,calories)=>
    {
      try{ 
        
        let status = this.saveCalorie();
       if(status=="success")
       {
      this.setState({ ...this.state,exercisedetails:  [...this.state.exercisedetails,{exercise,calories}]}, () => {
     // console.log("exercise details "+this.state.exercisedetails);     
     });
       }
       
         
      }
      catch(e)
      {
        console.log(e);
      }
    }

    //function to render table headers
        renderTableData = (exercise)=> {
      
        
        //this.setState([...fooditem,{foodname:i.food_name}]);
      const tabledata = 
        <Table className = "mt-5">
        <thead>
          <tr>
            <th>No</th>
            <th>Exercise</th>
            <th>Calories</th>
         
          
          </tr>
        </thead>
        <tbody>
     
        {this.generateTableData(this.state.exercisedetails)}
     
     
    
            </tbody>
      </Table>
       ;
       return tabledata;
        }
       saveCalorie = ()=>
        {
          var that = this;

          const params = {
      
            "exercise": this.state.exercise,
            "calories":this.state.calories,
            ///"meal": props.food.meal,
            "username":this.props.auth.username
            };

            apiRequest('POST', `/api/saveExercise`,params,'EXERCISE').then( function getData(result) {
              setTimeout(that.notify("success","Exercise Added Successfully"), 30000000);
              return "success";
             })
            .catch(function getError(error){
              setTimeout(that.notify("error","Error while adding Exercise"), 30000000);
              console.log(error);
              return "error";
            });
/*
        axios.post("http://localhost:8761/FITNESSFIRST-EXERCISE-SERVICE/api/saveExercise",   params,
        {  headers: {
        
         'Access-Control-Allow-Origin': '*',
         'Content-Type': 'application/json'
       }})
            .then(response => {
                //let token = response.data;
                setTimeout(this.notify("success","Exercise Added Successfully"), 30000000);
      
               // localStorage.setItem('jwtToken', token);
                //dispatch(success(true));
            })
            .catch(error => {
      
                //alert(error);
                setTimeout(this.notify("error","Error while adding Exercise"), 30000000);
               
                // dispatch(failure());
            });*/
      
        }
    getCalorieDetails = ()=>
    {
      var that = this;

        const params = {

            "query": this.state.exercise
            /*"gender":props.location.food_name,
            "weight_kg": props.food.meal,
            "height_cm":props.auth.username,
            "age":props.location.total_fat*/
           
            };

            apiRequest('POST', `/v2/natural/exercise`,params,'NUTRITIONIX').then( function getData(result) {
             // that.getVal(result);
             let data = result.data;

             let arr = [];
                    //let text = `<div class = 'list-group'>`;
                    var x= data.exercises;
                   // console.log(x);
                    const map =  x.map(i => {
                  
                    //arr[] = ;
                    arr.push({nf_calories:i.nf_calories});
                    that.setState({ ...that.state, calories: i.nf_calories })
             console.log(data);
             })
            })
            .catch(function getError(error){
              console.log(error);
            });
           /* axios.post("https://trackapi.nutritionix.com/v2/natural/exercise",   params,
            {  headers: {
            
             'Access-Control-Allow-Origin': '*',
             'Content-Type': 'application/json',
             'x-app-id': '0be23763',
             'x-app-key':'18a59c6f58cbcd1f4d04948204affa65'
           }})
                .then(response => {
                    let data = response.data;
                    let arr = [];
                    //let text = `<div class = 'list-group'>`;
                    var x= data.exercises;
                   // console.log(x);
                    const map =  x.map(i => {
                  
                    //arr[] = ;
                    arr.push({nf_calories:i.nf_calories});
                    this.setState({ ...this.state, calories: i.nf_calories })
                  
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
                 /* });
                })
                .catch(error => {
                    console.log(error);
          
                });*/

    }
    render() {
        return (
            <div>
                <Container fluid className= "mt-5">
          
<ToastContainer/>

<Form className="mt-5">
                        <FormGroup row>

                            <Label for="name" sm={2}>Enter Exercise Details</Label>

                            <Col sm={10}>
                           
          <Input type="textarea"  name="exercise"
                                    id="exercise"  onChange={this.credentialChange}/>

                             
                            </Col>
                        </FormGroup>
                        <FormGroup row>

                          <Label for="name" sm={2}>Calories Burnt</Label>

                          <Col sm={10} >
                            {this.state.calories}

 
                         </Col>
                       </FormGroup>
                       <FormGroup check row>
                            <Col sm={{ size: 12 }}>
                                <Button color="primary" onClick= {this.getCalorieDetails}>Search Calories Details</Button>{} <Button  color="primary" onClick={()=> {this.addExercise(this.state.exercise,this.state.calories)}}>Add Exercise</Button>
                            </Col>
                          
                        </FormGroup>
                       
                    </Form>
                    {this.state.exercisedetails.length>0?this.renderTableData(this.state.exercise):'no record found'}

                </Container>
               {/* {this.state.exercisedetails.size>0?'no record found':this.renderTableData(this.state.exercise)}*/}
      {/*{this.props.auth.username ? null : this.loginform}*/}
      </div>
        )
    }

    
}


const mapStateToProps = state => {
  return {
      auth: state.auth
    //  food:state.food
  };
};


export default connect(mapStateToProps)(Exercise);
//export default Exercise;