package ahu.bigdata.huiculture.module.recommand;

/**
 * Created by YCH on 2017/10/16.
 * Function:
 */
public class VideoModule {

    String url;
    String path;
    String name;
    int size;
    double modified;
    boolean isdir;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getModified() {
        return modified;
    }

    public void setModified(double modified) {
        this.modified = modified;
    }

    public boolean isdir() {
        return isdir;
    }

    public void setIsdir(boolean isdir) {
        this.isdir = isdir;
    }
}
