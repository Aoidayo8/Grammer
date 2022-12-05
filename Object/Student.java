package Object;

public class Student extends Person{
    String Sid;//student id学生学号
    Student(){
        super();
    }
    Student(int age,String name){
        super(age,name);
    }
    void accessTest(){
//        super.hobby; //no
        /*
        private的属性和方法子类不能访问
         */
        System.out.println(super.age+super.name);//ok
    }
    @Override
    void print(){
        System.out.println(this.age+this.name+this.Sid);
    }
}
