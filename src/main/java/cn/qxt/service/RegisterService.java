package cn.qxt.service;

public interface RegisterService {
    String generateVerificationCode();

    boolean sendEmail(String emailAddress,String code);
}
