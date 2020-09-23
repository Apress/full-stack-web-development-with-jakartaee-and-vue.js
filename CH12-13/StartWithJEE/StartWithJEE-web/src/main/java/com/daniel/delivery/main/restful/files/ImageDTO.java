
package com.daniel.delivery.main.restful.files;

import java.util.Objects;

public class ImageDTO {
    private String imageUrl;

    public ImageDTO() {
    }
    
    public ImageDTO(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.imageUrl);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ImageDTO other = (ImageDTO) obj;
        if (!Objects.equals(this.imageUrl, other.imageUrl)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ImageDTO{" + "imageUrl=" + imageUrl + '}';
    }
    
    
}
