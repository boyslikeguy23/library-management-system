import React, { Component } from 'react';
import CardComponent from './mainComponents/cardComponent';
import CarouselComponent from './mainComponents/carouselComponent';

class MainComponent extends Component {
    render(){
        return(
            <div className="container-fluid">
                <div style={{paddingTop: "15px"}} className="row">
                    <div className="col">
                        <CarouselComponent/>
                    </div>
                </div>
                <div style={{paddingTop: "20px"}} className="row">
                    <div className="col">
                        <CardComponent/>
                    </div>
                </div>
            </div>
        );
    }
}

export default MainComponent;