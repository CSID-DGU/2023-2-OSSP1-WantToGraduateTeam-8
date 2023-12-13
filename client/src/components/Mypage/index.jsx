import React, { useContext } from 'react';
import { useState, useEffect} from "react";
import styled from 'styled-components'
import { useMediaQuery } from 'react-responsive'
import { NavLink, Link, useNavigate } from 'react-router-dom'
import { AuthContext } from '../Login/AuthContext'
import {ReactComponent as Delishare} from '../../assets/imgs/Delishare_mobile_logo.svg'
import SearchImg from '../../assets/imgs/search_image.svg'
import Stars from '../../assets/imgs/ProfileStars.png'
//import ProfileStars from '../../assets/imgs/ProfileStars.png'
import Toggle from '../Toggle';
import RateStar from '../RateStar/RateStar';
import axios from 'axios';
import ProfileImg1 from '../../assets/imgs/ProfileImg.png'

export default function Mypage() {
  const [nickname, resetNickname] = useState('');
  const [account_number, resetaccount_number] = useState('');
  const [password, resetPassword] = useState('');
  const { logout } = useContext(AuthContext);

  const handleRevise = () => {
    const accountnumRegex = /^(?=[0-9]){16}$/;
    const passwordRegex = /^(?=.*[a-zA-Z0-9])[A-Za-z\d@$!%*?&]{4,}$/; 

    if (!accountnumRegex.test(accountnum)) {
      window.alert('계좌번호는 숫자만 입력해야 합니다.');
    } else if (!passwordRegex.test(password)) {
      window.alert('비밀번호는 4자 이상의 문자 혹은 숫자를 포함해야 합니다.');
    } else {
      window.alert('개인정보가 수정되었습니다.');
      // 서버로 개인정보를 전송하고 처리하는 로직이 추가되어야 함.
  
    }

    const newMember = { nickname, account_number, password };
  };

  const handleLogout = () => {
    logout(); // 로그아웃 함수 호출
    navigate('/'); // '/main' 페이지로 이동
  };

  return (
    <MypageDiv>
      <ProfileInfoDiv>
        <ProfileText> MY PROFILE </ProfileText>
        <ProfileImg>
          <img src ={ProfileImg1}></img>
        </ProfileImg>
        <ProfileNick>{userData.nickname}</ProfileNick>
        <ReviewStar>
        <StarRating grade={userData.grade} />
          </ReviewStar>
        <ProfileAccnt> 계좌번호: {userData.account_number}</ProfileAccnt>
      </ProfileInfoDiv>
      <ProfileReviseDiv>
        <MyProfile> 내 프로필 </MyProfile>
        <InputField input type = 'text' value = {nickname} onChange = {(e) => resetNickname(e.target.value)} placeholder = '본인 닉네임'/>
        <InputField  input type = 'text' value = {account_number} onChange = {(e) => resetaccount_number(e.target.value)} placeholder = '계좌번호'/>
        <SecuritySet> 보안설정 </SecuritySet>
        <InputField input type = 'password' value = {password} onChange = {(e) => resetPassword(e.target.value)} placeholder = '비밀번호 재설정'/>
        <MyReview> 내 리뷰 </MyReview>
        <MyReviewDiv><a>{userData.comment}</a></MyReviewDiv>
      </ProfileReviseDiv>
      <ProfileReviseBtn onClick={handleRevise}> 수정 </ProfileReviseBtn>
      <Toggle/>
    </MypageDiv>
  )
}

const StarRating = ({ grade }) => {
  // grade 값에 따라 별점을 표시하는 방식으로 변경
  // 이 예시에서는 5점 척도로 가정하여 grade 값에 따라 별을 표시합니다.
  const stars = '⭐'.repeat(grade);

  return <div>{stars}</div>;
};


const MypageDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`


  const ReviewStar = styled.div`
  position : relative;
  margin-left : 1.4rem;
  border : none;
  width : 10rem;
  height : 2rem;
  font-size : 1.25rem;
  color : #FFC000;
  `


const ProfileInfoDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin : 0.9375rem;
  width : 21.25rem;
  height : 21.25rem;
  border : 1px solid #000000;
  border-radius : 0.75rem;
`

const ProfileText = styled.p`
  position : relative;
  
  font-weight : bold;
  font-size : 1.45rem;
  text-align : center;
`

const ProfileImg = styled.div`
  display : flex;
  flex-direction : column;

  width : 9rem;
  height : 9rem;
  border : 1px solid #000000;
  border-radius : 4.5rem;
`

const ProfileNick = styled.p`
  margin : 0.875rem 0 0.5rem 0;
  text-align : center;
  font-size : 1.125rem;
`

const ProfileStar = styled.div`
  margin-left : 5.625rem;
  width : 10rem;
  height : 2rem;
`

const ProfileAccnt = styled.p`
  margin : 1rem 1.375rem 1.375rem 1.375rem;
  text-align : center;
  font-size : 1.125rem;
`

const ProfileReviseDiv = styled.div`
  margin : 0rem 0.9375rem 0.9375rem 0.9375rem;
  width : 21.25rem;
  height : 30rem;
  border : 1px solid #000000;
  border-radius : 0.75rem;
  color : #FF7062;
`

const MyProfile = styled.div`
  text-size : 1rem;
  margin-top : 1.25rem;
  margin-left : 2rem;
`

const SecuritySet = styled.div`
  text-size : 1rem;
  margin-top : 1.25rem;
  margin-left : 2rem;
`

const InputField = styled.input`
position : relative;
left : 1.125rem;
width: 16.875rem;
height: 2.875rem;
border-radius: 1rem;
margin-top : 0.25rem;
padding-left : 1rem;
padding-right : 1rem;
border: 2px solid #7D7D7D;
font-size : 1.0625rem;
`

const MyReview = styled.div`
  text-size : 1rem;
  margin-top : 1.25rem;
  margin-left : 2.25rem;
`

const MyReviewDiv = styled.div`
  margin : 0.25rem auto;
  width : 18.75rem;
  padding-top : 1rem;
  height : 11.25rem;
  border : 2px solid #7D7D7D;
  border-radius : 1rem;
  color : black;
`

const ProfileReviseBtn = styled.button`
  width : 21.25rem;
  height : 3rem;
  color : #FFFFFF;
  font-family : 'Pretendard';
  font-size : 1.125rem;
  background-color : #FF7062;
  border-radius : 0.75rem;
  border : none;
`