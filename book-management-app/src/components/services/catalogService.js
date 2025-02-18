import axios from "axios";
import authHeader from './authHeader'

const rootCopyURL = "http://localhost:7071/copies/";
const rootUserURL = "http://localhost:7071/users/";

let testUrl = () => {
    try{
        let pobj = axios.get(rootCopyURL);
        return pobj;
    }
    catch(exception){
        console.log(rootCopyURL + ": not correct " + (exception));
    }
}

let getCatalog = () => {
    try{
        let pobj = axios.get(rootCopyURL + "catalogAvail", {headers: authHeader()});

        /*pobj.then((response) => {
            console.log(JSON.stringify(response.data, null, 3));
        });*/

        return pobj;
    }
    catch(exception){
        console.log(rootCopyURL + "catalog: not correct " + (exception));
    }
}

let getMyBooks = (username) => {
    try{
        let pobj = axios.get(rootUserURL + "mybooks/" + username, {headers: authHeader()});

        /*pobj.then((response) => {
            console.log(JSON.stringify(response.data, null, 3));
        });*/

        return pobj;
    }
    catch(exception){
        console.log(rootCopyURL + "mybooks/" + username + ": not correct " + (exception));
    }
}

let checkOut = (bookId, username) => {
    try{
        let pobj = axios.put(rootUserURL + "checkOut/" + bookId + "?username=" + username, {}, {headers: authHeader()});
        
        /*pobj.then((response) => {
            console.log(JSON.stringify(response.data, null, 3));
        });*/

        return pobj;
    }
    catch(exception){
        console.log(rootUserURL + "checkOut/" + bookId + "?username=" + username + ": not correct" + (exception));
    }
}

let checkIn = (bookId) => {
    try{
        let pobj = axios.put(rootUserURL + "checkIn/" + bookId, {}, {headers: authHeader()});
        
        /*pobj.then((response) => {
            console.log(JSON.stringify(response.data, null, 3));
        });*/

        return pobj;
    }
    catch(exception){
        console.log(rootUserURL + "checkIn/" + bookId + ": not correct" + (exception));
    }
}

let getCheckedOut = () => {
    try{
        let pobj = axios.get(rootCopyURL + "checkedOut", {headers: authHeader()});
        
        /*pobj.then((response) => {
            console.log(JSON.stringify(response.data, null, 3));
        });*/

        return pobj;
    }
    catch(exception){
        console.log(rootCopyURL + "checkedOut: not correct" + (exception));
    }
}

let addBook = (title, author, date) => {
    try{
        let pobj = axios.post(rootCopyURL
            + "addBook?title=" + title + "&author=" + author + "&date=" + date, {}, {headers: authHeader()});
        
        /*pobj.then((response) => {
            console.log(JSON.stringify(response.data, null, 3));
        });*/

        return pobj;
    }
    catch(exception){
        console.log(rootCopyURL + "addBook: not correct" + (exception));
    }
}

export {testUrl, getCatalog, checkOut, getMyBooks, checkIn, getCheckedOut, addBook};