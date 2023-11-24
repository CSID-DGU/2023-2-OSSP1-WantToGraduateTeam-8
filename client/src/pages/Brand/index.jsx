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

  function groupCategories(categories) {
    const grouped = [];
    for (let i = 0; i < categories.length; i += 2) {
      grouped.push(categories.slice(i, i + 2));
    }
    return grouped;
  }

// 예시 데이터
const mockBrandList = [
  { id: 1, name: 'Brand A' },
  { id: 2, name: 'Brand B' },
  { id: 3, name: 'Brand C' },
  { id: 4, name: 'Brand D' },
  { id: 5, name: 'Brand E' },
  { id: 6, name: 'Brand F' },
  { id: 7, name: 'Brand G' },
  
];
export default function Brand({ categoryName }) {
  const [brandList, setBrandList] = useState([]);
  const [selectedBrands, setSelectedBrands] = useState([]);
  const navigate = useNavigate(); // useNavigate hook을 사용하여 navigate 함수를 가져옴
  const [priorityMap, setPriorityMap] = useState({});//우선순위맵

  useEffect(() => {
    // 실제 서버로부터 데이터를 가져오는 로직이 구현
    // 여기서 categoryName을 사용하여 해당 카테고리의 브랜드 리스트를 가져온다.
    const fetchBrandList = async () => {
      try {
        // 서버로부터 브랜드 리스트 데이터를 가져오는 API 호출
        // const response = await fetch(`https://example.com/api/brands/${categoryName}`);
        // const data = await response.json();

        // 실제 API 호출 결과 대신에 예시 데이터를 사용합니다.
        const data = mockBrandList;

        setBrandList(data);
      } catch (error) {
        console.error('Error fetching brand list:', error);
      }
    };

    fetchBrandList(); // 데이터 가져오는 함수 호출
  }, [categoryName]);
  //카페 / 양식 / 중식 / 베이커리 /( 닭/오리요리) / (일식/수산물) / 한식 / 퓨전요리 / 패스트푸드 / 분식 / 술안주
  const groupedBrandList = groupCategories(brandList);
  
    const handleSelectButtonClick = (brand) => {
    // 이전 선택된 브랜드에 대한 우선순위 확인
    const brandPriority = priorityMap[brand.id] || null;

    if (selectedBrands.includes(brand)) {
      // 선택 취소 시 priorityMap 업데이트
      const updatedBrands = selectedBrands.filter(item => item !== brand);
      setSelectedBrands(updatedBrands);
      // 우선순위 삭제
      if (brandPriority) {
        const updatedPriorityMap = { ...priorityMap };
        delete updatedPriorityMap[brand.id];
        setPriorityMap(updatedPriorityMap);
      }
    } else {
      if (selectedBrands.length < 3) {
        setSelectedBrands([...selectedBrands, brand]);
        // 우선순위 설정
        setPriorityMap({ ...priorityMap, [brand.id]: selectedBrands.length + 1 });
      }
    }
  };

  const handleMatchingButtonClick = () => {
    const selectedBrandIds = selectedBrands.map(brand => brand.id);

    console.log('선택한 브랜드 우선순위:', priorityMap);

    // 서버로 선택한 브랜드와 우선순위를 전송
    // fetch 또는 axios 등을 사용하여 서버에 데이터를 전송하는 로직을 추가해야 합니다.

    navigate('/matching'); // 
  };
  return (
   <>
        <Mobile>
          <MobileContainer>
          <MobileWrapper>
            <p>먹고 싶은 브랜드 순서대로 최대 3개를 선택해주세요!</p>
          <BrandListWrapper>
          {groupedBrandList.map((group, groupIndex) => (
      <BrandRow key={groupIndex}>
        {group.map((brand, brandIndex) => {
          const brandIsSelected = selectedBrands.includes(brand);
          const selectedIndex = selectedBrands.indexOf(brand);
          const buttonText =
            brandIsSelected && selectedIndex >= 0 ? `선택 ${selectedIndex + 1}` : '선택하기';

          return (
            <BrandSet key={brandIndex} onClick={() => handleSelectButtonClick(brand)}>
              <BrandItem key={brand.id}>{brand.name}</BrandItem>
              <SelectButton isSelected={brandIsSelected}>{buttonText}</SelectButton>
            </BrandSet>
          );
        })}
      </BrandRow>
        ))}
            </BrandListWrapper>
          </MobileWrapper>
          <FixedButton>
          <MatchingButton onClick={handleMatchingButtonClick}>매칭하기</MatchingButton>
      </FixedButton>
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
const BrandRow = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
`;
const MobileWrapper = styled.div`
display : flex;
flex-direction : column;
align-items : center;
width: 24.375rem;
height: 52.75rem;
flex-shrink: 0;
background: #FFF;
p{
  margin-top : 1rem;
  color : #E66049;
  font-style: bold;
  font-weight: 900;
}
`
const BrandItem = styled.div`
  margin-bottom: 10px;
`;
const BrandListWrapper = styled.div`
  margin-top : 1.2rem;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  flex-shrink: 0;
  background: #FFF;
  padding-left : 2rem;
  
`;

const BrandSet = styled.div`
  margin-bottom: 10px;
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  margin-left: 1rem;
  margin-right: 3rem;
  padding: 2.4375rem 0rem 0rem 0rem;
  flex-direction: column;
  height: 6rem;
  width: 8rem;
  border-radius: 0.9375rem;
  font-size: 1rem;
  font-style: normal;
  border: 1px solid #7D7D7D;
  cursor: pointer;
  position: relative;

  &:hover {
    button {
      background: #F29788;
    }
  }
`;

const SelectButton = styled.button`
  margin-top: 2rem;
  background: ${props => (props.isSelected ? '#a3b6e9' : '#FF7062')};
  border: none;
  width: 8rem;
  height: 2.4rem;
  border-radius: 0rem 0rem 0.9375rem 0.9375rem;
  cursor: pointer;
  font-size: 0.9375rem;
  font-style: bold;
  font-weight: 900;
  line-height: 130%;
  color: #FFF;

  &:hover {
    background: #F29788;
  }
`;

const FixedButton = styled.div`
  position: fixed;
  bottom: 0px;
 
`;

const MatchingButton = styled.button`
 background: #F1E050;
  border: none;
  width: 24rem;
  height: 4.6875rem;
  flex-shrink: 0;
  font-size: 1.3rem;
  font-style: bold;
  font-weight: 900;
  line-height: 130%;
  color: #040404;
  border-radius: 5px;
  cursor: pointer;
  

  &:hover {
    background: #EDE493;
  }
`;