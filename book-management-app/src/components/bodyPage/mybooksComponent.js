import React, { Component } from "react";
import SidebarComponent from "./bodyComponents/sidebarComponent";
import MainMyBooksComponent from "./mybooksComponents/mainmybooksComponent";

class MyBooksComponent extends Component {
    render(){
        return(
            <div className="container-fluid">
                <div className="row" style={{border: "solid"}}>
                    <div className="col-2" style={{borderRight: "solid"}}>
                        <SidebarComponent/>
                    </div>
                    <div className="col-9">
                        <MainMyBooksComponent/>
                    </div>
                </div>
            </div>
        );
    }
}

export default MyBooksComponent;