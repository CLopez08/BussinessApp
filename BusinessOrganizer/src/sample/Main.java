/**Final Project
* Author: Chris Lopez
* Project Purpose: To create a small business organizer application
* Input:  This program excepts several inputs including: 
* Desired Output:  First page: the user can enter basic information about each client using the text boxes or from a file which can be saved in the client rrayList
*                  Second page: the user can view the information about each client, generate an invoice for each client, view previous 
*                  invoices issued to a client, or view a chart that shows all clients and how much they have spent with you.
*                  Third page: A list of estimated taxes is displayed and the user can add expenses which are deducted from taxable income.
*                  The user can also view a chart with a breakdown of where the money is going.
* Variables and Classes:  Main classes are the Client and Invoice class
* Formulas: Each button holds the main formulas so I will describe each one.
* 			Save button takes the information in each text field, creates an instance of the client class, and saves it in an ArrayList(clientArray).
*           File button lets the user search for a text file, reads the information on the file, and stores it in the clientArray.
*           DisplayInfo button takes the index of the client that is selected from the combo box and displays their information.
*           Generate Invoice button takes information from the user and passes it to the printInvoice() method from the client class.  In this
*           method an invoice is created in the form of a text file, the total variable for this instance of the client classes is updated, and 
*           a copy of the invoice is stored in the form of an invoice class which is saved in an arrayList of invoices.  The calculateTax() method
*           is also called which updates the labels on the third scene.
*           Client Chart button creates a bar chart using the name and total variable of each client stored in the clientArray
*           Previous Invoices button displays the invoices that have been generated for which ever client is selected in the combo box.  The user
*           can used the plus and minus buttons to move through the invoice arrayList for that client.
*           Add Expense button calls the calculateTax() method which updates the labels on scene three and adds to the expense variable.
*           View Chart button in the third scene creates a pie chart using the estimated tax data.
* Description of the main algorithm: Each scene is created using several labels, buttons, and text fields which allow he user to perform any of the
*                                    actions described above.   
* December 14, 2019
**/

package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;

public class Main extends Application {
    private Stage primaryStage;
    private ArrayList<Client> clientArray;
    private ComboBox<String> clientComboBox;
    private Label nameLabel;
    private Label addressLabel;
    private Label emailLabel;
    private Label cityStateLabel;
    private Label phoneNumberLabel;
    private Label saveLabel;
    private Label nameSearch;
    private Label addressSearch;
    private Label emailSearch;
    private Label cityStateSearch;
    private Label phoneNumberSearch;
    private Label businessLabel;
    private Label infoLabel;
    private Label pageTwoLabel;
    private Label totalLabel;
    private Label expenseLabel;
    private Label displayExpense;
    private Label displayTotal;
    private Label federalLabel;
    private Label displayFederal;
    private Label stateLabel;
    private Label displayState;
    private Label ficaLabel;
    private Label displayFica;
    private Label netLabel;
    private Label displayNet;
    private Label pageThree;
    private TextField nameText;
    private TextField cityStateText;
    private TextField addressText;
    private TextField emailText;
    private TextField phoneNumberText;
    private Button saveButton;
    private Button displayInfoButton;
    private Button fileButton;
    private Button nextButton;
    private Button nextButton2;
    private Button backButton;
    private Button backButton2;
    private Button invoiceButton;
    private Button expenseButton;
    private Button previousInvoicesButton;
    private Button plusButton;
    private Button minusButton;
    private Button chartButton;
    private Button clientChartButton;
    private double total;
    private double expense;
    private double federal;
    private double state;
    private double fica;
    private double netIncome;
    private int invoiceIndex;

