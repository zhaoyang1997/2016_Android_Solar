package net.solar.server.solar.util;

import android.util.Log;

import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Created by CD on 2017/12/20
 * QQ邮箱设置规则
 * 如何设置IMAP服务的SSL加密方式？
 * 使用SSL的通用配置如下：
 * 发送邮件服务器：smtp.qq.com，使用SSL，端口号465或587
 * 账户名：您的QQ邮箱账户名（如果您是VIP帐号或Foxmail帐号，账户名需要填写完整的邮件地址）
 * 密码：授权码
 * 电子邮件地址：您的QQ邮箱的完整邮件地址
 */
public class EmailUtil {
    private static final String TAG = "EmailUtil";
	private String MAIL_FROM_EMAIL = "562479963@qq.com";//发件人邮箱地址（必填自己申请的）
    private String MAIL_FROM_PWD = "xmoyrexwjgylbbdj";//授权码（必填自己申请的）：成功开启IMAP/SMTP服务，在第三方客户端登录时，腾讯提供的授权码。注意不是邮箱密码
    private String MAIL_FROM_NAME = "Solar";//发件人用户名(随意改写)
    private String MAIL_TO_NAME = "shoujianren";//收件人用户名(随意改写)
    private boolean isDebug=true;//是否打印发送邮件的调试提示信息

    //发送方QQ邮箱通用设置
    private final String MAIL_SMTP_HOST = "smtp.qq.com";//发送邮件服务器：smtp.qq.com
    private final String MAIL_SMTP_PORT = "587";//使用465或587端口 
    private final String MAIL_SMTP_AUTH = "true";//设置使用验证
    private final String MAIL_SMTP_STARTTLS_ENABLE = "true";//使用 STARTTLS安全连接
    private static EmailUtil instance;
    public static EmailUtil getInstance() {
        if (instance == null) {
            synchronized (EmailUtil.class) {
                if (instance == null) {
                    instance = new EmailUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 发送邮箱验证码
     * @param emailTo 接收邮件用户的邮箱地址
     * @param subject 发送邮件主题
     * @param content 发送邮件内容
     * @return
     */
    public void sendEmail(String emailTo,String subject,String content){
        Properties props = new Properties();
        props.put("mail.smtp.host", MAIL_SMTP_HOST);
        props.put("mail.smtp.port", MAIL_SMTP_PORT);//使用465或587端口
        props.put("mail.smtp.auth", MAIL_SMTP_AUTH);//设置使用验证
        props.put("mail.smtp.starttls.enable",MAIL_SMTP_STARTTLS_ENABLE);//使用 STARTTLS安全连接
        try {
            PopupAuthenticator auth = new PopupAuthenticator();
            Session session = Session.getInstance(props, auth);
            session.setDebug(isDebug);//打印Debug信息
            MimeMessage message = new MimeMessage(session);
            Address addressFrom = new InternetAddress(MAIL_FROM_EMAIL, MAIL_FROM_NAME);//第一个参数为发件人邮箱地址；第二个参数为发件人用户名(随意改写)
            Address addressTo = new InternetAddress(emailTo, MAIL_TO_NAME);//第一个参数为接收方电子邮箱地址；第二个参数为接收方用户名
            message.setSubject(subject);
            message.setText(content);
            message.setFrom(addressFrom);
            message.addRecipient(Message.RecipientType.TO, addressTo);
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect(MAIL_SMTP_HOST, MAIL_FROM_EMAIL,MAIL_FROM_PWD);
            transport.send(message);
            transport.close();
            if(isDebug){
                Log.i(TAG,"----发送成功----");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            Log.i(TAG,"----发送失败----");
        }
    }


    public static String verifyCode() {
        Random random = new Random();
        String str = "";
        for (int i = 0; i < 6; i++) {
            int code = random.nextInt(10);
            str += code;
        }
        return str;
    }

    class PopupAuthenticator extends Authenticator {
        public PopupAuthenticator() {
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(MAIL_FROM_EMAIL, MAIL_FROM_PWD);
        }
    }
}
