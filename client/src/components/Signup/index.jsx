import React, { useContext, useState } from 'react';
import { AuthContext } from '../Login/AuthContext';
import Delishare from '../../assets/imgs/Login_logo.svg'
import styled from 'styled-components'
import { useMediaQuery } from "react-responsive"
import { NavLink, Link, useHistory } from 'react-router-dom';
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
  const validateEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };


export default function Signup() {
  const [nickname, setNickname] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [account_number, setAccount_Number] = useState('');
  const [message, setMessage] = useState('');
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();



  const handleSignup = () => {
    const nicknameRegex = /^\S{2,}$/;
    const passwordRegex = /^(?=.*[a-zA-Z0-9])[A-Za-z\d@$!%*?&]{4,}$/;
    

    if (!nicknameRegex.test(nickname)) {
      window.alert('닉네임은 2자 이상이어야 합니다.');
    } else if (!passwordRegex.test(password)) {
      window.alert('비밀번호는 4자 이상의 문자 혹은 숫자를 포함해야 합니다.');
    } else if (!validateEmail(email)) {
      window.alert('올바른 이메일 형식이 아닙니다.');
    } else {
      window.alert('회원가입이 완료되었습니다.');
      // 서버로 회원가입 정보를 전송하고 처리하는 로직이 추가되어야 함.
      // 회원가입 완료 후 로그인 창으로 이동
      navigate('/login');
    }

    const newMember = { nickname, password, account_number, email };
  };

  return (
   <>
        <Mobile>
        <MobileContainer>
          <MobileWrapper>
          <Logo>
            <Link to="/">
            <img src={Delishare} alt='Logo'></img>  
            </Link> 
          </Logo>
          <SignupSection>
        <SignupForm>
          <p>회원가입</p>
        <InputFieldRow>
        <InputField
        type="text"
        placeholder="닉네임"
        value={nickname}
        onChange={(e) => setNickname(e.target.value)}
      />
      <InputField
        type="email"
        placeholder="이메일 ex)email@naver.com"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <InputField
        type="password"
        placeholder="비밀번호 (문자 혹은 숫자를 포함한 4자 이상)"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <InputField
        type="text"
        placeholder="계좌번호(-포함)"
        value={account_number}
        onChange={(e) => setAccount_Number(e.target.value)}
      />
          
        </InputFieldRow>
        <SignupButton onClick={handleSignup}>
          <p>가입하기</p>
        </SignupButton>
        <p>{message}</p>
        </SignupForm>
          </SignupSection>

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
`

const MobileContainer = styled.div`
display: flex;
flex-direction: column;
justify-content: center;
align-items: center;

`

const MobileWrapper = styled.div`
display: flex;
flex-direction: column;
align-items: center;
width: 22.125rem;
height: 30.625rem;
flex-shrink: 0;
border-radius: 0.9375rem;
background: #FF7062;
margin-top : 10.06rem;
`

const Logo = styled.div`
margin-right : 1rem;
img{
  width: 8.8125rem;
  height: 4.5625rem;
  cursor : pointer;
}

`

const SignupSection = styled.div`
display: flex;
flex-direction: column;
align-items: center;
width: 20.5625rem;
height: 25.3125rem;
flex-shrink: 0;
border-radius: 0.9375rem;
background: #FFF;
`
const SignupForm = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top : 1.5rem;
  p{
    color: #000;
  text-align: center;
  font-size: 1.40625rem;
  font-style: bold;
  font-weight: 400;
  line-height: 130%; 
  margin-bottom : 0.5rem;
}
`;

const InputFieldRow = styled.div`
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
`;

const InputField = styled.input`
  width: 18.75rem;
  height: 3.125rem;
  border-radius: 0.9375rem;
  border: 1px solid #7D7D7D;
  margin-bottom:0.5rem;
  &:nth-child(2) {
    margin-bottom: 0.6rem;
  }

`;


const SignupButton = styled.button`
width: 18.71525rem;
height: 3.0625rem;
margin-top : 1rem;
flex-shrink: 0;
border-radius: 0.9375rem;
background: #FF7062;
border : none;
  cursor: pointer;
  p{
    color: #FFF;
text-align: center;
font-size: 1.09375rem;
font-style: bold;
font-weight: 800;
line-height: 100%; /* 1.09375rem */

  }
  &:hover {
    background: #F29788; /* 버튼 클릭 배경색 변경 */
  }
`;