    @Override
    public void start(Stage primaryStage) throws Exception {
        clientArray = new ArrayList<>();
        clientComboBox = new ComboBox<>();
        //Labels
        nameLabel = new Label("Client Name");
        addressLabel = new Label("Address");
        emailLabel = new Label("Email Address");
        cityStateLabel = new Label("City/State");
        phoneNumberLabel = new Label("Phone Number");
        saveLabel = new Label();
        nameSearch = new Label();
        addressSearch = new Label();
        emailSearch = new Label();
        cityStateSearch = new Label();
        phoneNumberSearch = new Label();
        businessLabel = new Label("Welcome to the Business Organizer");
        businessLabel.getStyleClass().add("title");
        infoLabel = new Label("Enter client information ");
        pageTwoLabel = new Label("Find A Client");
        pageTwoLabel.getStyleClass().add("title");
        pageTwoLabel.setFont(new Font("Arial", 20));
        totalLabel = new Label(Double.toString(total));
        displayTotal = new Label("Gross Income");
        expenseLabel = new Label(Double.toString(expense));
        displayExpense = new Label("Expenses  ");
        federalLabel = new Label(Double.toString(federal));
        displayFederal = new Label("Federal Taxes Owed");
        stateLabel = new Label(Double.toString(state));
        displayState = new Label("State Taxes Owed");
        ficaLabel = new Label(Double.toString(fica));
        displayFica = new Label("FICA taxes owed");
        netLabel = new Label(Double.toString(netIncome));
        displayNet = new Label("Net Income");
        pageThree = new Label("Estimated Taxes and Income");
        pageThree.getStyleClass().add("title");

        //TextFields
        nameText = new TextField();
        nameText.setPrefWidth(175);
        cityStateText = new TextField();
        addressText = new TextField();
        emailText = new TextField();
        phoneNumberText = new TextField();

        //Buttons
        saveButton = new Button("Save");
        saveButton.setOnAction(new StoreInfo());
        fileButton = new Button("File");
        fileButton.setOnAction(new FileClick());
        displayInfoButton = new Button("Display Info");
        displayInfoButton.setOnAction(new DisplayInfo());
        nextButton = new Button("Next Page");
        nextButton2 = new Button("Next Page");
        backButton = new Button("Back");
        backButton2 = new Button("Back");
        invoiceButton = new Button("Generate Invoice");
        invoiceButton.setOnAction(new GenerateInvoice());
        previousInvoicesButton = new Button("Previous Invoices");
        previousInvoicesButton.setOnAction(new ViewPreviousInvoice());
        plusButton = new Button("+");
        plusButton.setOnAction(new Plus());
        minusButton = new Button("-");
        minusButton.setOnAction(new Minus());
        expenseButton = new Button("Add Expense");
        expenseButton.setOnAction(new AddExpense());
        chartButton = new Button("View Chart");
        chartButton.setOnAction(new ChartWindow());
        clientChartButton = new Button("Client Chart");
        clientChartButton.setOnAction(new ClientChartWindow());
        
        //First Scene
        VBox businessBox = new VBox(businessLabel, infoLabel);
        businessBox.setAlignment(Pos.CENTER);
        businessBox.setPadding(new Insets(15));
        businessBox.setSpacing(10);
        VBox labelBox = new VBox(nameLabel, cityStateLabel, addressLabel, emailLabel, phoneNumberLabel, saveButton);
        labelBox.setSpacing(12);
        VBox textBox = new VBox(nameText, cityStateText, addressText, emailText, phoneNumberText, fileButton, saveLabel);
        textBox.setSpacing(10);
        HBox hBox = new HBox(labelBox, textBox);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(10));
        HBox nextBox = new HBox(nextButton);
        nextBox.setAlignment(Pos.BASELINE_RIGHT);
        nextBox.setPadding(new Insets(10));

        //Second Scene
        VBox pageTwoBox = new VBox(pageTwoLabel);
        pageTwoBox.setAlignment(Pos.CENTER);
        pageTwoBox.setPadding(new Insets(15));
        pageTwoBox.setSpacing(10);
        HBox infoButtonBox = new HBox(displayInfoButton, invoiceButton, clientChartButton);
        infoButtonBox.setPadding(new Insets(10));
        infoButtonBox.setSpacing(10);
        HBox invoiceBox = new HBox(minusButton, previousInvoicesButton, plusButton);
        invoiceBox.setPadding(new Insets(5));
        invoiceBox.setSpacing(10);
        HBox comboBoxBox = new HBox(clientComboBox);
        comboBoxBox.setPadding(new Insets(5));
        comboBoxBox.setSpacing(10); 
        VBox searchBox = new VBox(nameSearch, cityStateSearch, addressSearch,
                emailSearch, phoneNumberSearch, comboBoxBox, infoButtonBox, invoiceBox);
        searchBox.setSpacing(10);
        searchBox.setPadding(new Insets(20));
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setFillWidth(false);
        HBox backBox = new HBox(backButton);
        backBox.setAlignment(Pos.BASELINE_LEFT);
        backBox.setPadding(new Insets(10));
        HBox nextBox2 = new HBox(nextButton2);
        nextBox2.setAlignment(Pos.BASELINE_RIGHT);
        nextBox2.setPadding(new Insets(10));
        HBox buttonBox = new HBox(backBox, nextBox2);
        buttonBox.setSpacing(440);

