import {
  Button,
  Card,
  CardActionArea,
  CardContent,
  Chip,
  CircularProgress,
  Grid,
  Typography,
} from "@mui/material";
import { Box } from "@mui/system";
import React, { useContext, useState } from "react";
import CourseMode from "../../constants/CourseMode";
import Role from "../../constants/Role";
import UserContext from "../../context/UserContext";
import CourseDialog from "../../dialog/CourseDialog";
import { history } from "../../history";
import Course from "../../models/Course";
import CourseService from "../../services/CourseService";
import AuthStorageUtil from "../../util/AuthStorageUtil";

const AllCourseScreen = () => {
  const [courses, setCourses] = useState<Course[]>();
  React.useEffect(() => {
    CourseService.getAll().then((courses) => {
      setCourses(courses.data);
    });
  }, []);
  const isAdmin = AuthStorageUtil.getRole() === Role.ADMIN;

  const onclickCourse = (id: string) => {
    history.push("/courses/" + id);
  };

  const [openUpdate, setOpenUpdate] = useState(false);
  const onClickEnroll = async (course: Course) => {
    if (!AuthStorageUtil.getRole()) {
      history.push("/login");
    } else if (AuthStorageUtil.getRole() === Role.STUDENT) {
      try {
        if (course.isEnrolled) await CourseService.unenroll(course.id);
        else await CourseService.enroll(course.id);
        const isEnrolled = !course.isEnrolled;
        setCourses(
          courses?.map((val) =>
            val.id === course.id ? { ...val, isEnrolled } : val
          )
        );
      } catch (e) {
        console.error(e);
      }
    }
  };

  const userContext = useContext(UserContext);
  return (
    <div>
      {!Boolean(courses) ? (
        <>
          <CircularProgress />
        </>
      ) : (
        <div>
          {userContext?.role === Role.ADMIN && (
            <Button className="m-d" onClick={() => setOpenUpdate(true)}>
              + New Course
            </Button>
          )}
          <Grid container alignItems="stretch">
            {courses?.map((course) => {
              const content = (
                <>
                  <CardContent>
                    <Typography variant="h5">{course.name}</Typography>
                    <Box display="flex" alignItems="center" marginY={"1px"}>
                      <Typography
                        className="m-r"
                        variant="subtitle1"
                        color="text.secondary"
                      >
                        {course.code}
                      </Typography>
                      {course.type === CourseMode.C ? (
                        <Chip
                          label="Compulsory"
                          color="error"
                          variant="outlined"
                        />
                      ) : (
                        <Chip
                          label="Elective"
                          color="success"
                          variant="outlined"
                        />
                      )}
                    </Box>
                  </CardContent>
                </>
              );
              return (
                <Grid item xs={6} md={4} lg={2} key={course.id}>
                  <Card className="card" style={{ height: "204px" }}>
                    {isAdmin ? (
                      <CardActionArea
                        style={{ height: "204px" }}
                        onClick={() => onclickCourse(course.id)}
                      >
                        <>{content}</>
                      </CardActionArea>
                    ) : (
                      <>{content}</>
                    )}
                    {!isAdmin && (
                      <Button onClick={() => onClickEnroll(course)}>
                        {course.isEnrolled ? "Unenroll" : "Enroll"}
                      </Button>
                    )}
                  </Card>
                </Grid>
              );
            })}
          </Grid>
          <CourseDialog
            open={openUpdate}
            onClose={() => setOpenUpdate(false)}
            onSubmit={() => {
              setOpenUpdate(false);
              CourseService.getAll().then((courses) => {
                setCourses(courses.data);
              });
            }}
          />
        </div>
      )}
    </div>
  );
};

export default AllCourseScreen;
