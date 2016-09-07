package cn.examen.service.impl.course;

import cn.examen.domain.course.*;
import cn.examen.mapper.course.CollegeMapper;
import cn.examen.mapper.course.CourseMapper;
import cn.examen.mapper.course.MajorCourseMapper;
import cn.examen.mapper.course.MajorMapper;
import cn.examen.service.course.CourseService;
import cn.examen.utils.BeanUtils2;
import cn.examen.utils.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseMapper courseMapper;
	@Autowired
	CollegeMapper collegeMapper;
	@Autowired
	MajorCourseMapper majorCourseMapper;
	@Autowired
	MajorMapper majorMapper;
	
	@Override
	public List<Course> findAllCourseId() {
		return courseMapper.findAll(null, null);
	}

	@Override
	public String getCourseNameById(String courseId) {
		Course c = courseMapper.findById(courseId);
		return c.getCourseName();
	}

	@Override
	public List<Course> findCourseByMajorId(String majorId) {
		return courseMapper.findListByMajorId(majorId, null, null);
	}

	@Override
	public List<Course> getByParam(CourseEx courseEx, int offset, int rows) {
        Map<String, Object> condition = BeanUtils2.transBean2Map(courseEx);
        List<Course> courses = courseMapper.like(condition, (offset-1)*rows, rows, "courseName", "ASC");
        return courses;
	}

	@Override
	public Integer getTotalRecordByParam(CourseEx courseEx) {
        Integer totalRecord = courseMapper.countLike(courseEx);
        return totalRecord==null? 0 : totalRecord;
	}

    @Override
    public Course findByCourseName(String courseName) {
        return courseMapper.findOne("courseName", courseName);
    }

    @Override
	public String addOne(Course param, Object...objects) {
        String majorId = null;
        if(objects!=null){
            majorId = (String) objects[0];
        }
		int code = courseMapper.insert(param);
		if (code == 1 && majorId!=null) {
			MajorCourse majorCourse = new MajorCourse();
			majorCourse.setMajorId(majorId);
			majorCourse.setCourseId(param.getCourseId());
			code = majorCourseMapper.insert(majorCourse);
			return code==1 ? StateCode.ADD_SUCCESS : StateCode.ADD_FAIL;
		}
		return StateCode.ADD_FAIL;
	}

	@Override
	public Integer getTotalRecordByParam(Course param) {
		Integer totalRecord = courseMapper.countLike(param);
		return totalRecord==null? 0 : totalRecord;
	}

    @Override
    public Course getById(String id) {
        return courseMapper.findOne("courseId", id);
    }

	@Override
	public List<Course> getPageListByParam(Course param, int offset, int rows) {
		Map<String, Object> condition = BeanUtils2.transBean2Map(param);
		return courseMapper.like(condition, offset, (rows-1)*offset, "courseName", "ASC");
	}
}
