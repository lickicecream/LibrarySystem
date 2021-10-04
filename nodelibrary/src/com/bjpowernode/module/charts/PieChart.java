package com.bjpowernode.module.charts;

import com.bjpowernode.bean.Constant;
import com.bjpowernode.service.ChartService;
import com.bjpowernode.service.impl.ChartServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author admin
 */
public class PieChart implements Initializable {

    @FXML
    private javafx.scene.chart.PieChart pieChart;

    ChartService chartService=new ChartServiceImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Map<String,Integer>bookTypeCount=chartService.bookTypeCount();
        ObservableList<Data> pieChartData = FXCollections.observableArrayList(
                new Data("�����", bookTypeCount.get(Constant.TYPE_COMPUTER)),
                new Data("��ѧ", bookTypeCount.get(Constant.TYPE_LITERATURE)),
                new Data("����", bookTypeCount.get(Constant.TYPE_ECONOMY)),
                new Data("����", bookTypeCount.get(Constant.TYPE_MANAGEMENT))
        );
        pieChart.setData(pieChartData);
        pieChart.setClockwise(false);
    }
}
