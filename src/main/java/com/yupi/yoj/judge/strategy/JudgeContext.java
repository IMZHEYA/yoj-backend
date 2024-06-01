package com.yupi.yoj.judge.strategy;

import com.yupi.yoj.model.dto.question.JudgeCase;
import com.yupi.yoj.model.dto.questionSubmit.JudgeInfo;
import com.yupi.yoj.model.entity.Question;
import com.yupi.yoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 在策略中传递的参数
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}