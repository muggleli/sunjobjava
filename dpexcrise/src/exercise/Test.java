package exercise;

 import java.util.Scanner;
 public class Test { public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                String name = sc.next();
               int age = sc.nextInt();
                String sex = sc.next();
                Person p = new Person(name,age,sex);
                p.display();
           }
  }

   
  class Person{
      String name = "张三";
      int age = 18;
      String sex = "男";
      public Person(String name,int age,String sex){
                   this(age);
                   name = name;
                   this.sex = sex;
              }
       public Person(int age){
                   age = age;
              }
       public void display(){
                  String name = "李四";
                   int age = 11;
                 String sex = "男";
                  System.out.println("name：" + this.name);
                   System.out.println("age：" + this.age);
                  System.out.println("sex：" + sex);
              }
   }