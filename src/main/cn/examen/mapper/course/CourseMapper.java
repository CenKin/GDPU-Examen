package cn.examen.mapper.course;

import cn.examen.domain.course.Course;
import cn.examen.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper extends BaseMapper<Course> {

    List<Course> findListByMajorId(@Param("majorId") String majorId, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy);
}