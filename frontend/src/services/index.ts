import Axios from "axios";
import AuthStorageUtil from "../util/AuthStorageUtil";

const IP = "http://localhost";
const PORT = "8080";
export const BASE_URL = `${IP}:${PORT}/api`;

const axiosService = Axios.create({
  baseURL: BASE_URL,
  timeout: 60000,
  withCredentials: true,
});

axiosService.interceptors.request.use((config) => {
  const accessToken = AuthStorageUtil.getToken();

  if (accessToken && config?.headers) {
    config.headers.Authorization = `Bearer ${accessToken}`;
  }
  return config;
});

export default axiosService;
