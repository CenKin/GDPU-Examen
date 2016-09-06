package cn.examen.mapper.paper;

import cn.examen.domain.paper.PaperChoice;
import cn.examen.mapper.BaseMapper;

public interface PaperChoiceMapper extends BaseMapper<PaperChoice> {

    PaperChoice findOne(PaperChoice paperChoice);
}