import React from "react";
import SidebarComponent from "./bodyComponents/sidebarComponent";
import MainAdminComponent from './adminComponents/mainadminComponent';

function AdminComponent() {

    return(
        <div className="container-fluid">
            <div className="row" style={{border: "solid"}}>
                <div className="col-2" style={{borderRight: "solid"}}>
                    <SidebarComponent/>
                </div>
                <div className="col-9">
                    <MainAdminComponent/>
                </div>
            </div>
        </div>
    );
}

export default AdminComponent;