import axiosService from ".";
import AuthResponse from "../models/AuthResponse";
import NewStudentRequest from "../models/NewStudentRequest";
import StudentUpdateRequest from "../models/UpdateStudentRequest";

const createNewStudent = async (body: NewStudentRequest) => {
  return await axiosService.post<AuthResponse>("/students/new", body);
};

const deleteStudent = async (id: string) => {
  return await axiosService.delete(`/students/${id}`);
};

const updateStudent = async (
  id: string,
  studentUpdateRequest: StudentUpdateRequest
) => {
  return await axiosService.patch(`/students/${id}`, studentUpdateRequest);
};

export default {
  createNewStudent,
  deleteStudent,
  updateStudent,
};
