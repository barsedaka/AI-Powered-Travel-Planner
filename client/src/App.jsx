import './App.css'
import ListItineraryComponent from './components/ListItineraryComponent'
import HeaderComponent from './components/HeaderComponent'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import ItineraryComponent from './components/ItineraryComponent'
import RegisterComponent from './components/RegisterComponent'
import LoginComponent from './components/LoginComponent'
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
          
          {/* <Route path='/account/:subpage?' element = { 
              <AuthenticatedRoute>
                <AccountPage />
              </AuthenticatedRoute> 
            }/>
          <Route path='/account/itineraries' element = { 
              <AuthenticatedRoute>
                <ListItineraryComponent />
              </AuthenticatedRoute> 
            }/> */}
        </Route>
      </Routes>
    </UserContextProvider>

    
  )

  // return (
  //   <>
  //   <BrowserRouter>
  //     <HeaderComponent />
  //       <Routes>
  //         {/* // http://localhost:8080 */}
  //         <Route path='/' element = { <LoginComponent /> }></Route>

  //         {/* // http://localhost:8080/itineraries */}
  //         <Route path='/itineraries' element = { 
  //           <AuthenticatedRoute>
  //             <ListItineraryComponent />
  //           </AuthenticatedRoute> 
  //         }></Route>

  //         {/* // http://localhost:8080/add-itinerary */}
  //         <Route path='/add-itinerary' element = { 
  //           <AuthenticatedRoute>
  //             <ItineraryComponent />
  //           </AuthenticatedRoute> 
  //         }></Route>

  //         {/* // http://localhost:8080/update-itinerary/1 */}
  //         <Route path='/update-itinerary/:id' element = { 
  //           <AuthenticatedRoute>
  //             <ItineraryComponent />
  //           </AuthenticatedRoute>
  //         }></Route>

  //         {/* // http://localhost:8080/register */}
  //         <Route path='/register' element = { <RegisterComponent /> }></Route>
          
  //         {/* // http://localhost:8080/login */}
  //         <Route path='/login' element = { <LoginComponent /> }></Route>
  //       </Routes>
  //   </BrowserRouter>
  //   </>  
  // )
}

export default App
