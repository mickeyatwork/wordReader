package com.wordreader;

import com.wordreader.controllers.FileReaderController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class WordReaderApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(WordReaderApplication.class, args);

        FileReaderController fileReaderController = new FileReaderController();
        fileReaderController.fileFromUrl();
    }

}
