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
import java.util.Optional;

public class JavaLanguageJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        // 1、先判断沙箱执行的结果输出数量是否和预期输出数量相等
//        2、依次判断每一项输出和预期输出结果是否相等
//        3、判题题目的限制是否符合要求
//        4、可能还有其他的异常情况
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        Long time = Optional.ofNullable(judgeInfo.getTime()).orElse(0L);
        Long memory = Optional.ofNullable(judgeInfo.getMemory()).orElse(0L);
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        Question question = judgeContext.getQuestion();
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setTime(time);
        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setTotalNum(inputList.size());
        int passNum = 0;
        for(int i = 0; i < judgeCaseList.size(); i ++){
            JudgeCase judgeCase = judgeCaseList.get(i);
            String output = judgeCase.getOutput();
            String s = outputList.get(i);
            if(judgeCase.getOutput().equals(outputList.get(i))){
                passNum ++;
            }
        }
        judgeInfoResponse.setPassNum(passNum);
        // 输出用例与输入用例不匹配
        if(inputList.size() != outputList.size()){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
//            judgeInfoResponse.setPassNum(0);
            return judgeInfoResponse;
        }
        //根据沙箱执行结果设置题目状态和信息
        for(int i = 0; i < judgeCaseList.size();i ++){
            JudgeCase judgeCase = judgeCaseList.get(i);
            if(!Objects.equals(outputList.get(i), judgeCase.getOutput())){
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
//                judgeInfoResponse.setPassNum(i);
                return judgeInfoResponse;
            }
        }
        judgeInfoResponse.setPassNum(outputList.size());
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
        if(memory  > needmemoryLimit){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
    }
}


