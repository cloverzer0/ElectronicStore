//Class representing an electronic store
//Has an array of products that represent the items the store can sell

import java.util.*;

public class ElectronicStore {
    public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
    private int curProducts;
    private String name;
    private Product[] stock; //Array to hold all products
    private double revenue;
    private HashMap<Product,Integer> productsSelected;

    public ElectronicStore(String initName) {
        revenue = 0.0;
        name = initName;
        stock = new Product[MAX_PRODUCTS];
        curProducts = 0;
        productsSelected =new HashMap<Product,Integer>();
    }

    public String getName() {
        return name;
    }

    //Start of getting products in cart.
    public HashMap<Product, Integer> getProductsSelected() {
        return productsSelected;
    }
    //Start of getting products in cart.

    //Start of removing all products in cart.
    public void clearCart(){
        productsSelected.clear();
    }
    //Start of removing all products in cart.

    //Adds a product and returns true if there is space in the array
    //Returns false otherwise
    public boolean addProduct(Product newProduct) {
        if (curProducts < MAX_PRODUCTS) {
            stock[curProducts] = newProduct;
            curProducts++;
            return true;
        }
        return false;
    }
    public void addProductAmount(Product p){
        boolean check=false;
        for (Product P: productsSelected.keySet()){
            if (P.toString().equals(p.toString())){
                check=true;
                int newQuantity= productsSelected.get(p);
                newQuantity++;
                productsSelected.put(p,newQuantity);
            }
        }
        if (!check){
            productsSelected.put(p,1);
        }
    }
    //Start of getting products in stock.
    public Product[] getStock(){
        return stock;
    }
    //End of getting products in stock.

    //Start of getting products in stock that is not null.
    public Product[] StockList(){
        Product[] product=new Product[curProducts];
        int i=0;
        for (Product p:getStock()){
            if (p!=null){
                product[i]=p;
                i++;
            }
        }
        return product;
    }
    //End of getting products in stock that is not null.

    //Start of getting total amount of products in cart.
    public double totalAmountInCart(){
        double total=0;
        for (Product p: productsSelected.keySet()){
            total+=p.calculateMoney();
        }
        return total;
    }
    //End of getting total amount of products in cart.

    //Start of selling products in cart.
    public void sellProducts(){
        for (Product p: productsSelected.keySet()){
            p.sellUnits();
        }
    }
    //End of selling products in cart.

    //Start of finding most bought products in stock.
    public ArrayList<String> getPopularItems() {
        ArrayList<Product> productsAvailable=new ArrayList<Product>();
        for (Product p : StockList()) {
            productsAvailable.add(p);
        }
        Collections.sort(productsAvailable, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return p1.compareTo(p2);
            }
        });
        ArrayList<String> mostBoughtProducts = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            if (i < productsAvailable.size()) {
                mostBoughtProducts.add(productsAvailable.get(i).toString());
            }
        }
        return mostBoughtProducts;
    }
    //End of finding most bought products in stock.

    //Start of removing product from cart.
    public void removeFromCart(String s){
       for (Product p: productsSelected.keySet()){
           if (s.contains(p.toString())){
               if (productsSelected.get(p)==0){
                   productsSelected.remove(p);
               }
               else{
                   productsSelected.put(p, productsSelected.get(p)-1);
                   p.removeFromCart();
               }
           }
       }
    }
    //End of removing product from cart.

    public static ElectronicStore createStore() {
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }
} 