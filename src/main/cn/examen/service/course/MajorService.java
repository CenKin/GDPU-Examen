package cn.examen.service.course;


import cn.examen.domain.course.Major;
import cn.examen.service.BaseService;

import java.util.List;

public interface MajorService extends BaseService<Major> {

	List<Major> findMajorByCollId(String collId);
	
	String findMajorNameById(String majorId);
}
