package com.zhu.core.user.bean.eneity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 全局唯一id
     */
    @TableField(value = "user_id")
    private String user_id;

    /**
     * 账号名
     */
    @TableField(value = "account_name")
    private String account_name;

    /**
     * 头像地址
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 客户标识(预留)
     */
    @TableField(value = "customer_ide")
    private String customer_ide;

    /**
     * 主账户:MASTER_ACCOUNT,子账户:SUB_ACCOUNT
     */
    @TableField(value = "account_type")
    private String account_type;

    /**
     * 联系人
     */
    @TableField(value = "contact")
    private String contact;

    /**
     * 父ID
     */
    @TableField(value = "parent_id")
    private String parent_id;

    /**
     * 手机区号
     */
    @TableField(value = "region_code")
    private String region_code;

    /**
     * 手机号码
     */
    @TableField(value = "phone_num")
    private String phone_num;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 微信openId
     */
    @TableField(value = "we_chat_open_id")
    private String we_chat_open_id;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 加密盐值
     */
    @TableField(value = "salt")
    private String salt;

    /**
     * 状态 禁用：DISABLE 启用：ENABLE 冻结：FREEZE
     */
    @TableField(value = "status")
    private String status;

    /**
     * 最后登入时间【预留字段】
     */
    @TableField(value = "last_login_time")
    private LocalDateTime last_login_time;

    /**
     * 渠道
     */
    @TableField(value = "business_code")
    private String business_code;

    /**
     * 账号描述
     */
    @TableField(value = "user_desc")
    private String user_desc;

    /**
     * 删除标识
     */
    @TableField(value = "del_flag")
    private Integer del_flag;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 创建人
     */
    @TableField(value = "create_id")
    private String create_id;

    /**
     * 修改人
     */
    @TableField(value = "modifier_id")
    private String modifier_id;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create")
    private LocalDateTime gmt_create;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified")
    private LocalDateTime gmt_modified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
            && (this.getAccount_name() == null ? other.getAccount_name() == null : this.getAccount_name().equals(other.getAccount_name()))
            && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
            && (this.getCustomer_ide() == null ? other.getCustomer_ide() == null : this.getCustomer_ide().equals(other.getCustomer_ide()))
            && (this.getAccount_type() == null ? other.getAccount_type() == null : this.getAccount_type().equals(other.getAccount_type()))
            && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()))
            && (this.getParent_id() == null ? other.getParent_id() == null : this.getParent_id().equals(other.getParent_id()))
            && (this.getRegion_code() == null ? other.getRegion_code() == null : this.getRegion_code().equals(other.getRegion_code()))
            && (this.getPhone_num() == null ? other.getPhone_num() == null : this.getPhone_num().equals(other.getPhone_num()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getWe_chat_open_id() == null ? other.getWe_chat_open_id() == null : this.getWe_chat_open_id().equals(other.getWe_chat_open_id()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getLast_login_time() == null ? other.getLast_login_time() == null : this.getLast_login_time().equals(other.getLast_login_time()))
            && (this.getBusiness_code() == null ? other.getBusiness_code() == null : this.getBusiness_code().equals(other.getBusiness_code()))
            && (this.getUser_desc() == null ? other.getUser_desc() == null : this.getUser_desc().equals(other.getUser_desc()))
            && (this.getDel_flag() == null ? other.getDel_flag() == null : this.getDel_flag().equals(other.getDel_flag()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreate_id() == null ? other.getCreate_id() == null : this.getCreate_id().equals(other.getCreate_id()))
            && (this.getModifier_id() == null ? other.getModifier_id() == null : this.getModifier_id().equals(other.getModifier_id()))
            && (this.getGmt_create() == null ? other.getGmt_create() == null : this.getGmt_create().equals(other.getGmt_create()))
            && (this.getGmt_modified() == null ? other.getGmt_modified() == null : this.getGmt_modified().equals(other.getGmt_modified()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getAccount_name() == null) ? 0 : getAccount_name().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getCustomer_ide() == null) ? 0 : getCustomer_ide().hashCode());
        result = prime * result + ((getAccount_type() == null) ? 0 : getAccount_type().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        result = prime * result + ((getParent_id() == null) ? 0 : getParent_id().hashCode());
        result = prime * result + ((getRegion_code() == null) ? 0 : getRegion_code().hashCode());
        result = prime * result + ((getPhone_num() == null) ? 0 : getPhone_num().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getWe_chat_open_id() == null) ? 0 : getWe_chat_open_id().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getLast_login_time() == null) ? 0 : getLast_login_time().hashCode());
        result = prime * result + ((getBusiness_code() == null) ? 0 : getBusiness_code().hashCode());
        result = prime * result + ((getUser_desc() == null) ? 0 : getUser_desc().hashCode());
        result = prime * result + ((getDel_flag() == null) ? 0 : getDel_flag().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreate_id() == null) ? 0 : getCreate_id().hashCode());
        result = prime * result + ((getModifier_id() == null) ? 0 : getModifier_id().hashCode());
        result = prime * result + ((getGmt_create() == null) ? 0 : getGmt_create().hashCode());
        result = prime * result + ((getGmt_modified() == null) ? 0 : getGmt_modified().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", user_id=").append(user_id);
        sb.append(", account_name=").append(account_name);
        sb.append(", avatar=").append(avatar);
        sb.append(", customer_ide=").append(customer_ide);
        sb.append(", account_type=").append(account_type);
        sb.append(", contact=").append(contact);
        sb.append(", parent_id=").append(parent_id);
        sb.append(", region_code=").append(region_code);
        sb.append(", phone_num=").append(phone_num);
        sb.append(", email=").append(email);
        sb.append(", we_chat_open_id=").append(we_chat_open_id);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", status=").append(status);
        sb.append(", last_login_time=").append(last_login_time);
        sb.append(", business_code=").append(business_code);
        sb.append(", user_desc=").append(user_desc);
        sb.append(", del_flag=").append(del_flag);
        sb.append(", remark=").append(remark);
        sb.append(", create_id=").append(create_id);
        sb.append(", modifier_id=").append(modifier_id);
        sb.append(", gmt_create=").append(gmt_create);
        sb.append(", gmt_modified=").append(gmt_modified);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}