package com.campusdual.classroom;


import com.campusdual.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Phonebook {

    private Map<String,Contact> contacts;

    public Phonebook() {
        this.contacts = new HashMap<>();
    }

    public void menu() {

        int option;

        do {
            System.out.println("Menu");
            System.out.println("1. Add contact");
            System.out.println("2. Show contacts list");
            System.out.println("3. Show contact options");
            System.out.println("4. Delete contact");
            System.out.println("5. Exit");

            option = Utils.integer("Select option: ");

            switch (option) {
                case 1:
                    String name = Utils.string("Write name: ");
                    String surnames = Utils.string("Write surname: ");
                    String phone = Utils.string("Write phone number: ");

                    Contact contact = new Contact(name, surnames, phone);

                    addContact(contact);
                    break;
                case 2:
                    showPhonebook();
                    break;
                case 3:
                    String selected = Utils.string("Write contact code: ");
                    selectContact(selected);
                    break;
                case 4:
                    String deleted = Utils.string("Write contact code: ");
                    deleteContact(deleted);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }

        }
        while(option != 4);

    }

    public void addContact(Contact contact) {
        String code = contact.getCode();
        if(!contacts.containsKey(code)){
            contacts.put(code, contact);
            System.out.println("Contact added");
        }
        else {
            System.out.println("Contact already exists");
        }
    }

    public void showPhonebook() {

        if (contacts.isEmpty()) {
            System.out.println("There are not contacts.");
        }
        else{
            System.out.println("Contacts are: ");
            for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
                System.out.println(entry.getValue().getName() + " " + entry.getValue().getSurnames() + " "
                        + entry.getValue().getPhone() + " " + entry.getValue().getCode());
            }
        }
    }

    public void selectContact(String contactCode) {

        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
            if(contactCode.equals(entry.getValue().getCode())) {
                Contact selectedContact = entry.getValue();
                contactMenu(selectedContact);
            }
            else {
                System.out.println("Code is not correct");
            }
        }
    }

    public void deleteContact(String contactCode) {

        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
            if(contactCode.equals(entry.getValue().getCode())) {
                contacts.remove(entry.getKey());
                System.out.println("Contact has been deleted");

            }
            else {
                System.out.println("Code is not correct");
            }
        }
    }

    public Map<String, Contact> getData() {

        Map<String, Contact> contactList = new HashMap<>();
        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
            String code = entry.getKey();
            Contact contact = entry.getValue();
            contactList.put(code,contact);
        }
        return contactList;
    }

    public void contactMenu(Contact contact){

        System.out.println("1. Call my number");
        System.out.println("2. Call other number");
        System.out.println("3. Contact details");

        int option = Utils.integer("Select an option: ");

        switch (option) {
            case 1:
                contact.callMyNumber();
                break;
            case 2:
                String number = Utils.string("Write phone number: ");
                contact.callOtherNumber(number);
                break;
            case 3:
                contact.showContactDetails();
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }

}