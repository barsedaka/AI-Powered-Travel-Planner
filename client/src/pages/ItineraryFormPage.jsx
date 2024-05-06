import { useEffect, useState } from "react";
import { saveItinerary, getItinerary, updateItinerary } from "../services/ItineraryService";
import ActivityTypes from "../ActivityTypes";
import AccountNav from "../AccountNav";
import { Navigate, redirect, useNavigate, useParams } from "react-router-dom";

export default function ItineraryFormPage() {

    const {id} = useParams();
    const [destination, setDestination] = useState('');
    const [activityTypes, setActivityTypes] = useState([]);
    const [tripDuration, setTripDuration] = useState(1);
    //const [redirect, setRedirect] = useState(false);
    const navigate = useNavigate();
    const [generatedItinerary, setGeneratedItinerary] = useState(null);

    useEffect(() => {
        if (!id) {
            return;
        }

        getItinerary(id).then((response) => {
                console.log(response.data);
                setDestination(response.data.destination);
                setActivityTypes(response.data.activityTypes);
                setTripDuration(response.data.tripDuration);
                setGeneratedItinerary(JSON.parse(response.data.tripPlan));
            }).catch(error => {
                console.error(error);
            })

    }, [id]);

    function inputHeader(text) {
        return <h2 className="text-2xl mt-4">{text}</h2>
    }

    async function saveOrUpdateItinerary(e) {
        e.preventDefault();

        const itinerary = {
            destination, 
            activityTypes, 
            tripDuration, 
            tripPlan: " "
        };

        console.log(itinerary);

        if (id) {
            setGeneratedItinerary(null);
            updateItinerary(id, itinerary).then((response) => {
                console.log(response.data.tripPlan);
                setGeneratedItinerary(JSON.parse(response.data.tripPlan));
                //setRedirect(true);
            }).catch(error => {
                console.error(error);
            })
        } else {
            saveItinerary(itinerary).then((response) => {
                //navigate('/itineraries');
                //const data = JSON.parse(response.data);
                console.log(response.data);
                console.log(response.data.tripPlan);
                setGeneratedItinerary(JSON.parse(response.data.tripPlan));
            }).catch(error => {
                console.error(error);
            })
        }
    }

    // if (redirect) {
    //     return <Navigate to={'/account/itineraries'} />;
    // }

    return (
        <div>
            <AccountNav />
            <form>
                {inputHeader('Destination:')}
                <input 
                    type="text" 
                    className='form-control'
                    placeholder='Enter Trip Destination'
                    name='destination'
                    value={destination}
                    onChange={(e) => setDestination(e.target.value)}
                />

                {inputHeader('Trip Duration:')}
                <input 
                    type="number" 
                    className='form-control'
                    value={tripDuration} 
                    onChange={(e) => setTripDuration(e.target.value)} 
                    min="1" 
                />

                {inputHeader('Activity Types:')}
                <div className="grid mt-2 gap-2 grid-cols-2 md:grid-cols-3 lg:grid-cols-6">
                    <ActivityTypes selected={activityTypes} onChange={setActivityTypes} />
                </div>
                <button className="primary my-4" onClick={saveOrUpdateItinerary}>Save</button>
            </form>

            {generatedItinerary && (
                <div className="row justify-content-center mt-4">
                    <div className="col-md-8">
                        <h3 className="text-center">Generated Itinerary</h3>
                        <div className="border p-3">
                            {generatedItinerary.days.map(day => (
                                <div key={day.day}>
                                    <h4>Day {day.day}</h4>
                                    <ul>
                                        {day.activities.map(activity => (
                                            <li key={activity.title}>
                                                <h5>{activity.title}</h5>
                                                <p>{activity.description}</p>
                                                <p>Time: {activity.start_time} - {activity.end_time}</p>
                                                <a href={activity.link}>More Info</a>
                                                <p>Location: <a href={activity.location}>View on Map</a></p>
                                                <br />
                                            </li>
                                        ))}
                                    </ul>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}