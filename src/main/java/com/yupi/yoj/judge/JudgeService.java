package com.yupi.yoj.judge;

import com.yupi.yoj.model.vo.QuestionSubmitVO;

public interface JudgeService {
    //返回值应该是什么？

    /**
     * 判题服务
     * @param questionSubmitId
     * @return
     */
    QuestionSubmitVO doJudge(long questionSubmitId);
}
