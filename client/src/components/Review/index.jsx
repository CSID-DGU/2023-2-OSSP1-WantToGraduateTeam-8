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

export default function Review() {

    const [review, submitReview] = useState('');

    const handleSubmit = () => {
      window.alert('개인정보가 수정되었습니다.');
      // 서버로 개인정보를 전송하고 처리하는 로직이 추가되어야 함.
      window.location.href = '/main';
      const newMember = { review };
    };
    
  return (
   <>
        <ReviewDiv>
          <ReviewInfo>
            <Logo>
              <Delishare/>
            </Logo>
            <ReviewInfoDiv>
                <ReviewImg>
          
                </ReviewImg>
                <ReviewNick> (상대방 닉네임) </ReviewNick>
                <ReviewStar>
                  <RateStar/>
                </ReviewStar>
                <OppReview> 리뷰 </OppReview>
                <InputField input type = 'text' value = {review} 
                    onChange = {(e) => submitReview(e.target.value)} 
                    placeholder = '상대방이 어떠하셨나요?'/>
            </ReviewInfoDiv>
            <ReviewBtn onClick={handleSubmit}> 작성완료 </ReviewBtn>
          </ReviewInfo>
        </ReviewDiv>
   </>
  )
}

/* 별점 추후 구현 */

const ReviewDiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height : 100vh;
`

const ReviewInfo = styled.div`
  width: 22.5rem;
  height: 33.75rem;
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
  height: 27.5rem;
  left : 0.975rem;
  top : -2.5rem;
  border-radius : 0.9375rem;    
  background-color : #FFFFFF;
`

const ReviewImg = styled.div`
position : relative;
margin : 2.5rem 5.75rem 0rem 5.75rem;
width : 9rem;
height : 9rem;
top : 1.5rem;
border : 1px solid #000000;
border-radius : 4.5rem;
`

const ReviewNick = styled.p`
position : relative;
top : 2.25rem;
font-weight : bold;
font-size : 1.25rem;
text-align : center;
`

const ReviewStar = styled.div`
position : relative;
margin-left : 5.25rem;
border : none;
top : 2.5rem;
width : 10rem;
height : 2rem;
font-size : 1.25rem;
color : #FFC000;
`

const OppReview = styled.div`
position : relative;
left : 1.75rem;
top : 3.75rem;
color : #FF7062;
font-weight : bold;
`

const InputField = styled.input`
position : relative;
left : 0.925rem;
top : 3.75rem;
width: 16.5rem;
height: 5rem;
border-radius: 1rem;
margin-top : 0.25rem;
padding-left : 1rem;
padding-right : 1rem;
border: 2px solid #7D7D7D;
`

const ReviewBtn = styled.button`
  position : relative;
  width : 18.75rem;
  height : 3rem;
  left : 1.875rem;
  top : -6.375rem;
  color : #FFFFFF;
  font-family : 'Pretendard';
  font-size : 1.125rem;
  background-color : #FF7062;
  border-radius : 0.75rem;
  border : none;
`