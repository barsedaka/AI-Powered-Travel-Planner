import { useEffect, useState } from "react";
import { Link, Navigate, useNavigate, useParams } from "react-router-dom";
import ActivityTypes from "../ActivityTypes";
import { getAllItineraries, deleteItinerary } from "../services/ItineraryService";
import AccountNav from "../AccountNav";
import { isAdminUser } from "../services/AuthService";

export default function ItinerariesPage() {

    const [itineraries, setItineraries] = useState([]);

    const navigate = useNavigate();

    const isAdmin = isAdminUser();

    useEffect(() => {
        listItineraries();
    }, []);

    function listItineraries() {
        getAllItineraries().then((response) => {
            setItineraries(response.data);
            console.log(response.data);
        }).catch(error => {
        console.error(error);
        })
    }

    function removeItinerary(id) {
        deleteItinerary(id).then((response) => {
            listItineraries();
        }).catch(error => {
            console.error(error);
        })
    }

    return (
        <div>
            <AccountNav />
            <div className="text-center">
                <Link className="inline-flex gap-1 bg-primary text-white py-2 px-6 rounded-full" to={'/account/itineraries/new'}>
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" className="w-6 h-6">
              <path fillRule="evenodd" d="M12 3.75a.75.75 0 01.75.75v6.75h6.75a.75.75 0 010 1.5h-6.75v6.75a.75.75 0 01-1.5 0v-6.75H4.5a.75.75 0 010-1.5h6.75V4.5a.75.75 0 01.75-.75z" clipRule="evenodd" />
            </svg>
                    Plan a new trip
                </Link>
            </div>

            <div className="grid grid-cols-1 gap-6 max-w-3xl mx-auto">
                {
                    itineraries.length > 0 &&
                    
                    itineraries.map(itinerary => 
                    <div key={itinerary.id}>
                        <Link
                            to={`/account/itineraries/${itinerary.id}`}
                            className="bg-white rounded-lg shadow-md overflow-hidden flex p-4"
                        >
                            <div className="flex-grow">
                            <h2 className="text-xl font-semibold text-gray-900 mb-2">
                                {itinerary.destination}
                            </h2>
                            <p className="text-gray-700 mb-2">
                                Trip Duration: {itinerary.tripDuration} days
                            </p>
                            <p className="text-gray-700">
                                Activity Types: {itinerary.activityTypes.join(", ")}
                            </p>
                            </div>
                        </Link>
                        <button className="btn btn-danger" onClick={() => removeItinerary(itinerary.id)}>Delete</button>
                    </div>
                    )
                    
                }
                
                {
                    itineraries.length === 0 &&
                    <p>No itineraries found.</p>
                }
                </div>
        </div>
    )
}