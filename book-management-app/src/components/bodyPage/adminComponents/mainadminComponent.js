import React, { Component } from "react";
import AuthService from "../../services/authService";
import CheckInComponent from "./checkinComponent";
import AddBookComponent from "./addbookComponent";
import AddAdminComponent from "./addadminComponent";

class MainAdminComponent extends Component{

    constructor(props) {
        super(props);
        this.state = {
          user: AuthService.getCurrentUser()
        };

        this.admin = this.admin.bind(this);
    }

    admin() {
        if(this.state.user){
            for(let i = 0; i < this.state.user.roles.length; i++){
                if(this.state.user.roles[i] === "ROLE_ADMIN"){
                    return true;
                }
            }
        }

        return false;
    }

    render(){
       return this.admin() ? (
            <div className="container-fluid">
                <div className="row">
                    <CheckInComponent />
                </div>
                <div className="row">
                    <AddBookComponent />
                </div>
                <div className="row">
                    <AddAdminComponent />
                </div>
          </div>
        )
        : (
            <div className="container-fluid">
              <h3>Unauthorized Access!</h3>
            </div>)
    }
}

export default MainAdminComponent;