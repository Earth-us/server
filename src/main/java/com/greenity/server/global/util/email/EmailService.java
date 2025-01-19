package com.greenity.server.global.util.email;

import com.greenity.server.global.util.redis.RedisService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpTime;

    private final JavaMailSender mailSender;
    private final RedisService redisService;

    public void sendMail(String receiver) {
        try {
            String authCode = createAuthCode();

            MimeMessage message = mailSender.createMimeMessage();

            message.setSubject("[이메일 인증]");
            message.setText("<h3>이메일 인증 번호 : " + authCode + "</h3>", "utf-8", "html");
            message.setFrom(sender);
            message.addRecipients(MimeMessage.RecipientType.TO, receiver);

            mailSender.send(message);

            redisService.saveAuthCode(receiver, authCode, authCodeExpTime);

        } catch (MessagingException e) {
            throw new RuntimeException("메일 생성 실패");
        } catch (MailException e) {
            throw new RuntimeException("메일 전송 실패");
        }
    }

    public String createAuthCode() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }

}
