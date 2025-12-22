public class Patient {
    private int id;
    private String fullName;
    private int age;
    private String phone;

    public Patient(int id, String fullName, int age, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // logic methods
    public void updatePhone(String newPhone) {
        phone = newPhone;
    }

    public void displayInfo() {
        System.out.println("Patient: " + fullName + ", Age: " + age);
    }
}