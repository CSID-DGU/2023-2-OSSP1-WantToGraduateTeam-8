import React, { useContext } from 'react';
import Navbar from './components/Navbar/index'
import { useRoutes, Navigate } from 'react-router-dom';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import { AuthContext } from '../src/components/Login/AuthContext';
import { useState } from "react";
import LoginMain from './components/LoginMain'
import MainPage from './pages/MainPage/index'


export default function App() {
  const { isLoggedIn } = useContext(AuthContext);
  const routes = useRoutes([
    // 여기에 App 컴포넌트 하위의 라우트를 정의
    { path: "/", element: <MainPage /> },
    // 예: { path: "other-page", element: <OtherPage /> },
  ]);

  return (
    <>
      {isLoggedIn && <Navbar />}
      {routes}
    </>
  );
}