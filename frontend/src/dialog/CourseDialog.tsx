import {
  Dialog,
  DialogContent,
  DialogTitle,
  DialogActions,
  Button,
  TextField,
  FormControl,
  FormLabel,
  RadioGroup,
  FormControlLabel,
  Radio,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import CourseMode from "../constants/CourseMode";
import Course from "../models/Course";
import CourseService from "../services/CourseService";

interface Props {
  open: boolean;
  onClose: () => void;
  onSubmit: () => void;
  course?: Course;
}

const CourseDialog: React.FC<Props> = ({ onClose, open, onSubmit, course }) => {
  const [code, setCode] = useState(course?.code);
  const [name, setName] = useState(course?.name);
  const [maximumNumberOfStudents, setMaximumNumberOfStudents] = useState(
    course?.maxNumberOfStudent
  );
  const [type, setType] = useState(course?.type);
  const [link, setLink] = useState(course?.link);

  const onSubmitInternal = async (e) => {
      e.preventDefault();
    if (course) {
      await CourseService.update(course.id, {
        code,
        link,
        type,
        maxNumberOfStudent: maximumNumberOfStudents,
        name,
      });
    }else {
      await CourseService.create({
        code,
        link,
        type,
        maxNumberOfStudent: maximumNumberOfStudents,
        name,
      });
    }
    onSubmit();
  };

  useEffect(() => {
    setCode(course?.code);
    setName(course?.name);
    setMaximumNumberOfStudents(course?.maxNumberOfStudent);
    setType(course?.type);
    setLink(course?.link);
  }, [
    course?.code,
    course?.link,
    course?.maxNumberOfStudent,
    course?.name,
    course?.type,
  ]);

  return (
    <form onSubmit={onSubmitInternal}>
      <Dialog open={open} onClose={onClose} fullWidth>
        <DialogTitle>{course ? "Update" : "New"} Course</DialogTitle>
        <DialogContent style={{ display: "flex", flexDirection: "column" }}>
          <TextField
            label="Code"
            name="code"
            className="my-d"
            required
            value={code}
            onChange={(e) => setCode(e.target.value)}
          />
          <TextField
            required
            className="my-d"
            label="Name"
            name="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <TextField
            required
            className="my-d"
            key={maximumNumberOfStudents}
            value={maximumNumberOfStudents}
            onChange={(e) =>
              setMaximumNumberOfStudents(parseInt(e.target.value))
            }
            label="Maximum Number of students"
            type="number"
            name="maxNumberOfStudents"
          />
          <TextField
            required
            className="my-d"
            label={"Link"}
            name="link"
            value={link}
            onChange={(e) => setLink(e.target.value)}
          />
          <FormControl component="fieldset" className="my-d">
            <FormLabel component="legend">Course Type</FormLabel>
            <RadioGroup
              value={type}
              name="type"
              onChange={(e, v) => setType(v as CourseMode)}
            >
              <FormControlLabel
                value={CourseMode.C}
                control={<Radio />}
                label="Compulsory"
              />
              <FormControlLabel
                value={CourseMode.E}
                control={<Radio />}
                label="Elective"
              />
            </RadioGroup>
          </FormControl>
        </DialogContent>
        <DialogActions>
          <Button type="submit" variant="contained" onClick={onSubmitInternal}>
            Submit
          </Button>
          <Button variant="text" onClick={onClose}>
            Cancel
          </Button>
        </DialogActions>
      </Dialog>
    </form>
  );
};

export default CourseDialog;
