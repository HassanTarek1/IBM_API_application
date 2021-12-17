

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Example of how to translate a sentence from English to Spanish. */
public class Translator {
    private String api_key;
    private String modelToTrans;
    private com.ibm.watson.language_translator.v3.LanguageTranslator service;

    public String getModelToTrans() {
        return modelToTrans;
    }

    public void setModelToTrans(String modelToTrans) {
        this.modelToTrans = modelToTrans;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }


    public LanguageTranslator getService() {
        return service;
    }

    public void setService(LanguageTranslator service) {
        this.service = service;
    }

    public Translator(String api_key,String modelToTrans){
        this.api_key=api_key;
        this.modelToTrans=modelToTrans;
        Authenticator authenticator= new IamAuthenticator(this.api_key);
        this.service = new com.ibm.watson.language_translator.v3.LanguageTranslator("2018-05-01", authenticator);
    }

    public String translate(String scentence){
        TranslateOptions translateOptions = new TranslateOptions.Builder().addText(scentence).modelId(this.modelToTrans).build();
        TranslationResult translationResult = this.service.translate(translateOptions).execute().getResult();
        return translationResult.getTranslations().get(0).getTranslation();
    }




    public static void main(String[] args) throws IOException {
//        BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
//        System.out.println("Enter your text in english for translation: ");
//        String str = inp.readLine(); // for taking a string as an input
//        Authenticator authenticator = new IamAuthenticator("j3l2sWcNRX1e2TWI8LX1LtXKQpfyhZ0v30PiylamuR7r");
//        com.ibm.watson.language_translator.v3.LanguageTranslator service = new com.ibm.watson.language_translator.v3.LanguageTranslator("2018-05-01", authenticator);
//
//        TranslateOptions translateOptions =
//                new TranslateOptions.Builder().addText(str).modelId("en-it").build();
//        TranslationResult translationResult = service.translate(translateOptions).execute().getResult();
//
//        System.out.println(translationResult.getTranslations().get(0).getTranslation());

        //comment
    }
}