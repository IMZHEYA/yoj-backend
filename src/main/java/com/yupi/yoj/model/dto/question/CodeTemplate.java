package com.yupi.yoj.model.dto.question;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 诨号无敌鸭
 */
@NoArgsConstructor
@Data
public class CodeTemplate {
    private String java;
    private String python;
    private String cpp;
    private String c;

}
