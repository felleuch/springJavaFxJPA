package com.faiez.ihm;

import com.faiez.DomainApplicationConfig;
import com.faiez.application.*;
import com.faiez.domain.Pet;
import com.faiez.services.PetService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by faiez.elleuch on 26/02/14.
 */
public class NewPetController implements Initializable, ControlledScreen {


	ScreensController myController;

	AbstractApplicationContext context = new AnnotationConfigApplicationContext(DomainApplicationConfig.class);
	PetService petService = context.getBean(PetService.class);



	public void setScreenParent(ScreensController screenParent){
		myController = screenParent;
		myMenuController.setMyController(screenParent);
	}


	@FXML
	private MyMenu myMenu;
	@FXML private MyMenuController myMenuController;


	@FXML
	private TextField nameField;
	@FXML
	private TextField colorField;




	@FXML
	private void save(ActionEvent event){
		System.out.println("add button pressed.....");

		Pet pp =  new Pet();
		pp.setColor(colorField.getText());
		pp.setName(nameField.getText());
		pp.setOwnerid(3);
		petService.create(pp);

		myController.loadScreen(MainApplication.petScreenID, MainApplication.petScreenFile);
		myController.setScreen(MainApplication.petScreenID);
		//persons.add(new Person(firstNameField.getText(),lastNameField.getText(),emailField.getText(),birthDate.getValue()));
		// tableView.getItems().setAll(persons.subList(currentPageIndex * itemsPerPage, ((currentPageIndex * itemsPerPage + itemsPerPage <= persons.size()) ? currentPageIndex * itemsPerPage + itemsPerPage : persons.size())));

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// myMenuController.setMyController(myController);
	}
}

