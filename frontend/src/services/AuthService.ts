import axiosService from ".";
import AuthResponse from "../models/AuthResponse";
import CurrentUserDetails from "../models/CurrentUserDetails";
import AuthStorageUtil from "../util/AuthStorageUtil";

const login = (username: string, password: string) => {
  return axiosService.post<AuthResponse>("/auth/login", { username, password });
};

const getCurrentUserDetails = () => {
  if (!AuthStorageUtil.getToken()) return null;
  return axiosService.get<CurrentUserDetails>("/users/me");
};

const methods = {
  login,
  getCurrentUserDetails,
};

export default methods;
