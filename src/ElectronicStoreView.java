import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class ElectronicStoreView extends Pane{
    private TextField numberSales,Revenue,MoneySales;
    private ListView<String> MostPopularItems,CurrentCart;
    private ListView<Product> StoreStock;
    private Label label8;
    private ElectronicStoreButtonPane buttonPane;
    private double revenueTotalAmount;
    private double cartTotalAmount;
    private ArrayList<String> popularItems;
    public ElectronicStoreView(){
        Label label1 = new Label("Store Summary:");
        label1.relocate(50, 10);
        Label label2 = new Label("Store Stock:");
        label2.relocate(300, 10);
        Label label3 = new Label("Current Cart:");
        label3.relocate(600, 10);
        Label label4 = new Label("Most Popular Items:");
        label4.relocate(43,135);
        Label label5 = new Label("# Sales:");
        label5.relocate(40,35);
        Label label6 = new Label("Revenue:");
        label6.relocate(33,72);
        Label label7 = new Label("$ /Sale:");
        label7.relocate(43,105);
        label8=new Label("(0.00)");
        label8.relocate(670,10);

        numberSales = new TextField("0");
        numberSales.relocate(85, 30);
        numberSales.setPrefSize(110,30);
        Revenue = new TextField("0.00");
        Revenue.relocate(85, 65);
        Revenue.setPrefSize(110,30);
        MoneySales = new TextField("N/A");
        MoneySales.relocate(85, 100);
        MoneySales.setPrefSize(110,30);

        MostPopularItems = new ListView<String>();
        MostPopularItems.relocate(10, 160);
        MostPopularItems.setPrefSize(185,170);
        StoreStock = new ListView<Product>();
        StoreStock.relocate(200, 30);
        StoreStock.setPrefSize(285,300);
        CurrentCart = new ListView<String>();
        CurrentCart.relocate(490, 30);
        CurrentCart.setPrefSize(280,300);

        buttonPane = new ElectronicStoreButtonPane();
        buttonPane.relocate(30,340);
        buttonPane.getAddToCartButton().setDisable(true);
        buttonPane.getRemoveFromCartButton().setDisable(true);
        buttonPane.getCompleteSaleButton().setDisable(true);

        popularItems=new ArrayList<String>();
        revenueTotalAmount=0.00;
        cartTotalAmount=0.00;
        getChildren().addAll(label1, label2, label3, label4, label5, label6, label7, label8, numberSales, Revenue, MoneySales, MostPopularItems, StoreStock, CurrentCart , buttonPane);

        setPrefSize(800,400);
    }

    public void update(ElectronicStore model,int start) {
        //Gets popular item function
        Product[] stock = model.StockList();
        if (start==-1||buttonPane.getResetStoreButton().isPressed()){
            popularItems=new ArrayList<String>();
            for (int i=0;i<3;i++){
                popularItems.add(stock[i].toString());
            }
        }
        //end of getting popular item function

        //Start of Display available stock function
        ArrayList<Product> stockList=new ArrayList<Product>();
        for (int i=0;i<stock.length;i++){
            if (stock[i].getStockQuantity()>0){
                stockList.add(stock[i]);
            }
        }
        // end of display available stock function.

        //Start of add to cart function.
        ArrayList<String> addToCartList=new ArrayList<String>();
        for (Product p:model.getProductsSelected().keySet()){
            if (p.getAmountInCart()!=0){
                addToCartList.add(model.getProductsSelected().get(p) + " x " + p.toString());
            }
        }
        //End of add to cart function.

        //Start of resetting class attribute
        if (buttonPane.getResetStoreButton().isPressed()){
            revenueTotalAmount=0.00;
            cartTotalAmount=0.00;
            StoreStock.getSelectionModel().select(-1);
            numberSales.setText("0");
            Revenue.setText(String.format("%.2f",revenueTotalAmount));
            MoneySales.setText("N/A");
        }
        //End of resetting class attribute

        //Start of calculate cart amount
        if (this.buttonPane.getAddToCartButton().isPressed()){
            cartTotalAmount=model.totalAmountInCart();
        }
        if (this.buttonPane.getRemoveFromCartButton().isPressed()){
            cartTotalAmount=model.totalAmountInCart();
        }
        //End of calculate cart amount

        //Start of reset all.
        if (buttonPane.getCompleteSaleButton().isPressed()){
            int x=Integer.parseInt(numberSales.getText())+1;
            numberSales.setText("" + x);
            revenueTotalAmount+=cartTotalAmount;
            Revenue.setText(String.format("%.2f",revenueTotalAmount));
            cartTotalAmount=0.00;
            if (Integer.parseInt(numberSales.getText())>0){
                double average=revenueTotalAmount/Integer.parseInt(numberSales.getText());
                MoneySales.setText(String.format("%.2f",average));
            }
            addToCartList.clear();
            popularItems=model.getPopularItems();
            buttonPane.getAddToCartButton().setDisable(true);
            buttonPane.getRemoveFromCartButton().setDisable(true);
            buttonPane.getCompleteSaleButton().setDisable(true);
        }
        //End of reset all.

        //Start of enable add to cart button on item select function
        if (this.StoreStock.getSelectionModel().getSelectedItem()==null||this.StoreStock.getSelectionModel().getSelectedItem().getStockQuantity()==0){
            buttonPane.getAddToCartButton().setDisable(true);
        }
        else {
            buttonPane.getAddToCartButton().setDisable(false);
        }
        // end of enable add to cart  button on item select function.

        //Start of enable complete sale.
        if (addToCartList.size()>0){
            buttonPane.getCompleteSaleButton().setDisable(false);
        }
        else {buttonPane.getCompleteSaleButton().setDisable(true);}
        //End of enable complete sale.

        //Start of enable remove from cart.
        if (this.CurrentCart.getSelectionModel().getSelectedItem()==null){
            buttonPane.getRemoveFromCartButton().setDisable(true);
        }
        else {buttonPane.getRemoveFromCartButton().setDisable(false);}
        //End of remove from cart.

        //Start of disable remove button once a product has benn selected.
        if (buttonPane.getRemoveFromCartButton().isPressed()){
            System.out.println("Disable");
            buttonPane.getRemoveFromCartButton().setDisable(true);
        }
        //End of disable remove button once a product has benn selected.

        //Start of setting my list view.
        CurrentCart.setItems(FXCollections.observableArrayList(addToCartList));
        StoreStock.setItems(FXCollections.observableArrayList(stockList));
        MostPopularItems.setItems(FXCollections.observableArrayList(popularItems));
        //End of setting my list view.

        label8.setText(String.format("($%.2f)", cartTotalAmount));
    }

    public ListView<Product> getStoreStock() {return StoreStock;}
    public ListView<String> getCurrentCart() {return CurrentCart;}
    public ListView<String> getMostPopularItems() {return MostPopularItems;}

    public TextField getMoneySales() {return MoneySales;}
    public TextField getNumberSales() {return numberSales;}
    public TextField getRevenue() {return Revenue;}

    public ElectronicStoreButtonPane getButtonPane() {
        return buttonPane;
    }
}
