import cn.qxt.dao.ClientDao;
import cn.qxt.dao.GoodsDao;
import cn.qxt.dao.GoodsReturnOrderDao;
import cn.qxt.dao.OrderDao;
import org.apache.commons.mail.HtmlEmail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class text {
    @Autowired
    ClientDao clientDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    GoodsDao goodsDao;

    @Test
    public void test() {
        Date expiration_time = new Date();
        System.out.println(expiration_time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(expiration_time);//设置起时间
        cal.add(Calendar.DATE, 30);
        expiration_time = cal.getTime();
        System.out.println(expiration_time);

    }
    @Test
    public void test2() {
        int i = goodsDao.selectAllRepertoryByProductId(2);
        System.out.println(i);

    }    //邮箱验证码
    public static boolean sendEmail(String emailAddress,String code){
        try {
            HtmlEmail email = new HtmlEmail();//不用更改
            email.setHostName("smtp.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setCharset("UTF-8");
            email.addTo(emailAddress);// 收件地址

            email.setFrom("1070331843@qq.com", "齐笑天");//此处填邮箱地址和用户名,用户名可以任意填写

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
