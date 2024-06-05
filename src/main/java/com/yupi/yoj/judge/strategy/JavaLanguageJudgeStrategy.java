package com.yupi.yoj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.yupi.yoj.model.dto.question.JudgeCase;
import com.yupi.yoj.model.dto.question.JudgeConfig;
import com.yupi.yoj.judge.codesandbox.model.JudgeInfo;
import com.yupi.yoj.model.entity.Question;
import com.yupi.yoj.model.entity.QuestionSubmit;
import com.yupi.yoj.model.enums.JudgeInfoMessageEnum;

import java.util.List;
import java.util.Objects;

public class JavaLanguageJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        //        1、先判断沙箱执行的结果输出数量是否和预期输出数量相等
//        2、依次判断每一项输出和预期输出结果是否相等
//        3、判题题目的限制是否符合要求
//        4、可能还有其他的异常情况
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        Long time = judgeInfo.getTime();
        Long memory = judgeInfo.getMemory();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        Question question = judgeContext.getQuestion();
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setTime(time);
        judgeInfoResponse.setMemory(memory);
        if(inputList.size() != outputList.size()){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        for(int i = 0; i < outputList.size();i ++){
            if(!Objects.equals(outputList.get(i), inputList.get(i))){
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }
        //判断题目限制是否符合要求
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        Long needtimeLimit = judgeConfig.getTimeLimit();
        Long needmemoryLimit = judgeConfig.getMemoryLimit();
        //假设JAVA程序本身需要额外执行10s
        long JAVA_TIME_COST = 1000L;
        if(time - JAVA_TIME_COST> needtimeLimit){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        if(memory - JAVA_TIME_COST > needmemoryLimit){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
    }
}


