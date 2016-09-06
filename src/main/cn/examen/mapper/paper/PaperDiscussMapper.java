package cn.examen.mapper.paper;

import cn.examen.domain.paper.PaperDiscuss;
import cn.examen.mapper.BaseMapper;

public interface PaperDiscussMapper extends BaseMapper<PaperDiscuss>{

    PaperDiscuss findOne(PaperDiscuss paperDiscuss);
}