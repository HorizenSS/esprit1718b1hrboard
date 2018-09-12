package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import tn.esprit.b1.esprit1718b1hrboard.entities.PaySlip;

public class PayslipsFactory  extends ListCell<PaySlip> {
	
	
	 private final GridPane gridPane = new GridPane();

	    private final Label EmpName = new Label();

	    private final Label date = new Label();
	    private final Label type = new Label();
	    private final ImageView EmpImg = new ImageView();
	    private final AnchorPane content = new AnchorPane();
	    
	    public PayslipsFactory (){
	    
	    EmpImg.setFitWidth(50);
	    EmpImg.setFitHeight(50);

	    EmpImg.setPreserveRatio(true);
        GridPane.setConstraints(EmpImg, 0, 0, 1, 2);
        GridPane.setValignment(EmpImg, VPos.TOP);
        
        
        EmpName.setStyle("-fx-font-size: 1.7em;-fx-text-fill: #2c3e50;");
        GridPane.setConstraints(EmpName, 1, 0);
        
        
        date.setStyle("-fx-font-size: 1.6em;");
        GridPane.setConstraints(date, 1, 1);
        
        type.setStyle("-fx-font-size: 1.6em;");
        GridPane.setConstraints(type, 1, 2);
        
        
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
        gridPane.setHgap(6);
        gridPane.setVgap(6);
        gridPane.getChildren().setAll(EmpImg,type, EmpName	,  date);
        AnchorPane.setTopAnchor(gridPane, 0d);
        AnchorPane.setLeftAnchor(gridPane, 0d);
        AnchorPane.setBottomAnchor(gridPane, 0d);
        AnchorPane.setRightAnchor(gridPane, 0d);
        content.getChildren().add(gridPane);
	    }
        
        
        @Override
        protected void updateItem(PaySlip item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(null);
            setText(null);

            setContentDisplay(ContentDisplay.LEFT);
            if (!empty && item != null) {
                EmpName.setText(item.getEmployee().getFirstName());
                date.setText(item.getRequestExtractionDate().toString());
            //    prixProduit.setText(String.valueOf(item.getPrix()));
                type.setText(item.getType());

                
                    EmpImg.setImage(new Image("/images/avatar.png" ));
               

                setText(null);
                setGraphic(content);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        
	    
	
	    }
}
