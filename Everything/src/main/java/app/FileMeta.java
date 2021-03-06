package app;

import util.Util;

import java.io.File;
import java.util.Objects;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/5
 * @Time: 9:24
 */
public class FileMeta {
    //属性中使用引用类型，避免有初始值
    private String name;
    private String path;
    private Long size;
    private Long lastModified;
    private Boolean isDirectory;
    private String sizeText;
    private String lastModifiedText;

    //初始化中传入基本参数，是为了避免空指针异常
    public FileMeta(String name, String path, Long size, Long lastModified, Boolean isDirectory) {
        this.name = name;
        this.path = path;
        this.size = size;//大小需要转化
        this.lastModified = lastModified;//上次修改时间的转换
        this.isDirectory = isDirectory;
        this.sizeText = Util.parseSize(size);
        this.lastModifiedText = Util.parseDate(lastModified);
    }

    public FileMeta(File child) {
        this(child.getName(), child.getParent(), child.length(), child.lastModified(), child.isDirectory());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileMeta meta = (FileMeta) o;
        return Objects.equals(name, meta.name) &&
                Objects.equals(path, meta.path) &&
                Objects.equals(isDirectory, meta.isDirectory);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, path, isDirectory);
    }

    @Override
    public String toString() {
        return "FileMeta{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", lastModified=" + lastModified +
                ", isDirectory=" + isDirectory +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }

    public Boolean getDirectory() {
        return isDirectory;
    }

    public void setDirectory(Boolean directory) {
        isDirectory = directory;
    }

    public String getSizeText() {
        return sizeText;
    }

    public void setSizeText(String sizeText) {
        this.sizeText = sizeText;
    }

    public String getLastModifiedText() {
        return lastModifiedText;
    }

    public void setLastModifiedText(String lastModifiedText) {
        this.lastModifiedText = lastModifiedText;
    }
}
