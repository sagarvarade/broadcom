import { NavLink } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { authActions } from '_store';
import localStorageGet
 from 'broadcom/user/API/localStorageReader';
function RoleAdminNav(){
  const dispatch = useDispatch();
  const logout = () => dispatch(authActions.logout());

  return (<>
  <p style={{ color: 'white',backgroundColor:'black' }} >Role Admin   :  {localStorageGet('username')}</p>
  <nav className="navbar navbar-expand navbar-dark bg-dark px-3">
  
  <div className="navbar-nav">
    <NavLink to="/" className="nav-item nav-link">Home</NavLink>
    <NavLink to="/users" className="nav-item nav-link">Users</NavLink>
    <NavLink to="/addbroaduser" className="nav-item nav-link">Add Users </NavLink>
    <NavLink to="/addbroaduser" className="nav-item nav-link">Edit Users </NavLink>
    <NavLink to="/addbroaduser" className="nav-item nav-link">Create Group </NavLink>
    <NavLink to="/addbroaduser" className="nav-item nav-link">Broad Cast Message </NavLink>
    <button onClick={logout} className="btn btn-link nav-item nav-link">Logout</button>
    </div>
    </nav>
  </>)

}

export default RoleAdminNav;