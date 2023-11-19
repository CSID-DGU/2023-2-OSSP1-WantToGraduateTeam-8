import React from 'react'
import { useState } from "react";
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



export default function LoginMain() {
  return (
   <>
        <Mobile>
          <MobileContainer>
          <MobileWrapper>
          <Logo>
           <Delishare/>
        </Logo>

            <Logo2>
            <MiddleLogo>Share,<br/> Delicious</MiddleLogo>
            </Logo2>
            <LoginWrapper>
              <NavLink to='/login'>
              <LoginButton>
                <p>로그인</p>
              </LoginButton>
              </NavLink>
              <NavLink to='/signup'>
              <SignupButton>
                <p>회원가입</p>
              </SignupButton>
              </NavLink>
       
            </LoginWrapper>
        

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
display : flex;
flex-direction : column;
align-items : center;
width: 24.375rem;
height: 52.75rem;
flex-shrink: 0;
background: #FF7062;
`

const Logo = styled.div`
display : flex;
margin-top : 10.5rem;
width: 13.625rem;
height: 8.25rem;
margin-left : 2rem;
`
const Logo2 = styled.div`
display : flex;

`

const LoginWrapper = styled.div`
display : flex;
text-align : center;
flex-direction: column;
margin-top : 7.19rem;

`
const MiddleLogo = styled.div`
color: #F8E6E6;
text-align: center;
font-feature-settings: 'clig' off, 'liga' off;
font-family: Domine;
font-size: 2rem;
font-style: normal;
font-weight: 700;
line-height: 100%; /* 1.5rem */
margin-top : 3.8rem;

`

const LoginButton = styled.div`
display : flex;
text-align: center;
align-items: center;
flex-direction: column;
width: 18.71525rem;
height: 3.0625rem;

flex-shrink: 0;
border-radius: 0.9375rem;
background: #F1E050;
text-decoration-line : none;

p{
  
  margin-top:0.8rem;
  display : flex;
  text-align: center;
  flex-direction: column;
  text-decoration-line : none;
  width: 5rem;
  height: 1.36113rem;
  flex-shrink: 0;
  color: #040404;
  font-feature-settings: 'clig' off, 'liga' off;
  font-size: 1.5rem;
  font-style: normal;
  font-weight: 400;
  line-height: 100%; 
}

`

const SignupButton = styled.div`
display : flex;
text-align: center;
align-items: center;
margin-top : 1.7rem;
flex-direction: column;
width: 18.71525rem;
height: 3.0625rem;
flex-shrink: 0;
border-radius: 0.9375rem;
background: #F1E050;
text-decoration-line : none;

p{
  margin-top:0.8rem;
  display : flex;
  text-align: center;
  flex-direction: column;
  text-decoration-line: none;
  width: 8rem;
  height: 1.36113rem;
  flex-shrink: 0;
  color: #040404;
  font-feature-settings: 'clig' off, 'liga' off;
  font-size: 1.5rem;
  font-style: normal;
  font-weight: 400;
  line-height: 100%; 
}
`