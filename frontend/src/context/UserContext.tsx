
import { createContext } from "react";
import CurrentUserDetails from "../models/CurrentUserDetails";


const UserContext = createContext<CurrentUserDetails | undefined>(undefined);

export default UserContext;