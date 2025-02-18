function SidebarComponent(props) {

    return(
        <div className="container-fluid">
            <nav className="nav flex-column">
                <ul className="nav nav-pills" id="verticalNav">
                    <li>
                        <a className="nav-link" href="/dashboard">Dashboard</a>
                    </li>
                    <li>
                        <a className="nav-link" href="/catalog">Catalog</a>
                    </li>
                    <li>
                        <a className="nav-link" href="/account">Account</a>
                    </li>
                    <li>
                        <a className="nav-link" href="/mybooks">My Books</a>
                    </li>
                    <li>
                        <a className="nav-link" href="/admin">Admin</a>
                    </li>
                </ul>
            </nav>
        </div>
    );
}

export default SidebarComponent;