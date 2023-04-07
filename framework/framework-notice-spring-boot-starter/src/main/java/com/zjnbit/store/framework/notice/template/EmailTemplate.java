package com.zjnbit.store.framework.notice.template;

import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.zjnbit.store.framework.notice.config.EmailConf;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author chenjy
 * @emp chenjy
 * @date 2023/3/30 08:57
 * @Description
 **/
@Slf4j
public abstract class EmailTemplate {
    public Map<String, EmailConf> config = MapUtil.newHashMap();
    private MailAccount account;

    public EmailTemplate(Map<String, EmailConf> config) {
        this.config = config;
        for (EmailConf emailConf : config.values()) {
            if (emailConf.getIsDefault()) {
                this.account = getAccount(emailConf);
                break;
            }
        }
    }

    private MailAccount getAccount(EmailConf emailConf) {
        MailAccount account = new MailAccount();
        account.setHost(emailConf.getValue().getHost());
        account.setPort(emailConf.getValue().getPort());
        account.setAuth(emailConf.getValue().getAuth());
        account.setFrom(emailConf.getValue().getFrom());
        account.setUser(emailConf.getValue().getUser());
        account.setPass(emailConf.getValue().getPass());
        account.setSslEnable(emailConf.getValue().getSslEnable());
        return account;
    }

    private MailAccount getAccount(String providerName) {
        EmailConf emailConf = this.config.get(providerName);
        return getAccount(emailConf);
    }

    public abstract void init();

    private String sendSimpleMail(MailAccount account, String to, String subject, String content) {
        if (null == account) {
            return this.sendSimpleMail(to, subject, content);
        }
        String result = MailUtil.send(account, to, subject, content, false);
        log.info("send simple email to 【{}】 with subject 【{}】 and content 【{}】 result is 【{}】", to, subject, content, result);
        return result;
    }

    public String sendSimpleMail(String to, String subject, String content) {
        return sendSimpleMail(account, to, subject, content);
    }

    public String sendSimpleMail(String providerName, String to, String subject, String content) {
        return this.sendSimpleMail(getAccount(providerName), to, subject, content);
    }
}
