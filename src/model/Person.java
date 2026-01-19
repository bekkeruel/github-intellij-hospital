package model;

import exceptions.ValidationException;

public abstract class Person {
    protected int id;
    protected String name;
    protected int age;
    protected String phone;

    public Person(int id, String name, int age, String phone) throws ValidationException {
        setId(id);
        setName(name);
        setAge(age);
        setPhone(phone);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws ValidationException {
        if (id <= 0) {
            throw new ValidationException("ID must be positive");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ValidationException {
        if (name == null || name.isEmpty()) {
            throw new ValidationException("Name cannot be empty");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws ValidationException {
        if (age < 0) {
            throw new ValidationException("Age cannot be negative");
        }
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws ValidationException {
        if (phone == null || phone.isEmpty()) {
            throw new ValidationException("Phone cannot be empty");
        }
        this.phone = phone;
    }

    public void work() {
        System.out.println("Person is working.");
    }

    public String getRole() {
        return "Person";
    }

    public abstract String getDetails();
}