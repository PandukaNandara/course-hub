import Role from "../constants/Role";

export default interface AuthResponse {
    accessToken: String;
    role: Role;
}