package com.gst_main_package.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class ApplicationController {

    private ArrayList<String> products_in_cart=new ArrayList<String>();
    private ArrayList<String> product_codes_in_cart=new ArrayList<String>();

    @Autowired
    private productService ps;

    @RequestMapping("/")
    public ModelAndView product_entry(){

        ModelAndView mv= new ModelAndView();
        mv.setViewName("product_entry");
        String info=getAllProducts(ps.showAllProducts(),0);
        mv.addObject("info",info);
        mv.addObject("error","");

        return mv;
    }



    @RequestMapping("/billing_page")
    public ModelAndView billing_page(){

        products_in_cart.clear();

        ModelAndView mv= new ModelAndView();
        mv.setViewName("billing_page");

        mv.addObject("search_result","<tr><td>Search Something to view here :)</td></tr>");
        mv.addObject("cart","<tr><td colspan='4'>No products in the cart currently</td></tr>");
        mv.addObject("error","");

        return mv;
    }




    @PostMapping("/add_product")
    public ModelAndView addProduct(@RequestParam("product_code") String product_code, @RequestParam("product_name") String product_name, @RequestParam("product_price") String product_price, @RequestParam("product_gst") String product_gst){

        product item=new product(product_code.toLowerCase(),product_name,Double.parseDouble(product_price),Float.parseFloat(product_gst));

        int res=ps.addProduct(item);

        ModelAndView mv= new ModelAndView();
        mv.setViewName("product_entry");

        if(res==1){
            String info=getAllProducts(ps.showAllProducts(),0);
            mv.addObject("info",info);
            mv.addObject("error","");
        }
        else{
            String info=getAllProducts(ps.showAllProducts(),0);
            mv.addObject("info",info);
            mv.addObject("error","!!! Sorry. Given product code is already taken !!!");
        }
        return mv;
    }




    @PostMapping("/edit_product")
    public ModelAndView editProduct(@RequestParam("product_code") String product_code, @RequestParam("product_name") String product_name, @RequestParam("product_price") String product_price, @RequestParam("product_gst") String product_gst,@RequestParam("submit") String submit){

        product item=new product(product_code,product_name,Double.parseDouble(product_price),Float.parseFloat(product_gst));

        int res=0;


        if(submit.equals("Edit")) {
            res = ps.editProduct(item);
        }
        else{
            res= ps.deleteProduct(item);
        }

        ModelAndView mv= new ModelAndView();
        mv.setViewName("product_entry");

        if(res==1){
            String info=getAllProducts(ps.showAllProducts(),0);
            mv.addObject("info",info);
            mv.addObject("error","");
        }
        else{
            mv.addObject("error","!!! Problem at server side to edit/delete a product. Please try again after sometime !!!");
        }

        return mv;
    }



    @PostMapping("/search")
    public ModelAndView search(@RequestParam("search_word") String search_word){

        ModelAndView mv=new ModelAndView();
        mv.setViewName("billing_page");

        String table_heading="<tr><th>Product Code</th><th>Product Name</th><th>Price</th><th>GST %</th><th>Add to Cart</th></tr>";
        String info=table_heading;
        info+=getAllProducts(ps.showSearchedProducts(search_word),1);

        if(info.equals(table_heading)){
           info="<tr><td colspan='5'>Oops!! Cannot find given product in the database !!</td></tr>";
        }

        mv.addObject("search_result",info);

        String cart=get_products_from_cart();
        mv.addObject("cart",cart);
        mv.addObject("error","");

        return mv;
    }



    @PostMapping("/add_to_cart")
    public String add_to_cart( Model mv, @RequestParam("product_code") String product_code, @RequestParam("product_name") String product_name, @RequestParam("product_price") String product_price, @RequestParam("product_gst") String product_gst){

        if(product_codes_in_cart.contains(product_code)){
            mv.addAttribute("cart",get_products_from_cart());
            mv.addAttribute("error","!!! Please add different product which is not there in the cart !!!");

            return "billing_page";
        }
        double pc=Double.parseDouble(product_price);
        double pg=Double.parseDouble(product_gst);
        double cost=(pc+pc*pg*0.01);
        String info="<tr>";
        info+="<td>"+product_code+"</td>" + "<td>"+product_name+"</td>";
        info+="<td> <input id='"+product_code+"' name='"+String.valueOf(cost)+"' value=1 onkeyup='changeTotalCost(this)' type='number'> </td>";
        info+="<td class='total_row_cost' id='2"+product_code+"'>"+String.valueOf(cost)+"</td>";
        info+="</tr>";

        products_in_cart.add(info);
        product_codes_in_cart.add(product_code);

        String cart=get_products_from_cart();
        mv.addAttribute("cart",cart);

        return "billing_page";
    }




    //helper function
    public String getAllProducts(List<product> all_products,int flag){

        String ret="";

        for(int i=0;i<all_products.size();i++){
            product item2=all_products.get(i);
            ret+="<tr>";


            if(flag==0){

                ret+="<td>"+item2.getProduct_code()+"</td>";
                ret+="<td>"+item2.getProduct_name()+"</td>";
                ret+="<td>"+String.valueOf(item2.getProduct_price())+"</td>";
                ret+="<td>"+String.valueOf(item2.getProduct_gst())+"</td>";
                String parameters="'"+item2.getProduct_code()+"',"+"'"+item2.getProduct_name()+"',"+"'"+String.valueOf(item2.getProduct_price())+"',"+"'"+String.valueOf(item2.getProduct_gst())+"'";
                ret+="<td><button onclick=\"openEditForm("+parameters+")\">edit</button></td>";
            }
            else if(flag==1){

                ret+="<form method='post' action='add_to_cart' onsubmit='changeCartCost()'>";
                ret+="<td> <input class='search_result_td' type='text' name='product_code' value='"+item2.getProduct_code()+"' readonly></td>";
                ret+="<td> <input class='search_result_td' type='text' name='product_name' value='"+item2.getProduct_name()+"' readonly></td>";
                ret+="<td> <input class='search_result_td' type='text' name='product_price' value='"+String.valueOf(item2.getProduct_price())+"' readonly></td>";
                ret+="<td> <input class='search_result_td' type='text' name='product_gst' value='"+String.valueOf(item2.getProduct_gst())+"' readonly></td>";
                ret+="<td> <input class='search_result_td_add' type='submit' value='Add' ></td>";
                ret+="</form>";
            }
            ret+="</tr>";
        }

        return ret;
    }

    public String get_products_from_cart(){

        String ret="";

        for(int i=0;i<products_in_cart.size();i++){
            ret+=products_in_cart.get(i);
        }

        return ret;
    }



}
