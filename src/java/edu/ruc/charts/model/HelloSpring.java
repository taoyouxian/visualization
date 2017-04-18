package edu.ruc.charts.model;

public class HelloSpring {
    // 需要注入的属性
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {

        this.str = str;
    }

    public void print() {
        System.out.println("Hello, " + this.str);
    }

}
