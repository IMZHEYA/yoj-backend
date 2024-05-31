package com.yupi.yoj.judge.codesandbox.impl;

import com.yupi.yoj.judge.codesandbox.CodeSandbox;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeCodeRequest;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeResponse;
import com.yupi.yoj.model.dto.questionSubmit.JudgeInfo;
import com.yupi.yoj.model.enums.JudgeInfoMessageEnum;
import com.yupi.yoj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * 示例代码沙箱
 */
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecutecodeResponse executeCode(ExecutecodeCodeRequest excodeCodeRequest) {

        List<String> inputList = excodeCodeRequest.getInputList();
        String code = excodeCodeRequest.getCode();
        String language = excodeCodeRequest.getLanguage();
        ExecutecodeResponse executecodeResponse = new ExecutecodeResponse();
        executecodeResponse.setOutputList(inputList);
        executecodeResponse.setMessage("测试执行成功");
        executecodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setTime(100L);
        judgeInfo.setMemory(100L);
        executecodeResponse.setJudgeInfo(judgeInfo);
        return executecodeResponse;
    }
}
