package com.yupi.yoj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 题目提交
 * @TableName question_submit
 */
@TableName(value ="question_submit")
public class QuestionSubmit implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 判题信息（json 对象）
     */
    private String judgeInfo;

    /**
     * 判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）
     */
    private Integer status;

    /**
     * 题目 id
     */
    private Long questionId;

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
     * 编程语言
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 编程语言
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 用户代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 用户代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 判题信息（json 对象）
     */
    public String getJudgeInfo() {
        return judgeInfo;
    }

    /**
     * 判题信息（json 对象）
     */
    public void setJudgeInfo(String judgeInfo) {
        this.judgeInfo = judgeInfo;
    }

    /**
     * 判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 题目 id
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * 题目 id
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
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