import React from "react";
import MainComponent from "./bodyComponents/mainComponent";
import SidebarComponent from "./bodyComponents/sidebarComponent";

function BodyComponent() {

    return(
        <div className="container-fluid">
            <div className="row" style={{border: "solid"}}>
                <div className="col-2" style={{borderRight: "solid"}}>
                    <SidebarComponent/>
                </div>
                <div className="col-9">
                    <MainComponent/>
                </div>
            </div>
        </div>
    );
}

export default BodyComponent;