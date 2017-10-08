package bookmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bookmanager.dao.BookDao;
import bookmanager.model.Book;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    public void addBook(Book book) {
         bookDao.addBook(book);

    }

    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    public Book getBook(int id) {
        return bookDao.getBook(id);
    }

    public void deleteBook(int id) {
        bookDao.deleteBook(id);

    }

    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    public List<Book> searchBooks(Book book) {
        return bookDao.searchBooks(book);
    }
}
