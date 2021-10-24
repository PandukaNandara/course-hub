import {
  Button,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography,
} from "@mui/material";
import React, { useContext, useEffect, useState } from "react";
import { useParams } from "react-router";
import Role from "../../constants/Role";
import UserContext from "../../context/UserContext";
import CourseDialog from "../../dialog/CourseDialog";
import { YesNoDialog } from "../../dialog/YesNoDialog";
import { history } from "../../history";
import Course from "../../models/Course";
import CourseEnrolmentDetail from "../../models/CourseEnrolmentDetail";
import CourseService from "../../services/CourseService";

const CourseDetails = () => {
  const userContext = useContext(UserContext);

  const [details, setDetails] = useState<CourseEnrolmentDetail[]>([]);
  const [course, setCourse] = useState<Course>();
  const [openUpdate, setOpenUpdate] = useState(false);
  const [openDelete, setOpenDelete] = useState(false);

  const { id } = useParams<{ id: string }>();

  useEffect(() => {
    if (userContext?.role === Role.ADMIN)
      if (id) {
        CourseService.get(id).then((course) => {
          setCourse(course.data);
        });
        CourseService.getAllEnrollments(id).then((details) => {
          setDetails(details.data);
        });
      }
  }, []);

  const onPressDelete = async () => {
    if (id) {
      await CourseService.deleteOne(id); 
      setOpenDelete(false);
      history.replace("/courses");
    }
  };
  

  return (
    <>
      {userContext?.role !== Role.ADMIN ? (
        <Typography variant="h6">401 Unauthorized</Typography>
      ) : (
        <div className="m-d">
          <Button onClick={() => setOpenUpdate(true)}>Update</Button>
          <Button onClick={() => setOpenDelete(true)}>Delete</Button>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Student ID</TableCell>
                <TableCell>Student Name</TableCell>
                <TableCell>Joined Date</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {details.map((detail) => (
                <TableRow key={detail.student.id}>
                  <TableCell>{detail.student.id}</TableCell>
                  <TableCell>{detail.student.name}</TableCell>
                  <TableCell>{detail.date.toString()}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </div>
      )}
      <CourseDialog
        open={openUpdate}
        onClose={() => setOpenUpdate(false)}
        onSubmit={() => setOpenUpdate(false)}
        course={course}
      />
      <YesNoDialog
        open={openDelete}
        content="Do you want to delete?"
        title="Warning"
        noHandle={() => setOpenDelete(false)}
        yesHandle={onPressDelete}
      />
    </>
  );
};

export default CourseDetails;
