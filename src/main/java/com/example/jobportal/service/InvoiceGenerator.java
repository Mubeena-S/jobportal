package com.example.jobportal.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InvoiceGenerator {

    private String invoiceNumber;
    private String customerName;
    private List<Item> items;

    public InvoiceGenerator(String invoiceNumber, String customerName, List<Item> items) {
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.items = items;
    }

    public void generateInvoice() {
        System.out.println("========== INVOICE ==========");
        System.out.println("Invoice No: " + invoiceNumber);
        System.out.println("Customer: " + customerName);
        System.out.println("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        System.out.println("-----------------------------");
        double grandTotal = 0;
        for (Item item : items) {
            System.out.println(item);
            grandTotal += item.getTotalPrice();
        }
        System.out.println("-----------------------------");
        System.out.println("Grand Total: $" + grandTotal);
        System.out.println("========== THANK YOU ==========");
    }

    // Example usage
    public static void main(String[] args) {
        List<Item> items = List.of(
            new Item("Laptop", 1, 850.0),
            new Item("Mouse", 2, 25.0),
            new Item("Keyboard", 1, 45.0)
        );

        InvoiceGenerator invoice = new InvoiceGenerator("INV-1001", "Mubeena Savalagi", items);
        invoice.generateInvoice();
    }
}
