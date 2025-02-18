import { React, Component } from "react";
import AuthService from "../../services/authService";
import { getMyBooks } from "../../services/catalogService";

class MainMyBooksComponent extends Component{

    constructor(props) {
        super(props);
        this.state = {
            user: AuthService.getCurrentUser(),
            books: null
        };
    }

    componentDidMount() {
        if(this.state.user){
            getMyBooks(this.state.user.username).then((response) => {
                //console.log('response:', JSON.stringify(response.data, null, 3));
                if(JSON.stringify(response.data, null, 3) !== "[]")
                    this.setState({books: response.data});
            });
        }
    }
    
    render(){
        if(this.state.books !== null && this.state.user){
            return(
                <div className="container-fluid">
                    <table className ="table table-striped">
                        <thead>
                            <tr>
                                <th style={{'textAlign': 'center'}} colSpan={3}>My Books</th>
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
                </div>);
        }
        else if(this.state.books === null && this.state.user){
            return(
                <div className="container-fluid">
                    <table className ="table">
                        <thead>
                            <tr>
                                <th style={{'textAlign': 'center'}} colSpan={3}>My Books</th>
                            </tr>
                            <tr>
                                <td>Title Id</td>
                                <td>Title</td>
                                <td>Author</td>
                            </tr>
                        </thead>
                    </table>
                    <h3>No books checked out!</h3>
                </div>);
        }
        else{
            return (<div className="container-fluid">
                  <h3>Log in to access account information.</h3>
                </div>);
        }
    }
}

export default MainMyBooksComponent;