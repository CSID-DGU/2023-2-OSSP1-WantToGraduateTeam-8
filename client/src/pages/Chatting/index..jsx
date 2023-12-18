import React, { useState, useEffect } from 'react';
import styled from 'styled-components'
import { useMediaQuery } from "react-responsive"
import { NavLink, Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import Toggle from '../../components/Toggle';
import RateStar from '../../components/RateStar/RateStar';

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

export default function Chatting() {
  const [messages, setMessages] = useState([]);
  const [message, setMessage] = useState('');
  const currentUserId = 'myUserId'; // 현재 사용자의 ID
  const roomName = 'Test Room'; // 채팅방 이름

  const sendMessage = () => {
    if (message.trim() !== '') {
      setMessages([
        ...messages,
        {
          senderId: currentUserId,
          nickname: 'MyNickname',
          message: message.trim(),
        },
        {
          senderId: 'otherUserId',
          nickname: 'Other UserNickname',
          message: '다른사람 메세지',
        },
      ]);
      setMessage('');
    }
  };

    return (
       <>
        <Mobile>
         <MobileContainer>
      <MobileWrapper>
        <ChatWrapper>
          <Header>
            <Room>{roomName}</Room>
          </Header>
          <MessageList>
            {messages.map((message, index) => (
              <MessageItem key={index} isMine={message.senderId === currentUserId}>
                <Sender isMine={message.senderId === currentUserId}>
                  {message.senderId === currentUserId ? message.nickname : 'Other User'}
                </Sender>
                <MessageBubble isMine={message.senderId === currentUserId}>{message.message}</MessageBubble>
              </MessageItem>
            ))}
          </MessageList>
        </ChatWrapper>
        <ButtonSection>
          <ExitButton>나가기</ExitButton>
          <FormInput
            type="text"
            value={message}
            onChange={(e) => setMessage(e.target.value)}
            onKeyPress={(e) => e.key === 'Enter' && sendMessage()}
          />
          <SendButton onClick={sendMessage}>&#10148;</SendButton>
        </ButtonSection>
      </MobileWrapper>
    </MobileContainer>
    <Toggle />
        </Mobile>


        <Pc>
            <PcWrapper>
            pc
            </PcWrapper>   
        </Pc>
   </>
    );
}
const PcWrapper = styled.div`
width: 1920px;
height: 305px;
flex-shrink: 0;
`
const MobileContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const MobileWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 24.375rem;
  height: 52.75rem;
  flex-shrink: 0;
  background: #fff;
`;

const ChatWrapper = styled.div`
  display: flex;
  flex-direction: column;
  position: relative;
  width: 20.8125rem;
  height: 40.5625rem;
  border-radius: 0.9375rem;
  border: 2px solid #ff7062;
  text-align: right;
  background: #fff;
  margin-top: 1.25rem;
  overflow-y: auto;
`;

const Header = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Room = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 11rem;
  border-bottom: 2px solid #7d7d7d;
  color: #000;
  font-size: 1rem;
  font-style: normal;
  font-weight: 600;
`;

const ButtonSection = styled.div`
  display: flex;
  flex-direction: row;
  padding: 0.5rem;
  width: 20rem;
  height: 2.375rem;
  flex-shrink: 0;
  border-radius: 0.9375rem;
  border: 1px solid rgba(0, 0, 0, 0.2);
  background: #ff7062;
`;

const FormInput = styled.input`
  width: 13.6875rem;
  height: 2.125rem;
  flex-shrink: 0;
  border-radius: 1.875rem;
`;

const SendButton = styled.button`
  margin-top: 0.2rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  position: relative;
  justify-content: center;
  border-radius: 50%;
  background-color: #f1e050;
  border: 1.2px solid black;
  height: 2rem;
  width: 2em;
  cursor: pointer;
  font-size: 1.1rem;
  font-style: normal;
  font-weight: 600;
`;

const ExitButton = styled.button`
  margin-top: 0.2rem;
  display: flex;
  padding-top: 0.4rem;
  flex-direction: column;
  align-items: center;
  text-align: center;
  position: relative;
  border-radius: 0.5rem;
  background-color: #f1e050;
  border: 1.2px solid black;
  height: 2rem;
  width: 4.5em;
  cursor: pointer;
  font-size: 0.8rem;
  font-style: normal;
  font-weight: 600;
`;

const MessageList = styled.ul`
  display: flex;
  flex-direction: column;
  position: relative;
  padding: 0;
  margin: 0;
`;

const MessageItem = styled.li`
  display: flex;
  flex-direction: column;
  align-items: ${(props) => (props.isMine ? 'flex-end' : 'flex-start')};
`;

const Sender = styled.div`
  margin-top: ${(props) => (props.isMine ? '0.5rem' : '0')};
  margin-left: ${(props) => (props.isMine ? 'auto' : '0')};
  font-size: 0.8rem;
  font-weight: 600;
`;

const MessageBubble = styled.div`
  display: inline-block;
  padding: 0.5rem;
  margin-top: 0.25rem;
  border-radius: 1.875rem;
  font-size: 0.8rem;
  font-style: normal;
  font-weight: 600;
  background-color: ${(props) => (props.isMine ? '#f1e050' : '#bcf0a9')};
`;
