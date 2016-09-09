package cn.examen.service.impl.course;

import cn.examen.domain.course.College;
import cn.examen.domain.course.Course;
import cn.examen.domain.course.Major;
import cn.examen.domain.course.MajorCourse;
import cn.examen.exception.GlobolException;
import cn.examen.mapper.course.CollegeMapper;
import cn.examen.mapper.course.CourseMapper;
import cn.examen.mapper.course.MajorCourseMapper;
import cn.examen.mapper.course.MajorMapper;
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
    @Autowired
    MajorMapper majorMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    MajorCourseMapper majorCourseMapper;
	
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

	@Override
	public String updateOne(College param) {
		int code = collegeMapper.update(param);
		return code==1 ? StateCode.UPDATE_SUCCESS : StateCode.UPDATE_FAIL;
	}

    @Override
    public String deleteById(String id) {
        List<Major> majors = majorMapper.findList("collId", id, null, null);
        for(Major m : majors){
            List<MajorCourse> majorCourses = majorCourseMapper.findList("majorId", m.getMajorId(), null, null);
            for(MajorCourse mc : majorCourses){
                String courseId = mc.getCourseId();
                int code = courseMapper.deleteById(courseId);
                if(code!=1)
                    throw new GlobolException("课程删除失败");
            }
            int code = majorMapper.deleteById(m.getMajorId());
            if(code!=1)
                throw new GlobolException("专业删除失败");
        }
        int code = collegeMapper.deleteById(id);
        return code==1 ? StateCode.DELETE_SUCCESS : StateCode.DELETE_FAIL;
    }
}
