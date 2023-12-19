package ru.itis.employmentstub.util.jwt;

public interface JwtKeyHolder {
    String getKeyPEMFormat();
    String getKey();
    boolean hasKey();
    void setKey(String key);
}
