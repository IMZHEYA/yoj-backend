package com.yupi.yoj.judge;

import cn.hutool.json.JSONUtil;
import com.yupi.yoj.common.ErrorCode;
import com.yupi.yoj.exception.BusinessException;
import com.yupi.yoj.judge.codesandbox.CodeSandbox;
import com.yupi.yoj.judge.codesandbox.CodeSandboxFactory;
import com.yupi.yoj.judge.codesandbox.CodeSandboxProxy;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeCodeRequest;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeResponse;
import com.yupi.yoj.model.dto.question.JudgeCase;
import com.yupi.yoj.model.dto.question.JudgeConfig;
import com.yupi.yoj.model.dto.questionSubmit.JudgeInfo;
import com.yupi.yoj.model.entity.Question;
import com.yupi.yoj.model.entity.QuestionSubmit;
import com.yupi.yoj.model.enums.JudgeInfoMessageEnum;
import com.yupi.yoj.model.enums.QuestionSubmitStatusEnum;
import com.yupi.yoj.model.vo.QuestionSubmitVO;
import com.yupi.yoj.service.QuestionService;
import com.yupi.yoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codeSandbox.type:example}")
    private String type;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private QuestionService questionService;

    @Override
    public QuestionSubmitVO doJudge(long questionSubmitId) {
//        1）传入题目的提交id,获取到对应的题目，提交信息（包含代码，编程语言等）
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if(questionSubmit == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if(question == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目不存在");
        }
//        2）如果题目提交状态不为等待中，就不用重复执行了，只执行等待中的题目
        Integer status = questionSubmit.getStatus();
        if(!Objects.equals(status, QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"题目正在判題中");
        }
//        3）更改题目提交状态为“判题中”，防止重复执行，也能让用户即时看到状态
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmit.getId());
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean save = questionSubmitService.updateById(questionSubmitUpdate);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"题目状态更新失败");
        }
//        4）调用沙箱，获取到执行结果
        CodeSandbox codesandbox = CodeSandboxFactory.newInstance(type);
        codesandbox = new CodeSandboxProxy(codesandbox);
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        //输入用例转列表
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        //把判题用例中的输入用例过滤出来，喂给需要的inputList,得到了输入列表
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecutecodeCodeRequest executecodeCodeRequest = ExecutecodeCodeRequest.builder().code(code).language(language).inputList(inputList).build();
//        5）根据沙箱的执行结果，设置题目的判题状态和信息
//        1、先判断沙箱执行的结果输出数量是否和预期输出数量相等
//        2、依次判断每一项输出和预期输出结果是否相等
//        3、判题题目的限制是否符合要求
//        4、可能还有其他的异常情况
        ExecutecodeResponse executecodeResponse = codesandbox.executeCode(executecodeCodeRequest);
        List<String> outputList = executecodeResponse.getOutputList();
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.WAITING;
        if(inputList.size() != outputList.size()){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            return null;
        }
        for(int i = 0; i < outputList.size();i ++){
            if(!Objects.equals(outputList.get(i), inputList.get(i))){
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                return null;
            }
        }
        //判断题目限制是否符合要求
        JudgeInfo judgeInfo = executecodeResponse.getJudgeInfo();
        Long time = judgeInfo.getTime();
        Long memory = judgeInfo.getMemory();
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        Long needtimeLimit = judgeConfig.getTimeLimit();
        Long needmemoryLimit = judgeConfig.getMemoryLimit();
        if(time > needtimeLimit){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            return null;
        }
        if(memory > needmemoryLimit){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            return null;
        }
        return null;
    }
}
