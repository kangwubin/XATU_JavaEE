package com.projects.modular.api.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 校园公告
 * </p>
 *
 * @author demo
 * @since 2019-08-03
 */
@Data
public class NoticeResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文本
     */
    private String content;

    /**
     * 发布时间
     */
    private Date createTime;

}
