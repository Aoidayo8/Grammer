package chap4;

public class Shift {
    void ssShit(){
        int a=1<<2;
        System.out.println(a);//4
        int b=4>>2;
        System.out.println(b);//1
        int x=98;
        System.out.println(x>>1);//等价于x/2
        System.out.println(x<<1);//等价于x*2
    }
}
