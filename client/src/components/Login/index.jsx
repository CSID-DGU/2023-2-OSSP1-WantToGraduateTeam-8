import React, { useContext } from 'react';
import { useState } from "react";
import { AuthContext } from './AuthContext';
import {ReactComponent as Delishare} from '../../assets/imgs/Login_logo.svg'

import styled from 'styled-components'
import { useMediaQuery } from "react-responsive"
import { NavLink, Link } from 'react-router-dom';


export const Mobile = ({ children }) => {
    const isMobile = useMediaQuery({
      query: "(max-width:768px)"
    });
    return <>{isMobile && children}</>
  }
  
  export const Pc = ({ children }) => {
    const isPc = useMediaQuery({
      query: "(min-width:769px)"
    });
    return <>{isPc && children}</>
  }



export default function Login() {
    const { login } = useContext(AuthContext);
    const handleLogin = () => {
        // 로그인 로직 구현
        login();
      };
    
  return (
   <>
        <Mobile>
          <MobileContainer>
          <MobileWrapper>
          
          로그인 페이지입니다.

            </MobileWrapper>
          </MobileContainer>

        </Mobile>


        <Pc>
            <PcWrapper>
            pc
            </PcWrapper>   
        </Pc>
   </>
  )
}

const PcWrapper = styled.div`
width: 1920px;
height: 305px;
flex-shrink: 0;
background: #FF4256;
`

const MobileContainer = styled.div`
display: flex;
flex-direction: column;
justify-content: center;
align-items: center;

`

const MobileWrapper = styled.div`
width: 22.125rem;
height: 30.625rem;
flex-shrink: 0;
border-radius: 0.9375rem;
background: #FF7062;
`