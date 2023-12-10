import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class Main {

    public static void main(String[] args) {
            PhoneBook phoneBook = new PhoneBook();
            // Пример использования: добавление контакта
            phoneBook.addContact("Gtnh", "Иванов", "123-456-789", "456-789-012", "789-012-345");
            phoneBook.addContact("Иван", "Иванов", "123-456-789", "456-789-012", "789-012-345");
            phoneBook.addContact("Петр", "Петров", "123-456-789", "456-789-012", "789-012-345");
            phoneBook.addContact("Александр", "Александров", "987-654-321", "999-888-777", "789-012-345");
            phoneBook.addContact("Кирилл", "Дарьевич", "555-555-555", "456-789-012", "789-012-345");
            phoneBook.addContact("Дмитрий", "Евгеньевич", "248-585-900", "456-789-012", "789-012-345");

            // Пример использования: получение и вывод всех контактов
            System.out.println("Вывод всех контактов: ");
            List<Contact> allContacts_old = phoneBook.getAllContacts();
            for (Contact contact : allContacts_old) {
            System.out.println(contact);
            }

            // Пример использования: удаление контакта
            phoneBook.deleteContact(1);

            // Пример использования: редактирование контакта
            phoneBook.editContact(2, "Новое имя", "Новая фамилия", "111-222-333", "222-333-444", "333-444-555");

            System.out.println("Поиск по фамилии: ");
            // Пример использования: поиск по фамилии
            List<Contact> foundContacts_by_lastname = phoneBook.searchByLastName("Евгеньевич");
            for (Contact contact : foundContacts_by_lastname) {
            System.out.println(contact);
            }

            System.out.println("Поиск по id: ");
            List<Contact> foundContacts_by_id = phoneBook.searchById(3);
            for (Contact contact : foundContacts_by_id) {
            System.out.println(contact);
            }

            System.out.println("Вывод всех контактов после удаления: ");
            List<Contact> allContacts = phoneBook.getAllContacts();
            for (Contact contact : allContacts) {
            System.out.println(contact);
            }

            System.out.println("Вывод сортированных контактов после удаления: ");
            List<Contact> allContacts_sorted = phoneBook.SortContacts(phoneBook.getAllContacts());
            for (Contact contact : allContacts_sorted) {
            System.out.println(contact);
            }
            }
            }
