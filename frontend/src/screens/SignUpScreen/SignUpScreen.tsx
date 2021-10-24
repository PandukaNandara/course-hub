import { DatePicker } from "@mui/lab";
import { Button, Grid, TextField, Typography } from "@mui/material";
import React, { useState } from "react";

import AdapterDateFns from "@mui/lab/AdapterDateFns";
import LocalizationProvider from "@mui/lab/LocalizationProvider";
import StudentService from "../../services/StudentService";
import AuthStorageUtil from "../../util/AuthStorageUtil";
import { AxiosError } from "axios";
import { history } from "../../history";

const SignUpScreen = () => {
  const [username, setUsername] = useState("");

  const [password, setPassword] = useState("");

  const [name, setName] = useState("");

  const [dob, setDob] = useState<Date>();

  const onSubmit: React.FormEventHandler<any> = async (e) => {
    try {
      const rst = await StudentService.createNewStudent({
        name,
        password,
        username,
        dob: dob ?? new Date(),
      });
      AuthStorageUtil.save(rst.data);
      history.push('/');
    } catch (error) {
      alert(((error as AxiosError).response?.data as any).message);
    }
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
          <Typography variant="h2">Sign Up</Typography>
          <Grid item className="my-d">
            <TextField
              type="name"
              label="Username"
              required
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
            <TextField
              type="name"
              label="Full Name"
              required
              inputProps={{ minLength: 5 }}
              onChange={(e) => setName(e.target.value)}
            />
          </Grid>
          <Grid item className="my-d">
            <LocalizationProvider dateAdapter={AdapterDateFns}>
              <DatePicker
                label="Date of birth"
                value={new Date()}
                onChange={(newValue) => {
                  setDob(newValue as any);
                }}
                renderInput={(params) => <TextField {...params} />}
              />
            </LocalizationProvider>
          </Grid>
          <Grid item className="my-d">
            <Button type="submit" color="primary" variant="contained">
              SIGN UP
            </Button>
          </Grid>
        </Grid>
      </Grid>
    </form>
  );
};

export default SignUpScreen;
