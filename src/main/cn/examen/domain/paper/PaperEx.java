package cn.examen.domain.paper;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.examen.domain.user.User;

public class PaperEx extends Paper{

	private String createtimeVo;
    private String vettedVo;
    private String phone;

	public PaperEx(Paper paper, User user) {
		super(paper);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date createtime = paper.getCreatetime();

		this.setPaperId(paper.getPaperId());
		this.setPhone(user.getPhone());
		this.setCreatetimeVo(createtime!=null ? sdf.format(createtime) : null);
        this.setUseClasses(paper.getUseClasses()==null ? "未指定" : paper.getUseClasses());

        Integer v = paper.getVetted();
        if (v != null) {
            switch (v) {
                case 1:this.setVettedVo("<font style='color:gray'>未提交</font>");break;
                case 2:this.setVettedVo("<font style='color:balck'>等待审核</font>");break;
                case 3:this.setVettedVo("<font style='color:green'>通过</font>");break;
                case 4:this.setVettedVo("<font style='color:red'>不通过</font>");break;
                default:throw new RuntimeException("未知错误");
            }
        }
	}

	public String getCreatetimeVo() {
		return createtimeVo;
	}

	public void setCreatetimeVo(String createtimeVo) {
		this.createtimeVo = createtimeVo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    public String getVettedVo() {
        return vettedVo;
    }

    public void setVettedVo(String vettedVo) {
        this.vettedVo = vettedVo;
    }
}