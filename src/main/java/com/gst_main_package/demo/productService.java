package com.gst_main_package.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class productService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<product> showAllProducts(){

        return jdbcTemplate.query("select * from products", (rs, rowNum) -> new product(rs.getString("product_code"), rs.getString("product_name"), rs.getDouble("product_price"),rs.getFloat("product_gst")));
    }

    public int addProduct(product item){

        List<product> temp=showSearchedProducts(item.getProduct_code());

        if(temp.size()!=0){
            return 0;
        }

        return jdbcTemplate.update("insert into products (product_code, product_name,product_price,product_gst) values(?,?,?,?)", item.getProduct_code(), item.getProduct_name(),item.getProduct_price(),item.getProduct_gst());

    }

    public int editProduct(product item){

        return jdbcTemplate.update("update products set product_name=?,product_price=?,product_gst=? where product_code = ?", item.getProduct_name(),item.getProduct_price(),item.getProduct_gst(),item.getProduct_code());

    }

    public List<product> showSearchedProducts(String search_word){

        if(search_word.substring(0,2).equalsIgnoreCase("pc")&&Character.isDigit(search_word.charAt(2))){

            return jdbcTemplate.query("select * from products where product_code=\""+search_word+"\"", (rs, rowNum) -> new product(rs.getString("product_code"), rs.getString("product_name"), rs.getDouble("product_price"),rs.getFloat("product_gst")));

        }

        return jdbcTemplate.query("select * from products where product_name=\""+search_word+"\"", (rs, rowNum) -> new product(rs.getString("product_code"), rs.getString("product_name"), rs.getDouble("product_price"),rs.getFloat("product_gst")));

    }

    public int deleteProduct(product item){
        return jdbcTemplate.update("delete from products where product_code = ?", item.getProduct_code());
    }

}
