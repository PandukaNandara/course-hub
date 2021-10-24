import CourseMode from "../constants/CourseMode";

export default interface UpdateCourseRequest {
  code?: string;
  type?: CourseMode;
  name?: string;
  maxNumberOfStudent?: number;
  link?: string;
}
