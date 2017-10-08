package bookmanager.controller;

import bookmanager.model.Book;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidator implements Validator {
    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Book book = (Book) obj;

        //Валидация названия книги
        ValidationUtils.rejectIfEmpty(errors,"title", "title.empty", "Поле \"Наименование\" должно быть заполнено!");

        //Валидация описания
        ValidationUtils.rejectIfEmpty(errors,"description", "description.empty", "Поле \"Описание\" должно быть заполнено!");

        //Валидация автора
        ValidationUtils.rejectIfEmpty(errors,"author", "author.empty", "Поле \"Автор\" должно быть заполнено!");

        //Валидация ISBN
        ValidationUtils.rejectIfEmpty(errors,"isbn", "isbn.empty", "Поле \"ISBN\" должно быть заполнено!");

        //Валидация года издания
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"printYear", "printYear.is.empty" ,"Поле \"Год\"должено быть заполнено!");
        if (book.getPrintYear() != null) {
            pattern = Pattern.compile("[0-9]{0,4}");
            matcher = pattern.matcher(book.getPrintYear().toString());
            if (!matcher.matches()) {
                errors.rejectValue("printYear", "printYear.is.bad", "Заполните правильно поле \"Год\". Год должен быть числом!");

            }
            else if (book.getPrintYear() < 1900 || book.getPrintYear() > 2200) {
                errors.rejectValue("printYear", "printYear.to.long", "Год должен быть от 1900 г. до 2200 г.‚");
            }

        }

    }
}