package com.yupi.yoj.model.dto.question;

import lombok.Data;

/**
 * 判题配置
 */
@Data
public class JudgeConfig {


    private Long timeLimit;


    private Long memoryLimit;
}
