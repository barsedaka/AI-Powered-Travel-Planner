import './App.css'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import IndexPage from './pages/IndexPage'
import LoginPage from './pages/LoginPage'
import Layout from './Layout'
import RegisterPage from './pages/RegisterPage'
import ProfilePage from './pages/ProfilePage'
import { UserContextProvider } from './UserContext'
import ItinerariesPage from './pages/ItinerariesPage'
import ItineraryFormPage from './pages/ItineraryFormPage'

function App() {

  // function AuthenticatedRoute({children}) {

  //   const isAuth = isUserLoggedIn();

  //   if(isAuth) {
  //     return children;
  //   }

  //   return <Navigate to="/login" />;
  // }

  return (
    <UserContextProvider>
      <Routes>
        <Route path='/' element={<Layout />}>
          <Route index element={<IndexPage />} />
          <Route path='/login' element={<LoginPage />}/>
          <Route path='/register' element={<RegisterPage />}/>
          <Route path='/account' element={<ProfilePage />}/>
          <Route path='/account/itineraries' element={<ItinerariesPage />}/>
          <Route path='/account/itineraries/new' element={<ItineraryFormPage />}/>
          <Route path='/account/itineraries/:id' element={<ItineraryFormPage />}/>
        </Route>
      </Routes>
    </UserContextProvider>
  )
}

export default App
