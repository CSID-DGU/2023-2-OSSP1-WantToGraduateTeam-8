//nickname grade comment
import React, { useContext } from 'react';
import { useState } from "react";
import styled from 'styled-components'
import { useMediaQuery } from 'react-responsive'
import { NavLink, Link } from 'react-router-dom'
import { AuthContext } from '../Login/AuthContext'
import {ReactComponent as Delishare} from '../../assets/imgs/Delishare_mobile_logo.svg'
import SearchImg from '../../assets/imgs/search_image.svg'
import Stars from '../../assets/imgs/ProfileStars.png'
import Toggle from '../Toggle';
import RateStar from '../RateStar/RateStar';
import { useNavigate } from 'react-router-dom';
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
export default function Review() {

  const [comment, setComment] = useState('');
  const navigate = useNavigate();
  const [partnerNames, setPartnerNames] = useState(['User2', 'User3']); // 임시로 닉네임 정의
  const [partnerReviews, setPartnerReviews] = useState(['', '']); // 상대방 리뷰
  const [starCounts, setStarCounts] = useState([0, 0]); // 각 사용자의 별점

  const accessToken = localStorage.getItem('accessToken');
  const handleSubmit = async () => {
    try{
      const dataToSend = 
        {
          nickname_1: partnerNames[0],
          grade_1: starCounts[0],
          comment_1: partnerReviews[0],
          nickname_2: partnerNames[1],
          grade_2: starCounts[1],
          comment_2: partnerReviews[1],
        };

      const response = await axios.post(`http://ec2-13-125-45-64.ap-northeast-2.compute.amazonaws.com:8080/matching/review`,
      {
        nickname_1: partnerNames[0],
        grade_1: starCounts[0],
        comment_1: partnerReviews[0],
        nickname_2: partnerNames[1],
        grade_2: starCounts[1],
        comment_2: partnerReviews[1],
      },
        {
          headers : {
            Authorization : `Bearer ${accessToken}`
          }
        }
      );
      
      alert('리뷰 작성이 완료되었습니다.');
      navigate('/mypage');
      console.log('서버로 전송할 데이터:', dataToSend);
    } catch(error) {
      console.error("Error : ", error.message);
    }
  };

  const receiveStarCount = (count, index) => {
    const updatedStarCounts = [...starCounts];
    updatedStarCounts[index] = count;
    setStarCounts(updatedStarCounts);
  };
    
  return (
   <> 
      <Mobile>
        <ReviewDiv>
          <ReviewInfo>
            <ReviewInfoDiv>
            <ReviewNick> {partnerNames[0]} </ReviewNick>
            <ReviewStar>
              <RateStar sendStarCount={(count) => receiveStarCount(count, 0)} />
              </ReviewStar>
              <OppReview> 리뷰 </OppReview>
              <InputField
                input
                type="textarea"
                placeholder="상대방이 어떠하셨나요?"
                value={partnerReviews[0]}
                onChange={(e) => {
                  const reviews = [...partnerReviews];
                  reviews[0] = e.target.value;
                  setPartnerReviews(reviews);
                }}
              />
              <ReviewNick> {partnerNames[1]} </ReviewNick>
              <ReviewStar>
              <RateStar sendStarCount={(count) => receiveStarCount(count, 1)} />
              </ReviewStar>
              <OppReview> 리뷰 </OppReview>
              <InputField
                input
                type="textarea"
                placeholder="상대방이 어떠하셨나요?"
                value={partnerReviews[1]}
                onChange={(e) => {
                  const reviews = [...partnerReviews];
                  reviews[1] = e.target.value;
                  setPartnerReviews(reviews);
                }}
              />
              <ReviewBtn onClick={handleSubmit}> 작성완료 </ReviewBtn>
            </ReviewInfoDiv>
           
          </ReviewInfo>
        </ReviewDiv>
      </Mobile>


      <Pc>
            <PcWrapper>
            pc
            </PcWrapper>   
        </Pc>
   </>
  )
}

/* 별점 추후 구현 */
const PcWrapper = styled.div`
width: 1920px;
height: 305px;
flex-shrink: 0;
background: #FF4256;
`

const ReviewDiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height : 100vh;
`

const ReviewInfo = styled.div`
  position : relative;
  width: 22.5rem;
  height: 33.75rem;
  padding-bottom : 2rem;
  border-radius : 0.9375rem;    
  background-color : #FF7062;
`

const Logo = styled.div`
  position : relative;
  left : 6.75rem;
` 

const ReviewInfoDiv = styled.div`
  position : relative;
  width: 20.5rem;
  height: 32rem;
  left : 0.975rem;
  
  border-radius : 0.9375rem;    
  background-color : #FFFFFF;
`

// const ReviewImg = styled.div`
// position : relative;
// margin : 2.5rem 5.75rem 0rem 5.75rem;
// width : 9rem;
// height : 9rem;
// top : 1.5rem;
// border : 1px solid #000000;
// border-radius : 4.5rem;
// `

const ReviewNick = styled.p`
position : relative;
margin-top : 1.5rem;
font-weight : bold;
font-size : 1.25rem;
text-align : center;
padding-top : 1rem;
`

const ReviewStar = styled.div`
position : relative;
margin-left : 5.25rem;
margin-top : -1rem;
border : none;
width : 10rem;
height : 2rem;
font-size : 1.25rem;
color : #FFC000;
`

const OppReview = styled.div`
position : relative;
left : 1.75rem;
margin-top:2rem;
color : #FF7062;
font-weight : bold;
`
const InputField = styled.textarea`
  position: relative;
  left: 0.925rem;
  width: 16.5rem;
  min-height: 5rem; /* 최소 높이 */
  max-height: 10rem; /* 최대 높이 */
  resize: vertical; /* 세로 크기 조절 */
  border-radius: 1rem;
  margin-top: 0.25rem;
  padding-left: 1rem;
  padding-right: 1rem;
  border: 2px solid #7D7D7D;
  overflow-y: auto; /* 스크롤바 추가 */
`;

const ReviewBtn = styled.button`
  position : relative;
  width : 18.75rem;
  height : 3rem;
  margin-top : 1rem;
  margin-left : 0.9rem;
  color : #FFFFFF;
  font-family : 'Pretendard';
  font-size : 1.125rem;
  background-color : #FF7062;
  border-radius : 0.75rem;
  border : none;
`