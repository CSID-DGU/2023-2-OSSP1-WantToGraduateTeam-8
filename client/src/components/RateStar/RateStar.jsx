//RateStar.jsx
import React, { useState } from 'react'
import styled from 'styled-components'
import OneStar from './OneStar'

export default function RateStar({ sendStarCount }) {
  const countArr = new Array(5).fill(0);
  const [starCnt, setStarCnt] = useState(0);

  const handleStarCnt = (i) => {
    setStarCnt(i + 1); // 선택된 별점 개수 설정
    sendStarCount(i + 1); // 부모 컴포넌트에 선택된 별점 개수 전달
  };
  
  return (
    <RateStarWrapper>
      {countArr.map((_, i) => (
        <OneStar key={i} handleStarCnt={() => handleStarCnt(i)} isFill={i < starCnt} />
      ))}
    </RateStarWrapper>
  );
}

const RateStarWrapper = styled.div`
  display: flex;
  gap: 0.5rem;
  justify-content: center;
  margin-top: 1rem;
`