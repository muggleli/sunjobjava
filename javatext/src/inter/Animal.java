package inter;

public interface Animal {
    int ID = 1;
    void breathe();
    default void getType(String type){
        System.out.println("该动物属于:"+type);
    }
    static  int getID(){
        return  Animal.ID;
    }
}
