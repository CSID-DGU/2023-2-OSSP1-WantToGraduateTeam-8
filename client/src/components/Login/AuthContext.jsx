// AuthContext.jsx
import React, { createContext, useState } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    // 초기 상태를 false로 설정
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const login = () => {
      setIsLoggedIn(true);
    };

    const logout = () => {
      setIsLoggedIn(false);
    };

    return (
      <AuthContext.Provider value={{ isLoggedIn, login, logout }}>
        {children}
      </AuthContext.Provider>
    );
};
