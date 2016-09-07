package cn.examen.service.impl.course;

import cn.examen.domain.course.College;
import cn.examen.mapper.course.CollegeMapper;
import cn.examen.service.course.CollegeService;
import cn.examen.utils.BeanUtils2;
import cn.examen.utils.StateCode;
import cn.itcast.commons.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("collegeService")
public class CollegeServiceImpl implements CollegeService {

	@Autowired
	CollegeMapper collegeMapper;
	
	@Override
	public List<College> getCollegeIds() {
		return collegeMapper.findAll(null, null);
	}

	@Override
	public String getCollegeNameById(String collId) {
		College c = collegeMapper.findById(collId);
		return c.getCollName();
	}

	@Override
	public String addOne(College param, Object... objects) {
		param.setCollId(CommonUtils.uuid());
		int code = collegeMapper.insert(param);
		return code==1 ? StateCode.ADD_SUCCESS : StateCode.ADD_FAIL;
	}

	@Override
	public Integer getTotalRecordByParam(College param) {
		Integer totalRecord = collegeMapper.countLike(param);
		return totalRecord == null ? 0 : totalRecord;
	}

	@Override
	public College getById(String id) {
		return collegeMapper.findOne("collId", id);
	}

	@Override
	public List<College> getPageListByParam(College param, int offset, int rows) {
		Map<String, Object> condition = BeanUtils2.transBean2Map(param);
		return collegeMapper.like(condition, offset, (rows-1)*offset, "collName", "ASC");
	}
}
