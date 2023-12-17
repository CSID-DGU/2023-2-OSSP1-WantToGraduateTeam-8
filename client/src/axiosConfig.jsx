// axiosConfig.js

import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://ec2-13-125-45-64.ap-northeast-2.compute.amazonaws.com:8080/',
  // 다른 설정들을 여기에 추가할 수 있습니다.
});

export const setInterceptor = (token) => {
  if (!token) return false;
  instance.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  return true;
};

instance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('accessToken');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default instance;
