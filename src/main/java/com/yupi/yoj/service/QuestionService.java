package com.yupi.yoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.yoj.model.dto.question.QuestionQueryRequest;
import com.yupi.yoj.model.entity.Question;
import com.yupi.yoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author 13425
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2024-05-22 18:55:53
*/
public interface QuestionService extends IService<Question> {
    /**
     * 校验
     *
     * @param Question
     * @param add
     */
    void validQuestion(Question Question, boolean add);

    /**
     * 获取查询条件
     *
     * @param QuestionQueryRequest
     * @return
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest QuestionQueryRequest);



    /**
     * 获取题目封装
     *
     * @param Question
     * @param request
     * @return
     */
    QuestionVO getQuestionVO(Question Question, HttpServletRequest request);

    /**
     * 分页获取题目封装
     *
     * @param QuestionPage
     * @param request
     * @return
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> QuestionPage, HttpServletRequest request);
}
