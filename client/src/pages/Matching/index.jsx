import React, { useState, useEffect } from 'react';
import styled from 'styled-components'
import { useMediaQuery } from "react-responsive"
import { NavLink, Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

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
    return (
       <>
        <Mobile>
          <MobileContainer>
          <MobileWrapper>
            매칭페이지입니다.
          </MobileWrapper>
        
          </MobileContainer>

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