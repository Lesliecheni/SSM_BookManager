package com.leslie.controller;

import com.leslie.pojo.Books;
import com.leslie.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author LeslieCheni
 * @Date Created in 19:43 2020/6/2 0002
 * @Version JDK1.8
 */

@Controller
@RequestMapping("/book")
public class BookController {
    //controller 调service层

    @Autowired
    private BookService bookService;


    //查询所有书籍
    @RequestMapping("/allBook")
    public String List(Model model){
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list",list);
        return "allBook";
    }

    //跳转到添加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddPaper(){

        return "addBook";
    }

    //添加书籍实现
    @RequestMapping("/addBook")
    public String addBook(Books books){
        System.out.println("addbook->"+books);
        bookService.addBook(books);
        return "redirect:/book/allBook";    //重定向到一个请求
    }


    //跳转到修改页面
    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(int id,Model model){
        Books books = bookService.queryBookById(id);
        model.addAttribute("Qbook",books);

        return "updateBook";
    }


    //修改数据
    @RequestMapping("/updateBook")
    public String updateBook(Books books){
        System.out.println("updatebook--->"+books);
        bookService.updateBook(books);

        return "redirect:/book/allBook";    //重定向到一个请求
    }

    //删除数据
    @RequestMapping("/del")
    public String delBook(int id){
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    //查询数据
    @RequestMapping("/queryBook")
    public String queryBook(String queryBookName,Model model){
        Books books = bookService.queryBookByName(queryBookName);

        //System.err.println(books);
        List<Books> list = new ArrayList<Books>();
        list.add(books);

        if (books==null){
            list = bookService.queryAllBook();
            model.addAttribute("error","未查到");
        }
        model.addAttribute("list",list);
        return "allBook";

    }

    @RequestMapping("/toUploadFile")
    public String toUploadFile(){

        return "addBook";
    }

    @RequestMapping("/toDownloadFile")
    public String toDownloadFile(){
        return "downloadFile";
    }

    @RequestMapping("/fileupload")
    public String fileupload(HttpServletRequest request, MultipartFile upload) throws Exception {

        System.out.println("文件上传...");
        String path = request.getSession().getServletContext().getRealPath("/uploads");

        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }


        String filename = upload.getOriginalFilename();


        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;

        upload.transferTo(new File(path, filename));


        return "success";
    }



}
