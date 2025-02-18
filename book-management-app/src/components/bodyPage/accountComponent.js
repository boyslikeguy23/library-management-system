import React from "react";
import MainAccComponent from "./accountComponents/mainaccComponent";
import SidebarComponent from "./bodyComponents/sidebarComponent";

function AccountComponent() {

    return(
        <div className="container-fluid">
            <div className="row" style={{border: "solid"}}>
                <div className="col-2" style={{borderRight: "solid"}}>
                    <SidebarComponent/>
                </div>
                <div className="col-9">
                    <MainAccComponent/>
                </div>
            </div>
        </div>
    );
}

export default AccountComponent;