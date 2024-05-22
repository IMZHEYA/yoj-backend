package com.yupi.yoj.model.dto.question;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 编辑请求
 *
 *
 *
 */
@Data
public class QuestionEditRequest implements Serializable {


    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;

    /**
     * 题目答案
     */
    private String answer;



    /**
     * 判题用例（json 数组）
     */
    private List<judgeCase> judgeCase;

    /**
     * 判题配置（json 对象）
     */
    private judgeConfig judgeConfig;



    private static final long serialVersionUID = 1L;
}