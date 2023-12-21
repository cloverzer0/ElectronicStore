import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class ElectronicStoreApp extends Application {
    private ElectronicStore model;
    public ElectronicStoreApp(){
        model=ElectronicStore.createStore();
    }
    public void start(Stage stage) {
        Pane aPane = new Pane();
        ElectronicStoreView view=new ElectronicStoreView();
        view.update(model,-1);
        view.getStoreStock().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                view.update(model,0);
            }
        });
        view.getButtonPane().getAddToCartButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                Product selectedItem=view.getStoreStock().getSelectionModel().getSelectedItem();
                selectedItem.addToCart();
                model.addProductAmount(selectedItem);
                view.update(model,0);
            }
        });
        view.getCurrentCart().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update(model,0);
            }
        });
        view.getButtonPane().getRemoveFromCartButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                String item=view.getCurrentCart().getSelectionModel().getSelectedItem();
                model.removeFromCart(item);
                view.update(model,0);
            }
        });
        view.getButtonPane().getCompleteSaleButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                model.getPopularItems();
                model.sellProducts();
                model.clearCart();
                view.update(model,0);
            }
        });
        view.getButtonPane().getResetStoreButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                model=ElectronicStore.createStore();
                model.clearCart();
                view.update(model,0);
            }
        });
        aPane.getChildren().add(view);
        stage.setTitle("Electronic Store Application");
        stage.setResizable(false);
        stage.setScene(new Scene(aPane));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }}