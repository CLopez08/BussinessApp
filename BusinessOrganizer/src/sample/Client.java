package sample;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class Client {
    private String name;
    private String cityState;
    private String address;
    private String phoneNumber;
    private String email;
    private double total;
    private ArrayList<Invoice> invoiceList;


    public Client(String name, String cityState, String address, String phoneNumber, String email) {
        this.name = name;
        this.cityState = cityState;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.total = 0.0;
        this.invoiceList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCityState() {
        return cityState;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	

	public ArrayList<Invoice> getChargeList() {
		return invoiceList;
	}

	public void setChargeList(ArrayList<Invoice> chargeList) {
		this.invoiceList = chargeList;
	}

	public void printInvoice(String invoiceNumber, double invoicePrice, String invoiceReason) throws FileNotFoundException
    {
    	PrintWriter outputFile = new PrintWriter("invoice" + invoiceNumber + ".txt");
    	String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());

        outputFile.println("               Company Logo");
        outputFile.println("");
        outputFile.println("Invoice to:");
        outputFile.println(this.getName());
        outputFile.println(this.getCityState());
        outputFile.println(this.getAddress());
        outputFile.println(this.getEmail());
        outputFile.println(this.getPhoneNumber());
        outputFile.println("");
        outputFile.println(date);
        outputFile.println("Invoice Number: " + invoiceNumber);
        outputFile.println("");
        outputFile.println("------------------------------------------------------------");
        outputFile.println("Product: " + invoiceReason);
        outputFile.println("Price:   $" + invoicePrice);
        outputFile.println();
        outputFile.println("               Thank You!");

        outputFile.close();
        
        this.setTotal(this.getTotal() + invoicePrice);
        Invoice invoice = new Invoice(invoiceNumber, invoicePrice, date, invoiceReason);
        this.invoiceList.add(invoice);
    }
}

