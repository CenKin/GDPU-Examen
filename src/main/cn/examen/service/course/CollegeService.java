package cn.examen.service.course;


import cn.examen.domain.course.College;
import cn.examen.service.BaseService;

import java.util.List;

public interface CollegeService extends BaseService<College> {

	List<College> getCollegeIds();
	
	String getCollegeNameById(String collId);
}
