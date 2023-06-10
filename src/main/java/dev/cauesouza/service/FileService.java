package dev.cauesouza.service;

import java.io.File;
import java.io.IOException;

public interface FileService {

    String salvarImagemUserEntity(byte[] image, String imageName) throws IOException;

    File download(String fileName);
}
