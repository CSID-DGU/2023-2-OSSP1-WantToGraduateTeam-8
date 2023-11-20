import React, { useContext, useState } from 'react';
import { AuthContext } from './AuthContext';
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



  export default function Login() {
    const { login } = useContext(AuthContext);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
  
    const handleLogin = () => {
      const correctUsername = 'abcd'; // 실제 아이디
      const correctPassword = '1234'; // 실제 비밀번호
  
      if (username === correctUsername && password === correctPassword) {
        login(); // AuthContext의 login 함수를 호출하여 사용자 로그인 상태를 변경합니다.
  
        // 로그인 성공 시 MainPage로 이동합니다.
        navigate('/main');
      } else {
        alert('아이디 혹은 비밀번호가 틀립니다.');
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
            <b>ID 로그인</b>
        <LoginForm>
          <InputField input type="text" value={username} onChange={(e) => setUsername(e.target.value)} placeholder="아이디" />
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
margin-right : 1rem;
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
  width: 18.75rem;
  height: 3.125rem;
  border-radius: 0.9375rem;
  margin-bottom : 0.3rem;
  border: 1px solid #7D7D7D;
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
line-height: 100%; /* 1.09375rem */

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