package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.dao.ChartDao;
import jdk.nashorn.internal.ir.WhileNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartDaoImpl implements ChartDao {
    @Override
    public Map<String, Integer> bookTypeCount() {
        Map<String, Integer> countData = new HashMap<>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File(PathConstant.BOOK_PATH)));
            List<Book> list = (List<Book>) ois.readObject();
            List<Book>wenXue=getSameBookList(list,Constant.TYPE_LITERATURE);
//            老方法
            countData.put(Constant.TYPE_LITERATURE, getSameBookList(list, Constant.TYPE_LITERATURE).size());
            countData.put(Constant.TYPE_COMPUTER, getSameBookList(list, Constant.TYPE_COMPUTER).size());
            countData.put(Constant.TYPE_ECONOMY, getSameBookList(list, Constant.TYPE_ECONOMY).size());
            countData.put(Constant.TYPE_MANAGEMENT, getSameBookList(list, Constant.TYPE_MANAGEMENT).size());
            return countData;

            //stream流分类统计
//            Map<String, List<Book>> collect = list.stream().collect(Collectors.groupingBy(Book::getType));
//            Map<String, Integer> map = new HashMap<>();
//            Iterator<Map.Entry<String, List<Book>>> iterator = collect.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<String, List<Book>> next = iterator.next();
//                map.put(next.getKey(), next.getValue() == null ? 0 : next.getValue().size());
//            }
//            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("数据异常联系管理员");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//
    }

    List<Book> getSameBookList(List<Book> list, String type) {
        if (list != null) {
            return list.stream().filter(b -> b.getType().equals(type)).collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
