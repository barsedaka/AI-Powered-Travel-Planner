import React, { createContext, useState, useEffect } from 'react';
import { getLoggedInUser, getLoggedInUserRole } from './services/AuthService';

export const UserContext = createContext();

export const UserContextProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [role, setRole] = useState(null);
  const [ready, setReady] = useState(false);

  useEffect(() => {
    const storedUser = getLoggedInUser();
    const storedRole = getLoggedInUserRole();

    console.log("Stored user:", storedUser);
    console.log("Stored role:", storedRole);

    setUser(storedUser ?? null);
    setRole(storedRole ?? null);
    setReady(true);
  }, []);

  useEffect(() => {
    const handleStorageChange = () => {
      const storedUser = getLoggedInUser();
      const storedRole = getLoggedInUserRole();

      setUser(storedUser ?? null);
      setRole(storedRole ?? null);
    };

    window.addEventListener('storage', handleStorageChange);

    return () => {
      window.removeEventListener('storage', handleStorageChange);
    };
  }, []);


  return (
    <UserContext.Provider value={{ user, setUser, role, setRole, ready }}>
      {children}
    </UserContext.Provider>
  )
}