package mg.telma.data.llm;

import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class QuestionValidationService {

    // Regex pour vérifier qu'il y a au moins quelques mots avec des espaces (exclut les chaînes aléatoires sans structure)
    private static final Pattern VALID_QUESTION_PATTERN = Pattern.compile("([A-Za-zÀ-ÿ]{2,}\\s){2,}"); // Au moins deux mots avec espaces

    public boolean isValidQuestion(String question) {
        if (question == null || question.trim().isEmpty()) {
            return false;
        }

        // Vérifier que la question est une phrase cohérente (au moins deux mots, etc.)
        return VALID_QUESTION_PATTERN.matcher(question).find();
    }
}
