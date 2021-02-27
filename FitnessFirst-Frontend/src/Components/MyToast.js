import React, {Component} from 'react';
import { Container, Toast, ToastBody, ToastHeader } from 'reactstrap';

export default class MyToast extends Component {
    render() {
        const toastCss = {
            position: 'fixed',
            top: '10px',
            right: '10px',
            zIndex:'1'
        };
          {/*}  boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)'*/}

        return (
         <>
     <Container className="text-center">
      <div style={this.props.show ? toastCss : null}>
        <Toast className={`border text-white ${this.props.type === "success" ? "border-success bg-success" : "border-danger bg-danger"}`} >
          <ToastHeader className={`text-white ${this.props.type === "success" ? "bg-success" : "bg-danger"}`} closeButton={true}>
            
          </ToastHeader>
          <ToastBody className = "text-center">
          {this.props.message}
          </ToastBody>
        </Toast>
      </div>
     
      </Container>
         
         

         </>
     
        
        );
    };
}