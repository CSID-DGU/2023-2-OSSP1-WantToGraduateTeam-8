// App.jsx
import React, { useContext } from 'react';
import { Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar/index';
import LoginMain from './components/LoginMain';
import MainPage from './pages/MainPage/index';
import Brand from './pages/Brand/index';
import Matching from './pages/Matching';
import { AuthContext } from '../src/components/Login/AuthContext';
import Login from './components/Login/index';
import Signup from './components/Signup/index';
import Chatting from './pages/Chatting/index.';
import Mypage from './components/Mypage/index';

export default function App() {
  const { isLoggedIn } = useContext(AuthContext);

  return (
    <>
      {isLoggedIn && <Navbar />}
      <Routes>
        {!isLoggedIn && (
          <>
            <Route path="/" element={<LoginMain />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
          </>
        )}
        <Route path="/main" element={<MainPage />} />
        <Route path="/brand" element={<Brand />} />
        <Route path="/matching" element={<Matching />} />
        <Route path="/chatting" element={<Chatting />} />
        <Route path="/mypage" element={<Mypage />} />
      </Routes>
    </>
  );
}