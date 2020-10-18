package inter;

public class Dog implements Animal {
    @Override
    public void breathe() {
        System.out.println("我重写Animal的方法");
    }

    public static void main(String[] args) {
        System.out.println(Animal.getID());
        Dog dog = new Dog();
        System.out.println(dog.ID);
        dog.breathe();
        dog.getType("犬科");
    }
}
