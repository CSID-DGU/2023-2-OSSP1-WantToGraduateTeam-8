import React, { useState } from 'react';
import styled from 'styled-components';
import { FaEllipsisV } from 'react-icons/fa';
import { NavLink } from 'react-router-dom';
import { TiThMenu } from "react-icons/ti";
import { IoMdHome } from "react-icons/io";
import { IoChatboxEllipsesOutline } from "react-icons/io5";
import { CgProfile } from "react-icons/cg";



const Toggle = () => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  return (
    <FloatingMenuContainer>
      <ToggleButton onClick={toggleMenu} isOpen={isOpen}>
        <TiThMenu size={30} />
      </ToggleButton>
      <MenuList isOpen={isOpen}>
        <MenuItem>
          <NavLink to="/main">
            <IoMdHome size={44} color="#fff" />
          </NavLink>
        </MenuItem>
        <MenuItem>
          <NavLink to="/chatting">
            <IoChatboxEllipsesOutline size={45} color="#fff" />
          </NavLink>
        </MenuItem>
        <MenuItem>
          <NavLink to="/mypage">
            <CgProfile size={44} color="#fff" />
          </NavLink>
        </MenuItem>
      </MenuList>
    </FloatingMenuContainer>
  );
};

const FloatingMenuContainer = styled.div`
  position: fixed;
  bottom: 20px;
  right: 20px;
`;

const ToggleButton = styled.button`
  width: 4rem;
  height: 4rem;
  border-radius: 50%;
  background-color: #ff7062;
  border: none;
  color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom : 2rem;
  font-size: 24px;
  cursor: pointer;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2);
  transition: transform 0.3s ease-in-out;
`;

const MenuList = styled.ul`
  list-style-type: none;
  padding: 0;
  position: absolute;
  top: ${({ isOpen }) => (isOpen ? '-240px' : '50px')};
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
  transition: all 0.3s ease-in-out;
  opacity: ${({ isOpen }) => (isOpen ? '1' : '0')};
  transform: translateY(${({ isOpen }) => (isOpen ? '0' : '-10px')});
`;

const MenuItem = styled.li`
  background-color: #ff9489;
  display: flex;
  flex-direction: column;
  padding: 10px;
  border-radius: 100%;
  margin-bottom: 10px;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2);
  width: 3rem;
  height: 3rem;
  cursor: pointer;
  a {
    text-decoration: none;
    color: black;
  }
`;

export default Toggle;