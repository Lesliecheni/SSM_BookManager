package com.leslie.dao;

import com.leslie.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeslieCheni
 * @Date Created in 21:28 2020/6/3 0003
 * @Version JDK1.8
 */
public interface BookMapper {

    //add a book
    int addBook(Books books);


    //del a book
    int deleteBookById(@Param("bookID") int id);

    //update a book
    int updateBook(Books books);


    //find a book
    Books queryBookById(@Param("bookID") int id);

    //find all
    List<Books> queryAllBook();

    Books queryBookByName(@Param("bookName") String bookName);
}
