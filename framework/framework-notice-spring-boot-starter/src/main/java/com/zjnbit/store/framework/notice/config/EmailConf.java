package com.zjnbit.store.framework.notice.config;

import cn.hutool.extra.mail.MailAccount;
import lombok.Data;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/30 08:48
 * @Description
 **/
@Data
public class EmailConf {
    private Long id;
    private Boolean isDefault;
    private Value value;

    public MailAccount toMailAccount() {
        MailAccount account = new MailAccount();
        account.setHost(this.value.getHost());
        account.setPort(this.value.getPort());
        account.setFrom(this.value.getFrom());
        account.setUser(this.value.getUser());
        account.setAuth(this.value.getAuth());
        account.setPass(this.value.getPass());
        account.setSslEnable(this.value.getSslEnable());
        return account;
    }

    @Data
    public static class Value {
        private String host;
        private Integer port;
        private String from;
        private String user;
        private Boolean auth;
        private String pass;
        private Boolean sslEnable;

    }
}
