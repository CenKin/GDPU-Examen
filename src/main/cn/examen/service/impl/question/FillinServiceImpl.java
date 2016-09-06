package cn.examen.service.impl.question;

import cn.examen.domain.question.Fillin;
import cn.examen.mapper.question.FillinMapper;
import cn.examen.service.question.FillinService;
import cn.examen.utils.BeanUtils2;
import cn.examen.utils.StateCode;
import cn.itcast.commons.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("fillinService")
public class FillinServiceImpl implements FillinService {

	@Autowired
	FillinMapper fillinMapper;

	@Override
	public String addOne(Fillin param, Object... objects) {
		param.setId(CommonUtils.uuid());
		int code = fillinMapper.insert(param);
		return code==1 ? StateCode.ADD_SUCCESS : StateCode.ADD_FAIL;
	}

	@Override
	public Integer getTotalRecordByParam(Fillin f) {
		Integer totalRecord = fillinMapper.count(f);
		return totalRecord==null? 0 : totalRecord;
	}

	@Override
	public Fillin getById(String id) {
		return fillinMapper.findById(id);
	}

	@Override
	public List<Fillin> getPageListByParam(Fillin f, int offset, int rows) {
		Map<String, Object> condition = BeanUtils2.transBean2Map(f);
		return fillinMapper.like(condition, (offset-1)*rows, rows, "`section`", "ASC");
	}
}
