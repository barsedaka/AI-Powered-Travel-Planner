import axios from "axios";
import { getToken, isTokenExpired, refreshToken } from "./AuthService";

const BASE_REST_API_URL = 'http://localhost:8080/api/itineraries';

// Add a request interceptor
axios.interceptors.request.use(function (config) {
    
    config.headers['Authorization'] = getToken();
    
    return config;
  }, function (error) {
    return Promise.reject(error);
  });

export const getAllItineraries = () => axios.get(BASE_REST_API_URL);

export const saveItinerary = (itinerary) => axios.post(BASE_REST_API_URL, itinerary);

export const getItinerary = (id) => axios.get(BASE_REST_API_URL + '/' + id);

export const updateItinerary = (id, itinerary) => axios.put(BASE_REST_API_URL + '/' + id, itinerary);

export const deleteItinerary = (id) => axios.delete(BASE_REST_API_URL + '/' + id);