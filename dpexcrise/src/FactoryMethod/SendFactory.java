package FactoryMethod;

public class SendFactory {
    public Sender produce (String type)
    {
      if("mail".equals(type))
          return new MailSender();
      else  if ("sms".equals(type))
          return  new SmsSender();
      else
          System.out.println("输入错误");
          return null;
    }
}
