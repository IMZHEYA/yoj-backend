package com.yupi.yoj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 题目
 * @TableName question
 */
@TableName(value ="question")
public class Question implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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
    private String tags;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;

    /**
     * 判题用例（json 数组）
     */
    private String judgeCase;

    /**
     * 判题配置（json 对象）
     */
    private String judgeConfig;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 标签列表（json 数组）
     */
    public String getTags() {
        return tags;
    }

    /**
     * 标签列表（json 数组）
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 题目答案
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 题目答案
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 题目提交数
     */
    public Integer getSubmitNum() {
        return submitNum;
    }

    /**
     * 题目提交数
     */
    public void setSubmitNum(Integer submitNum) {
        this.submitNum = submitNum;
    }

    /**
     * 题目通过数
     */
    public Integer getAcceptedNum() {
        return acceptedNum;
    }

    /**
     * 题目通过数
     */
    public void setAcceptedNum(Integer acceptedNum) {
        this.acceptedNum = acceptedNum;
    }

    /**
     * 判题用例（json 数组）
     */
    public String getJudgeCase() {
        return judgeCase;
    }

    /**
     * 判题用例（json 数组）
     */
    public void setJudgeCase(String judgeCase) {
        this.judgeCase = judgeCase;
    }

    /**
     * 判题配置（json 对象）
     */
    public String getJudgeConfig() {
        return judgeConfig;
    }

    /**
     * 判题配置（json 对象）
     */
    public void setJudgeConfig(String judgeConfig) {
        this.judgeConfig = judgeConfig;
    }

    /**
     * 点赞数
     */
    public Integer getThumbNum() {
        return thumbNum;
    }

    /**
     * 点赞数
     */
    public void setThumbNum(Integer thumbNum) {
        this.thumbNum = thumbNum;
    }

    /**
     * 收藏数
     */
    public Integer getFavourNum() {
        return favourNum;
    }

    /**
     * 收藏数
     */
    public void setFavourNum(Integer favourNum) {
        this.favourNum = favourNum;
    }

    /**
     * 创建用户 id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 创建用户 id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 是否删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}