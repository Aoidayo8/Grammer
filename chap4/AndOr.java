package chap4;

public class AndOr {
  void testLogicAnd(){
      //测试逻辑与
      int a=10;
      int b=100;
      if(a<10 & ++b>100) System.out.println("Yes");//不输出
      else System.out.println("false");//输出
      System.out.println("b:"+b);//101
      //逻辑与计算b,b=101
  }
    void testLogicOr() {
        //测试逻辑或
        int a = 10;
        int b = 100;
        if (a <= 10 | ++b > 100) System.out.println("Yes");//输出
        else System.out.println("false");//不输出
        System.out.println("b:" + b);//101
        //逻辑或,不短路,计算b,b=101
    }
    /**
     * 反之,使用短路与或者短路或的时候,一旦短路,后面的条件不再计算
     * 改:使用&&||时,b=100
     */
}
