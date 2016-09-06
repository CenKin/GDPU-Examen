package cn.examen.mapper.paper;

import cn.examen.domain.paper.PaperFillin;
import cn.examen.mapper.BaseMapper;

public interface PaperFillinMapper extends BaseMapper<PaperFillin>{

    PaperFillin findOne(PaperFillin paperFillin);
}