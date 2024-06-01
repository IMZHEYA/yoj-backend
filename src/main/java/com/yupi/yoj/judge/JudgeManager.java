package com.yupi.yoj.judge;

import com.yupi.yoj.judge.strategy.DefaultJudgeStrategy;
import com.yupi.yoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.yupi.yoj.judge.strategy.JudgeContext;
import com.yupi.yoj.judge.strategy.JudgeStrategy;
import com.yupi.yoj.model.dto.questionSubmit.JudgeInfo;
import com.yupi.yoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Component;

/**
 * 判题管理
 */
@Component
public class JudgeManager {


    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if("java".equals(language)){
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
