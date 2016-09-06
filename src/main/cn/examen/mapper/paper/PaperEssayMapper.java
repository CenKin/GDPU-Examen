package cn.examen.mapper.paper;

import cn.examen.domain.paper.PaperEssay;
import cn.examen.mapper.BaseMapper;

public interface PaperEssayMapper extends BaseMapper<PaperEssay>{

    PaperEssay findOne(PaperEssay paperEssay);
}