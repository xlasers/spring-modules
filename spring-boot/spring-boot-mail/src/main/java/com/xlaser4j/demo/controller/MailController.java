package com.xlaser4j.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/2/6 15:50
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class MailController {
    private final JavaMailSender sender;

    private final TemplateEngine engine;

    public MailController(JavaMailSender sender, TemplateEngine engine) {
        this.sender = sender;
        this.engine = engine;
    }

    /**
     * simple
     *
     * @return
     */
    @GetMapping("/s")
    public String send() {
        // 群发
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("from-email@qq.com");
        msg.setTo("to-email@gmail.com ", "to-email@outlook.com");
        msg.setSentDate(new Date());
        msg.setSubject("邮件主题!");
        msg.setText("邮件内容");
        sender.send(msg);

        // 抄送
        SimpleMailMessage cc = new SimpleMailMessage();
        cc.setFrom("from-email@qq.com");
        cc.setTo("test-email@qq.com");
        cc.setCc("to-email@gmail.com ", "to-email@outlook.com");
        cc.setSentDate(new Date());
        cc.setSubject("邮件主题!");
        cc.setText("邮件内容");
        sender.send(cc);

        // 密抄
        SimpleMailMessage bcc = new SimpleMailMessage();
        bcc.setFrom("from-email@qq.com");
        bcc.setTo("test-email@qq.com");
        bcc.setBcc("to-email@gmail.com ", "to-email@outlook.com");
        bcc.setSentDate(new Date());
        bcc.setSubject("邮件主题!");
        bcc.setText("邮件内容");
        sender.send(bcc);

        return "Simple: 发送邮件成功!";
    }

    /**
     * attachment带附件邮件:{@link javax.mail.internet.MimeMessage}
     * <p>
     * {@link org.springframework.mail.javamail.MimeMessageHelper}构建邮件内容
     * <p>
     * multipart whether to create a multipart message that supports alternative texts, inline
     * elements and attachments (corresponds to MULTIPART_MODE_MIXED_RELATED)
     * 非文本形式的邮件需要设置multipart为true
     *
     * @return
     */
    @GetMapping("/a")
    public String sendWithAttachment() throws MessagingException, FileNotFoundException {
        MimeMessage msg = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom("from-email@qq.com");
        helper.setTo("to-email@outlook.com");
        helper.setSentDate(new Date());
        helper.setSubject("邮件主题!");
        helper.setText("邮件内容");
        helper.addAttachment("06161043.png", ResourceUtils.getFile("classpath:06161043.png"));

        sender.send(msg);
        return "Attachment: 发送邮件成功!";
    }

    /**
     * 内容带图片邮件:{@link javax.mail.internet.MimeMessage}内容中设置为html,然后拼接html标签
     * <p>
     * {@link org.springframework.mail.javamail.MimeMessageHelper}构建邮件内容
     * <p>
     * multipart whether to create a multipart message that supports alternative texts, inline
     * elements and attachments (corresponds to MULTIPART_MODE_MIXED_RELATED)
     * 非文本形式的邮件需要设置multipart为true
     *
     * @return
     */
    @GetMapping("/p")
    public String sendWithPicture() throws MessagingException, FileNotFoundException {
        MimeMessage msg = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom("from-email@qq.com");
        helper.setTo("to-email@outlook.com");
        helper.addCc("to-email@gmail.com");
        helper.setSentDate(new Date());
        helper.setSubject("邮件主题!");
        File picture = ResourceUtils.getFile("classpath:06161043.png");
        helper.addAttachment("06161043.png", picture);
        helper.setText("邮件内容p1:<img src='cid:p1'/>,p2:<img src='cid:p2'/>", true);
        helper.addInline("p1", picture);
        helper.addInline("p2", picture);

        sender.send(msg);
        return "Picture: 发送邮件成功!";
    }

    /**
     * thymeleaf发送邮件{@link org.thymeleaf.TemplateEngine}无需过多配置,方便易用
     * <p>
     * 利用模板引擎解析填充页面,再转换为text发送{@link org.thymeleaf.TemplateEngine#process(String, IContext)}
     *
     * @return
     */
    @GetMapping("/t")
    public String sendByThymeleaf() throws MessagingException {
        MimeMessage msg = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom("from-email@qq.com");
        helper.setTo("to-email@outlook.com");
        helper.setCc("to-email@gmail.com");
        helper.setSentDate(new Date());
        helper.setSubject("邮件主题!");

        Context context = new Context();
        context.setVariable("to", "thymeleaf");
        context.setVariable("name", "Martin");
        context.setVariable("age", 30);
        context.setVariable("gender", "Male");
        context.setVariable("content", "Hello");

        String text = engine.process("thymeleaf.html", context);
        helper.setText(text, true);

        sender.send(msg);
        return "Thymeleaf: 发送邮件成功!";
    }

    /**
     * freemarker发送邮件,与thymeleaf相似
     * <p>
     * 没有thymeleaf自动化配置完全,不建议使用!
     *
     * @return
     */
    @GetMapping("/f")
    public String sendByFreemarker() throws MessagingException, IOException, TemplateException {
        MimeMessage msg = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom("from-email@qq.com");
        helper.setTo("to-email@outlook.com");
        helper.setSentDate(new Date());
        helper.setSubject("邮件主题!");

        // 指明对应依赖版本:org.freemarker:freemarker:2.3.28
        Configuration config = new Configuration(Configuration.VERSION_2_3_28);
        config.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "templates");
        Template template = config.getTemplate("freemarker.ftl");

        Map<String, Object> model = new HashMap<>();
        model.put("to", "freemarker");
        model.put("name", "Martin");
        model.put("age", 30);
        model.put("gender", "Male");

        StringWriter writer = new StringWriter();
        template.process(model, writer);
        helper.setText(writer.toString(), true);

        sender.send(msg);
        return "Freemarker: 发送邮件成功!";
    }
}
