package wkp;

import java.util.HashMap;
import java.util.Map;

public class FileModel {
    public Map<String, FileModel> subMap = new HashMap();
    private String name;
    private String type;
    private int attr;
    private int startNum;
    private int size;
    private FileModel father = null;

    public FileModel(String name, String type, int startNum, int size) {
        this.name = name;
        this.type = type;
        this.attr = 2;
        this.startNum = startNum;
        this.size = size;
    }//创建文件的全参构造

    public FileModel(String name, int startNum) {
        this.name = name;
        this.attr = 3;
        this.startNum = startNum;
        this.type = "  ";
        this.size = 1;
    }//创建目录的全参构造

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAttr() {
        return this.attr;
    }

    public void setAttr(int attr) {
        this.attr = attr;
    }

    public int getStartNum() {
        return this.startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public FileModel getFather() {
        return this.father;
    }

    public void setFather(FileModel father) {
        this.father = father;
    }
}
