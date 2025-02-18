import BodyComponent from '../components/bodyPage/bodyComponent';
import HeaderComponent from '../components/headerPage/headerComponent';
import CatalogComponent from '../components/bodyPage/catalogComponent';
import AccountComponent from '../components/bodyPage/accountComponent';
import MyBooksComponent from '../components/bodyPage/mybooksComponent';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { useState, useEffect } from 'react';
import AuthService from '../components/services/authService';
import RegisterComponent from '../components/bodyPage/registerComponent';
import LoginComponent from '../components/bodyPage/loginComponent';
import AdminComponent from '../components/bodyPage/adminComponent';
import './App.css';

function App() {

  const [ user, setUser ] = useState(null);

  /*const myCallback = useCallback((dataFromHeader) => {
    setUser(dataFromHeader);
  }, []);*/

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if(user){
      setUser(AuthService.getCurrentUser());
    }
  }, []);

  return (
    <div className="App">
      <HeaderComponent user={user}/>

      <div className="container-fluid">
        <Router>
          <Routes>
            <Route path="/" exact element={<BodyComponent />}/>
            <Route path="/dashboard" exact element={<BodyComponent />}/>
            <Route path="/register" exact element={<RegisterComponent />}/>
            <Route path="/login" exact element={<LoginComponent />}/>
            <Route path="/catalog" exact element={<CatalogComponent />}/>
            <Route path="/account" exact element={<AccountComponent />}/>
            <Route path="/mybooks" exact element={<MyBooksComponent />}/>
            <Route path="/admin" exact element={<AdminComponent />}/>
          </Routes>
        </Router>
      </div>
    </div>
  );
}

export default App;
