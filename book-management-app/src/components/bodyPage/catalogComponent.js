import React, { useState, useEffect } from "react";
import SidebarComponent from "./bodyComponents/sidebarComponent";
import MainCatComponent from "./catalogComponents/maincatComponent";

function CatalogComponent(props) {

    const [ user, setUser ] = useState(null);

    useEffect(() => {
        setUser(props.user);
        //console.log("body user: " + JSON.stringify(user, null, 3));
    }, [props.user]);

    return(
        <div className="container-fluid">
            <div className="row" style={{border: "solid"}}>
                <div className="col-2" style={{borderRight: "solid"}}>
                    <SidebarComponent user={user}/>
                </div>
                <div className="col-9">
                    <MainCatComponent/>
                </div>
            </div>
        </div>
    );
}

export default CatalogComponent;