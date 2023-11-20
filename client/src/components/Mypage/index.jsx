import React from 'react'
import styled from 'styled-components'

export default function Mypage() {
  return (
    <Header>안녕하세요</Header>
  )
}

const Header = styled.div`
  display: flex;
  flex-direction: column;
  height: 10rem;
  border : 1px solid black;
`

