package mg.telma.data.llm.utils;

import mg.telma.data.llm.LlmExtractionApplication;
import mg.telma.data.llm.configuration.OllamaException;

import java.io.InputStream;
import java.util.Scanner;

public class PromptUtils {
    private PromptUtils() {}
    public static String getDatabasePromptWithQuestion(String question) throws OllamaException {
        ClassLoader classLoader = LlmExtractionApplication.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("db-prompt-template.txt");
        if (inputStream != null) {
            Scanner scanner = new Scanner(inputStream);
            StringBuilder stringBuffer = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuffer.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
            return stringBuffer.toString().replace("<question>", question);
        } else {
            throw new OllamaException("Database question file [db-prompt-template.txt] not found.");
        }
    }

}
