package com.yupi.yoj.model.vo;

import cn.hutool.json.JSONUtil;
import com.yupi.yoj.judge.codesandbox.model.JudgeInfo;
import com.yupi.yoj.model.entity.QuestionSubmit;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目视图
 */
@Data
public class QuestionSubmitVO implements Serializable {
    /**
     * id
     */
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
    private JudgeInfo judgeInfo;

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
     * 提交用户信息
     */
    private UserVO userVO;


    /**
     * 对应题目信息
     */
    private QuestionVO questionVO;

    /**
     * 包装类转对象,属性JAVA对象转为字符串
     *
     * @param questionSubmitVO
     * @return
     */
    public static QuestionSubmit voToObj(QuestionSubmitVO questionSubmitVO) {
        if (questionSubmitVO == null) {
            return null;
        }
        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtils.copyProperties(questionSubmitVO, questionSubmit);
        JudgeInfo voJudgeInfo = questionSubmitVO.getJudgeInfo();
        if(voJudgeInfo != null){
            String jsonStr = JSONUtil.toJsonStr(voJudgeInfo);
            questionSubmit.setJudgeInfo(jsonStr);
        }
        return questionSubmit;
    }

    /**
     * 对象转包装类,把json字符串转为java对象
     *
     * @param questionSubmit
     * @return
     */
    public static QuestionSubmitVO objToVo(QuestionSubmit questionSubmit) {
        if (questionSubmit == null) {
            return null;
        }
        QuestionSubmitVO questionSubmitVO = new QuestionSubmitVO();
        BeanUtils.copyProperties(questionSubmit, questionSubmitVO);
        String info = questionSubmit.getJudgeInfo();
        questionSubmitVO.setJudgeInfo(JSONUtil.toBean(info,JudgeInfo.class));
        return questionSubmitVO;
    }

    private static final long serialVersionUID = 1L;
}
