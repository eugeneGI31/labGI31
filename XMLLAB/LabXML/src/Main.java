import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // 1. Чтение и парсинг XML-файла
            File xmlFile = new File("library.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // 2. Вывод списка всех книг
            System.out.println("\nСписок всех книг в библиотеке:");
            printAllBooks(doc);

            // 3. Вычисление средней цены
            calculateAveragePrice(doc);

            // 4. Фильтрация книг
            System.out.print("\nВведите жанр для фильтрации: ");
            String genreFilter = scanner.nextLine();
            filterBooksByGenre(doc, genreFilter);

            System.out.print("\nВведите год для фильтрации: ");
            String yearFilter = scanner.nextLine();
            filterBooksByYear(doc, yearFilter);

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод для вывода всех книг
    private static void printAllBooks(Document doc) {
        NodeList bookList = doc.getElementsByTagName("book");

        for (int i = 0; i < bookList.getLength(); i++) {
            Node bookNode = bookList.item(i);

            if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                Element bookElement = (Element) bookNode;

                System.out.println("\nID: " + bookElement.getAttribute("id"));
                System.out.println("Название: " + bookElement.getElementsByTagName("title").item(0).getTextContent());
                System.out.println("Автор: " + bookElement.getElementsByTagName("author").item(0).getTextContent());
                System.out.println("Год: " + bookElement.getElementsByTagName("year").item(0).getTextContent());
                System.out.println("Жанр: " + bookElement.getElementsByTagName("genre").item(0).getTextContent());
                System.out.println("Цена: " + bookElement.getElementsByTagName("price").item(0).getTextContent());
            }
        }
    }

    // Метод для вычисления средней цены
    private static void calculateAveragePrice(Document doc) {
        NodeList priceList = doc.getElementsByTagName("price");
        double sum = 0;

        for (int i = 0; i < priceList.getLength(); i++) {
            sum += Double.parseDouble(priceList.item(i).getTextContent());
        }

        double average = sum / priceList.getLength();
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("\nСредняя цена книг: " + df.format(average));
    }

    // Метод для фильтрации книг по жанру
    private static void filterBooksByGenre(Document doc, String genre) {
        NodeList bookList = doc.getElementsByTagName("book");
        boolean found = false;

        System.out.println("\nКниги в жанре '" + genre + "':");

        for (int i = 0; i < bookList.getLength(); i++) {
            Node bookNode = bookList.item(i);

            if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                Element bookElement = (Element) bookNode;
                String bookGenre = bookElement.getElementsByTagName("genre").item(0).getTextContent();

                if (bookGenre.equalsIgnoreCase(genre)) {
                    found = true;
                    System.out.println("\nID: " + bookElement.getAttribute("id"));
                    System.out.println("Название: " + bookElement.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Автор: " + bookElement.getElementsByTagName("author").item(0).getTextContent());
                    System.out.println("Год: " + bookElement.getElementsByTagName("year").item(0).getTextContent());
                    System.out.println("Цена: " + bookElement.getElementsByTagName("price").item(0).getTextContent());
                }
            }
        }

        if (!found) {
            System.out.println("Книг в жанре '" + genre + "' не найдено.");
        }
    }

    // Метод для фильтрации книг по году
    private static void filterBooksByYear(Document doc, String year) {
        NodeList bookList = doc.getElementsByTagName("book");
        boolean found = false;

        System.out.println("\nКниги, изданные в " + year + " году:");

        for (int i = 0; i < bookList.getLength(); i++) {
            Node bookNode = bookList.item(i);

            if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                Element bookElement = (Element) bookNode;
                String bookYear = bookElement.getElementsByTagName("year").item(0).getTextContent();

                if (bookYear.equals(year)) {
                    found = true;
                    System.out.println("\nID: " + bookElement.getAttribute("id"));
                    System.out.println("Название: " + bookElement.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Автор: " + bookElement.getElementsByTagName("author").item(0).getTextContent());
                    System.out.println("Жанр: " + bookElement.getElementsByTagName("genre").item(0).getTextContent());
                    System.out.println("Цена: " + bookElement.getElementsByTagName("price").item(0).getTextContent());
                }
            }
        }

        if (!found) {
            System.out.println("Книг, изданных в " + year + " году, не найдено.");
        }
    }
}