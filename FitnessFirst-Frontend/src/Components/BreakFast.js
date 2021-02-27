import {
    Card, CardImg, CardText, CardBody,
    CardTitle, CardSubtitle, Button
  } from 'reactstrap';
  import SearchFood from "./SearchFood";

  import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
  import { faPlus } from "@fortawesome/free-solid-svg-icons";
const BreakFast = () =>
{
   const style = {

        width:'100%',
      height:'25px'
       };

return(
    <div>
      <SearchFood meal = "BreakFast"/>

  </div>

    );
}
export default BreakFast