import { React, Component } from 'react';
import { getCatalog, checkOut } from '../../services/catalogService';
import AuthService from '../../services/authService';

class MainCatComponent extends Component{

    constructor(props) {
        super(props);
        this.state = {
            user: AuthService.getCurrentUser(),
            books: []
        };

        this.checkout = this.checkout.bind(this);
    }

    componentDidMount() {
        getCatalog().then((response) => {
            //console.log('response:', JSON.stringify(response.data, null, 3));
            this.setState({books: response.data});
        });
    }
    
    checkout(id, username) {
        checkOut(id, username);
    }

    render(){
       if(this.state.books === null){
        return(
            <div className="container-fluid">
                <table className ="table table-hover">
                    <thead>
                        <tr>
                            <th style={{'textAlign': 'center'}} colSpan={3}>Library Catalog</th>
                        </tr>
                        <tr>
                            <td>Title Id</td>
                            <td>Title</td>
                            <td>Author</td>
                        </tr>
                    </thead>
                    <tbody>
                        <h3>No books available in catalog.</h3>
                    </tbody>
                </table>
            </div>
        ); 
       }
       else if(this.state.books !== null && this.state.user){
        return(
            <div className="container-fluid">
                <table className ="table table-hover table-striped">
                    <thead>
                        <tr>
                            <th style={{'textAlign': 'center'}} colSpan={4}>Library Catalog</th>
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
                                        this.checkout(item.copyId, this.state.user.username);
                                        window.location.reload();
                                    }}>Check Out</button></td>
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
                    <table className ="table table-striped">
                        <thead>
                            <tr>
                                <th style={{'textAlign': 'center'}} colSpan={3}>Library Catalog</th>
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
                                    </tr>
                                );
                            })}
                        </tbody>
                    </table>
                </div>
            );
        }
    }
}   


export default MainCatComponent;