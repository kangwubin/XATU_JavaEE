package com.projects.modular.comment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 评价
 * </p>
 *
 * @author demo
 * @since 2020-01-07
 */
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "comment_id", type = IdType.ID_WORKER)
    private Long commentId;

    /**
     * 评价人
     */
    @TableField("name")
    private String name;

    /**
     * 评价时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 评价内容
     */
    @TableField("content")
    private String content;
    
    @TableField("lost_id")
    private Long lostId;

    public Long getLostId() {
		return lostId;
	}

	public void setLostId(Long lostId) {
		this.lostId = lostId;
	}

	public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
        "commentId=" + commentId +
        ", name=" + name +
        ", createTime=" + createTime +
        ", content=" + content +
        "}";
    }
}
