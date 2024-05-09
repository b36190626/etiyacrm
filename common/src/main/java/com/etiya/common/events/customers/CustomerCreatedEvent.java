package com.etiya.common.events.customers;


public class CustomerCreatedEvent {
    public CustomerCreatedEvent() {
    }

    public CustomerCreatedEvent(long id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String firstName;
}
