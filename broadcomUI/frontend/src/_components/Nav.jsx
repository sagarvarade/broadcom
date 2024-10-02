//import { NavLink } from 'react-router-dom';
//import { useSelector, useDispatch } from 'react-redux';
import { useSelector } from 'react-redux';

//import { authActions } from '_store';
import localStorageGet from 'broadcom/user/API/localStorageReader';
import RoleAdminNav from './RoleAdminNav';
import RoleUserNav from './RoleUserNav';
export { Nav };

function Nav() {
    const auth = useSelector(x => x.auth.value);
    //const dispatch = useDispatch();
    //const logout = () => dispatch(authActions.logout());
    const logedUserRole=localStorageGet('roles');
    console.log('Loged user role ', logedUserRole)
    // only show nav when logged in
    if (!auth) return null;
    
    return (
            logedUserRole === "ROLE_ADMIN" ? (
                    <RoleAdminNav />
                ) : (
                    <RoleUserNav />
            )
    );
}