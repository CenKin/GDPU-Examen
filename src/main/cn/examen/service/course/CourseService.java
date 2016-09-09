package cn.examen.service.course;

import cn.examen.domain.course.Course;
import cn.examen.domain.course.CourseEx;
import cn.examen.service.BaseService;

import java.util.List;

public interface CourseService extends BaseService<Course>{

	List<Course> findAllCourseId();

	String getCourseNameById(String courseId);

	List<Course> findCourseByMajorId(String majorId);

	List<Course> getByParam(CourseEx courseEx, int offset, int rows);

	Integer getTotalRecordByParam(CourseEx courseEx);

    Course findByCourseName(String courseName);

	String deleteByParam(Course course);
}
