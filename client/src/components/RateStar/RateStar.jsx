import React, { useState } from 'react'
import styled from 'styled-components'
import OneStar from './OneStar'

export default function RateStar() {
  const countArr = new Array(5).fill(0)
  const [starCnt, setStarCnt] = useState()

  const handleStarCnt = (i) => {
    setStarCnt(i)
  }

  return (
    <RateStarWrapper>
      {countArr.map((_, i) => (
        <OneStar key={i} handleStarCnt={() => handleStarCnt(i)} isFill={i < starCnt +1}/>
      ))}
    </RateStarWrapper>
  )
}

const RateStarWrapper = styled.div`
  display: flex;
  gap: 0.5rem;
  justify-content: center;
  margin-top: 1rem;
`