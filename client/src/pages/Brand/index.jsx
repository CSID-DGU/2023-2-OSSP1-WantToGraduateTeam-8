//Brand.jsx
import React, { useState, useEffect } from 'react';
import styled from 'styled-components'
import { useMediaQuery } from "react-responsive"
import { NavLink, Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import Toggle from '../../components/Toggle';
import axios from 'axios';
import { useLocation, useParams } from 'react-router-dom';

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
// const mockBrandList = [
//   { id: 1, name: '프랭크버거 동국대점' },
//   { id: 2, name: '블리스버거 앤 카페' },
//   { id: 3, name: '버거킹 충무로역점' },
//   { id: 4, name: '맘스터치 동국대점' },
//   { id: 5, name: 'KFC 충무로역' },
//   { id: 6, name: '버거원하우스 장충점' },
//   { id: 7, name: '노브랜드버거 동국대점' },
  
// ];
export default function Brand(props) {
  const [brandList, setBrandList] = useState([]);
  const [selectedBrands, setSelectedBrands] = useState([]);
  const [priorityMap, setPriorityMap] = useState({});
  const navigate = useNavigate();
  const location = useLocation();
  const { category } = useParams();
  const accessToken = localStorage.getItem('accessToken');
  //서버
  useEffect(() => {
    const categoryName = location.search.split('=')[1];
    //const categoryName = localStorage.getItem("categoryName");
    console.log(categoryName);
    const fetchBrandList = async () => {
      try {
        const response = await axios.get(`http://ec2-13-125-45-64.ap-northeast-2.compute.amazonaws.com:8080/brand/list/all?category=${categoryName}`,
        {
          //withCredentials : true,
           headers : {
             //'Access-Control-Allow-Origin' : '*',
             //'Access-Control-Allow-Methods' : 'GET, PUT, POST, DELETE, OPTIONS',
             //'Access-Control-Allow-Headers' : 'Content-Type, Authorization, Content-Length, X-Request-With',
             //'Access-Control-Allow-Credentials' : 'true',
             Authorization : `Bearer ${accessToken}`
           }
         }
        );
        console.log('2');
        if (response.data) {
          setBrandList(response.data.brandNameList.map((brand, index) => ({ name: brand.brandName })));
          console.log(setBrandList);
        }
        console.log('3');
      } catch (error) {
        console.error('Error fetching brand list:', error);
      }
    };

    fetchBrandList();
  }, [location.search]);

  //로컬
  // useEffect(() => {
  //   // 실제 서버로부터 데이터를 가져오는 로직이 구현
  //   // 여기서 categoryName을 사용하여 해당 카테고리의 브랜드 리스트를 가져온다.
  //   const fetchBrandList = async () => {
  //     try {
  //       // 서버로부터 브랜드 리스트 데이터를 가져오는 API 호출
  //       // const response = await fetch(`https://example.com/api/brands/${categoryName}`);
  //       // const data = await response.json();

  //       const data = mockBrandList;

  //       setBrandList(data);
  //     } catch (error) {
  //       console.error('Error fetching brand list:', error);
  //     }
  //   };

  //   fetchBrandList(); // 데이터 가져오는 함수 호출
  // }, [categoryName]);
  //카페 / 양식 / 중식 / 베이커리 /( 닭/오리요리) / (일식/수산물) / 한식 / 퓨전요리 / 패스트푸드 / 분식 / 술안주
  const groupedBrandList = groupCategories(brandList);
  
  const handleSelectButtonClick = (brand) => {
    const brandPriority = priorityMap[brand.name] || null;
  
    if (selectedBrands.includes(brand)) {
      const updatedBrands = selectedBrands.filter(item => item !== brand);
      setSelectedBrands(updatedBrands);
      if (brandPriority) {
        const updatedPriorityMap = { ...priorityMap };
        delete updatedPriorityMap[brand.name];
        setPriorityMap(updatedPriorityMap);
      }
    } else {
      if (selectedBrands.length < 3) {
        setSelectedBrands([...selectedBrands, brand]);
        setPriorityMap({ ...priorityMap, [brand.name]: selectedBrands.length + 1 });
      } else {
        alert('3개의 브랜드까지만 선택 가능합니다!');
      }
    }
  };
  

  const handleMatchingButtonClick = async () => {
    if (selectedBrands.length !== 3) {
      alert('3개의 브랜드를 선택해주세요!');
    } else {
      try {
        const preferBrandList = selectedBrands.map((brand, index) => ({
          brandName: brand.name,
          priority: priorityMap[brand.name],
        }));

        const data = {
          userId: 3, // 사용자 ID는 여기서 예시로 9로 설정
          preferBrandList,
        };
        console.log('보내는 데이터:', data); // 여기서 데이터를 콘솔에 출력
        const response = await axios.post(
          'http://ec2-13-125-45-64.ap-northeast-2.compute.amazonaws.com:8080/matching/run/v1',
          data
        );

        console.log('서버 응답:', response.data);
        navigate('/matching');
      } catch (error) {
        console.error('매칭 요청 실패:', error);
      }
    }
  };
  

  return (
   <>
         <Mobile>
        <MobileContainer>
          <MobileWrapper>
            <p>먹고 싶은 브랜드 순서대로 3개를 선택해주세요!</p>
            <BrandListWrapper>
                    {groupedBrandList.map((group, groupIndex) => (
          <BrandRow key={groupIndex}>
            {group.map((brand, brandIndex) => {
              const brandIsSelected = selectedBrands.includes(brand);
              const selectedIndex = selectedBrands.indexOf(brand);
              const buttonText = brandIsSelected && selectedIndex >= 0 ? `선택 ${selectedIndex + 1}` : '선택하기';

              return (
                <BrandSet key={brandIndex} onClick={() => handleSelectButtonClick(brand)}>
                  <BrandItem>{brand.name}</BrandItem> {/* brand.name 대신 brand.brandName으로 수정 */}
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
        <Toggle />
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
  text-align : center;
  margin-left: 1rem;
  margin-right: 3rem;
  padding: 2.4375rem 0rem 0rem 0rem;
  flex-direction: column;
  height: 6rem;
  width: 8rem;
  border-radius: 0.9375rem;
  font-size: 1rem;
  font-style: normal;
  border : none;
  cursor: pointer;
  position: relative;
  box-shadow: 2px 1px 3px 3px rgba(0, 0, 0, 0.2);
  &:hover {
    button {
      background: #F29788;
    }
  }
`;

const SelectButton = styled.button`
  position : absolute;
  margin-top: 3.5rem;
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
  box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.2);
  &:hover {
    background: #F29788;
  }
`;

const FixedButton = styled.div`
  position: fixed;
  bottom: 20px;


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
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2);
  transition: background-color 0.3s ease-in-out;

  &:hover {
    background: #EDE493;
  }
`;