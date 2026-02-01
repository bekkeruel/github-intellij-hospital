package database;

import model.*;
import exceptions.ValidationException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    // 1. INSERT DOCTOR
    public boolean insertDoctor(Doctor doctor) {
        String sql = "INSERT INTO person (name, age, phone, person_type, specialization, experience_years) " +
                "VALUES (?, ?, ?, 'DOCTOR', ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, doctor.getName());
            statement.setInt(2, doctor.getAge());
            statement.setString(3, doctor.getPhone());
            statement.setString(4, doctor.getSpecialization());
            statement.setInt(5, doctor.getExperienceYears());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting doctor: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    // 2. INSERT PATIENT
    public boolean insertPatient(Patient patient) {
        String sql = "INSERT INTO person (name, age, phone, person_type, diagnosis, admitted) " +
                "VALUES (?, ?, ?, 'PATIENT', ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getPhone());
            statement.setString(4, patient.getDiagnosis());
            statement.setBoolean(5, patient.isAdmitted());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting patient: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    // 3. GET ALL PEOPLE
    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT * FROM person ORDER BY id";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return people;

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Person person = extractPersonFromResultSet(resultSet);
                if (person != null) {
                    people.add(person);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting all people: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return people;
    }

    // 4. GET PERSON BY ID
    public Person getPersonById(int id) {
        String sql = "SELECT * FROM person WHERE id = ?";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractPersonFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting person by ID: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return null;
    }

    // 5. UPDATE DOCTOR
    public boolean updateDoctor(Doctor doctor) {
        String sql = "UPDATE person SET name = ?, age = ?, phone = ?, " +
                "specialization = ?, experience_years = ? " +
                "WHERE id = ? AND person_type = 'DOCTOR'";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, doctor.getName());
            statement.setInt(2, doctor.getAge());
            statement.setString(3, doctor.getPhone());
            statement.setString(4, doctor.getSpecialization());
            statement.setInt(5, doctor.getExperienceYears());
            statement.setInt(6, doctor.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error updating doctor: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    // 6. UPDATE PATIENT
    public boolean updatePatient(Patient patient) {
        String sql = "UPDATE person SET name = ?, age = ?, phone = ?, " +
                "diagnosis = ?, admitted = ? " +
                "WHERE id = ? AND person_type = 'PATIENT'";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getPhone());
            statement.setString(4, patient.getDiagnosis());
            statement.setBoolean(5, patient.isAdmitted());
            statement.setInt(6, patient.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error updating patient: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    // 7. DELETE PERSON
    public boolean deletePerson(int id) {
        String sql = "DELETE FROM person WHERE id = ?";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting person: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    // 8. SEARCH BY NAME (ILIKE)
    public List<Person> searchByName(String name) {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE name ILIKE ? ORDER BY name";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return people;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Person person = extractPersonFromResultSet(resultSet);
                    if (person != null) {
                        people.add(person);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching by name: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return people;
    }

    // 9. SEARCH BY AGE RANGE (BETWEEN)
    public List<Person> searchByAgeRange(int minAge, int maxAge) {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE age BETWEEN ? AND ? ORDER BY age";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return people;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, minAge);
            statement.setInt(2, maxAge);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Person person = extractPersonFromResultSet(resultSet);
                    if (person != null) {
                        people.add(person);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching by age: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return people;
    }

    // 10. SEARCH DOCTORS BY EXPERIENCE
    public List<Doctor> searchDoctorsByExperience(int minExperience) {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE person_type = 'DOCTOR' AND experience_years >= ? ORDER BY experience_years DESC";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return doctors;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, minExperience);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    try {
                        Doctor doctor = new Doctor(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getString("phone"),
                                resultSet.getString("specialization"),
                                resultSet.getInt("experience_years")
                        );
                        doctors.add(doctor);
                    } catch (ValidationException e) {
                        System.out.println("Invalid doctor data: " + e.getMessage());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching doctors: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return doctors;
    }

    // 11. HELPER METHOD
    private Person extractPersonFromResultSet(ResultSet resultSet) throws SQLException {
        String type = resultSet.getString("person_type");

        try {
            if ("DOCTOR".equals(type)) {
                return new Doctor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("phone"),
                        resultSet.getString("specialization"),
                        resultSet.getInt("experience_years")
                );
            } else if ("PATIENT".equals(type)) {
                return new Patient(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("phone"),
                        resultSet.getString("diagnosis"),
                        resultSet.getBoolean("admitted")
                );
            }
        } catch (ValidationException e) {
            System.out.println("Invalid data in database: " + e.getMessage());
        }
        return null;
    }

    // 12. DISPLAY ALL PEOPLE
    public void displayAllPeople() {
        List<Person> people = getAllPeople();
        if (people.isEmpty()) {
            System.out.println("No people in database.");
            return;
        }
        System.out.println("\n=== ALL PEOPLE ===");
        for (Person p : people) {
            System.out.println(p.getDetails());
        }
    }
}
