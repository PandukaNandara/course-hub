import {
  AppBar,
  Avatar,
  Button,
  IconButton,
  Toolbar,
  Typography,
} from "@mui/material";
import { Box } from "@mui/system";
import React, { useEffect, useState } from "react";
import CurrentUserDetails from "../models/CurrentUserDetails";
import UserContext from "../context/UserContext";
import AuthService from "../services/AuthService";
import AuthStorageUtil from "../util/AuthStorageUtil";
import { history } from "../history";

const MainLayout = ({ children }): JSX.Element => {
  const [currentUserDetails, setCurrentUserDetails] =
    useState<CurrentUserDetails>();
  useEffect(() => {
    AuthService.getCurrentUserDetails()?.then((currentUserDetails) => {
      setCurrentUserDetails(currentUserDetails.data);
    });
  }, []);
  const onClickLogout = () => {
    AuthStorageUtil.deleteDetails();
    window.location.reload();
  };

  const onClickLogin = () => {
    history.push("/login");
  };

  return (
    <>
      <UserContext.Provider value={currentUserDetails}>
        <Box sx={{ flexGrow: 1 }} height="65px">
          <AppBar position="static" className="app-bar">
            <Toolbar>
              <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                COURSE HUB
              </Typography>

              {currentUserDetails && (
                <Button onClick={onClickLogout}>LOG OUT</Button>
              )}
              {!currentUserDetails && (
                <Button onClick={onClickLogin}>LOG IN</Button>
              )}
              <IconButton component="span">
                <Avatar
                  src={
                    currentUserDetails?.name &&
                    `https://avatars.dicebear.com/api/initials/${currentUserDetails.name}.svg`
                  }
                />
              </IconButton>
            </Toolbar>
          </AppBar>
        </Box>

        <main>{children}</main>
      </UserContext.Provider>
    </>
  );
};

export default MainLayout;
