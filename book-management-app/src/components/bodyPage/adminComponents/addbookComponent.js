import { Component } from "react";
import Form from 'react-validation/build/form';
import Input from 'react-validation/build/input';
import CheckButton from 'react-validation/build/button';
import { addBook } from "../../services/catalogService";

const required = value => {
    if(!value){
        return (
            <div className="alert alert-danger" role="alert">
                This field is required!
            </div>
        );
    }
};

export default class AddBookComponent extends Component {

    constructor(props){
        super(props);
        this.handleAdd = this.handleAdd.bind(this);
        this.onChangeTitle = this.onChangeTitle.bind(this);
        this.onChangeAuthor = this.onChangeAuthor.bind(this);
        this.onChangeDate = this.onChangeDate.bind(this);
        
        this.state = {
            title: "",
            author: "",
            date: "",
            loading: false,
            message: "",
        };
    }

    onChangeTitle(e) {
        this.setState({
            title: e.target.value
        });
    }

    onChangeAuthor(e) {
        this.setState({
            author: e.target.value
        });
    }

    onChangeDate(e) {
        this.setState({
            date: e.target.value
        });
    }

    handleAdd(e){
        e.preventDefault();

        this.setState({
            message: "",
            loading: true
        });

        this.form.validateAll();

        if(this.checkBtn.context._errors.length === 0){
            addBook(this.state.title, this.state.author, this.state.date);
            window.location.reload();
        }
        else{
            this.setState({
                loading: false
            });
        }
    }

    render(){
        return (
            <div  className="col-md-12">
                <h2 style={{'textAlign': 'center'}}>Add Books</h2>
                <div className="card card-container">
                    <Form onSubmit={this.handleAdd} ref={c => {this.form = c;}}>
                        <div className="form-group">
                            <label htmlFor="title">Title</label>
                            <Input type="text" className="form-control" name="title" value={this.state.title} onChange={this.onChangeTitle} validations={[required]} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="author">Author</label>
                            <Input type="text" className="form-control" name="author" value={this.state.author} onChange={this.onChangeAuthor} validations={[required]} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="date">Publication Date</label>
                            <Input type="date" className="form-control" name="date" value={this.state.date} onChange={this.onChangeDate} validations={[required]} />
                        </div>
                        <div className="form-group">
                            <button className="btn btn-primary">
                                {this.state.loading && (<span className="spinner-border spinner-border-sm"></span>)}
                                <span>Add</span>
                            </button>
                        </div>

                        {this.state.message && (
                            <div>
                                <div className="alert alert-danger" role="alert">{this.state.message}</div>
                            </div>
                        )}
                        <CheckButton
                            style={{display: "none"}}
                            ref={c => {
                                this.checkBtn = c;
                            }}/>
                    </Form>
                </div>
            </div>
        );
    }
}