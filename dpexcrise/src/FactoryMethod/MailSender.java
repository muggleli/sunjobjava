package FactoryMethod;

public class MailSender implements  Sender {
    @Override
    public void Sender() {
        System.out.println("this is mailsender!");
    }
}
