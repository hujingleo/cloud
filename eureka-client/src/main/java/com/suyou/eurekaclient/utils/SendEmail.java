package com.suyou.eurekaclient.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 发送只带存文本的邮件
 */
public class SendEmail {

    /**
     * 利用定时器发送邮件，设定指定任务task在指定延迟delay后进行固定延迟peroid的执行
     * @param me
     * @param session1
     * @param username 要用的那个邮箱的用户名
     * @param pwd 登录密码
     */
    public static void sendMail(final Message me, final Session session1, final String username, final String pwd){
        Timer timer= new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                /**
                 * 发送邮件
                 */
                Transport ts = null;
                try {
                    ts = session1.getTransport();
                } catch (NoSuchProviderException e) {
                    e.printStackTrace();
                }
                try {
                    ts.connect(username,pwd);//配置所用的账户名和登录密码
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                try {
                    ts.sendMessage(me,me.getAllRecipients());//对象，用实例方法
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        } ,1000,80000);
    }

    /**
     * 设置头部内容
     * @param message
     *
     * @param fromAddress 从哪里发送
     * @param toAddress 发送给谁
     * @param titles 邮件标题
     * @throws MessagingException
     */
    public static void setTop(final MimeMessage message, String fromAddress, String toAddress, String titles) throws MessagingException {
        message.setFrom(new InternetAddress(fromAddress));//从哪个邮件过来
        message.setRecipients(Message.RecipientType.TO,toAddress);//发送到那里？
        message.setSubject(titles);//邮件的标题
    }

    /**
     * 设置正文内容
     * @param message
     * @param contentText 正文内容
     */

    public static void setContent(final MimeMessage message,String contentText ) throws MessagingException {
        message.setContent(contentText,"text/html");

        message.saveChanges();//保存改变
    }
    public static void main(String[] args) throws  Exception{

        Properties props=new Properties();//key value：配置参数，真正发邮件时再配置
        props.setProperty("mail.transport.protocol","smtp");//指定邮件发送的协议，参数是规范guid规定的
        props.setProperty("mail.host","smtp.163.com");//指定发送服务器的地址，参数是规范规定的，163的规范
        props.setProperty("mail.smtp.auth","true");//请求服务器进行身份认证，参数与具体的JavaMail实现有关

        Session session=Session.getInstance(props);//发送有件事使用的环境配置
        session.setDebug(true);//设置调试模式
        MimeMessage message= new MimeMessage(session);
        String fromAddress="hujingleo01@163";//从哪里发出
        String toAddress="13916455415@163.com";//发送给谁
        String titles="来自JAVA程序的邮件";//邮件标题
        String contentText="from leo";//邮件内容
        /**
         * 设置邮件头部
         */

        setTop(message,fromAddress,toAddress,titles);
        /**
         * 设置正文
         */
        setContent(message,contentText);

        message.saveChanges();//保存改变


        /**
         * 发送邮件
         */

        String username ="hujingleo01@163.com";
        String pwd="leo123456";
        sendMail(message,session,username,pwd);



    }
}
