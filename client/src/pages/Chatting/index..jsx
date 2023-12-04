import React, { useState, useEffect } from 'react';
import styled from 'styled-components'
import { useMediaQuery } from "react-responsive"
import { NavLink, Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import Toggle from '../../components/Toggle';
import RateStar from '../../components/RateStar/RateStar';
import ChatBtn from '../../assets/imgs/ChattingBtnImg.png';
import ExitChat from '../../assets/imgs/ExitChattingImg.png';

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

export default function Chatting() {

    return (
       <>
        <Mobile>
          <MobileContainer>
          <MobileWrapper>
            <ChattingContainer>
             채팅페이지입니다.
            </ChattingContainer>
            <ChattingInputDiv>
              <ExitChatting>
                <img src={ExitChat}/>
              </ExitChatting>
              <ChattingInput>

              </ChattingInput>
              <ChattingBtn>
                <img src={ChatBtn}/>
              </ChattingBtn>
            </ChattingInputDiv>
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

const ChattingContainer = styled.div`
display : flex;
flex-direction : column;
width: 20.8125rem;
height: 40.5625rem;
border-radius: 0.9375rem;
border: 1px solid #FF7062;
align-items : center;
background: #FFF;
margin-top : 1.25rem;
`

const ChattingInputDiv = styled.div`
  background-color : #FF7062;
  border-radius: 0.9375rem;
  margin-top : 0.75rem; 
  width: 20.8125rem;
  height : 2.875rem;
`

const ExitChatting = styled.button`
 position : relative;
 top : 0.325rem;
  left : 0.35rem;
  height : 2.125rem;
  border : 1.5px solid #FFFFFF;
  background-color : #FF7062;
  width : 2.125rem;
  border-radius : 1.0625rem 0.375rem 0.375rem 1.0625rem;
  img {
    position : relative;
    top : 0.125rem;
    left : 0.125rem;
  }
`

const ChattingInput = styled.input`
position : relative;
padding-left : 0.75rem;
border-radius: 0.375rem 0.9375rem 0.9375rem 0.375rem;
border : none;
left : 0.75rem;
height : 2rem;
width : 14.125rem;
`

const ChattingBtn = styled.button`
position : relative;
  top : 0.325rem;
  left : 1.125rem;
  height : 2.125rem;
  border : 1.5px solid #FFFFFF;
  background-color : #FF7062;
  width : 2.125rem;
  border-radius : 1.0625rem;
`