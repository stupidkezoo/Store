package org.kezoo.store.service.impl;

import org.kezoo.store.service.ConsoleListener;
import org.kezoo.store.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class ConsoleListenerImpl implements ConsoleListener{

    @Autowired
    private FileService fileService;

    @Override
    public void start() {
        new Thread(() -> {
            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);
            String curLine = "";

            while (!("quit".equals(curLine))) {
                try {
                    curLine = in.readLine();
                    processInput(curLine);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.exit(0);
        }).start();
    }

    private void processInput(String input) {
        if (input == null || input.isEmpty()) {
            return;
        }
        String[] trimmedInput = input.split("\\s+");

        switch (trimmedInput[0]) {
            case "import":
                fileService.importFromFile(trimmedInput[1]);
                break;
            case "export":
                fileService.exportToFile(trimmedInput[1]);
                break;
        }
    }
}
