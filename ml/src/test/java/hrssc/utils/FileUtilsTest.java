package com.hrssc.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Test;


import java.io.*;
import java.text.DateFormat;
import java.util.*;

public class FileUtilsTest {

    private static Logger logger = Logger.getLogger(FileUtilsTest.class);

    @Test
    public void testLog4j() {
        logger.info("info...");
        logger.warn("warn...");
        logger.error("error...");

        // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");
    }



    @Test
    public void testRenameFile(){
        FileUtils.removeFileSuffix("E:\\Doc\\Niukun\\工作记录\\2020年12月\\02 岗位技能提升\\Paas java file");
        System.out.println("success...");
    }

    @Test
    public void testid(){
        System.out.println(DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CHINA).format(new Date()));
    }


    @Test
    public void testReadFromJson() {
        String s = FileUtils.readJsonFile("src/main/resources/行政区域2020.json");

        JSONObject jsonObject = JSON.parseObject(s);

        String province = null;
        String city = null;

        Set<String> strings = jsonObject.keySet();
        for (String str : strings) {
            System.out.println("代码：" + str + "\t名称：" + jsonObject.get(str) + "\t省份名称：" + jsonObject.get(str.substring(0, 2) + "0000") + "\t城市名称：" + jsonObject.get(str.substring(0, 4) + "00"));
        }
    }

    @Test
    public void testSortFileLines() {
        FileUtils.sortFileLines("src/main/resources/companyinfo.txt");
        FileUtils.sortFileLines("src/main/resources/staffinfo.txt");
    }


    /**
     * 级联解密当前文件夹下面的文件
     */
    @Test
    public void testDecodeFilesCascade(){
        String filePath = "E:\\logs\\0622\\转正材料V1.1";

        try {
            FileUtils.cascadeDecodeFiles(filePath, filePath + "[de]\\");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 批量解密文件夹下面的文件（非级联）
     * @throws Exception
     */
    @Test
    public void testDecodeFiles() throws Exception {
        FileUtils.decodeAllFilesInFolder("E:\\logs\\0622");
//        FileUtils.decodeAllFilesInFolder("E:\\Doc\\项目文档\\容器化\\jar");
    }


    /**
     * 解密单个文件
     * @throws Exception
     */
    @Test
    public void testDecodeFile() throws Exception {
        String path = "E:\\Doc\\项目文档\\PAAS\\第三期\\【培训管理】PaaS数据操作设计【牛坤】20210519.pptx";
        FileUtils.decodeFilev2(path,path+".class" );
//        FileUtils.decodeFileOld(path+".class" ,path1 );
//        FileUtils.decodeFile("C:\\java\\runapi-1.0.0\\runapi-1.0.0.exe","C:\\java\\runapi-1.0.0\\runapi-1.0.1.exe" );
    }




    /**
     * 解密单个文件
     * @throws Exception
     */
    @Test
    public void testDecodeFileV3() throws Exception {
        String path = "E:\\Doc\\项目文档\\福袋\\V2.1\\生产环境代码备份\\libs\\orms\\orm_zkml_mysql.luckybag.main.xml";
        FileUtils.decodeFilev3(path );
//        FileUtils.decodeFileOld(path+".class" ,path1 );
//        FileUtils.decodeFile("C:\\java\\runapi-1.0.0\\runapi-1.0.0.exe","C:\\java\\runapi-1.0.0\\runapi-1.0.1.exe" );

    }




    @Test
    public void testList(){
        List<String> list = new ArrayList<String>();
        System.out.println(null == list);
        System.out.println(list);
    }



    @Test
    public void testit(){
        String filePath = "E:\\files\\1228\\111.pdf";
        String destPath = filePath +".txt";
        String f = "D:\\github\\NK_Utils\\ml\\src\\test\\java\\hrssc\\utils\\11111.pdf";
        try {
            File file = new File(filePath);
            file.length();
            file.renameTo(new File(destPath));
//            File f = new File(destPath);
//            f.renameTo(new File(destfile));
//            System.out.println(f.length());
//            FileUtils.decodeFileOld(destPath,f);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
