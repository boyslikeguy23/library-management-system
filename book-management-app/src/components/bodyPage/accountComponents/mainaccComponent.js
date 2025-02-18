import React, { Component } from "react";
import AuthService from "../../services/authService";

class MainAccComponent extends Component{

    constructor(props) {
        super(props);
        this.state = {
          user: AuthService.getCurrentUser()
        };
    }

    render(){
       return this.state.user ? (<div className="container-fluid">
              <form>
                  <div>
                    <label id="accountusernameLabel" htmlFor="accountusername">Username</label>
                    <br/>
                    <p id="accountusername">{this.state.user.username}</p>
                  </div>
                  <div>
                    <label id="accountpasswordLabel" htmlFor="accountpassword">Roles</label>
                    <br/>
                    <p id="accountpassword">{this.state.user.roles}</p>
                  </div>
              </form>
          </div>
        )
        : (
            <div className="container-fluid">
              <h3>Log in to access account information.</h3>
            </div>)
    }
}

export default MainAccComponent;