package com.example.application.fragment.ui.share;

public class Contacts {

   /* private String Name;
    private Integer Age;
    private Long ph;
    private Float ht; */

   private String Name;
   private String Middlename;
   private String Surname;
   private Long ph;
   private String Email;
   private String Address;
   private String Notes;

    public Contacts() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMiddlename() {
        return Middlename;
    }

    public void setMiddlename(String middlename) {
        Middlename = middlename;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public Long getPh() {
        return ph;
    }

    public void setPh(Long ph) {
        this.ph = ph;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
