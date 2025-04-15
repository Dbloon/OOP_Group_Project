package Patient_and_Medication_Management;

import java.time.LocalDate;

public class MedicationStock {
    private int stockCount;  
    private LocalDate expiryDate;  
    
    
    public MedicationStock(int stockCount, LocalDate expiryDate) {
        this.stockCount = stockCount;
        this.expiryDate = expiryDate;
    }

    
    public void prescribeMedication() {
        if (stockCount > 0) {
            stockCount--;  
            System.out.println("Medication prescribed. Remaining stock: " + stockCount);
        } else {
            System.out.println("Stock is empty! Cannot prescribe medication.");
        }

        
        if (stockCount < 20) {
            System.out.println("Alert: Stock is low! Only " + stockCount + " left.");
        }
    }

    
    public void displayStockInfo() {
        System.out.println("Current stock: " + stockCount);
        System.out.println("Expiry Date: " + expiryDate);
    }
}

