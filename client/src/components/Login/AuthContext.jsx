// AuthContext.jsx
import React, { createContext, useState, useEffect } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        // 페이지 로드시 로컬스토리지에서 토큰을 가져와서 검사
        const token = localStorage.getItem('accessToken');
        if (token) {
            // 토큰이 있다면 로그인 상태로 설정
            setIsLoggedIn(true);
        }
    }, []);

    const login = (token) => {
        // 로그인 함수에서 토큰을 받아서 상태와 로컬스토리지에 저장
        setIsLoggedIn(true);
    };

    const logout = () => {
        // 로그아웃 시 상태와 로컬스토리지의 토큰 삭제
        setIsLoggedIn(false);
    };

    return (
        <AuthContext.Provider value={{ isLoggedIn, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};
