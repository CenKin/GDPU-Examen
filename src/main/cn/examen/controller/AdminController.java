package cn.examen.controller;

import cn.examen.domain.commons.Page;
import cn.examen.domain.commons.Result;
import cn.examen.domain.course.College;
import cn.examen.domain.course.Course;
import cn.examen.domain.course.CourseEx;
import cn.examen.domain.question.*;
import cn.examen.domain.user.User;
import cn.examen.domain.user.UserEx;
import cn.examen.exception.GlobolException;
import cn.examen.service.course.CollegeService;
import cn.examen.service.course.CourseService;
import cn.examen.service.paper.PaperService;
import cn.examen.service.question.ChoiceService;
import cn.examen.service.question.DiscussService;
import cn.examen.service.question.EssayService;
import cn.examen.service.question.FillinService;
import cn.examen.service.user.UserService;
import cn.examen.utils.ExcelUtils;
import cn.examen.utils.StateCode;
import cn.itcast.commons.CommonUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ChoiceService choiceService;
    @Autowired
    DiscussService discussService;
    @Autowired
    EssayService essayService;
    @Autowired
    FillinService fillinService;
    @Autowired
    UserService userService;
    @Autowired
    PaperService paperService;
    @Autowired
    CourseService courseService;
    @Autowired
    CollegeService collegeService;

    /*页面跳转的控制器*/
    @RequestMapping("/indexBody")
    public String indexBody() {
        return "admin/indexBody";
    }

    //跳转到增加试题页面
    @RequestMapping("/toAddQuestion")
    public String toAddQuestion() {
        return "admin/addQuestion";
    }

    //跳转到检索试题页面
    @RequestMapping("/toSelectQuestion")
    public String toSelectQuestion() {
        return "admin/selectQuestion";
    }

    @RequestMapping("/getQuestionForm")
    public String getQuestionForm(String type) {
        return "admin/questionForm/"+ type +"Form";
    }

    //跳转到题库导入页面
    @RequestMapping("/toImportQuestion")
    public String toImportQuestion() {
        return "admin/importQuestion";
    }

    //跳转到增加科目页面
    @RequestMapping("/toAddCourse")
    public String toAddCourse() {
        return "admin/addCourse";
    }

    //跳转到检索科目页面
    @RequestMapping("/toSelectCourse")
    public String toSelectCourse() {
        return "admin/selectCourse";
    }

    //跳转到检索试卷页面
    @RequestMapping("/toSelectPaper")
    public String toSelectPaper() {
        return "admin/selectPaper";
    }

    //跳转到增加用户页面
    @RequestMapping("/toAddUser")
    public String toAddUser() {
        return "admin/addUser";
    }

    //跳转到检索试题页面
    @RequestMapping("/toSelectUser")
    public String toSelectUser() {
        return "admin/selectUser";
    }

    /**
     * 功能：根据时间顺序获取10个用户
     *
     * @return 用户列表
     */
    @RequestMapping("/getUserByLoginTime")
    public
    @ResponseBody
    List<User> getUserByLoginTime() {
        return null;
    }

	/*题库管理*/

    /**
     * 增加试题
     */
    @RequestMapping("/addQuestion")
    public @ResponseBody Result addQuestion(String questionType, Choice choice, Essay essay, Discuss discuss, Fillin fillin) {
        Result result = new Result();
        String stateCode = "";
        switch (questionType) {
            case "choice":
                if (choice != null) {
                    stateCode = choiceService.addOne(choice); break;
                } else break;
            case "essay":
                if (essay != null) {
                    stateCode = essayService.addOne(essay); break;
                } else break;
            case "discuss":
                if (discuss != null) {
                    stateCode = discussService.addOne(discuss); break;
                } else break;
            case "fillin":
                if (fillin != null) {
                    stateCode = fillinService.addOne(fillin); break;
                } else break;
            default: break;
        }
        if(stateCode.equals(StateCode.ADD_SUCCESS)){
            result.setMsg("操作成功");
            result.setStateCode(stateCode);
            result.setTimestamp(new Date());
            return result;
        } else {
            result.setMsg("操作失败");
            result.setStateCode(stateCode);
            result.setTimestamp(new Date());
            result.setException(new GlobolException("增加题目失败"));
            return result;
        }
    }

    /**
     * 高级检索试题
     */
    @RequestMapping(value = "/selectQuestion", method = RequestMethod.POST)
    public @ResponseBody Page<Question> selectQuestion(String questionId, String questionType, String courseId,
                                  String questionContent, String questionSection, Integer diff,
                                  @RequestParam(value = "offset", required = false, defaultValue = "1") int offset,
                                  @RequestParam(value = "rows", required = false, defaultValue = "10") int rows) {

        if (questionType != null) {
            if (questionType.equals("choice")) {
                Choice c = new Choice();
                c.setId(questionId);
                c.setCourseId(courseId);
                c.setContent(questionContent);
                c.setSection(questionSection);
                c.setDiff(diff);
                int totalRecord = choiceService.getTotalRecordByParam(c);
                List<Choice> choices = choiceService.getPageListByParam(c, offset, rows);
                List<Question> list = new ArrayList();
                list.addAll(choices);
                Page<Question> page = new Page(totalRecord, rows, list);
                return page;
            } else if (questionType.equals("fillin")) {
                Fillin f = new Fillin();
                f.setId(questionId);
                f.setCourseId(courseId);
                f.setContent(questionContent);
                f.setSection(questionSection);
                f.setDiff(diff);
                int totalRecord = fillinService.getTotalRecordByParam(f);
                List<Fillin> fillins = fillinService.getPageListByParam(f, offset, rows);
                List<Question> list = new ArrayList();
                list.addAll(fillins);
                Page<Question> page = new Page(totalRecord, rows, list);
                return page;
            } else if (questionType.equals("essay")) {
                Essay e = new Essay();
                e.setId(questionId);
                e.setCourseId(courseId);
                e.setContent(questionContent);
                e.setSection(questionSection);
                e.setDiff(diff);
                int totalRecord = essayService.getTotalRecordByParam(e);
                List<Essay> essays = essayService.getPageListByParam(e, offset, rows);
                List<Question> list = new ArrayList();
                list.addAll(essays);
                Page<Question> page = new Page(totalRecord, rows, list);
                return page;
            } else if (questionType.equals("discuss")) {
                Discuss d = new Discuss();
                d.setId(questionId);
                d.setCourseId(courseId);
                d.setContent(questionContent);
                d.setSection(questionSection);
                d.setDiff(diff);
                int totalRecord = discussService.getTotalRecordByParam(d);
                List<Discuss> discusses = discussService.getPageListByParam(d, offset, rows);
                List<Question> list = new ArrayList();
                list.addAll(discusses);
                Page<Question> page = new Page(totalRecord, rows, list);
                return page;
            }
        }
        return null;
    }

    /**
     * 查看某道试题
     */
    @RequestMapping(value = "/selectOneQuestion", method = RequestMethod.GET)
    public @ResponseBody Page<?> selectQuestion(String questionId, String questionType){
        switch (questionType){
            case "choice" :
                Choice choice = choiceService.getById(questionId);
                List<Choice> cs = new ArrayList();
                cs.add(choice);
                return new Page(1,1,cs);
            case "fillin" :
                Fillin fillin = fillinService.getById(questionId);
                List<Fillin> fs = new ArrayList();
                fs.add(fillin);
                return new Page(1,1,fs);
            case "essay" :
                Essay essay = essayService.getById(questionId);
                List<Essay> es = new ArrayList();
                es.add(essay);
                return new Page(1,1,es);
            case "discuss" :
                Discuss discuss = discussService.getById(questionId);
                List<Discuss> ds = new ArrayList();
                ds.add(discuss);
                return new Page(1,1,ds);
        }
        return new Page(0,0,null);
    }

    /***/
    @RequestMapping(value = "/updateOneQuestion", method = RequestMethod.POST)
    public @ResponseBody Result updateOneQuestion(String questionType, Choice choice, Essay essay, Discuss discuss, Fillin fillin){

        Result result = new Result();
        String stateCode = "";
        switch (questionType) {
            case "choice":
                if (choice != null) {
                    stateCode = choiceService.updateOne(choice); break;
                } else break;
            case "essay":
                if (essay != null) {
                    stateCode = essayService.updateOne(essay); break;
                } else break;
            case "discuss":
                if (discuss != null) {
                    stateCode = discussService.updateOne(discuss); break;
                } else break;
            case "fillin":
                if (fillin != null) {
                    stateCode = fillinService.updateOne(fillin); break;
                } else break;
            default: break;
        }
        if(stateCode.equals(StateCode.UPDATE_SUCCESS)){
            result.setMsg("操作成功");
            result.setStateCode(stateCode);
            result.setTimestamp(new Date());
            return result;
        } else {
            result.setMsg("操作失败");
            result.setStateCode(stateCode);
            result.setTimestamp(new Date());
            result.setException(new GlobolException("增加题目失败"));
            return result;
        }
    }

    /***/
    @RequestMapping(value = "/deleteOneQuestion", method = RequestMethod.GET)
    public @ResponseBody Result deleteOneQuestion(String questionId, String questionType){
        Result result = new Result();
        String stateCode = "";
        switch (questionType){
            case "choice" :
                if (questionId != null) {
                    stateCode = choiceService.deleteById(questionId); break;
                } else break;
            case "fillin" :
                if (questionId != null) {
                    stateCode = fillinService.deleteById(questionId); break;
                } else break;
            case "essay" :
                if (questionId != null) {
                    stateCode = essayService.deleteById(questionId); break;
                } else break;
            case "discuss" :
                if (questionId != null) {
                    stateCode = discussService.deleteById(questionId); break;
                } else break;
        }
        if(stateCode.equals(StateCode.DELETE_SUCCESS)){
            result.setMsg("操作成功");
            result.setStateCode(stateCode);
            result.setTimestamp(new Date());
            return result;
        } else {
            result.setMsg("操作失败");
            result.setStateCode(stateCode);
            result.setTimestamp(new Date());
            result.setException(new GlobolException("删除题目失败"));
            return result;
        }
    }

    /**
     * 文件上传后解析并导入
     *
     * @return
     */
    @RequestMapping("/importQuestion")
    public @ResponseBody Result importQuestion(MultipartFile file) {
        try {
            InputStream fis = file.getInputStream();
            if (fis != null) {
                String courseName;
                Course course;
                String courseId;
                String fileName = file.getOriginalFilename();
                String questionType = fileName==null ? null : fileName.substring(0, fileName.lastIndexOf("."));
                switch (questionType) {
                    case "choice":
                        List<Choice> cs = ExcelUtils.readExcel(Choice.class, fileName, fis);
                        courseName = cs.get(0).getCourseName();
                        course = courseService.findByCourseName(courseName);
                        if (course == null) {
                            courseId = CommonUtils.uuid();
                            course = new Course();
                            course.setCourseId(courseId);
                            course.setCourseName(courseName);
                            courseService.addOne(course);
                        } else courseId = course.getCourseId();

                        for (Choice c : cs) {
                            c.setId(CommonUtils.uuid());
                            c.setCourseId(courseId);
                            choiceService.addOne(c);
                        }
                        break;
                    case "fillin":
                        List<Fillin> fs = ExcelUtils.readExcel(Fillin.class, fileName, fis);
                        courseName = fs.get(0).getCourseName();
                        course = courseService.findByCourseName(courseName);
                        if (course == null) {
                            courseId = CommonUtils.uuid();
                            course = new Course();
                            course.setCourseId(courseId);
                            course.setCourseName(courseName);
                            courseService.addOne(course);
                        } else courseId = course.getCourseId();

                        for (Fillin f : fs) {
                            f.setId(CommonUtils.uuid());
                            f.setCourseId(courseId);
                            fillinService.addOne(f);
                        }
                        break;
                    case "essay":
                        List<Essay> es = ExcelUtils.readExcel(Essay.class, fileName, fis);
                        courseName = es.get(0).getCourseName();
                        course = courseService.findByCourseName(courseName);
                        if (course == null) {
                            courseId = CommonUtils.uuid();
                            course = new Course();
                            course.setCourseId(courseId);
                            course.setCourseName(courseName);
                            courseService.addOne(course);
                        } else courseId = course.getCourseId();

                        for (Essay e : es) {
                            e.setId(CommonUtils.uuid());
                            e.setCourseId(courseId);
                            essayService.addOne(e);
                        }
                        break;
                    case "discuss":
                        List<Discuss> ds = ExcelUtils.readExcel(Discuss.class, fileName, fis);
                        courseName = ds.get(0).getCourseName();
                        course = courseService.findByCourseName(courseName);
                        if (course == null) {
                            courseId = CommonUtils.uuid();
                            course = new Course();
                            course.setCourseId(courseId);
                            course.setCourseName(courseName);
                            courseService.addOne(course);
                        } else courseId = course.getCourseId();

                        for (Discuss d : ds) {
                            d.setId(CommonUtils.uuid());
                            d.setCourseId(courseId);
                            discussService.addOne(d);
                        }
                        break;
                    default:
                        throw new GlobolException("题目类型不匹配");
                }
            } else throw new GlobolException("文件上传失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(StateCode.UPLOAD_FAIL, "导入失败", new Date(), e);
        }
        return new Result(StateCode.UPLOAD_SUCCESS, "导入完成", new Date());
    }

	/*科目管理*/
	/**
	 * 增加科目
	 */
	@RequestMapping("/addCourse")
	public @ResponseBody Result addCourse(String majorId, String courseName){
		Result result = new Result();
		if(majorId==null || courseName==null){
			result.setMsg("课程名称和专业不能为空");
			result.setTimestamp(new Date());
			return result;
		}
		Course course = new Course();
		course.setCourseId(CommonUtils.uuid());
		course.setCourseName(courseName);
		String stateCode = courseService.addOne(course, majorId);

		if(stateCode.equals(StateCode.ADD_SUCCESS)){
			result.setMsg("操作成功");
			result.setStateCode(stateCode);
			result.setTimestamp(new Date());
			return result;
		} else {
			result.setMsg("操作失败");
			result.setStateCode(stateCode);
			result.setTimestamp(new Date());
            result.setException(new GlobolException("增加课程失败"));
			return result;
		}
	}
	/**
	 * 检索科目
	 */
	@RequestMapping("/selectCourse")
	public @ResponseBody Page<Course> selectCourse(CourseEx courseEx,
                                                   @RequestParam(value="offset", required=false, defaultValue="1") int offset,
                                                   @RequestParam(value="rows", required=false, defaultValue="10") int rows) {
        int totalRecord = courseService.getTotalRecordByParam(courseEx);
        List<Course> list = courseService.getByParam(courseEx, offset, rows);
        Page<Course> page = new Page(totalRecord, rows, list);
        return page;
    }

    /***/
    @RequestMapping("/deleteCourse")
    public @ResponseBody Result deleteCourse(String courseId){
        Result result = new Result();
        Course course = new Course();
        course.setCourseId(courseId);
        String stateCode = courseService.deleteByParam(course);

        if(stateCode.equals(StateCode.DELETE_SUCCESS)){
            result.setMsg("操作成功");
            result.setStateCode(stateCode);
            result.setTimestamp(new Date());
            return result;
        } else {
            result.setMsg("操作失败");
            result.setStateCode(stateCode);
            result.setTimestamp(new Date());
            result.setException(new GlobolException("删除课程失败"));
            return result;
        }
    }

    /***/
    @RequestMapping("/updateCourse")
    public @ResponseBody Result updateCourse(Course course){
        Result result = new Result();
        String stateCode = courseService.updateOne(course);

        if(stateCode.equals(StateCode.UPDATE_SUCCESS)){
            result.setMsg("操作成功");
            result.setStateCode(stateCode);
            result.setTimestamp(new Date());
            return result;
        } else {
            result.setMsg("操作失败");
            result.setStateCode(stateCode);
            result.setTimestamp(new Date());
            result.setException(new GlobolException("更新课程失败"));
            return result;
        }
    }

	/*用户管理*/
    @RequestMapping("/addUser")
    public @ResponseBody Result addUser(User user){
        Result result = new Result();
        String stateCode = userService.addOne(user);

        if(stateCode.equals(StateCode.ADD_SUCCESS)){
            result.setMsg("操作成功");
            result.setStateCode(stateCode);
            result.setTimestamp(new Date());
            return result;
        } else {
            result.setMsg("操作失败");
            result.setStateCode(stateCode);
            result.setTimestamp(new Date());
            result.setException(new GlobolException("用户注册失败"));
            return result;
        }
    }

    @RequestMapping("/selectUser")
    public @ResponseBody Page<UserEx> selectUser(User user,
                                                 @RequestParam(value="offset", required=false, defaultValue="1") int offset,
                                                 @RequestParam(value="rows", required=false, defaultValue="10") int rows) {
        int totalRecord = userService.getTotalRecordByParam(user);
        List<User> list = userService.getPageListByParam(user, offset, rows);
        List<UserEx> listVo = new ArrayList();
        for(User u : list) {
            College college = collegeService.getById(u.getCollId());
            listVo.add(new UserEx(u, college));
        }
        Page<UserEx> page = new Page(totalRecord, rows, listVo);
        return page;
    }

}
