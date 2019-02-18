package ethered.wallpapershots;

public class ImageModel {
    private String image_url;
    private String user_id;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImage_title() {
        return image_title;
    }

    public void setImage_title(String image_title) {
        this.image_title = image_title;
    }

    public String getImage_desc() {
        return image_desc;
    }

    public void setImage_desc(String image_desc) {
        this.image_desc = image_desc;
    }

    private String image_title;
    private String image_desc;
    //Empty class for Firestore
    public ImageModel(){

    }

    public ImageModel(String image_url, String user_id, String image_title, String image_desc) {
        this.image_url = image_url;
        this.user_id = user_id;
        this.image_title = image_title;
        this.image_desc = image_desc;
    }




}
