import cn.qxt.dao.*;
import cn.qxt.pojo.GoodsRecord;
import cn.qxt.pojo.GoodsReturnOrder;
import cn.qxt.pojo.GoodsReturnOrderExample;
import org.apache.commons.mail.HtmlEmail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class text {
    @Autowired
    ClientDao clientDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    GoodsRecordDao goodsRecordDao;

    @Test
    public void test() {
        GoodsRecord goodsRecord = new GoodsRecord();
        //Mapper中设置insertSelective方法，可以得到goods的主键
        goodsRecord.setGoods_id(1);
        goodsRecord.setProduct_id(1);
        goodsRecord.setQuantity(100);
        goodsRecord.setType("入库");
        goodsRecord.setExternal_key(1);
        goodsRecordDao.insertSelective(goodsRecord);
    }

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
