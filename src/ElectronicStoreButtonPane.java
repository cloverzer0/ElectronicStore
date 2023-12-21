import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ElectronicStoreButtonPane extends Pane {
    private Button ResetStoreButton,AddToCartButton,RemoveFromCartButton,CompleteSaleButton;

    public Button getResetStoreButton() {
        return ResetStoreButton;
    }

    public Button getAddToCartButton() {

        return AddToCartButton;
    }

    public Button getRemoveFromCartButton() {

        return RemoveFromCartButton;
    }

    public Button getCompleteSaleButton() {

        return CompleteSaleButton;
    }

    public ElectronicStoreButtonPane(){
        Pane innerPane = new Pane();

        ResetStoreButton=new Button("Reset Store");
        ResetStoreButton.setStyle("-fx-font: 12 arial;");
        ResetStoreButton.relocate(0, 0);
        ResetStoreButton.setPrefSize(140,40);

        AddToCartButton=new Button("Add to Cart");
        AddToCartButton.setStyle("-fx-font: 12 arial;");
        AddToCartButton.relocate(230, 0);
        AddToCartButton.setPrefSize(140,40);

        RemoveFromCartButton=new Button("Remove from Cart");
        RemoveFromCartButton.setStyle("-fx-font: 12 arial;");
        RemoveFromCartButton.relocate(460, 0);
        RemoveFromCartButton.setPrefSize(140,40);

        CompleteSaleButton=new Button("Complete Sale");
        CompleteSaleButton.setStyle("-fx-font: 12 arial;");
        CompleteSaleButton.relocate(600, 0);
        CompleteSaleButton.setPrefSize(140,40);

        innerPane.getChildren().addAll(ResetStoreButton,AddToCartButton,RemoveFromCartButton,CompleteSaleButton);
        getChildren().addAll(innerPane);
    }

}
