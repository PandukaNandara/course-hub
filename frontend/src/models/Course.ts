import CourseMode from "../constants/CourseMode";

export default interface Course {
    id: string;
    code: string;
    type: CourseMode;
    name: string;
    maxNumberOfStudent: number;
    link: string;
    isEnrolled?: boolean;
}