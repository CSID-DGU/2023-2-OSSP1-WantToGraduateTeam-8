import React, { useContext } from 'react';
import { useState } from "react";
import {ReactComponent as Delishare} from '../../assets/imgs/Delishare_mobile_logo.svg'
import styled from 'styled-components'
import { useMediaQuery } from "react-responsive"
import { NavLink, Link } from 'react-router-dom';
import SearchImg from '../../assets/imgs/search_image.svg'
import { AuthContext } from '../Login/AuthContext'; 

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



export default function Navbar() {
  const { logout } = useContext(AuthContext); // AuthContext에서 logout 함수 가져오기

  const handleLogout = () => {
    logout(); // 로그아웃 함수 호출
    window.location.href = '/'; // '/main' 페이지로 이동
  };
  return (
   <>
        <Mobile>
          <MobileContainer>
          <MobileWrapper>
            <Header>
            <Logo>
            <Link to='/main'>
            <Delishare/>
            </Link>
            </Logo>
            <LoginBox>
            {/* <NavLink to='/'>
                회원가입
            </NavLink> */}
            <Link to='/' onClick={handleLogout}>
                로그아웃
            </Link>
            </LoginBox>
            
            </Header>
            <SearchContainer>
              <SearchWrapper >
              <SearchBox className="textbox" type="text" placeholder="검색어 입력"></SearchBox>
              </SearchWrapper>

              <Link to='/'>
              <ButtonWrapper>
              <ButtonImg src={SearchImg}></ButtonImg>
            </ButtonWrapper>
              </Link>
           
            </SearchContainer>
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
const MobileContainer = styled.div`
display: flex;
flex-direction: column;
justify-content: center;
align-items: center;
`;


const MobileWrapper = styled.div`
display : flex;
flex-direction : column;
align-items : center;
width: 24.375rem;
height: 9.375rem;
flex-shrink: 0;
background: #FF7062;
`;
const PcWrapper = styled.div`
width: 1920px;
height: 305px;
flex-shrink: 0;
background: #FF4256;
`;


const Header = styled.div`
  display: flex;
`
const Logo = styled.div`
display : flex;
margin-right : 9rem;
margin-top : 0.45rem;
width: 8.8125rem;
height: 4.5625rem;

`;

const LoginBox = styled.div`
  display: flex;
  
width: 4rem;
height: 3rem;
flex-direction: column;
justify-content: center;
margin-right : 1.5rem;
flex-shrink: 0;

  align-items: center;
  a{

    color: #FFF;
  text-align: center;
  font-feature-settings: 'clig' off, 'liga' off;
  font-size: 0.9375rem;
  font-family : 'Pretendard';
  font-style: normal;
  font-weight: 400;
  text-decoration-line : none;

  }
`;



const SearchContainer = styled.div`
display : flex;
width : 100%;
`;
const SearchWrapper = styled.div`
  display: flex;
  position : relative;
  width: 17rem;
  margin-left : 1.625rem;
  height: 3.0625rem;
  border-radius: 3.125rem;
  background: #FFF;
  flex-shrink: 0;
  }
`;
const SearchBox = styled.input`
  display: flex;
  position: relative;
  width: 100%; 
  border: none;
  padding: 10px; 
  padding-left : 1.25rem;
  height: 3.0625rem;
  border-radius: 3.125rem;
  box-sizing: border-box;
  font-size : 1.0625rem;
`;

const ButtonWrapper = styled.div`
width: 3.0625rem;
height: 3.00513rem;
flex-shrink: 0;
background: #FF7062;
border : 2px solid white;
border-radius : 100%;
margin-left : 1rem;
`;
const ButtonImg = styled.img`

display: flex;
width: 1.8375rem;
height: 1.80306rem;
padding: 0.7rem 0.6em;
justify-content: center;
align-items: center;
flex-shrink: 0;

`;