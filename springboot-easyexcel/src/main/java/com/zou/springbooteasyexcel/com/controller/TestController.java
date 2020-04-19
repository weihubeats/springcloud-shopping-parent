package com.zou.springbooteasyexcel.com.controller;

import com.alibaba.excel.EasyExcel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.read.metadata.ReadWorkbook;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.zou.springbooteasyexcel.com.Dao.StudentDao;
import com.zou.springbooteasyexcel.com.Listener.StudentListener;
import com.zou.springbooteasyexcel.com.model.FillData;
import com.zou.springbooteasyexcel.com.model.Student;
import com.zou.springbooteasyexcel.com.util.TestFileUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WH
 * @version 1.0
 * @date 2020/4/17 23:00
 * @Description TODO
 */
@RestController
public class TestController {

    @Autowired
    StudentDao studentDao;

    /**
     *  导出excel
     * @param response
     * @throws IOException
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        //   Student.class 是按导出类  data()应为数据库查询数据，这里只是模拟
        EasyExcel.write(response.getOutputStream(), Student.class).sheet("模板").doWrite(data());
    }

    /**
     * 读取 excel
     * @return
     */
    @PostMapping("upload")
    public String upload(MultipartFile file) throws IOException {

        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        EasyExcel.read(file.getInputStream(), Student.class, new StudentListener(studentDao)).sheet(1).doRead();
        // 写法2：
        /*ExcelReader excelReader = EasyExcel.read(file.getInputStream(), Student.class, new StudentListener(studentDao)).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();*/
        return "success";
    }



    private static List<Student> data() {
        List<Student> list = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            list.add(student);
            student.setName("张三" + i);
            student.setAge(18 + i);
            student.setScore(new BigDecimal(i + ""));
        }
        return list;
    }


    @GetMapping("template")
    public String downloadDataByExcelTemplate(HttpServletResponse response) throws Exception {
        String templateFileName =
                TestFileUtil.getPath() + "excel"  + File.separator + "list.xlsx";
        System.out.println("templateFileName" +templateFileName);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = TestFileUtil.getPath() + "listFill" + System.currentTimeMillis() + ".xlsx";
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
        System.out.println("fileName" + fileName);
        EasyExcel.write(response.getOutputStream()).withTemplate(templateFileName).sheet().doFill(data());
        return "success";

    }

    /*public void list() {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // 填充list 的时候还要注意 模板中{.} 多了个点 表示list
        String templateFileName =
                TestFileUtil.getPath() + "excel"  + File.separator + "list.xlsx";

        // 方案1 一下子全部放到内存里面 并填充
        String fileName = TestFileUtil.getPath() + "listFill" + System.currentTimeMillis() + ".xlsx";
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(data());


        // 方案2 分多次 填充 会使用文件缓存（省内存）
        fileName = TestFileUtil.getPath() + "listFill" + System.currentTimeMillis() + ".xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        excelWriter.fill(data(), writeSheet);
        excelWriter.fill(data(), writeSheet);
        // 千万别忘记关闭流
        excelWriter.finish();

    }*/



    public static void downLoadExcelFile(HttpServletResponse response, Workbook wb, String filename) throws Exception {
        ServletOutputStream os = null;

        try {
            os = response.getOutputStream();
            response.setContentType("application/application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            wb.write(os);
        } catch (Exception var8) {
            var8.printStackTrace();
            throw var8;
        } finally {
            if (os != null) {
                os.close();
            }

        }

    }
}







