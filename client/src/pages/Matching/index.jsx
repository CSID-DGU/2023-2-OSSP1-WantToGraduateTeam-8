import React, { useState, useEffect } from 'react';
import styled from 'styled-components'
import { useMediaQuery } from "react-responsive"
import { NavLink, Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import Spinner from '../../assets/imgs/MatchingSpin.gif';
import Toggle from '../../components/Toggle';
import axios from 'axios';

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
    const navigate = useNavigate();
    const [isLoading, setIsLoading] = useState(true);
  
    useEffect(() => {
      const accessToken = localStorage.getItem('accessToken');
      let count = 0; // 요청 횟수를 저장하는 변수
  
      const interval = setInterval(async () => {
        try {
          count++;
          if (count > 10) {
            clearInterval(interval);
            setIsLoading(false); // 로딩 상태 해제
            return;
          }
  
          const response = await axios.get('http://ec2-13-125-45-64.ap-northeast-2.compute.amazonaws.com:8080/matching/run/status/flag', {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
          });
          const { flag } = response.data;
          console.log(response.data);
          if (flag) {
            navigate('/chat/main');
            clearInterval(interval);
          } else {
            setIsLoading(true);
          }
        } catch (error) {
          console.error('API 호출 오류:', error);
        }
      }, 3000); // 3초마다 실행
  
      return () => clearInterval(interval);
    }, [navigate]);
  
    const handleChattingClick = () => {
      navigate('/main');
    };
  
    return (
       <>
        <Mobile>
          <MobileContainer>
          <MobileWrapper>
            <MatchingContainer>
              {isLoading &&(
                <>
                <img src={Spinner} alt="로딩중" width="50%" />
                <p>선택하신 브랜드 위주로 매칭중입니다...</p>
                </>
              ) }
                {!isLoading && (
                <>
                  <MiddleLogo>Share,<br/> Delicious</MiddleLogo>
                  <p>매칭이 실패하였습니다..</p>
                  <Button onClick={handleChattingClick}>음식점 다시 선택하기</Button>
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