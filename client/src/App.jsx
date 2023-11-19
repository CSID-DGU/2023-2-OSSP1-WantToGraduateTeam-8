import React, { useContext } from 'react';
import Navbar from './components/Navbar/index'
import { AuthContext } from '../src/components/Login/AuthContext';
import { useState } from "react";
import LoginMain from './components/LoginMain'
import MainPage from './pages/MainPage/index'


export default function App() {
  const { isLoggedIn } = useContext(AuthContext);

  return (
    <>
      {isLoggedIn ? (
        <>
          <Navbar />
          <MainPage /> 
        </>
      ) : (
        <LoginMain />
      )}
    </>
  );
}