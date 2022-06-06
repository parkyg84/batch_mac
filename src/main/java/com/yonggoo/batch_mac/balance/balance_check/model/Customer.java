package com.yonggoo.batch_mac.balance.balance_check.model;

import java.time.LocalDateTime;

public class Customer {

    private int customerId;
    private int storeId;
    private String lastName;
    private String firstName;
    private String email;
    private int addressId;
    private int active;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;

    public Customer() {}

    public Customer(    int customerId, int storeId, String lastName,
                        String firstName, String email, int addressId,
                        int active, LocalDateTime createDate, LocalDateTime lastUpdate) {
        this.customerId = customerId;
        this.storeId = storeId;

        this.lastName = lastName;
        this.firstName = firstName;

        this.email = email;
        this.addressId = addressId;
        this.active = active;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;

    }



    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getActive() {
        return active;
    }



    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }


    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }




}
