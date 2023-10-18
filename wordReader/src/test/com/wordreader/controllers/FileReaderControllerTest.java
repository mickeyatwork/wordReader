package com.wordreader.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderControllerTest {

    @Test
    void isValidURL() {

        FileReaderController frc = new FileReaderController();
        assertTrue(frc.isValidURL("https://janelwashere.com/files/bible_daily.txt"));
        assertFalse(frc.isValidURL(""));

    }
}