import React, { useContext, useState } from 'react';
import { AuthContext } from './AuthContext';
import Delishare from '../../assets/imgs/Login_logo.svg'
import styled from 'styled-components'
import { useMediaQuery } from "react-responsive"
import { NavLink, Link, useHistory } from 'react-router-dom';
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

  export default function Login() {
    const { login } = useContext(AuthContext);
    const { logout } = useContext(AuthContext);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();


    const handleLogin = async (e) => {
      e.preventDefault();
      
      if(email === ""){
        alert("아이디를 입력해주세요.");
      } else if (password === "") {
        alert("비밀번호를 입력해주세요.")
      } else {
        try {
          const res = await axios.post("http://ec2-13-125-45-64.ap-northeast-2.compute.amazonaws.com:8080/user/login", 
          {
            email, 
            password,
          }
          );
          // alert(JSON.stringify(res.data));
         const accessToken = res.data.accessToken;

      // 토큰 저장
          localStorage.setItem("accessToken", accessToken);
          //axios.defaults.headers.common['Authorization'] = accessToken;
          login();
          navigate('/main');
        } catch (err) {
          navigate('/login');
          alert("로그인 실패" , err);
        }
      }
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
          <LoginSection>
            <b>이메일 로그인</b>
        <LoginForm>
          <InputField input type="text" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="이메일 ex)user@naver.com" />
          <InputField input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="비밀번호" />
          <LoginButton onClick={handleLogin}><p>로그인</p></LoginButton>
        </LoginForm>
          </LoginSection>
          <RegisterLink to="/signup">회원가입</RegisterLink>
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

export const setInterceptor = (token) => {

  if (!token) return false
  axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
  return true

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
img{
  width: 8.8125rem;
  height: 4.5625rem;
  cursor : pointer;

}
`

const LoginSection = styled.div`
display: flex;
flex-direction: column;
align-items: center;
width: 20.5625rem;
height: 21.25rem;
flex-shrink: 0;
border-radius: 0.9375rem;
background: #FFF;
b{
    color: #000;
  text-align: center;
  font-size: 1.40625rem;
  font-style: bold;
  font-weight: 400;
  line-height: 130%; /* 1.82813rem */
  margin-top : 2.94rem;
}
`
const LoginForm = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 2rem;
`;

const InputField = styled.input`
  width: 18.25rem;
  height: 3.125rem;
  border-radius: 0.9375rem;
  margin-bottom : 0.3rem;
  border: 1px solid #7D7D7D;
  padding-left : 0.5rem;
  font-size : 1.0625rem;
`;

const LoginButton = styled.button`
width: 18.71525rem;
height: 3.0625rem;
margin-top : 2rem;
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
line-height: 100%; 

  }
  &:hover {
    background: #F29788; /* 버튼 클릭 배경색 변경 */
  }
`;

const RegisterLink = styled(NavLink)`
  margin-top: 1.8rem;
  color: #FFF;
text-align: center;
font-size: 0.9375rem;
font-style: normal;
font-weight: 400;
line-height: 100%; /* 0.9375rem */

 
`;