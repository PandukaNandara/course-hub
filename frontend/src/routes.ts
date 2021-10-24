/**
 * defining the routes and its layout. Inspired by a Github
 * project <link>https://github.com/INTECS-ITFAC/intecs_webapp_front-end</link>
 *
 * @author pandu
 */

import { ReactNode } from "react";
import MainLayout from "./layout/MainLayout";
import NoLayout from "./layout/NoLayout";
import AllCourseScreen from "./screens/AllCourseScreen/AllCourseScreen";
import CourseDetails from "./screens/CourseDetails/CourseDetails";
import LoginScreen from "./screens/LoginScreen/LoginScreen";
import SignUpScreen from "./screens/SignUpScreen/SignUpScreen";

interface SubRoute {
  path: string | string[];
  component: React.FC;
  exact: boolean;
}

interface LayoutProps {
  children: ReactNode;
}

interface Routes {
  layout: ({ children }: LayoutProps) => JSX.Element;
  subRoutes: SubRoute[];
}

const routes: Routes[] = [
  {
    layout: NoLayout,
    subRoutes: [
      {
        path: "/login",
        component: LoginScreen,
        exact: true,
      },
      {
        path: "/signup",
        component: SignUpScreen,
        exact: true,
      },
    ],
  },
  {
    layout: MainLayout,
    subRoutes: [
      {
        path: ["/", "/courses"],
        component: AllCourseScreen,
        exact: true,
      },
      {
        path: "/courses/:id",
        component: CourseDetails,
        exact: true,
      },
    ],
  },
];

export default routes;
