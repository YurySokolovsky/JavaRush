package bookmanager.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bookmanager.model.Book;


@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private SessionFactory sessionFactory;

    //Получение текущей сессии
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    //Добавление книги
    public void addBook(Book book) {
        getCurrentSession().save(book);
    }

    //Редактирование книги
    public void updateBook(Book book) {
        Book bookToUpdate = getBook(book.getId());
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setDescription(book.getDescription());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setIsbn(book.getIsbn());
        bookToUpdate.setPrintYear(book.getPrintYear());
        bookToUpdate.setReadAlready(book.getReadAlready());
        getCurrentSession().update(bookToUpdate);
    }

    //Получение книги
    public Book getBook(int id) {
        Book book = (Book) getCurrentSession().get(Book.class, id);
        return book;
    }

    //Удаление книги
    public void deleteBook(int id) {
        Book book = getBook(id);
        if (book != null) {
            getCurrentSession().delete(book);
        }
    }

    //Получение списка книг
    @SuppressWarnings("uncheked")
    public List<Book> getBooks() {
        return getCurrentSession().createQuery("from Book").list();
    }

    //ПОИСК КНИГ

    List<Book> list = new ArrayList<>();
    List<Book> resultList = new ArrayList<>();

    //Поиск по имени
    public List<Book> searchBooks(Book book) {
        list = getBooks();
        resultList.clear();
         //Если поля пустые
        if (book.getId() == null && book.getTitle() == null && book.getDescription() == null && book.getAuthor() == null
                && book.getIsbn() == null && book.getPrintYear() == null && book.getReadAlready() == null) {
            return resultList;
        }
        else {
            searchBooksID (book);
        }
        return resultList;
    }

    //Поиск книги по ID
    public void searchBooksID (Book book) {
        if (book.getId() != null) {
            for (Book b : list) {
                if (b.getId().equals(book.getId())) {
                    resultList.add(b);
                }
            }
            searchBooksTitleIterator(book);
        }
        else {
            searchBooksTitle(book);
        }
    }

   //Поиск книги по названию
   public void searchBooksTitle(Book book) {
       if (book.getTitle() != null && book.getTitle().length() != 0) {
           for (Book b : list) {
               if (b.getTitle().equals(book.getTitle())) {
                   resultList.add(b);
               }
           }
           searchBooksDescriptionIterator(book);
       }
       else {
           searchBooksDescription(book);
       }
   }

   //Поиск книги по Аннотации
   public void searchBooksDescription(Book book) {
       if (book.getDescription() != null && book.getDescription().length() != 0) {
           for (Book b : list) {
               if (b.getDescription().toLowerCase().indexOf(book.getDescription().toLowerCase()) != -1) {
                   resultList.add(b);
               }
           }
           searchBooksAuthorIterator(book);
       }
       else {
           searchBooksAuthor(book);
       }
   }

   //Поиск книги по Автору
    public void searchBooksAuthor(Book book) {
        if (book.getAuthor() != null && book.getAuthor().length() != 0) {
            for (Book b : list) {
                if (b.getAuthor().equals(book.getAuthor())) {
                    resultList.add(b);
                }
            }
            searchBooksIsbnIterator(book);
        }
        else {
            searchBooksIsbn(book);
        }
    }


    //Поиск книги по ISBN
    public void searchBooksIsbn(Book book) {
        if (book.getIsbn() != null && book.getIsbn().length() != 0) {
            for (Book b : list) {
                if (b.getIsbn().equals(book.getIsbn())) {
                    resultList.add(b);
                }
            }
            searchBooksPrintYearIterator(book);
        }
        else {
            searchBooksPrintYear(book);
        }
    }


   //Поиск книги по Году
   public void searchBooksPrintYear(Book book){
       if (book.getPrintYear() != null) {
           for (Book b : list) {
               if (b.getPrintYear().equals(book.getPrintYear())) {
                   resultList.add(b);
               }
           }
           searchBooksReadAlreadyIterator(book);
       }
       else {
           searchBooksReadAlready(book);
       }
   }

   //Поиск книги по прочтению
    public void searchBooksReadAlready(Book book){
        if (book.getReadAlready() != null) {
            for (Book b : list) {
                if (b.getReadAlready().equals(book.getReadAlready())) {
                    resultList.add(b);
                }
            }
        }
    }

   //Исключение книги по Наименованию
   public void searchBooksTitleIterator(Book book) {
       if (book.getTitle() != null && book.getTitle().length() != 0) {
           Iterator<Book> it = resultList.iterator();
           while (it.hasNext()) {
               Book taken = it.next();
               if (!taken.getTitle().contains(book.getTitle())) {
                   it.remove();
               }
           }
       }
       searchBooksDescriptionIterator(book);
   }

    //Исключение книги по Аннотации
    public void searchBooksDescriptionIterator(Book book) {
        if (book.getDescription() != null && book.getDescription().length() != 0) {
            Iterator<Book> it = resultList.iterator();
            while (it.hasNext()) {
                Book taken = it.next();
                if (taken.getDescription().toLowerCase().indexOf(book.getDescription().toLowerCase()) != -1) {
                    it.remove();
                }
            }
        }
        searchBooksAuthorIterator(book);
    }

    //Исключение книги по Автору
    public void searchBooksAuthorIterator(Book book) {
        if (book.getAuthor() != null) {
            Iterator<Book> it = resultList.iterator();
            while (it.hasNext()) {
                Book taken = it.next();
                if (!taken.getAuthor().contains(book.getAuthor())) {
                    it.remove();
                }
            }
        }
        searchBooksIsbnIterator(book);
    }

    //Исключение книги по ISBN
    public void searchBooksIsbnIterator(Book book) {
        if (book.getIsbn() != null && book.getIsbn().length() != 0) {
            Iterator<Book> it = resultList.iterator();
            while (it.hasNext()) {
                Book taken = it.next();
                if (!taken.getIsbn().contains(book.getIsbn())) {
                    it.remove();
                }
            }
        }
        searchBooksPrintYearIterator(book);
    }

    //Исключение книги по Году
    public void searchBooksPrintYearIterator(Book book) {
        if (book.getPrintYear() != null) {
            Iterator<Book> it = resultList.iterator();
            while (it.hasNext()) {
                Book taken = it.next();
                if (!taken.getPrintYear().equals(book.getPrintYear())) {
                    it.remove();
                }
            }
        }
        searchBooksReadAlreadyIterator(book);
    }

    //Исключение книги по Прочтению
    public void searchBooksReadAlreadyIterator(Book book) {
        if (book.getReadAlready() != null) {
            Iterator<Book> it = resultList.iterator();
            while (it.hasNext()) {
                Book taken = it.next();
                if (!taken.getReadAlready().equals(book.getReadAlready())) {
                    it.remove();
                }
            }
        }
    }
}




