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

//   function groupCategories(categories) {
//     const grouped = [];
//     for (let i = 0; i < categories.length; i += 2) {
//       grouped.push(categories.slice(i, i + 2));
//     }
//     return grouped;
//   }

export default function MainPage() {

  //카페 / 양식 / 중식 / 베이커리 /( 닭/오리요리) / (일식/수산물) / 한식 / 퓨전요리 / 패스트푸드 / 분식 / 술안주
  return (
   <>
        <Mobile>
          <MobileContainer>
          <MobileWrapper>
            각 카테고리 페이지입니다.
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
background: #FFF;
`

const CategoriesContainer = styled.div`
  margin-top : 1.2rem;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  align-items: center;
  width: 100%;
  flex-shrink: 0;
  background: #FFF;
  padding-left : 2rem;
  
`;

const Menu = styled.div`
  width: calc(50% - 0.5rem);
  margin-bottom: 0.75rem;
  position: relative;
  flex-shrink: 0;
  img {
    border : 2px solid #FF7062;
    border-radius : 0.5rem;
    position: relative;
    width: 9rem;
    height: 9rem;
  }
  b {
    color: #000;
    font-size: 1.25rem;
    font-style: normal;
    font-weight: 400;
    position: absolute;
    top: 0;
    left: 0;
    padding: 0.5rem;
    z-index: 1;
  }

`;

const MenuGroup = styled.div`
  display: flex;
  justify-content: space-between;
  width: calc(100% - 1rem);
  margin-bottom: 1rem;
`;