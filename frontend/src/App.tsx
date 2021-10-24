import * as React from "react";
import { Route, Router, Switch,  } from "react-router";
import routes from "./routes";
import { createTheme, CssBaseline, Theme, ThemeProvider } from "@mui/material";
import { history } from "./history";

const primaryColor = {
  light: "#0047b2",
  main: "#0066ff",
  dark: "#3384ff",
  contrastText: "#fff",
};

const secondaryColor = {
  light: "#b26b00",
  main: "#ff9900",
  dark: "#ffad33",
  contrastText: "#000",
};

function App() {
  const [theme, ] = React.useState<Theme>(
    createTheme({
      palette: {
        primary: primaryColor,
        secondary: secondaryColor,
        mode: "dark"
      },
    })
  );

  return (
    <div className="App">
      <ThemeProvider theme={theme}>
        <CssBaseline />

        <Router history={history}>
          <Switch>
            {routes.map((route, i) => {
              let path: string[] = [];
              route.subRoutes.forEach((r) => {
                if (Array.isArray(r.path)) {
                  path = [...path, ...r.path];
                } else {
                  path = [...path, r.path];
                }
              });
              return (
                <Route
                  key={i}
                  exact={route.subRoutes.some((r) => r.exact)}
                  path={path}
                >
                  <route.layout>
                    {route.subRoutes.map((subRoute, i) => (
                      <Route {...subRoute} key={i} />
                    ))}
                  </route.layout>
                </Route>
              );
            })}
          </Switch>
        </Router>
      </ThemeProvider>
    </div>
  );
}

export default App;
