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

let getBookTotal = () => {
    try{
        let pobj = axios.get(rootCopyURL + "totalBooks", {headers: authHeader()});

        /*pobj.then((response) => {
            console.log(JSON.stringify(response.data, null, 3));
        });*/

        return pobj;
    }
    catch(exception){
        console.log(rootCopyURL + "totalBooks: not correct " + (exception));
    }
}

let getBooksAvail = () => {
    try{
        let pobj = axios.get(rootCopyURL + "totalAvailable", {headers: authHeader()});

        /*pobj.then((response) => {
            console.log(JSON.stringify(response.data, null, 3));
        });*/

        return pobj;
    }
    catch(exception){
        console.log(rootCopyURL + "totalAvailable: not correct " + (exception));
    }
}

let getNumOfUsers = () => {
    try{
        let pobj = axios.get(rootUserURL + "viewNumOfUsers", {headers: authHeader()});

        /*pobj.then((response) => {
            console.log(JSON.stringify(response.data, null, 3));
        });*/

        return pobj;
    }
    catch(exception){
        console.log(rootUserURL + "viewNumOfUsers: not correct " + (exception));
    }
}

export {testUrl, getBookTotal, getBooksAvail, getNumOfUsers};