import React from 'react'
import {ReactComponent as Delishare} from '../../assets/imgs/Login_logo.svg'
import styled from 'styled-components'
import { useMediaQuery } from "react-responsive"
import { NavLink, Link } from 'react-router-dom';
import LogoImg from '../../assets/imgs/Delishare_PC.png'


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



export default function LoginMain() {
  return (
   <>
        <Mobile>
          <MobileContainer>
          <MobileWrapper>
          <Logo>
           <Delishare/>
        </Logo>

            <Logo2>
            <MiddleLogo>Share,<br/> Delicious</MiddleLogo>
            </Logo2>
            <LoginWrapper>
              <StyledLink to='/login'>
              <LoginButton>
              <p>로그인</p>
              </LoginButton>
              </StyledLink>
              <StyledLink to='/signup'>
              <SignupButton>
                <p>회원가입</p>
              </SignupButton>
            </StyledLink>
       
            </LoginWrapper>
        

            </MobileWrapper>
          </MobileContainer>

        </Mobile>


        <Pc>
            <PcWrapper>
              <PC_Logo>
              <img src={LogoImg} alt='Logo'></img>  
          </PC_Logo>
          <PC_Logo2>
            <PC_MiddleLogo>Share,<br/> Delicious</PC_MiddleLogo>
            </PC_Logo2>
            <LoginWrapper>
              <StyledLink to='/login'>
              <LoginButton_PC>
              <p>로그인</p>
              </LoginButton_PC>
              </StyledLink>
              <StyledLink to='/signup'>
              <SignupButton_PC>
                <p>회원가입</p>
              </SignupButton_PC>
            </StyledLink>
       
            </LoginWrapper>
            </PcWrapper>   
        </Pc>
   </>
  )
}

const PcWrapper = styled.div`
margin : 0px auto;
flex-shrink: 0;
display : flex;
width : 100%;
height : 100%;
flex-direction : column;
align-items : center;
background: #FF7062;

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
background: #FF7062;
`

const Logo = styled.div`
display : flex;
margin-top : 10.5rem;
width: 13.625rem;
height: 8.25rem;
margin-left : 2rem;
`
const Logo2 = styled.div`
display : flex;

`
const PC_Logo = styled.div`
display : flex;
margin-top : 6rem;
width: 28rem;
height: 14.5rem;
img{
  width : 100%;
  height : 100%;
}
`
const PC_Logo2 = styled.div`
display : flex;
font-size : 5rem;
`


const LoginWrapper = styled.div`
display : flex;
text-align : center;
flex-direction: column;
margin-top : 7.19rem;

`
const PC_MiddleLogo = styled.div`
color: #F8E6E6;
text-align: center;
font-feature-settings: 'clig' off, 'liga' off;
font-family: Domine;
font-size: 4rem;
font-style: normal;
font-weight: 700;
line-height: 100%; /* 1.5rem */
margin-top : 3.8rem;

`
const MiddleLogo = styled.div`
color: #F8E6E6;
text-align: center;
font-feature-settings: 'clig' off, 'liga' off;
font-family: Domine;
font-size: 2rem;
font-style: normal;
font-weight: 700;
line-height: 100%; /* 1.5rem */
margin-top : 3.8rem;

`

const LoginButton = styled.div`
display : flex;
text-align: center;
align-items: center;
flex-direction: column;
width: 18.71525rem;
height: 3.0625rem;

flex-shrink: 0;
border-radius: 0.9375rem;
background: #F1E050;
text-decoration-line : none;

p{
  
  margin-top:0.8rem;
  display : flex;
  text-align: center;
  flex-direction: column;
  text-decoration-line : none;
  width: 5rem;
  height: 1.36113rem;
  flex-shrink: 0;
  color: #040404;
  font-feature-settings: 'clig' off, 'liga' off;
  font-size: 1.5rem;
  font-style: normal;
  font-weight: 400;
  line-height: 100%; 
}
&:hover {
  background: #EDE493; /* 클릭 배경색 변경 */
}

`

const SignupButton = styled.div`
display : flex;
text-align: center;
align-items: center;
margin-top : 1.7rem;
flex-direction: column;
width: 18.71525rem;
height: 3.0625rem;
flex-shrink: 0;
border-radius: 0.9375rem;
background: #F1E050;
text-decoration-line : none;

p{
  margin-top:0.8rem;
  display : flex;
  text-align: center;
  flex-direction: column;
  text-decoration-line: none;
  width: 8rem;
  height: 1.36113rem;
  flex-shrink: 0;
  color: #040404;
  font-size: 1.5rem;
  font-style: normal;
  font-weight: 400;
  line-height: 100%; 
}
&:hover {
  background: #EDE493; /* 클릭 배경색 변경 */
}
`

const LoginButton_PC = styled.div`
display : flex;
text-align: center;
align-items: center;
flex-direction: column;
width: 25rem;
height: 3.0625rem;

flex-shrink: 0;
border-radius: 0.9375rem;
background: #F1E050;
text-decoration-line : none;

p{
  
  margin-top:0.8rem;
  display : flex;
  text-align: center;
  flex-direction: column;
  text-decoration-line : none;
  width: 5rem;
  height: 1.36113rem;
  flex-shrink: 0;
  color: #040404;
  font-feature-settings: 'clig' off, 'liga' off;
  font-size: 1.5rem;
  font-style: normal;
  font-weight: 400;
  line-height: 100%; 
}
&:hover {
  background: #EDE493; /* 클릭 배경색 변경 */
}

`

const SignupButton_PC = styled.div`
display : flex;
text-align: center;
align-items: center;
margin-top : 1.7rem;
flex-direction: column;
width: 25rem;
height: 3.0625rem;
flex-shrink: 0;
border-radius: 0.9375rem;
background: #F1E050;
text-decoration-line : none;

p{
  margin-top:0.8rem;
  display : flex;
  text-align: center;
  flex-direction: column;
  text-decoration-line: none;
  width: 8rem;
  height: 1.36113rem;
  flex-shrink: 0;
  color: #040404;
  font-size: 1.5rem;
  font-style: normal;
  font-weight: 400;
  line-height: 100%; 
}
&:hover {
  background: #EDE493; /* 클릭 배경색 변경 */
}
`

const StyledLink = styled(Link)`
  text-decoration: none; /* 밑줄 제거 */
`;
