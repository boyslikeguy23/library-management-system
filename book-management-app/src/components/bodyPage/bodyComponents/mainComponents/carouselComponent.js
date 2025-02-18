import React, { Component } from 'react';

class CarouselComponent extends Component {
    render(){
        return(
            <div style={{border: "solid"}}>
                <div id="carouselExampleControls" className="carousel slide" data-bs-ride="carousel">
                    <div className="carousel-inner">
                      <div className="carousel-item active">
                        <img src="./lib1.jfif" className="d-block w-100" alt="..."/>
                      </div>
                      <div className="carousel-item">
                        <img src="./lib2.jfif" className="d-block w-100" alt="..."/>
                      </div>
                      <div className="carousel-item">
                        <img src="./lib3.jfif" className="d-block w-100" alt="..."/>
                      </div>
                    </div>
                    <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                      <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                      <span className="visually-hidden">Previous</span>
                    </button>
                    <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                      <span className="carousel-control-next-icon" aria-hidden="true"></span>
                      <span className="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        );
    }
}

export default CarouselComponent;