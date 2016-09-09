package cn.examen.service.impl.question;

import java.util.List;
import java.util.Map;

import cn.examen.domain.course.Course;
import cn.examen.domain.question.Essay;
import cn.examen.mapper.course.CourseMapper;
import cn.examen.mapper.question.EssayMapper;
import cn.examen.service.question.EssayService;
import cn.examen.utils.BeanUtils2;
import cn.examen.utils.StateCode;
import cn.itcast.commons.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("essayService")
public class EssayServiceImpl implements EssayService {

	@Autowired
	EssayMapper essayMapper;
    @Autowired
    CourseMapper courseMapper;


    @Override
	public String addOne(Essay param, Object... objects) {
		param.setId(CommonUtils.uuid());
        String courseId = param.getCourseId();
        if(courseId!=null) {
			Course course = courseMapper.findById(courseId);
			param.setCourseName(course.getCourseName());
			int code = essayMapper.insert(param);
			return code==1 ? StateCode.ADD_SUCCESS : StateCode.ADD_FAIL;
		}else return StateCode.ADD_FAIL;
	}

	@Override
	public Integer getTotalRecordByParam(Essay e) {
		Integer totalRecord = essayMapper.countLike(e);
		return totalRecord==null? 0 : totalRecord;
	}

	@Override
	public Essay getById(String id) {
		return essayMapper.findById(id);
	}

	@Override
	public List<Essay> getPageListByParam(Essay e, int offset, int rows) {
		Map<String, Object> condition = BeanUtils2.transBean2Map(e);
		return essayMapper.like(condition, (offset-1)*rows, rows, "`section`", "ASC");
	}

    @Override
    public String updateOne(Essay param) {
        String courseId = param.getCourseId();
        if(courseId!=null){
            Course course = courseMapper.findById(courseId);
            param.setCourseName(course.getCourseName());
            int code = essayMapper.update(param);
            return code==1 ? StateCode.UPDATE_SUCCESS : StateCode.UPDATE_FAIL;
        } else return StateCode.UPDATE_FAIL;
    }

    @Override
    public String deleteById(String id) {
        int code = essayMapper.deleteById(id);
        return code==1 ? StateCode.DELETE_SUCCESS : StateCode.DELETE_FAIL;
    }
}
