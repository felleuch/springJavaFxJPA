package com.faiez.ihm;

import com.faiez.DomainApplicationConfig;
import com.faiez.application.*;
import com.faiez.domain.Pet;
import com.faiez.exception.BusinessException;
import com.faiez.repository.PetsRepository;
import com.faiez.services.PetService;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import javafx.util.Callback;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

	public class PetController implements Initializable, ControlledScreen {

		ScreensController myController;

		@FXML
		private MyMenu myMenu;
		@FXML private MyMenuController myMenuController;

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(DomainApplicationConfig.class);
		PetService petService = context.getBean(PetService.class);


		@FXML
		private TableView<Pet> tableView;


		@FXML
		private Button addButton;

		@FXML
		private Pagination pagination;


		private List<Pet> lstPerson = getAllPets();
		private ObservableList<Pet> persons = FXCollections.observableArrayList(lstPerson);

		/*@FXML
		protected void addPerson(ActionEvent event) {
			persons.add(new Person(firstNameField.getText(),lastNameField.getText(),emailField.getText(),birthDate.getValue()));
			tableView.getItems().setAll(persons.subList(currentPageIndex * itemsPerPage, ((currentPageIndex * itemsPerPage + itemsPerPage <= persons.size()) ? currentPageIndex * itemsPerPage + itemsPerPage : persons.size())));
			firstNameField.setText("");
			lastNameField.setText("");
			emailField.setText("");
		}*/
		public List<Pet> getAllPets(){
			return (List<Pet>)petService.findAll();
		}



		int pageCount = 5;
		int itemsPerPage = 10;
		int currentPageIndex = 0;

		public int getItemsPerPage() {
			return itemsPerPage;
		}

		public void setItemsPerPage(int itemsPerPage) {
			this.itemsPerPage = itemsPerPage;
		}

		public int getCurrentPageIndex() {
			return currentPageIndex;
		}

		public void setCurrentPageIndex(int currentPageIndex) {
			this.currentPageIndex = currentPageIndex;
		}

		private int getPageCount(int totalCount, int itemsPerPage) {
			float floatCount = (float)totalCount / itemsPerPage;
			int intCount = totalCount / itemsPerPage;
			return ((floatCount > intCount) ? ++intCount : intCount);
		}


		/**
		 * Initializes the controller class.
		 */
		@Override
		public void initialize(URL url, ResourceBundle rb) {
			lstPerson = getAllPets();
			TableColumn<Pet, Boolean> actionCol = new TableColumn<Pet, Boolean>("");
			actionCol.setSortable(false);
			actionCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pet, Boolean>, ObservableValue<Boolean>>() {
				@Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Pet, Boolean> features) {
					return new SimpleBooleanProperty(features.getValue() != null);
				}
			});
			actionCol.setCellFactory(new Callback<TableColumn<Pet, Boolean>, TableCell<Pet, Boolean>>() {
				@Override public TableCell<Pet, Boolean> call(TableColumn<Pet, Boolean> personBooleanTableColumn) {
					return new DeletePetCell(tableView);
				}
			});
			tableView.getColumns().add(actionCol);

			if(persons.size() <= itemsPerPage ){
				tableView.getItems().setAll(persons);
			}else{
				tableView.getItems().setAll(persons.subList(0, itemsPerPage));
			}



			pageCount = getPageCount(persons.size(), itemsPerPage);
			pagination.setPageCount(pageCount);
			pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					currentPageIndex = newValue.intValue();
					tableView.getItems().setAll(persons.subList(currentPageIndex * itemsPerPage, ((currentPageIndex * itemsPerPage + itemsPerPage <= persons.size()) ? currentPageIndex * itemsPerPage + itemsPerPage : persons.size())));
				}
			});
		}


		public void setScreenParent(ScreensController screenParent)
		{
			myController = screenParent;
			myMenuController.setMyController(screenParent);
		}

		@FXML
		private void goToScreen2(ActionEvent event){
			myController.setScreen(MainApplication.screen2ID);
		}

		@FXML
		private void goToScreen3(ActionEvent event){
			myController.setScreen(MainApplication.screen3ID);
		}




		@FXML
		private void newPet(){
			myController.setScreen(MainApplication.createPetID);
		}



		class DeletePetCell extends TableCell<Pet, Boolean> {
			// a button for adding a new person.
			final Button addButton = new Button("Delete");
			// pads and centers the add button in the cell.
			final StackPane paddedButton = new StackPane();
			// records the y pos of the last button press so that the add person dialog can be shown next to the cell.
			final DoubleProperty buttonY = new SimpleDoubleProperty();


			DeletePetCell(final TableView table) {
				paddedButton.setPadding(new Insets(3));
				paddedButton.getChildren().add(addButton);
				addButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent actionEvent) {
						table.getSelectionModel().select(getTableRow().getIndex());
						int iindex = currentPageIndex * itemsPerPage + getTableRow().getIndex();
						Pet pp = (Pet) persons.get(iindex);
						try {
							petService.delete(pp.getId());
						} catch (BusinessException e) {
							e.printStackTrace();
						}
						myController.loadScreen(MainApplication.petScreenID, MainApplication.petScreenFile);
						myController.setScreen(MainApplication.petScreenID);

					}
				});
			}

			/** places an add button in the row only if the row is not empty. */
			@Override protected void updateItem(Boolean item, boolean empty) {
				super.updateItem(item, empty);
				if (!empty) {
					setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
					setGraphic(paddedButton);
				}
			}
		}
	}

