import { Button, Grid, Snackbar, TextField, Typography } from "@mui/material";

import React, { FormEventHandler, useState } from "react";

import { history } from "../../history";

import AuthService from "../../services/AuthService";
import AuthStorageUtil from "../../util/AuthStorageUtil";

const LoginScreen: React.FC = () => {
  const [showSnackBarMessage, setShowSnackBarMessage] = useState<string>();

  const [username, setUsername] = useState("");

  const [password, setPassword] = useState("");

  const onSubmit: FormEventHandler<any> = (e) => {
    e.preventDefault();
    AuthService.login(username, password)
      .then((res) => {
        AuthStorageUtil.save(res.data);
        history.push("/");
      })
      .catch((err) => {
        setShowSnackBarMessage(err.message);
      });
  };

  const onClickSignUp = () => {
    history.push("/signup");
  };

  return (
    <form onSubmit={onSubmit}>
      <Grid
        container
        direction="column"
        alignItems="center"
        justifyContent="center"
        style={{ minHeight: "100vh" }}
      >
        <Grid item style={{ textAlign: "center" }}>
          <Typography variant="h2">Course Hub</Typography>
          {/* <img src={logo} alt="logo" width="15%" /> */}
        </Grid>
        <Grid item>
          <Typography variant="h4">LOG IN</Typography>
        </Grid>
        <Grid item className="my-d">
          <TextField
            type="name"
            label="Username"
            onChange={(e) => setUsername(e.target.value)}
          />
        </Grid>
        <Grid item className="my-d">
          <TextField
            type="password"
            label="Password"
            required
            inputProps={{ minLength: 5 }}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Grid>
        <Grid item className="my-d">
          <Button type="submit" color="primary" variant="contained">
            LOGIN
          </Button>
        </Grid>
        <Grid item>
          <Button
            variant="outlined"
            color="primary"
            onClick={() => onClickSignUp()}
          >
            Signup Now
          </Button>
        </Grid>
      </Grid>
      <Snackbar
        anchorOrigin={{
          vertical: "bottom",
          horizontal: "left",
        }}
        open={Boolean(showSnackBarMessage)}
        onClose={() => {
          setTimeout(() => {
            setShowSnackBarMessage(undefined);
          }, 3000);
        }}
        message={showSnackBarMessage}
      />
    </form>
  );
};
export default LoginScreen;
