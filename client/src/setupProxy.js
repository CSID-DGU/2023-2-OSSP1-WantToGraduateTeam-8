const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://ec2-13-125-45-64.ap-northeast-2.compute.amazonaws.com:8080', // 실제 백엔드 주소
      changeOrigin: true,
    })
  );
};
