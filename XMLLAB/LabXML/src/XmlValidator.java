import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class XmlValidator {
    public static void main(String[] args) {
        try {
            // 1. Создание фабрики для XSD схемы
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // 2. Компиляция схемы
            File schemaFile = new File("library.xsd");
            Schema schema = factory.newSchema(schemaFile);

            // 3. Создание валидатора
            Validator validator = schema.newValidator();

            // 4. Валидация XML файла
            Source xmlFile = new StreamSource(new File("library.xml"));
            validator.validate(xmlFile);

            System.out.println("XML файл валиден по XSD схеме.");

        } catch (Exception e) {
            System.out.println("Ошибка валидации: " + e.getMessage());
        }
    }
}