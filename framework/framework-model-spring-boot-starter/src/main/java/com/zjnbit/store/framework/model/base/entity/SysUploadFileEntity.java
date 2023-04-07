package com.zjnbit.store.framework.model.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.store.framework.web.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/21 16:14
 * @Description ${description}
 **/

/**
 * 文件上传
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_upload_file")
public class SysUploadFileEntity extends BaseEntity {
    @TableField(value = "file_path")
    private String filePath;

    @TableField(value = "web_url")
    private String webUrl;

    @TableField(value = "content_type")
    private String contentType;

    @TableField(value = "`size`")
    private Long size;
}