import React, { useState, useEffect } from 'react';
import styled from 'styled-components'
import { useMediaQuery } from "react-responsive"
import { NavLink, Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import Spinner from '../../assets/imgs/MatchingSpin.gif';
import Toggle from '../../components/Toggle';

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

export default function Matching() {
  const navigate = useNavigate(); // useNavigate 훅을 사용하여 navigate 함수를 가져옴

  // 예시: 매칭이 완료되었다고 가정하고, 일정 시간 후에 chatting 페이지로 이동하는 함수
  useEffect(() => {
    const timer = setTimeout(() => {
      navigate('/chatting'); 
    }, 3000); // 3초 후에 채팅 페이지로 이동하도록 설정

    return () => clearTimeout(timer); // 컴포넌트가 unmount될 때 타이머 클리어
  }, [navigate]);
    return (
       <>
        <Mobile>
          <MobileContainer>
          <MobileWrapper>
            <MatchingContainer>
             <img src={Spinner} alt="로딩중" width="50%" />
                <p>선택하신 브랜드 위주로 매칭중입니다...</p>
            </MatchingContainer>
          </MobileWrapper>
        
          </MobileContainer>
          <Toggle/>
        </Mobile>


        <Pc>
            <PcWrapper>
            pc
            </PcWrapper>   
        </Pc>
   </>
    );
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
background: #FFF;
`

const MatchingContainer = styled.div`
display : flex;
flex-direction : column;
align-items : center;
background: #FFF;
margin-top : 13rem;
p{
  margin-top: 0.4rem;
  font-size : 1.5rem;
}
`