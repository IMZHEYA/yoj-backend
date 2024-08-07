package com.yupi.yoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.yoj.common.BaseResponse;
import com.yupi.yoj.common.ErrorCode;
import com.yupi.yoj.common.ResultUtils;
import com.yupi.yoj.exception.BusinessException;
import com.yupi.yoj.model.dto.questionSubmit.QuestionSubmitAddRequest;
import com.yupi.yoj.model.dto.questionSubmit.QuestionSubmitQueryRequest;
import com.yupi.yoj.model.entity.QuestionSubmit;
import com.yupi.yoj.model.entity.User;
import com.yupi.yoj.model.vo.QuestionSubmitVO;
import com.yupi.yoj.service.QuestionSubmitService;
import com.yupi.yoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return resultNum 题目提交
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                               HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        final User loginUser = userService.getLoginUser(request);
        long questionId = questionSubmitAddRequest.getQuestionId();
        Long result = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(result);
    }


    /**
     * 分页获取题目提交列表，只有管理员和做题的用户可以查看提交的代码
     *
     * @param questionSubmitQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                                 HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        //从数据库中查询原始的题目提交分页信息
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        //返回脱敏信息
        User loginUser = userService.getLoginUser(request);
        Page<QuestionSubmitVO> questionSubmitVOPage = questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser);
        return ResultUtils.success(questionSubmitVOPage);
    }

    /**
     * 根据ID获取提交信息
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<QuestionSubmitVO> getJudgeResult(Long id,HttpServletRequest request){
        if(id <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QuestionSubmit questionSubmit = questionSubmitService.getById(id);
        if(questionSubmit == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"提交记录不存在");
        }
        User loginUser = userService.getLoginUser(request);
        QuestionSubmitVO questionSubmitVO = questionSubmitService.getQuestionSubmitVO(questionSubmit, loginUser);
        return ResultUtils.success(questionSubmitVO);
    }
}
