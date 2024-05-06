import { useContext, useState } from "react";
import {Link, Navigate, useParams} from "react-router-dom";
import { UserContext } from "../UserContext";
import { logout } from "../services/AuthService";
import ItinerariesPage from "./ItinerariesPage";
import AccountNav from "../AccountNav";

export default function ProfilePage() {

    const [redirect, setRedirect] = useState(null);
    const { ready, user, setUser } = useContext(UserContext);
    let {subpage} = useParams();
    if (subpage === undefined) {
        subpage = 'profile';
    }

    function handleLogout() {
		logout();
		setRedirect('/');
        setUser(null);
	}

    if (!ready) {
        return 'Loading...';
    }

    if (ready && !user && !redirect) {
        return <Navigate to={'/login'} />;
    }

    if (redirect) {
        return <Navigate to={redirect} />
    }

    return (
      <div>
        <AccountNav />
        {subpage === 'profile' && (
            <div className="text-center">
                Logged in as {user} <br />
                <button className="primary max-w-sm mt-2" onClick={handleLogout}>Logout</button>
            </div>
        )}
        {subpage === 'itineraries' && (
            <ItinerariesPage />
        )}
      </div>
    )
}