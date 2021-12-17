

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Example of how to translate a sentence from English to Spanish. */
public class LanguageTranslatorExample {

    public static void main(String[] args) throws IOException {
        BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
        System.out.println("Enter your text in english for translation: ");
        String str = inp.readLine(); // for taking a string as an input
        Authenticator authenticator = new IamAuthenticator("j3l2sWcNRX1e2TWI8LX1LtXKQpfyhZ0v30PiylamuR7r");
        LanguageTranslator service = new LanguageTranslator("2018-05-01", authenticator);

        TranslateOptions translateOptions =
                new TranslateOptions.Builder().addText(str).modelId("en-it").build();
        TranslationResult translationResult = service.translate(translateOptions).execute().getResult();

        System.out.println(translationResult);
    }
}