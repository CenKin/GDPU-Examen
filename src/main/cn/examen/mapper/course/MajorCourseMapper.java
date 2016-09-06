package cn.examen.mapper.course;

import cn.examen.domain.course.MajorCourse;
import cn.examen.mapper.BaseMapper;

public interface MajorCourseMapper extends BaseMapper<MajorCourse>{

    MajorCourse findOne(MajorCourse majorCourse);
}