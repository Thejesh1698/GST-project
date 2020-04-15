package com.gst_main_package.demo;

public class product {
    private String product_code;
    private String product_name;
    private double product_price;
    private float product_gst;

    public product() {
    }

    public product(String product_code, String product_name, double product_price, float product_gst) {
        this.product_code = product_code;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_gst = product_gst;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public float getProduct_gst() {
        return product_gst;
    }

    public void setProduct_gst(float product_gst) {
        this.product_gst = product_gst;
    }
}
