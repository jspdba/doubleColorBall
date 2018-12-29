package wuchaofei.top.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *文件工具类
 * Created by cofco on 2018/12/29.
 */

public class FileUtil {
   public static List<ArrayList> readTextFile(String path){
       ArrayList<String> arrayList = new ArrayList();
       try {
           File file = new File(path);
           InputStreamReader input = new InputStreamReader(new FileInputStream(file));
           BufferedReader bf = new BufferedReader(input);
           // 按行读取字符串
           String str;
           while ((str = bf.readLine()) != null) {
               str=str.trim();
               if(str.length()>0){
                   arrayList.add(str);
               }
           }
           bf.close();
           input.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
       List<ArrayList> result = new ArrayList<ArrayList>();
       for (String line : arrayList) {
           ArrayList list = new ArrayList();
           String[] strs = line.split("\\s+");
           for (int i = 0; i < strs.length; i++) {
               String str=strs[i].trim();
               if(str.length()>0){
                   list.add(Integer.parseInt(str));
               }
           }
           result.add(list);
       }
       return result;
   }


}
