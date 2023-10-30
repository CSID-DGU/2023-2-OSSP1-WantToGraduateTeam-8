import React from 'react'
// import {ReactComponent as Farmely} from '../../assets/imgs/Farmely.svg'
import styled from 'styled-components'
// import {HiOutlineShoppingCart, HiOutlineMenu} from 'react-icons/hi'
import { NavLink, Link } from 'react-router-dom';

export default function Navbar() {
  return (
    <NavbarContainer>
      <Header>
        <Link to='/'>
          {/* <Farmely/> */}
        </Link>
        <LoginBox>
          <NavLink to='/'>
            로그인
          </NavLink>
          {/* <HiOutlineShoppingCart font-size='1.5rem'/> */}
        </LoginBox>
      </Header>
      <Menu>
        {/* <HiOutlineMenu font-size='1.5rem'/> */}
        <NavLink to='/'>
          메뉴
        </NavLink>
        <NavLink to='/'>
          채팅
        </NavLink>
        <NavLink to='/'>
          기타
        </NavLink>
      </Menu>

    </NavbarContainer>
  )
}

const NavbarContainer = styled.div`
  display: flex;
  flex-direction: column;
  height: 10rem;
  align-items: center;
  justify-content: center;
  margin: 0 11rem;
  gap: 3.2rem;

  a {
    text-decoration: none;
  }
`

const Header = styled.div`
  display: flex;

  a{
    font-weight: 500;
    color: #686868;
  }
`


const LoginBox = styled.div`
  display: flex;
  gap: 1.5rem;
  position: absolute;
  right: 11rem;
  align-items: center;
  a{
    color: #686868;
  }
`

const Menu = styled.nav`
  display: flex;
  gap: 12rem;
  justify-content: center;
  align-items: center;
  a{
    font-size: 1.25rem;
    color: black;
    font-weight: 500;
  }

  svg{
    position: absolute;
    left: 11rem;
  }
`

const MypageBox = styled.nav`
  display: flex;
  gap: 1.5rem;
  position: absolute;
  left: 11rem;
  align-items: center
  a{
    font-weight: 500;
    color: #686868;
  }
  

`

