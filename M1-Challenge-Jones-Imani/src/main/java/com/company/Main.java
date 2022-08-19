package com.company;

import java.util.*;

public class Main {
    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    /* Creates a new customer and account record and adds it to the provided customer list */
    private static void createNewCustomer(List<Customer> customerList, HashSet<Integer> customerIDs,
                                          int customerID, String customerName, int chargeAmount, String chargeDate) {

        Customer newCustomer = new Customer();
        AccountRecord newRecord = new AccountRecord();

        newCustomer.setId(customerID);
        newCustomer.setName(customerName);

        newRecord.setCharge(chargeAmount);
        newRecord.setChargeDate(chargeDate);

        newCustomer.getCharges().add(newRecord);
        customerIDs.add(customerID);

        customerList.add(newCustomer);
    }


    public static void main(String[] args) {

        List<Customer> customerList = new ArrayList<>();
        HashSet<Integer> customerIDs = new HashSet<>(); // keeps track of all unique ids

        List<Customer> positiveAccounts = new ArrayList<>();
        List<Customer> negativeAccounts = new ArrayList<>();



        /* Checking to see if the customer id already exists. If so, create a new account record
         * for that customer and add it to the existing customer. If not, then create a new customer
         * and account record for that customer*/
        for (String[] customer: customerData) {

            int customerID = Integer.parseInt(customer[0]);
            String customerName = customer[1];

            int chargeAmount = Integer.parseInt(customer[2]);
            String chargeDate = customer[3];

            if (!customerList.isEmpty()) { // if the customer list is not empty
                if (customerIDs.contains(customerID)) { // this customer already exists
                    // new account record instance to add to an existing customer
                    AccountRecord newRecord = new AccountRecord();
                    newRecord.setCharge(chargeAmount);
                    newRecord.setChargeDate(chargeDate);

                    // finding this customer in the customer list and adding the new record to it
                    customerList
                            .stream()
                            .filter(c -> c.getId() == customerID)
                            .forEach(c -> c.getCharges().add(newRecord));

                } else {
                    // customerID not found so create a new customer and account record
                    createNewCustomer(customerList, customerIDs, customerID,
                            customerName, chargeAmount, chargeDate);
                }
            } else {
                // empty customer list so create new customer and account record
                createNewCustomer(customerList, customerIDs, customerID,
                        customerName, chargeAmount, chargeDate);
            }

        }

        /* Iterating through the list of customers to find the positive and negative accounts */
        for (Customer c : customerList) {

            int balance = c.getBalance();

            if (balance >= 0) {
                positiveAccounts.add(c);
            } else {
                negativeAccounts.add(c);
            }
        }

        /* Printing the positive and negative accounts */
        System.out.println("Positive accounts: " + positiveAccounts);
        System.out.println("Negative accounts: " + negativeAccounts);
    }
}
