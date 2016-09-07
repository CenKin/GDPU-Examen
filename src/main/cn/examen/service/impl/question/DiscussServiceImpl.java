package cn.examen.service.impl.question;


import cn.examen.domain.question.Discuss;
import cn.examen.mapper.question.DiscussMapper;
import cn.examen.service.question.DiscussService;
import cn.examen.utils.BeanUtils2;
import cn.examen.utils.StateCode;
import cn.itcast.commons.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("discussService")
public class DiscussServiceImpl implements DiscussService {

	@Autowired
	DiscussMapper discussMapper;

	@Override
	public String addOne(Discuss param, Object... objects) {
		param.setId(CommonUtils.uuid());
		int code = discussMapper.insert(param);
		return code==1 ? StateCode.ADD_SUCCESS : StateCode.ADD_FAIL;
	}

	@Override
	public Integer getTotalRecordByParam(Discuss d) {
		Integer totalRecord = discussMapper.countLike(d);
		return totalRecord==null? 0 : totalRecord;
	}

	@Override
	public Discuss getById(String id) {
		return discussMapper.findById(id);
	}

	@Override
	public List<Discuss> getPageListByParam(Discuss d, int offset, int rows) {
		Map<String, Object> condition = BeanUtils2.transBean2Map(d);
		return discussMapper.like(condition, (offset-1)*rows, rows, "`section`", "ASC");
	}
}
