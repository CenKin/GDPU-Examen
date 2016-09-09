package cn.examen.service.impl.question;

import cn.examen.domain.course.Course;
import cn.examen.domain.question.Choice;
import cn.examen.mapper.course.CourseMapper;
import cn.examen.mapper.question.ChoiceMapper;
import cn.examen.service.question.ChoiceService;
import cn.examen.utils.BeanUtils2;
import cn.examen.utils.StateCode;
import cn.itcast.commons.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("choiceService")
public class ChoiceServiceImpl implements ChoiceService {

	@Autowired
	ChoiceMapper choiceMapper;
	@Autowired
	CourseMapper courseMapper;


	@Override
	public String addOne(Choice param, Object... objects) {
		param.setId(CommonUtils.uuid());
        String courseId = param.getCourseId();
		if(courseId!=null){
            Course course = courseMapper.findById(courseId);
            param.setCourseName(course.getCourseName());
            int code = choiceMapper.insert(param);
            return code==1 ? StateCode.ADD_SUCCESS : StateCode.ADD_FAIL;
        } else return StateCode.ADD_FAIL;
	}

	@Override
	public Integer getTotalRecordByParam(Choice c) {
		Integer totalRecord = choiceMapper.countLike(c);
		return totalRecord==null? 0 : totalRecord;
	}

	@Override
	public Choice getById(String id) {
		return choiceMapper.findById(id);
	}

	@Override
	public List<Choice> getPageListByParam(Choice choice, int offset, int rows) {
		Map<String, Object> condition = BeanUtils2.transBean2Map(choice);
		return choiceMapper.like(condition, (offset-1)*rows, rows, "`section`", "ASC");
	}

	@Override
	public String updateOne(Choice param) {
		String courseId = param.getCourseId();
		if(courseId!=null){
			Course course = courseMapper.findById(courseId);
			param.setCourseName(course.getCourseName());
			int code = choiceMapper.update(param);
			return code==1 ? StateCode.UPDATE_SUCCESS : StateCode.UPDATE_FAIL;
		} else return StateCode.UPDATE_FAIL;
	}

	@Override
	public String deleteById(String id) {
        int code = choiceMapper.deleteById(id);
        return code==1 ? StateCode.DELETE_SUCCESS : StateCode.DELETE_FAIL;
	}
}
