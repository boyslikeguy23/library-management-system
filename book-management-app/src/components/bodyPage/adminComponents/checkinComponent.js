import { Component } from 'react';
import AuthService from '../../services/authService';
import {checkIn, getCheckedOut} from '../../services/catalogService';


class CheckInComponent extends Component{

    constructor(props) {
        super(props);
        this.state = {
            user: AuthService.getCurrentUser(),
            books: []
        };

        this.checkin = this.checkin.bind(this);
    }

    componentDidMount() {
        getCheckedOut().then((response) => {
            this.setState({books: response.data});
            //console.log('response:', response.data);
        });
    }

    checkin(id) {
        checkIn(id);
    }

    render(){
        if(this.state.books){
            return(
                <div className="container-fluid">
                    <table className ="table table-hover table-striped">
                        <thead>
                            <tr>
                                <th style={{'textAlign': 'center'}} colSpan={4}>Checked Out Books</th>
                            </tr>
                            <tr>
                                <td>Title Id</td>
                                <td>Title</td>
                                <td>Author</td>
                            </tr>
                        </thead>
                        <tbody className="table-primary">
                            {this.state.books.map((item, index) => {
                                return(
                                    <tr key={"row" + index}>
                                        <td key={"book" + index}>{item.copyId}</td>
                                        <td key={"title" + index}>{item.title}</td>
                                        <td key={"author" + index}>{item.author}</td>
                                        <td><button className="btn btn-primary" type="submit" onClick={() => {
                                            this.checkin(item.copyId);
                                            window.location.reload();
                                        }}>Check In</button></td>
                                    </tr>
                                );
                            })} 
                        </tbody>
                    </table>
                </div>
            ); 
        }
        else{
            return(
                <div className="container-fluid">
                    <table className ="table">
                        <thead>
                            <tr>
                                <th style={{'textAlign': 'center'}} colSpan={3}><h2>Checked Out Books</h2></th>
                            </tr>
                            <tr>
                                <td>Title Id</td>
                                <td>Title</td>
                                <td>Author</td>
                            </tr>
                        </thead>
                    </table>
                    <div style={{'textAlign': 'center'}}>(No books checked out)</div>
                </div>
            );
        }
    }
}

export default CheckInComponent;