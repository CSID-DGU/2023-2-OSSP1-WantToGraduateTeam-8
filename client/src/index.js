import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import LoginMain from "./components/LoginMain"
import Login from "./components/Login/index"
import Signup from "./components/Signup/index"
import Navbar from './components/Navbar';
import MainPage from './pages/MainPage/index'
import { AuthProvider } from './components/Login/AuthContext'; // 경로는 실제 구조에 맞게 조정
import Brand from './pages/Brand/index'

const router = createBrowserRouter([
  { path: "/", element: <LoginMain /> },
  { path: "/login", element: <Login /> },
  { path: "/signup", element: <Signup /> },
  { path: "/main", element: <App />, children: [
    // App 컴포넌트 내에서 처리할 추가적인 하위 경로들을 여기에 추가
    { index: true, element: <MainPage /> },
    { path: "brand", element: <Brand /> } // '/category' 경로 추가
    // 예: { path: "other-page", element: <OtherPage /> },
  ]},
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <AuthProvider>
      <RouterProvider router={router} />
    </AuthProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
