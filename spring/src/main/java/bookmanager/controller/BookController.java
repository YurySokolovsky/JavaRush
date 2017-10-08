package bookmanager.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import bookmanager.model.Book;
import  bookmanager.service.BookService;

@Controller
public class BookController {
    List<Book> searResult = new ArrayList<>();

    @Autowired
    private BookService bookService;

    //ГЛАВНАЯ СТРАНИЦА
    @RequestMapping(value = {"/list", "/", "/index"})
    public ModelAndView listOfBooks(@RequestParam(required = false) Integer page) {
        ModelAndView modelAndView = new ModelAndView("list-of-books");

        List <Book> books = bookService.getBooks();
        PagedListHolder<Book> pagedListHolder = new PagedListHolder<>(books);
        pagedListHolder.setPageSize(10);
        modelAndView.addObject("maxPages", pagedListHolder.getPageCount());

        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) page = 1;

        modelAndView.addObject("page", page);

        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
            modelAndView.addObject("books", pagedListHolder.getPageList());
        } else if (page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page - 1);
            modelAndView.addObject("books", pagedListHolder.getPageList());
        }
        return modelAndView;
    }

    //ДОБАВЛЕНИЕ КНИГИ
    // Добавление книги (GET)
    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String addBookPage(Book book) {
        return "add-book-form";
    }

    //Добавление книги (POST)
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ModelAndView addingBook (@ModelAttribute Book book, BindingResult result) {
        FormValidator formValidator = new FormValidator();
        formValidator.validate(result.getModel().get("book"), result);

        if(result.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("add-book-form",result.getModel());
            return modelAndView;
        }

        bookService.addBook(book);
        ModelAndView modelAndView = new ModelAndView("home");

        String message = "Книга \"" + book.getTitle() + ". "+ book.getAuthor() + ", " + book.getPrintYear() + "\"  успешно добавлена в базу.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    //РЕДАКТИРОВАНИЕ КНИГИ
    //Редактирование данных (GET)
   @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editBookPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("edit-book-form");

        Book book = bookService.getBook(id);

        modelAndView.addObject("book", book);
        return modelAndView;
    }

    //Редактирование данных (POST)
    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView edditingBook(@ModelAttribute Book book, @PathVariable Integer id, BindingResult result) {
        FormValidator formValidator = new FormValidator();
        formValidator.validate(result.getModel().get("book"), result);

        if(result.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("edit-book-form",result.getModel());
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("home");

        book.setReadAlready(false);
        bookService.updateBook(book);

        String message = "Книга \"" + book.getTitle() + ". "+ book.getAuthor() + ", " + book.getPrintYear() + "\"  успешно отредактирована.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    //УДАЛЕНИЕ КНИГИ
    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteBook(@PathVariable Integer id) {
        if(!searResult.isEmpty()){
            for(Book book : searResult)
            {
                if(book.getId().equals(id)){
                    searResult.remove(book);
                    break;
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView("home");

        Book book = bookService.getBook(id);

        String message = "Книга \"" + book.getTitle() + ". "+ book.getAuthor() + ", " + book.getPrintYear() + "\"  успешно удалена.";
        bookService.deleteBook(id);
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    //ПОИСК КНИГИ
    //Поиск книги(GET)
    @RequestMapping(value="/search-init", method=RequestMethod.GET)
    public ModelAndView searchBook() {
        ModelAndView modelAndView = new ModelAndView("search-books");
        modelAndView.addObject("searchBook", new Book());

        return modelAndView;
    }

    //Поиск книги(POST)
    @RequestMapping(value="/search-init", method = RequestMethod.POST)
    public ModelAndView searchBook(@ModelAttribute Book book, @RequestParam(required = false) Integer page) {
        ModelAndView modelAndView = new ModelAndView("result-of-search");
        searResult = bookService.searchBooks(book);

        PagedListHolder<Book> pagedListHolder = new PagedListHolder<>(searResult);
        pagedListHolder.setPageSize(10);
        modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
        modelAndView.addObject("sizeresult", pagedListHolder.getNrOfElements());

        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

        modelAndView.addObject("page", page);
        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            modelAndView.addObject("books", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            modelAndView.addObject("books", pagedListHolder.getPageList());
        }
        return modelAndView;
    }

    //РЕЗУЛЬТАТ ПОИСКА КНИГИ
    @RequestMapping(value="result")
    public ModelAndView resultOfSearch(@RequestParam(required = false) Integer page) {
        ModelAndView modelAndView;
        if(!searResult.isEmpty()){
            modelAndView = new ModelAndView("result-of-search");

            PagedListHolder<Book> pagedListHolder = new PagedListHolder<>(searResult);
            pagedListHolder.setPageSize(10);
            modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
            modelAndView.addObject("sizeresult", pagedListHolder.getNrOfElements());

            if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

            modelAndView.addObject("page", page);
            if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
                pagedListHolder.setPage(0);
                modelAndView.addObject("books", pagedListHolder.getPageList());
            }
            else if(page <= pagedListHolder.getPageCount()) {
                pagedListHolder.setPage(page-1);
                modelAndView.addObject("books", pagedListHolder.getPageList());
            }
        }
        else {
            modelAndView = new ModelAndView("search-books");
            modelAndView.addObject("searchBook", new Book());
            modelAndView.addObject("isSearch", "Результаты поиска не найдены, заполните форму");
        }
        return modelAndView;
    }

    //ЧИТАТЬ
    @RequestMapping(value="/read/{id}", method=RequestMethod.GET)
    public ModelAndView readBook(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("read-book");

        Book book = bookService.getBook(id);

        if (!book.getReadAlready()){
            book.setReadAlready(true);
            bookService.updateBook(book);
        }

        String message = "" + book.getTitle() + ". "+ book.getAuthor() + ", " + book.getPrintYear();
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
