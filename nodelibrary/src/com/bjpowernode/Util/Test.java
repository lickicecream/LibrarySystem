package com.bjpowernode.Util;

import com.bjpowernode.bean.Book;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        String s="book/dsfsdfsdfasdf";
//        System.out.println(s.substring(0,5));

//        try {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1001, "lhm", "male"));
        list.add(new Student(1002, "zzg", "male"));
        list.add(new Student(1003, "wx", "male"));


        Student student = new Student(1001, "jordan", "female");
        Student newStudent = list.stream().filter(ss -> ss.getId() == student.getId()).findFirst().get();
//            newStudent.setName("jordan");
//        newStudent=student.
//        System.out.println(newStudent);
        BeanUtil.populate(student, newStudent);


        for (Student ss : list) {
            System.out.println(ss.toString());
        }

//            throw new IOException("shit bro");
//            m1();

//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }

    public static void m1() throws RuntimeException {
//        throw new RuntimeException("520");
    }
}
