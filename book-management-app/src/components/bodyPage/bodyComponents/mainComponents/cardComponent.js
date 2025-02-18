import React, { useState } from "react";
import { getBooksAvail, getBookTotal, getNumOfUsers } from "../../../services/cardService";

function CardComponent() {

    const[ totalBooks, settotalBooks ] = useState(0);
    const[ totalAvail, settotalAvail ] = useState(0);

    const[ totalUsers, settotalUsers ] = useState(0);

    getBookTotal().then((response) => {
        settotalBooks(JSON.stringify(response.data, null, 3));
    });

    getBooksAvail().then((response) => {
        settotalAvail(JSON.stringify(response.data, null, 3));
    });

    getNumOfUsers().then((response) => {
        settotalUsers(JSON.stringify(response.data, null, 3));
    });

    return(
        <div className="container-fluid">
            <div className="row">
                <div className="col">
                    <div className="card text-white bg-primary mb-3"  style={{width: "18rem"}}>
                         <div className="card-body">
                            <h5 className="card-title">Total Books</h5>
                            <p id="totalBooks" className="card-text">{ totalBooks }</p>
                        </div>
                    </div>
                </div>
                <div className="col">
                    <div className="card text-white bg-info mb-3"  style={{width: "18rem"}}>
                         <div className="card-body">
                            <h5 className="card-title">Total Books Available</h5>
                            <p className="card-text">{ totalAvail }</p>
                        </div>
                    </div>
                </div>
            </div>

            <div className="row">
                <div className="col">
                    <div className="card text-white bg-success mb-3"  style={{width: "18rem"}}>
                        <div className="card-body">
                            <h5 className="card-title">Total Users</h5>
                            <p className="card-text">{ totalUsers }</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default  CardComponent;