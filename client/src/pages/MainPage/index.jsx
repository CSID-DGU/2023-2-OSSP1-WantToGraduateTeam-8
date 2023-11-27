import React from 'react'
import { useState } from "react";
import {ReactComponent as Delishare} from '../../assets/imgs/Login_logo.svg'
import styled from 'styled-components'
import { useMediaQuery } from "react-responsive"
import { NavLink, Link } from 'react-router-dom';
import Cafe from '../../assets/imgs/Cafe.png'
import Chinese from '../../assets/imgs/Chinese.png'
import Pizza from '../../assets/imgs/Pizza.png'
import Bakery from '../../assets/imgs/Bakery.png'
import Chicken from '../../assets/imgs/Chicken.png'
import Sushi from '../../assets/imgs/Sushi.png'
import Korean from '../../assets/imgs/Korean.png'
import Fusion from '../../assets/imgs/Fusion.png'
import Fastfood from '../../assets/imgs/Hamburger.png'
import Bunsik from '../../assets/imgs/Bunsik.png'
import Nightfood from '../../assets/imgs/NightFood.png'
import Toggle from '../../components/Toggle/index'

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

  function groupCategories(categories) {
    const grouped = [];
    for (let i = 0; i < categories.length; i += 2) {
      grouped.push(categories.slice(i, i + 2));
    }
    return grouped;
  }

export default function MainPage() {
  const categories = [
    { name: '카페', image: Cafe },
    { name: '양식', image: Pizza },
    { name: '중식', image: Chinese },
    { name: '베이커리', image: Bakery },
    { name: '닭/오리요리', image: Chicken },
    { name: '일식/수산물', image: Sushi },
    { name: '한식', image: Korean },
    { name: '퓨전요리', image: Fusion },
    { name: '패스트푸드', image: Fastfood },
    { name: '분식', image: Bunsik },
    { name: '술안주', image: Nightfood },
    
  ];
  //카페 / 양식 / 중식 / 베이커리 /( 닭/오리요리) / (일식/수산물) / 한식 / 퓨전요리 / 패스트푸드 / 분식 / 술안주
  const handleCategoryClick = async (categoryName) => {
    try {
      // 서버에 요청을 보낼 준비 (axios 또는 fetch 등을 사용하여 API 요청)
      const response = await fetch('https://example.com/api/categories', {
        method: 'POST', // POST 방식으로 데이터 전송
        headers: {
          'Content-Type': 'application/json', // 전송할 데이터의 타입 설정
        },
        body: JSON.stringify({ category: categoryName }), // 카테고리 이름을 JSON 형식으로 변환하여 전송
      });
  
      if (!response.ok) {
        throw new Error('Failed to fetch data');
      }
  
      // 서버로부터 응답 받은 데이터 처리
      const data = await response.json();
      // data를 Categories 컴포넌트로 전달하여 해당 카테고리의 음식점 리스트를 업데이트
      // 여기서 data를 Categories 컴포넌트로 전달하거나 상태를 업데이트하는 등의 작업을 할 수 있다.
    } catch (error) {
      console.error('Error:', error.message);
    }
  };
  
  return (
   <>
        <Mobile>
          <MobileContainer>
          <MobileWrapper>
          <CategoriesContainer>
              {groupCategories(categories).map((group, index) => (
                <MenuGroup key={index}>
                  {group.map((category, idx) => (
                    <Menu key={idx}>
                      {/* <Link to="category">
                        <b>{category.name}</b>
                        <img src={category.image} alt={category.name} />
                      </Link> */}
                       <Link to="/brand" onClick={() => handleCategoryClick(category.name)}>
                        <b>{category.name}</b>
                        <img src={category.image} alt={category.name} />
                      </Link>   
                      {/* -> api연결 할때 코드 */}
                    </Menu>
                  ))}
                </MenuGroup>
              ))}
            </CategoriesContainer>
            <Toggle/>
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