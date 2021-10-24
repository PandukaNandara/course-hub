import axiosService from ".";
import Course from "../models/Course";
import CourseEnrolmentDetail from "../models/CourseEnrolmentDetail";
import UpdateCourseRequest from "../models/UpdateCourseRequest";

const getAll = async () => {
  const data = await axiosService.get<Course[]>("/courses");
  return data;
};

const update = async (
  courseId: string,
  updateCourseRequest: UpdateCourseRequest
) => {
  await axiosService.put(`/courses/${courseId}`, updateCourseRequest);
};


const create = async (
  updateCourseRequest: UpdateCourseRequest
) => {
  await axiosService.post(`/courses`, updateCourseRequest);
};

const get = async (
  courseId: string
) => {
  return await axiosService.get<Course>(`/courses/${courseId}`);
};

const deleteOne = async (
  courseId: string
) => {
  return await axiosService.delete(`/courses/${courseId}`);
};

const enroll = async (courseId: string) => {
  await axiosService.post(`/courses/enrols/${courseId}`);
};

const unenroll = async (courseId: string) => {
  await axiosService.delete(`/courses/enrols/${courseId}`);
};

const getAllEnrollments = async (courseId: string) => {
  return await axiosService.get<CourseEnrolmentDetail[]>(
    `/courses/enrols/${courseId}`
  );
};

const methods = {
  enroll,
  getAll,
  unenroll,
  getAllEnrollments,
  update,
  get,
  deleteOne,
  create
};
export default methods;
