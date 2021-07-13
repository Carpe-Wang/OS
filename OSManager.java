package wkp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OSManager {
    public Map<String, FileModel> totalFiles = new HashMap();
    private int[] fat = new int[128];
    private FileModel root = new FileModel("王凯澎", 1);
    private FileModel nowCatalog;

    public OSManager() {
        this.nowCatalog = this.root;

        for(int i = 0; i < this.fat.length; ++i) {
            this.fat[i] = 0;
        }

        this.fat[1] = 255;
        this.fat[0] = 126;
        this.root.setFather(this.root);
        this.totalFiles.put("root", this.root);
    }//

    public int setFat(int size) {
        int[] startNum = new int[128];
        int i = 2;

        for(int j = 0; j < size; ++i) {
            if (this.fat[i] == 0) {
                startNum[j] = i;
                if (j > 0) {
                    this.fat[startNum[j - 1]] = i;
                }

                ++j;
            }
        }

        this.fat[i - 1] = 255;
        return startNum[0];
    }

    public void delFat(int startNum) {
        int var10000 = this.fat[startNum];
        int nowPoint = startNum;

        int nextPoint;
        int count;
        for(count = 0; this.fat[nowPoint] != 0; nowPoint = nextPoint) {
            nextPoint = this.fat[nowPoint];
            if (nextPoint == 255) {
                this.fat[nowPoint] = 0;
                ++count;
                break;
            }

            this.fat[nowPoint] = 0;
            ++count;
        }

        int[] var5 = this.fat;
        var5[0] += count;
    }

    public void reAddFat(int startNum, int addSize) {
        int nowPoint = startNum;

        for(int nextPoint = this.fat[startNum]; this.fat[nowPoint] != 255; nextPoint = this.fat[nextPoint]) {
            nowPoint = nextPoint;
        }

        int i = 2;

        for(int count = 0; count < addSize; ++i) {
            if (this.fat[i] == 0) {
                this.fat[nowPoint] = i;
                nowPoint = i;
                ++count;
                this.fat[i] = 255;
            }
        }

    }

    public void createFile(String name, String type, int size) {
        if (this.fat[0] >= size) {
            FileModel value = (FileModel)this.nowCatalog.subMap.get(name);
            int[] var10000;
            int startNum;
            FileModel file;
            if (value != null) {
                if (value.getAttr() == 3) {
                    startNum = this.setFat(size);
                    file = new FileModel(name, type, startNum, size);
                    this.nowCatalog.subMap.put(name, file);
                    this.totalFiles.put(file.getName(), file);
                    var10000 = this.fat;
                    var10000[0] -= size;
                    System.out.println("创建文件成功！");
                    this.showFile();
                } else if (value.getAttr() == 2) {
                    System.out.println("创建失败，该文件已存在！");
                    this.showFile();
                }
            } else if (value == null) {
                startNum = this.setFat(size);
                file = new FileModel(name, type, startNum, size);
                file.setFather(this.nowCatalog);
                this.nowCatalog.subMap.put(name, file);
                this.totalFiles.put(file.getName(), file);
                var10000 = this.fat;
                var10000[0] -= size;
                System.out.println("创建文件成功！");
                this.showFile();
            }
        } else {
            System.out.println("创建文件失败，磁盘空间不足！");
        }

    }

    public void createCatolog(String name) {
        if (this.fat[0] >= 1) {
            FileModel value = (FileModel)this.nowCatalog.subMap.get(name);
            int var10002;
            int startNum;
            FileModel catalog;
            if (value != null) {
                if (value.getAttr() == 2) {
                    startNum = this.setFat(1);
                    catalog = new FileModel(name, startNum);
                    catalog.setFather(this.nowCatalog);
                    this.nowCatalog.subMap.put(name, catalog);
                    var10002 = this.fat[0]--;
                    this.totalFiles.put(catalog.getName(), catalog);
                    System.out.println("创建目录成功！");
                    this.showFile();
                } else if (value.getAttr() == 3) {
                    System.out.println("创建目录失败，该目录已存在！");
                    this.showFile();
                }
            } else if (value == null) {
                startNum = this.setFat(1);
                catalog = new FileModel(name, startNum);
                catalog.setFather(this.nowCatalog);
                this.nowCatalog.subMap.put(name, catalog);
                var10002 = this.fat[0]--;
                this.totalFiles.put(catalog.getName(), catalog);
                System.out.println("创建目录成功！");
                this.showFile();
            }
        } else {
            System.out.println("创建目录失败，磁盘空间不足！");
        }

    }

    public void showFile() {
        System.out.println("***************** < " + this.nowCatalog.getName() + " > *****************");
        if (!this.nowCatalog.subMap.isEmpty()) {
            Iterator var2 = this.nowCatalog.subMap.values().iterator();

            while(var2.hasNext()) {
                FileModel value = (FileModel)var2.next();
                if (value.getAttr() == 3) {
                    System.out.println("文件名 : " + value.getName());
                    System.out.println("操作类型 ： 文件夹");
                    System.out.println("起始盘块 ： " + value.getStartNum());
                    System.out.println("大小 : " + value.getSize());
                    System.out.println("<-------------------------------------->");
                } else if (value.getAttr() == 2) {
                    System.out.println("文件名 : " + value.getName() + "." + value.getType());
                    System.out.println("操作类型 ： 可读可写文件");
                    System.out.println("起始盘块 ： " + value.getStartNum());
                    System.out.println("大小 : " + value.getSize());
                    System.out.println("<-------------------------------------->");
                }
            }
        }

        for(int i = 0; i < 2; ++i) {
            System.out.println();
        }

        System.out.println("磁盘剩余空间 ：" + this.fat[0] + "            " + "退出系统请输入exit");
        System.out.println();
    }

    public void deleteFile(String name) {
        FileModel value = (FileModel)this.nowCatalog.subMap.get(name);
        if (value == null) {
            System.out.println("删除失败，没有该文件或文件夹!");
        } else if (!value.subMap.isEmpty()) {
            System.out.println("删除失败，该文件夹内含有文件！");
        } else {
            this.nowCatalog.subMap.remove(name);
            this.delFat(value.getStartNum());
            if (value.getAttr() == 3) {
                System.out.println("文件夹 " + value.getName() + " 已成功删除");
                this.showFile();
            } else if (value.getAttr() == 2) {
                System.out.println("文件 " + value.getName() + "已成功删除");
                this.showFile();
            }
        }

    }

    public void reName(String name, String newName) {
        if (this.nowCatalog.subMap.containsKey(name)) {
            if (this.nowCatalog.subMap.containsKey(newName)) {
                System.out.println("重命名失败，同名文件已存在！");
                this.showFile();
            } else {
                FileModel value = (FileModel)this.nowCatalog.subMap.get(name);
                value.setName(newName);
                this.nowCatalog.subMap.remove(name);
                this.nowCatalog.subMap.put(newName, value);
                System.out.println("重命名成功！");
                System.out.println();
                this.showFile();
            }
        } else {
            System.out.println("重命名失败，没有该文件！");
            this.showFile();
        }

    }

    public void changeType(String name, String type) {
        this.nowCatalog = this.nowCatalog.getFather();
        if (this.nowCatalog.subMap.containsKey(name)) {
            FileModel value = (FileModel)this.nowCatalog.subMap.get(name);
            if (value.getAttr() == 2) {
                value.setType(type);
                this.nowCatalog.subMap.remove(name);
                this.nowCatalog.subMap.put(name, value);
                System.out.println("修改类型成功！");
                this.showFile();
            } else if (value.getAttr() == 3) {
                System.out.println("修改错误，文件夹无法修改类型！");
                this.openFile(value.getName());
            }
        } else {
            System.out.println("修改错误，请检查输入文件名是否正确！");
        }

    }

    public void openFile(String name) {
        if (this.nowCatalog.subMap.containsKey(name)) {
            FileModel value = (FileModel)this.nowCatalog.subMap.get(name);
            if (value.getAttr() == 2) {
                this.nowCatalog = value;
                System.out.println("文件已打开，文件大小为 : " + value.getSize());
            } else if (value.getAttr() == 3) {
                this.nowCatalog = value;
                System.out.println("文件夹已打开！");
                this.showFile();
            }
        } else {
            System.out.println("打开失败，文件不存在！");
        }

    }

    public void reAdd(String name, int addSize) {
        if (this.fat[0] >= addSize) {
            this.nowCatalog = this.nowCatalog.getFather();
            if (this.nowCatalog.subMap.containsKey(name)) {
                FileModel value = (FileModel)this.nowCatalog.subMap.get(name);
                if (value.getAttr() == 2) {
                    value.setSize(value.getSize() + addSize);
                    this.reAddFat(value.getStartNum(), addSize);
                    System.out.println("追加内容成功！正在重新打开文件...");
                    this.openFile(name);
                } else {
                    System.out.println("追加内容失败，请确认文件名是否正确输入。");
                }
            } else {
                System.out.println("追加内容失败，请确认文件名是否正确输入！");
                this.showFile();
            }
        } else {
            System.out.println("追加内容失败，内存空间不足！");
        }

    }

    public void backFile() {
        if (this.nowCatalog.getFather() == null) {
            System.out.println("该文件没有上级目录！");
        } else {
            this.nowCatalog = this.nowCatalog.getFather();
            this.showFile();
        }

    }

    public void searchFile(String[] roadName) {
        FileModel theCatalog = this.nowCatalog;
        if (this.totalFiles.containsKey(roadName[roadName.length - 1])) {
            this.nowCatalog = this.root;
            if (this.nowCatalog.getName().equals(roadName[0])) {
                System.out.println("yes");

                for(int i = 1; i < roadName.length; ++i) {
                    if (!this.nowCatalog.subMap.containsKey(roadName[i])) {
                        System.out.println("找不到该路径下的文件或目录，请检查路径是否正确");
                        this.nowCatalog = theCatalog;
                        this.showFile();
                        break;
                    }

                    this.nowCatalog = (FileModel)this.nowCatalog.subMap.get(roadName[i]);
                }

                if (roadName.length > 1) {
                    this.nowCatalog = this.nowCatalog.getFather();
                    this.showFile();
                }
            } else {
                this.nowCatalog = theCatalog;
                System.out.println("请输入正确的绝对路径！");
                this.showFile();
            }
        } else {
            System.out.println("该文件或目录不存在，请输入正确的绝对路径！");
            this.showFile();
        }

    }

    public void showFAT() {
        for(int j = 0; j < 125; j += 5) {
            System.out.println("第几项 | " + j + "        " + (j + 1) + "        " + (j + 2) + "        " + (j + 3) + "        " + (j + 4));
            System.out.println("内容    | " + this.fat[j] + "        " + this.fat[j + 1] + "        " + this.fat[j + 2] + "        " + this.fat[j + 3] + "        " + this.fat[j + 4]);
            System.out.println();
        }

        int j = 125;
        System.out.println("第几项 | " + j + "        " + (j + 1) + "        " + (j + 2));
        System.out.println("内容    | " + this.fat[j] + "        " + this.fat[j + 1] + "        " + this.fat[j + 2]);
        System.out.println();
        this.showFile();
    }
}
