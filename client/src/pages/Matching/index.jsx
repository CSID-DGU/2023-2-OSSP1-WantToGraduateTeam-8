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
  const [isMatchingComplete, setIsMatchingComplete] = useState(false);

  // 예시: 매칭이 완료되었다고 가정하고, 일정 시간 후에 매칭완료 페이지로 이동
  useEffect(() => {
    // 예시: 매칭이 완료되었다고 가정
    setTimeout(() => {
      setIsMatchingComplete(true);
    }, 3000); // 3초 후 매칭 완료 상태로 변경
});

  const handleChattingClick = () => {
    navigate('/chatting');
  };
    return (
       <>
        <Mobile>
          <MobileContainer>
          <MobileWrapper>
            <MatchingContainer>
              {!isMatchingComplete &&(
                <>
                <img src={Spinner} alt="로딩중" width="50%" />
                <p>선택하신 브랜드 위주로 매칭중입니다...</p>
                </>
              ) }
                {isMatchingComplete && (
                <>
                  <MiddleLogo>Share,<br/> Delicious</MiddleLogo>
                  <p>매칭이 완료되었습니다!</p>
                  <Button onClick={handleChattingClick}>채팅방 이동하기</Button>
                </>
              )}
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
margin-top : 9rem;
p{
  margin-top: 0.4rem;
  font-size : 1.5rem;
}
`

const Button = styled.button`
  margin-top: 3rem;
  padding: 10px 20px;
  background-color: #FF7062;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
`;

const MiddleLogo = styled.div`
color: #FF7062;
margin-bottom : 2rem;
text-align: center;
font-family: Domine;
font-size: 2rem;
font-style: normal;
font-weight: 700;
line-height: 100%; /* 1.5rem */
margin-top : 3.8rem;
text-shadow: 0px 2px 2px rgba(0, 0, 0, 0.25);

`