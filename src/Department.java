public class Department {
    private int id;
    private String name;
    private String floor;
    private int roomCount;

    public Department(int id, String name, String floor, int roomCount) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.roomCount = roomCount;
    }

    public void displayDepartment() {
        System.out.println("Department: " + name + ", Floor: " + floor);
    }
}