        //Third Scene
        HBox totalBox = new HBox(displayTotal, totalLabel);
        totalBox.setAlignment(Pos.CENTER_LEFT);
        totalBox.setPadding(new Insets(5));
        totalBox.setSpacing(10);
        HBox expenseBox = new HBox(displayExpense, expenseLabel);
        expenseBox.setAlignment(Pos.CENTER_LEFT);
        expenseBox.setPadding(new Insets(5));
        totalBox.setSpacing(10);
        HBox federalBox = new HBox(displayFederal, federalLabel);
        federalBox.setAlignment(Pos.CENTER_LEFT);
        federalBox.setPadding(new Insets(5));
        federalBox.setSpacing(10);
        HBox stateBox = new HBox(displayState, stateLabel);
        stateBox.setAlignment(Pos.CENTER_LEFT);
        stateBox.setPadding(new Insets(5));
        stateBox.setSpacing(10);
        HBox ficaBox = new HBox(displayFica, ficaLabel);
        ficaBox.setAlignment(Pos.CENTER_LEFT);
        ficaBox.setPadding(new Insets(5));
        ficaBox.setSpacing(10);
        HBox netBox = new HBox(displayNet, netLabel);
        netBox.setAlignment(Pos.CENTER_LEFT);
        netBox.setPadding(new Insets(5));
        netBox.setSpacing(10);
        HBox backBox2 = new HBox(backButton2);
        backBox2.setAlignment(Pos.BOTTOM_LEFT);
        backBox2.setPadding(new Insets(10));
        HBox threeTitle = new HBox(pageThree);
        threeTitle.setPadding(new Insets(20));
        threeTitle.setAlignment(Pos.CENTER);
        VBox stageThreeButton = new VBox(expenseButton, chartButton);
        stageThreeButton.setPadding(new Insets(10));
        stageThreeButton.setSpacing(10);
        stageThreeButton.setAlignment(Pos.CENTER);
        VBox taxBox = new VBox(threeTitle, totalBox, expenseBox, federalBox, stateBox, ficaBox, netBox, stageThreeButton);
        taxBox.setAlignment(Pos.CENTER);
        taxBox.setPadding(new Insets(20));
        
        BorderPane borderPane1 = new BorderPane();
        borderPane1.setTop(businessBox);
        borderPane1.setCenter(hBox);
        borderPane1.setBottom(nextBox);

        BorderPane borderPane2 = new BorderPane();
        borderPane2.setTop(pageTwoBox);
        borderPane2.setCenter(searchBox);
        borderPane2.setBottom(buttonBox);

        BorderPane borderPane3 = new BorderPane();
        borderPane3.setTop(taxBox);
        borderPane3.setLeft(backBox2);


        Scene scene = new Scene(borderPane1, 600, 475);
        scene.getStylesheets().add("style.css");
        Scene scene2 = new Scene(borderPane2, 600, 475);
        scene2.getStylesheets().add("style.css");
        Scene scene3 = new Scene(borderPane3, 600, 475);
        
        scene3.getStylesheets().add("style.css");

