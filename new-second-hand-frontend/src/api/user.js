import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/user'; // 后端接口地址

export function register(data) {
  return axios.post(`${BASE_URL}/register`, data);
}

export function login(data) {
  return axios.post(`${BASE_URL}/login`, data);
}

export function sendPhoneCode(data) {
  return axios.post(`${BASE_URL}/sendPhoneCode`, data);
}

export function sendEmailCode(data) {
  return axios.post(`${BASE_URL}/sendEmailCode`, data);
}