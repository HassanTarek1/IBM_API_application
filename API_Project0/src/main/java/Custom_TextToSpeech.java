import com.ibm.cloud.sdk.core.http.HttpMediaType;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.text_to_speech.v1.util.WaveUtils;

import java.io.*;

public class Custom_TextToSpeech {
    private String path;
    private String api_key;
    private TextToSpeech synthesizer;
    private String speaker;

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public TextToSpeech getSynthesizer() {
        return synthesizer;
    }

    public void setSynthesizer(TextToSpeech synthesizer) {
        this.synthesizer = synthesizer;
    }

    public Custom_TextToSpeech(String api_key){
        Authenticator ttsAuthenticator = new IamAuthenticator(api_key);
        synthesizer = new com.ibm.watson.text_to_speech.v1.TextToSpeech(ttsAuthenticator);
        this.speaker=SynthesizeOptions.Voice.ES_ES_ENRIQUEV3VOICE;

    }
    public void createSpeech(String scentence) throws IOException {
        SynthesizeOptions synthesizeOptions =
                new SynthesizeOptions.Builder()
                        .text(scentence)
                        .voice(this.speaker)
                        .accept(HttpMediaType.AUDIO_WAV)
                        .build();
        InputStream in = synthesizer.synthesize(synthesizeOptions).execute().getResult();
        File file = new File("output.wav");
        this.path=file.getAbsolutePath();
        writeToFile(WaveUtils.reWriteWaveHeader(in),file);
    }
    private static void writeToFile(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
