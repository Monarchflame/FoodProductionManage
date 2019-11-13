package cn.qxt.service;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService{

    /**
     * 生成验证码
     * @return String
     */
    public String generateVerificationCode()
    {
        int verificationCode = 0;
        int i=4;
        while (i-->0)
        {
            verificationCode = 10 * verificationCode + (int)(Math.random() *10);
        }
        return verificationCode+"";
    }

    /**
     * 发送邮箱验证码
     * @param emailAddress
     * @param code
     * @return
     */
    //邮箱验证码
    public boolean sendEmail(String emailAddress,String code){
        try {
            HtmlEmail email = new HtmlEmail();//不用更改
            email.setHostName("smtp.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setCharset("UTF-8");
            email.addTo(emailAddress);// 收件地址

            email.setFrom("1070331843@qq.com", "食品厂");//此处填邮箱地址和用户名,用户名可以任意填写

            email.setAuthentication("1070331843@qq.com", "nmhzwsisolupbbig");//此处填写邮箱地址和客户端授权码

            email.setSubject("验证码");//此处填写邮件名，邮件名可任意填写
            //email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + code);//此处填写邮件内容
            email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + code);
            email.send();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
