package org.kezoo.store.service;

public interface FileService {
    void importFromFile(String path);
    void exportToFile(String path);
}
