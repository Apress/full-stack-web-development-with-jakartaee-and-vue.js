package com.daniel.delivery.main.storage.disk;

import com.daniel.delivery.main.storage.disk.DiskStorageService;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class DiskStorageServiceTest {
    private String testFile = "testFile.txt";

    @Test
    public void save() throws IOException {
        DiskStorageService diskStorageService = new DiskStorageService("target/");

        String fileKey = diskStorageService.save(testFile, Files.newInputStream(Paths.get("src/test/resources/", testFile)));

        assertThat(fileKey).isNotBlank();
        assertThat(Files.exists(Paths.get("target/", fileKey))).isTrue();
        assertThat(Files.readAllLines(Paths.get("target/", fileKey))).isEqualTo(Files.readAllLines(Paths.get("src/test/resources/", testFile)));
    }

    @Test
    public void load() throws IOException {
        DiskStorageService diskStorageService = new DiskStorageService("src/test/resources/");

        try (InputStream file = diskStorageService.load(testFile)) {
            assertThat(file).isNotNull();
            
            Files.copy(file, Paths.get("target", testFile), StandardCopyOption.REPLACE_EXISTING);
            
            assertThat(Files.readAllLines(Paths.get("target", testFile))).isEqualTo(Files.readAllLines(Paths.get("src/test/resources/", testFile)));
        }
    }
}