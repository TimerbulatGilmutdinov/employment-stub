package ru.itis.employmentstub.util.jwt;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JwtPublicKeyHolder implements JwtKeyHolder {
    private final String key = "PUBLIC_KEY";
    private final String keyPEMFormat = "PUBLIC_KEY_PEM_FORMAT";
    private final Map<String, String> holder = new ConcurrentHashMap<>();

    @Override
    public String getKeyPEMFormat() {
        return holder.get(keyPEMFormat);
    }

    @Override
    public String getKey() {
        return holder.get(key);
    }

    @Override
    public boolean hasKey() {
        return holder.get(key) != null;
    }

    @Override
    public void setKey(String stringKey) {
        holder.put(keyPEMFormat, removeWhitespaces(stringKey));
        holder.put(key, removePEMHeaders(stringKey));
    }

    private String removeWhitespaces(String key) {
        return key.replaceAll("\\s", "");
    }

    private String removePEMHeaders(String pemKey) {
        return pemKey.replaceAll("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
    }
}
