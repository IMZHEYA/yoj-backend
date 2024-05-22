package com.yupi.yoj.model.dto.question;

import lombok.Data;

/**
 * 判题配置
 */
@Data
public class judgeConfig {


    private Long timeLimit;


    private Long memoryLimit;
}
