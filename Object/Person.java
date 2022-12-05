package Object;

public class Person {
    int age;
    String name;
    Person(int a,String nn){
        this.age=a;
        this.name=nn;
    }
    Person(){
        ;
    }
    Person(Person p1){
        this.age=p1.age;
        this.name=p1.name;
    }
     void print(){
         System.out.println(this.age+this.name);
    }
    public static Person copyPerson(Person p1){
        Person cp=new Person(p1);
        return cp;
    }

    public static Person copyPerson(int a,String n){
        /*
        同上面的方法为重载
         */
        Person cp=new Person(a,n);
        return cp;
    }
    private String hobby;
}
