import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PhoneBook {

    private Connection connection;

    public PhoneBook() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src\\main\\java\\contacts.db");
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE contacts");
            statement.executeUpdate("CREATE TABLE if not exists contacts (id INTEGER PRIMARY KEY, " +
                    "first_name TEXT, last_name TEXT, phone1 TEXT, phone2 TEXT, phone3 TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Добавление контакта
    public void addContact(String firstName, String lastName, String phone1, String phone2, String phone3) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO contacts (first_name, last_name, phone1, phone2, phone3) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phone1);
            statement.setString(4, phone2);
            statement.setString(5, phone3);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление контакта по ID
    public void deleteContact(int contactId) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM contacts WHERE id = ?")) {
            statement.setInt(1, contactId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Редактирование контакта по ID
    public void editContact(int contactId, String firstName, String lastName, String phone1, String phone2, String phone3) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE contacts SET first_name = ?, last_name = ?, phone1 = ?, phone2 = ?, phone3 = ? WHERE id = ?")) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phone1);
            statement.setString(4, phone2);
            statement.setString(5, phone3);
            statement.setInt(6, contactId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Поиск контакта по фамилии
    public List<Contact> searchByLastName(String lastName) {
        List<Contact> contacts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM contacts WHERE last_name = ?")) {
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contacts.add(new Contact(
                        resultSet.getString("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("phone1"),
                        resultSet.getString("phone2"),
                        resultSet.getString("phone3")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public List<Contact> searchById(Integer id) {
        List<Contact> contacts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM contacts WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contacts.add(new Contact(
                        resultSet.getString("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("phone1"),
                        resultSet.getString("phone2"),
                        resultSet.getString("phone3")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    // Получение всех контактов
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts");
            while (resultSet.next()) {
                contacts.add(new Contact(
                        resultSet.getString("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("phone1"),
                        resultSet.getString("phone2"),
                        resultSet.getString("phone3")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public List<Contact> SortContacts(List<Contact> contacts) {
        Collections.sort(contacts, Comparator.comparing(Contact::getLastName)
                .thenComparing(Contact::getFirstName)
                .thenComparing(Contact::getPhone1)
                .thenComparing(Contact::getPhone2)
                .thenComparing(Contact::getPhone3));
        return contacts;
    }
}

