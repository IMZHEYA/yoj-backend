package com.yupi.yoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.yoj.common.ErrorCode;
import com.yupi.yoj.exception.BusinessException;
import com.yupi.yoj.mapper.QuestionSubmitMapper;
import com.yupi.yoj.model.dto.questionSubmit.QuestionSubmitAddRequest;
import com.yupi.yoj.model.entity.Question;
import com.yupi.yoj.model.entity.QuestionSubmit;
import com.yupi.yoj.model.entity.User;
import com.yupi.yoj.model.enums.QuestionSubmitLanguageEnum;
import com.yupi.yoj.model.enums.QuestionSubmitStatusEnum;
import com.yupi.yoj.service.QuestionService;
import com.yupi.yoj.service.QuestionSubmitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 13425
* @description 针对表【question_submit(题目提交)】的数据库操作Service实现
* @createDate 2024-05-22 18:56:03
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{

    
    @Resource
    private QuestionService questionService;


    @Resource
    private QuestionSubmitService questionSubmitService;

    @Override
    public Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        //校验编程语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if(languageEnum == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"编程语言错误");
        }
        // 判断实体是否存在，根据类别获取实体
        Long questionId = questionSubmitAddRequest.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        long userId = loginUser.getId();
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setLanguage(questionSubmitAddRequest.getLanguage());
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setJudgeInfo("{}");
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setUserId(userId);
        boolean save = questionSubmitService.save(questionSubmit);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"题目提交失败");
        }
        return  questionSubmit.getId();
    }
}




