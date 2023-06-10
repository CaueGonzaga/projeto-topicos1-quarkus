package dev.cauesouza.util;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import jakarta.ws.rs.FormParam;

public class ImageForm {

    @FormParam("ImageName")
    private String imageName;

    @FormParam("image")
    @PartType("application/octet-stream")
    private byte[] image;

    public String getNomeImagem() {
        return imageName;
    }

    public void setNomeImagem(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImagem() {
        return image;
    }

    public void setImagem(byte[] image) {
        this.image = image;
    }
}
