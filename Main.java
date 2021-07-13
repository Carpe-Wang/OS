package wkp;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
        public static void main(String[] args)  {
            OSManager manager=new OSManager();
            meun(manager);
        }
        public static void meun(OSManager manager) {
            Scanner s = new Scanner(System.in);
            String str = null;
            System.out.println("***********欢迎使用文件模拟操作系统***********");
            System.out.println();
            manager.showFile();
            System.out.println("请输入命令（输入help查看命令表）：");

            for(; (str = s.nextLine()) != null; System.out.println("请输入命令（输入help查看命令表）：")) {
                if (str.equals("exit")) {
                    System.out.println("感谢您的使用！");
                    break;
                }

                String[] strs = editStr(str);
                String var4;
                switch((var4 = strs[0]).hashCode()) {
                    case -2131902710:
                        if (var4.equals("changeType")) {
                            if (strs.length < 3) {
                                System.out.println("您所输入的命令有误，请检查！");
                            } else {
                                manager.changeType(strs[1], strs[2]);
                            }
                            continue;
                        }
                        break;
                    case -1541601573:
                        if (var4.equals("addContents")) {
                            if (strs.length < 3) {
                                System.out.println("您所输入的命令有误，请检查！");
                            } else {
                                manager.reAdd(strs[1], Integer.parseInt(strs[2]));
                            }
                            continue;
                        }
                        break;
                    case -1492745347:
                        if (var4.equals("createCatalog")) {
                            if (strs.length < 2) {
                                System.out.println("您所输入的命令有误，请检查！");
                            } else {
                                manager.createCatolog(strs[1]);
                            }
                            continue;
                        }
                        break;
                    case -1335458389:
                        if (var4.equals("delete")) {
                            if (strs.length < 2) {
                                System.out.println("您所输入的命令有误，请检查！");
                            } else {
                                manager.deleteFile(strs[1]);
                            }
                            continue;
                        }
                        break;
                    case -934594754:
                        if (var4.equals("rename")) {
                            if (strs.length < 3) {
                                System.out.println("您所输入的命令有误，请检查！");
                            } else {
                                manager.reName(strs[1], strs[2]);
                            }
                            continue;
                        }
                        break;
                    case -906336856:
                        if (var4.equals("search")) {
                            if (strs.length < 2) {
                                System.out.println("您所输入的命令有误，请检查！");
                            } else {
                                String[] roadName = strs[1].split("/");
                                manager.searchFile(roadName);
                            }
                            continue;
                        }
                        break;
                    case 3169:
                        if (var4.equals("cd")) {
                            if (strs.length < 2) {
                                System.out.println("您所输入的命令有误，请检查！");
                            } else {
                                manager.openFile(strs[1]);
                            }
                            continue;
                        }
                        break;
                    case 3046881:
                        if (var4.equals("cd..")) {
                            manager.backFile();
                            continue;
                        }
                        break;
                    case 3198785:
                        if (var4.equals("help")) {
                            System.out.println("命令如下（空格不能省略）：");
                            System.out.println("createFile FileName fileType fileSize");
                            System.out.println("<创建文件 如：createFile marco txt 5 >");
                            System.out.println();
                            System.out.println("createCatalog FatalogName");
                            System.out.println("<创建目录 如：createCatalog myFile >");
                            System.out.println();
                            System.out.println("open Name.FileTypt");
                            System.out.println("<打开文件 如：open marco.txt >");
                            System.out.println();
                            System.out.println("cd CatalogName");
                            System.out.println("<打开目录 如： cd myFile >");
                            System.out.println();
                            System.out.println("cd..");
                            System.out.println("<返回上级目录 如： cd..");
                            System.out.println();
                            System.out.println("delete FileName/CatalogName");
                            System.out.println("<删除文件或目录（目录必须为空）如：delete marco >");
                            System.out.println();
                            System.out.println("rename FileName/CatalogName NewName");
                            System.out.println("<重命名文件或目录 如： rename myfile mycomputer >");
                            System.out.println();
                            System.out.println("search FileAbsolutedRoad/CatalogAbsolutedRoad");
                            System.out.println("<根据绝对路径寻找文件或者目录 如： search root/marco >");
                            System.out.println();
                            System.out.println("showFAT");
                            System.out.println("<查看FAT表 如： showFAT>");
                            System.out.println();
                            System.out.println();
                            System.out.println("下列命令需要打开文件后操作：");
                            System.out.println("addContents FileName ContentSize");
                            System.out.println("<在文件内增加内容 如：ddContents marco 4 >");
                            System.out.println();
                            System.out.println("changeType FileName newType");
                            System.out.println("<改变文件类型 如： changeType marco doc>");
                            System.out.println();
                            continue;
                        }
                        break;
                    case 3417674:
                        if (var4.equals("open")) {
                            if (strs.length < 2) {
                                System.out.println("您所输入的命令有误，请检查！");
                            } else {
                                manager.openFile(strs[1]);
                            }
                            continue;
                        }
                        break;
                    case 1368796312:
                        if (var4.equals("createFile")) {
                            if (strs.length < 4) {
                                System.out.println("您所输入的命令有误，请检查");
                            } else {
                                manager.createFile(strs[1], strs[2], Integer.parseInt(strs[3]));
                            }
                            continue;
                        }
                        break;
                    case 2067265244:
                        if (var4.equals("showFAT")) {
                            manager.showFAT();
                            continue;
                        }
                }

                String[] var8 = strs;
                int var7 = strs.length;

                for(int var6 = 0; var6 < var7; ++var6) {
                    String st = var8[var6];
                    System.out.println(st);
                }

                System.out.println("您所输入的命令有误，请检查！");
            }

        }
        public  static String[] editStr(String str) {
            Pattern pattern = Pattern.compile("([a-zA-Z0-9.\\\\/]*) *");
            Matcher m = pattern.matcher(str);
            ArrayList list = new ArrayList();//JDK1.8以后，不需要写范型

            while(m.find()) {
                list.add(m.group(1));
            }

            String[] strs = (String[])list.toArray(new String[list.size()]);

            for(int i = 1; i < strs.length; ++i) {
                int j = strs[i].indexOf(".");
                if (j != -1) {
                    String[] index = strs[i].split("\\.");
                    strs[i] = index[0];
                }
            }

            return strs;
        }
}
