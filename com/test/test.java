package com.test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 测试git上传
 */
public class test {
    public static void main(String[] args) {
        TestArrayCopy();
    }
    static void TestSwitch(){
        int a;
        Scanner sc=new Scanner(System.in);
        while((a=sc.nextInt())!=0){
            switch (a){
                case 1:{
                    System.out.println(1);
                    break;//结束switch,不再进行其他匹配
                }
                case 2:{
                    System.out.println(2);
                    //若匹配执行该语句
                    //若不匹配,执行下一个匹配,顺序向下匹配
                }
                case 3:{
                    System.out.println(3);
                }
                default:{
                    System.out.println("default");
                    //break;
                    /*
                    default中不用写break
                    执行结束后switch结束
                     */
                }
            }
        }
    }

    static void TestContinue(){
        int a=10;
        int i;
        for(i=1;
            i<12;
            i++){
            if(i>a)continue;
            /*
            将for语句分开写,打断点运行时会发现在i=11时,执行continue跳转到for3执行
             */
            //如果不执行for3语句将会无限循环
        }
        System.out.println("a:"+a+" i:"+i);
    }

    static void TestArrayCopy(){
        //数组元素为基本类型时
        int[] a={1,2,3};
        int[] b=a.clone();//一维深拷贝
        b[0]=2;
        System.out.println(a[0]+" "+b[0]);

        //数组元素为引用类型时
        int[][] a1={{1,2,3},{4,5,6},{7,8,9}};
        int[][] b1=a1.clone();
        b1[0][0]=2;
        System.out.println(a1[0][0]);//2
        b1[1]=a;
        System.out.println(a1[1][1]+" "+b1[1][1]);//浅拷贝,只拷贝第一层

        int[][]a2={{1,2,3},{4,5,6},{7,8,9}};
        int[][]b2=new int[a2.length][];
        for(int i=0;i<a2.length;i++)b2[i]=a2[i].clone();//O(n^2)

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(a1));
        System.out.println(Arrays.deepToString(a2));
    }

}

