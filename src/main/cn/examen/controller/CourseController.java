package cn.examen.controller;

import cn.examen.domain.course.College;
import cn.examen.domain.course.Course;
import cn.examen.domain.course.Major;
import cn.examen.service.course.CollegeService;
import cn.examen.service.course.CourseService;
import cn.examen.service.course.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	@Autowired
	CollegeService collegeService;
	@Autowired
	MajorService majorService;
	
	@RequestMapping("/getCollegeId")
	public @ResponseBody
	List<College> getCollegeId() {
		
		List<College> list = collegeService.getCollegeIds();
		return list;
	}
	
	@RequestMapping("/getAllCourseId")
	public @ResponseBody List<Course> getAllCourseId() {
		return courseService.findAllCourseId();
	}
	
	@RequestMapping("/getCollId")
	public @ResponseBody List<College> getCollId() {
		return collegeService.getCollegeIds();
	}
	
	@RequestMapping("/getMajorByCollId")
	public @ResponseBody List<Major> getMajorByCollId(String collId) {
		return majorService.findMajorByCollId(collId);
	}
	
	@RequestMapping("/getCourseByMajorId")
	public @ResponseBody List<Course> getCourseByMajorId(String majorId) {
		return courseService.findCourseByMajorId(majorId);
	}
}
