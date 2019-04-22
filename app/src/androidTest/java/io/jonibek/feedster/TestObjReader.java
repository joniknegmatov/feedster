package io.jonibek.feedster;

import java.io.IOException;
import java.io.InputStream;

public class TestObjReader {


    public String readFromFile(String filename) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        byte[] b = new byte[4096];
        while ((i = is.read(b)) != -1) {
            stringBuilder.append(new String(b, 0, i));
        }
        return stringBuilder.toString();
    }
}