        nextButton.setOnAction(event ->
        {
            primaryStage.setScene(scene2);
        });
        backButton.setOnAction(event ->
        {
            primaryStage.setScene(scene);
        });
        nextButton2.setOnAction(event ->
        {
            primaryStage.setScene(scene3);
        });
        backButton2.setOnAction(event ->
        {
            primaryStage.setScene(scene2);
        });
        primaryStage.setTitle("Business Organizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    class StoreInfo implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (!nameText.getText().isEmpty() && !cityStateText.getText().isEmpty() && !addressText.getText().isEmpty()
                    && !emailText.getText().isEmpty() && !phoneNumberText.getText().isEmpty()) {
                String name = nameText.getText();
                String cityState = cityStateText.getText();
                String address = addressText.getText();
                String email = emailText.getText();
                String phoneNumber = phoneNumberText.getText();

                Client client = new Client(name, cityState, address, phoneNumber, email);

                clientArray.add(client);
                clientComboBox.getItems().add(client.getName());

                saveLabel.setText("Saved");
                nameText.clear();
                cityStateText.clear();
                addressText.clear();
                emailText.clear();
                phoneNumberText.clear();
            } else {
                saveLabel.setText("All Fields required");
            }
        }
    }

    class FileClick implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                try {
                    Scanner inputFile = new Scanner(selectedFile);
                    String name = inputFile.nextLine();
                    String cityState = inputFile.nextLine();
                    String address = inputFile.nextLine();
                    String email = inputFile.nextLine();
                    String phoneNumber = inputFile.nextLine();
                    inputFile.close();

                    Client client = new Client(name, cityState, address, phoneNumber, email);
                    clientArray.add(client);
                    clientComboBox.getItems().add(client.getName());
                    saveLabel.setText("Saved");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class DisplayInfo implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (clientComboBox.getSelectionModel().isEmpty()) {
                nameSearch.setText("Please select a client");
            } else {
                int index = clientComboBox.getSelectionModel().getSelectedIndex();
                nameSearch.setText(clientArray.get(index).getName());
                cityStateSearch.setText(clientArray.get(index).getCityState());
                addressSearch.setText(clientArray.get(index).getAddress());
                emailSearch.setText(clientArray.get(index).getEmail());
                phoneNumberSearch.setText(clientArray.get(index).getPhoneNumber());
            }
        }
    }
    
    class ViewPreviousInvoice implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (clientComboBox.getSelectionModel().isEmpty()) {
                nameSearch.setText("Please select a client");
            } else {
            	try {
                int index = clientComboBox.getSelectionModel().getSelectedIndex();
                invoiceIndex = 0;
                nameSearch.setText(clientArray.get(index).getChargeList().get(invoiceIndex).getNumber());
                cityStateSearch.setText(clientArray.get(index).getChargeList().get(invoiceIndex).getDate());
                addressSearch.setText(String.format("$%,.2f",clientArray.get(index).getChargeList().get(invoiceIndex).getCost()));
                emailSearch.setText(clientArray.get(index).getChargeList().get(invoiceIndex).getReason());
                phoneNumberSearch.setText("");
            	} catch(IndexOutOfBoundsException e) {
            		
            	}
            }
        }
    }
    
    class Minus implements EventHandler<ActionEvent> {
    	@Override
    	public void handle(ActionEvent event) {
    		if (invoiceIndex > 0) {
    			int index = clientComboBox.getSelectionModel().getSelectedIndex();
    			invoiceIndex--;
    			nameSearch.setText(clientArray.get(index).getChargeList().get(invoiceIndex).getNumber());
                cityStateSearch.setText(clientArray.get(index).getChargeList().get(invoiceIndex).getDate());
                addressSearch.setText(String.format("$%,.2f",clientArray.get(index).getChargeList().get(invoiceIndex).getCost()));
                emailSearch.setText(clientArray.get(index).getChargeList().get(invoiceIndex).getReason());
                phoneNumberSearch.setText("");
    		}
    	}
    }
    
    class Plus implements EventHandler<ActionEvent> {
    	@Override
    	public void handle(ActionEvent event) {
    		int index = clientComboBox.getSelectionModel().getSelectedIndex();
    		try {
    		if (invoiceIndex < clientArray.get(index).getChargeList().size() - 1) {
    			invoiceIndex++;
    			nameSearch.setText(clientArray.get(index).getChargeList().get(invoiceIndex).getNumber());
                cityStateSearch.setText(clientArray.get(index).getChargeList().get(invoiceIndex).getDate());
                addressSearch.setText(String.format("$%,.2f",clientArray.get(index).getChargeList().get(invoiceIndex).getCost()));
                emailSearch.setText(clientArray.get(index).getChargeList().get(invoiceIndex).getReason());
                phoneNumberSearch.setText("");
    		} 
    		} catch (IndexOutOfBoundsException e) {
    			
    		}
    	}
    }
   
    class GenerateInvoice implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (clientComboBox.getSelectionModel().isEmpty()) {
                nameSearch.setText("Please select a client");
            } else {
            	try {
                String invoiceNumber = JOptionPane.showInputDialog("Invoice number?");
                int index = clientComboBox.getSelectionModel().getSelectedIndex();
                String invoiceReason = JOptionPane.showInputDialog("What is the invoice for?");
                String invoicePrice = JOptionPane.showInputDialog("Invoice Total?");
                double price = Double.parseDouble(invoicePrice);
                try {
      				clientArray.get(index).printInvoice(invoiceNumber, price, invoiceReason);
      				} catch (FileNotFoundException e) {
      					e.printStackTrace();
      				}
                    calculateTax(price);
                } catch(NumberFormatException e) {
                	JOptionPane.showMessageDialog(null, "There was an error");
                } catch (NullPointerException e) {
            	 JOptionPane.showMessageDialog(null, "There was an error");
             }
            }
         }
    }
        
    
    class ClientChartWindow implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
        	if(netIncome != 0) {
        	
        	        CategoryAxis xAxis = new CategoryAxis();
        	        NumberAxis yAxis = new NumberAxis();
        	        BarChart<String,Number> chart = new BarChart<String,Number>(xAxis,yAxis);
        	        chart.setTitle("Client Summary");
        	        xAxis.setLabel("Client");       
        	        yAxis.setLabel("Value");
        	        
        	        XYChart.Series series = new XYChart.Series();
        	        for(int i = 0; i < clientArray.size(); i++) {
        	        series.getData().add(new XYChart.Data(clientArray.get(i).getName(), clientArray.get(i).getTotal()));
        	        }
            
            Stage chartStage = new Stage();
            Scene chartScene = new Scene(chart, 400, 400);
            chart.getData().addAll(series);
            chartStage.setScene(chartScene);
            chartStage.show();
            }
        }
      }
    
    class ChartWindow implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

        	if(netIncome != 0) {
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                    new PieChart.Data("Net Income", netIncome),
                    new PieChart.Data("Federal", federal),
                    new PieChart.Data("State", state),
                    new PieChart.Data("FICA", fica),
                    new PieChart.Data("Expenses", expense));
            PieChart chart = new PieChart(pieChartData);
            chart.setTitle("Income Breakdown");
            
            Stage chartStage = new Stage();
            Scene chartScene = new Scene(chart, 400, 400);
            chartStage.setScene(chartScene);
            chartStage.show();
            }
        }
      }
 

    class AddExpense implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
        	if (netIncome > 0) {
        	try {
        	double price = Double.parseDouble(JOptionPane.showInputDialog("Expense Total?"));
        	expense += price ;
            expenseLabel.setText(String.format("$%,.2f", + expense));
            calculateTax();
        	} catch (NullPointerException e) {
        		JOptionPane.showMessageDialog(null, "No Expense Entered");
        	} catch (NumberFormatException e) {
        		JOptionPane.showMessageDialog(null, "No Expense Entered");
        	}
        	} else {
        		JOptionPane.showMessageDialog(null, "You have no money to spend");
        	}
        }
    }

    public void calculateTax(double invoicePrice) {
        total += invoicePrice;
        totalLabel.setText(String.format("$%,.2f", total));

        federal = ((total - expense) * .12);
        federalLabel.setText(String.format("$%,.2f", federal));

        state = ((total - expense) * .06);
        stateLabel.setText(String.format("$%,.2f", state));

        fica = ((total - expense) * .153);
        ficaLabel.setText(String.format("$%,.2f", fica));

        netIncome = total - (expense + federal + state + fica);
        netLabel.setText(String.format("$%,.2f", netIncome));
    }

    public void calculateTax() {
        federal = ((total - expense) * .12);
        federalLabel.setText(String.format("$%,.2f", federal));

        state = ((total - expense) * .06);
        stateLabel.setText(String.format("$%,.2f", state));

        fica = ((total - expense) * .153);
        ficaLabel.setText(String.format("$%,.2f", fica));

        netIncome = total - (expense + federal + state + fica);
        netLabel.setText(String.format("$%,.2f", netIncome));
    }

    public static Connection getConnection() throws Exception{
    	try {
    		
    	} catch(Exception e) {
    		
    	}
    	
    	return null;
    }
}





