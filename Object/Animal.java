package Object;

public class Animal {
    String name="动物";
    int age;
    void eat(){
        System.out.println("Animal::吃喝拉撒睡");
    }
}
class Cat extends Animal{
    void catchMouse(){
        System.out.println("Cat::抓老鼠");
    }
}