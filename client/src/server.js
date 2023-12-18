const express = require('express');
const React = require('react');
const ReactDOMServer = require('react-dom/server');
const App = require('../src/pages/Chatting/chat'); // 수정이 필요한 부분

const app = express();

app.get('/chat/main', async (req, res) => {
  try {
    // 여기서 로컬 스토리지 또는 쿠키에서 accessToken을 가져옵니다.
    const accessToken = localStorage.getItem('accessToken');

    const htmlContent = ReactDOMServer.renderToString(<App accessToken={accessToken} />); // 여기서 React 컴포넌트를 서버사이드 렌더링합니다.

    res.send(htmlContent);
  } catch (error) {
    res.status(500).send('Internal Server Error');
  }
});

// 서버 시작
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server is listening on port ${PORT}`);
});