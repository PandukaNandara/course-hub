import AuthResponse from "../models/AuthResponse";

const save = (details: AuthResponse) => {
  const json = JSON.stringify(details);
  localStorage.setItem("auth", json);
};

const getRole = () => {
  const details = localStorage.getItem("auth");
  return details ? (JSON.parse(details)).role : null;
};

const getToken = () => {
  const details = localStorage.getItem("auth");
  return details ? (JSON.parse(details)).accessToken : null;
};

const deleteDetails = () => {
  localStorage.removeItem("auth");
};

const methods = {
  save,
  getRole,
  getToken,
  deleteDetails
};

export default methods;