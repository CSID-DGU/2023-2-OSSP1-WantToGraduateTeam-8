import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Chat = ({ accessToken }) => {
  const [htmlContent, setHtmlContent] = useState('');

  useEffect(() => {
    const fetchData = async () => {
      try {
        const htmlResponse = await axios.get('http://ec2-13-125-45-64.ap-northeast-2.compute.amazonaws.com:8080/chat/main', {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        });

        setHtmlContent(htmlResponse.data);
      } catch (error) {
        console.error('API 호출 오류:', error);
      }
    };

    fetchData();
  }, [accessToken]);

  return (
    <div dangerouslySetInnerHTML={{ __html: htmlContent }} />
  );
};

export default Chat;
