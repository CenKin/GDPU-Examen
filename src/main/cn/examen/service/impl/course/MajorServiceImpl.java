package cn.examen.service.impl.course;

import cn.examen.domain.course.Major;
import cn.examen.mapper.course.MajorMapper;
import cn.examen.service.course.MajorService;
import cn.examen.utils.BeanUtils2;
import cn.examen.utils.StateCode;
import cn.itcast.commons.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("majorService")
public class MajorServiceImpl implements MajorService {

	@Autowired
	MajorMapper majorMapper;
	
	@Override
	public List<Major> findMajorByCollId(String collId) {
		return majorMapper.findList("collId", collId, null, null);
	}

	@Override
	public String findMajorNameById(String majorId) {
		Major m = majorMapper.findById(majorId);
		return m.getMajorName();
	}

	@Override
	public String addOne(Major param, Object... objects) {
		param.setMajorId(CommonUtils.uuid());
		int code = majorMapper.insert(param);
		return code==1 ? StateCode.ADD_SUCCESS : StateCode.ADD_FAIL;
	}

	@Override
	public Integer getTotalRecordByParam(Major param) {
		return null;
	}

	@Override
	public Major getById(String id) {
		return majorMapper.findOne("majorId", id);
	}

	@Override
	public List<Major> getPageListByParam(Major param, int offset, int rows) {
		Map<String, Object> condition = BeanUtils2.transBean2Map(param);
		return majorMapper.like(condition, offset, (rows-1)*offset, "majorName", "ASC");
	}

	@Override
	public String updateOne(Major param) {
		int code = majorMapper.update(param);
		return code==1 ? StateCode.UPDATE_SUCCESS : StateCode.UPDATE_FAIL;
	}

	@Override
	public String deleteById(String id) {
		int code = majorMapper.deleteById(id);
		return code==1 ? StateCode.DELETE_SUCCESS : StateCode.DELETE_FAIL;
	}
}
