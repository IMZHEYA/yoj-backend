package com.yupi.yoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.yoj.model.entity.Question;
import com.yupi.yoj.service.QuestionService;
import com.yupi.yoj.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 13425
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2024-05-22 18:55:53
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




