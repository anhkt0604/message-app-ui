package ui;

public class Contact {
    private String name;
    private String hastag;
    private String imageFileName;

    public Contact(String name, String hastag)
    {
        this.name = name;
        this.hastag = hastag;
        this.imageFileName = name + ".png";
    }

    public Contact() {
        this.name = "";
        this.hastag = "";
        this.imageFileName = "";
    }


    public String getName()
    {
        return name;
    }

    public String getHastag()
    {
        return hastag;
    }

    public String getImageFileName()
    {
        return imageFileName;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setHastag(String hastag)
    {
        this.hastag = hastag;
    }

    public void setImageFileName(String imageFileName)
    {
        this.imageFileName = imageFileName;
    }
}
