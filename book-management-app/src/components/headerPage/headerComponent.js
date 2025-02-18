import React, { useCallback, useState, useEffect } from 'react';
import AuthService from '../services/authService';

function HeaderComponent(props){

  const [ user, setUser ] = useState(props.user);

  const logOut = useCallback(() => {
    AuthService.logout()
  }, []);

  useEffect(() => {
    setUser(props.user);
  }, [props.user]);

  return (
      <div  className="container-fluid">
        <div className="row">
          <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="col-10">
              <a className="navbar-brand" href="/dashboard">Legacy Library</a>
            </div>
            <div className="col">
              <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
              </button>
          
              {user ? (
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                  <li className="nav-item">
                    <a role="button" className="btn nav-link" href='/login' onClick={logOut}>LogOut</a>
                  </li>
                </ul>
              </div>
              ) : (
              <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                  <li className="nav-item">
                    <a href="/register" role="button" className="btn nav-link">Sign up</a>
                  </li>
                  <li className="nav-item">
                    <a role="button" className="btn nav-link" href='/login'>Sign In</a>
                  </li>
                </ul>
              </div>)}
            </div>
          </nav>
        </div>
      </div>
  );
}

export default HeaderComponent;