import React,{useState} from 'react'
import {  Collapse,
    Navbar,
    NavbarToggler,
    NavbarBrand,
    Nav,
    NavItem,
    NavLink,
    UncontrolledDropdown,
    DropdownToggle,
    DropdownMenu,
    DropdownItem,
    NavbarText} from 'reactstrap';
    import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
    import {faUserPlus, faSignInAlt, faSignOutAlt} from '@fortawesome/free-solid-svg-icons';
    import {connect} from 'react-redux';

    import {logoutUser} from '../Services/index';
    import {Link} from 'react-router-dom';

function Header(props) {

  const logout = () => {
 props.logoutUser();
};
  const [isOpen, setIsOpen] = useState(false);
  const toggle = () => setIsOpen(!isOpen);
  const guestLinks = (
    <div>
    <Navbar color="primary" dark expand="md">
    <NavbarBrand href="/">Fitness First</NavbarBrand>
    <NavbarToggler onClick={toggle} />
    <Collapse isOpen={isOpen} navbar>
      <Nav className="ml-auto" navbar>
        <NavItem>
          <NavLink href="/components/">Home</NavLink>
        </NavItem>
        <NavItem>
          <NavLink href="/AboutUs">About</NavLink>
        </NavItem>
        <NavItem>
          <NavLink href="/login">Login</NavLink>
        </NavItem>
        <NavItem>
          <NavLink href="/register">Sign Up</NavLink>
        </NavItem>
        </Nav>
      
    </Collapse>
  </Navbar>
    </div>
    

);

const userLinks = (
/*navlink not working properly so had to change*/
  <div>
  <Navbar color="primary" dark expand="md">
  <NavbarBrand href="/">Fitness First</NavbarBrand>
  <NavbarToggler onClick={toggle} />
  <Collapse isOpen={isOpen} navbar>
    <Nav className="ml-auto" navbar>

      <NavItem>
        <NavLink href="/">Home</NavLink>
      </NavItem>
      <NavItem>
        <NavLink href="/AboutUs">About</NavLink>
      </NavItem>
      {/*<NavItem>
        <NavLink href="/logout/" onClick={() => {logout()}}>Logout1</NavLink>
      </NavItem>
      <NavItem>
        <NavLink href="/components/" onClick={() => {logout()}}>Logout</NavLink>
      </NavItem>*/}

      <Nav className="navbar-right">
                    <Link to={"logout"} className="nav-link" onClick={() => {logout()}}><FontAwesomeIcon icon={faSignOutAlt} /> Logout</Link>
                </Nav>
      </Nav>
      {/*<NavItem>
        <NavLink href="https://github.com/reactstrap/reactstrap">GitHub</NavLink>
      </NavItem>
      <UncontrolledDropdown nav inNavbar>
        <DropdownToggle nav caret>
          Options
        </DropdownToggle>
        <DropdownMenu right>
          <DropdownItem>
            Option 1
          </DropdownItem>
          <DropdownItem>
            Option 2
          </DropdownItem>
          <DropdownItem divider />
          <DropdownItem>
            Reset
          </DropdownItem>
        </DropdownMenu>
      </UncontrolledDropdown>
   
      <NavbarText>Simple Text</NavbarText>*/}
  </Collapse>
</Navbar>
</div>
 
);
 
    return (
      <div>
      {/*{props.auth.isLoggedIn ? userLinks : guestLinks}*/}
      {localStorage.getItem('jwtToken')?userLinks:guestLinks}

      </div>
   
   )
}
const mapStateToProps = state => {
  return {
      auth: state.auth
  };
};

const mapDispatchToProps = dispatch => {
  return {
      logoutUser: () => dispatch(logoutUser())
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(Header);

