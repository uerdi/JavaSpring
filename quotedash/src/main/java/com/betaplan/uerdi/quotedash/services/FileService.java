package com.betaplan.uerdi.quotedash.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service

public class FileService {
    private final Path root = Paths.get("uploads");
    public void init() throws IOException {
        if (!Files.exists(root))
            try {
                Files.createDirectories(root);

            }
        catch (IOException e) {
                throw new RuntimeException("Download Folder not Initialized");
        }
    }
}
