package library.digitalization.controllers;

import library.digitalization.dao.BookDAO;
import library.digitalization.dao.PersonDAO;
import library.digitalization.models.Book;
import library.digitalization.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.getAllBooks());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookDAO.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(@PathVariable int id, Model model) {
        model.addAttribute("book", bookDAO.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String editBook(@ModelAttribute("book") @Valid Book book, @PathVariable int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDAO.updateBook(book, id);

        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("people", personDAO.findAll());
        model.addAttribute("book", bookDAO.findById(id));
        model.addAttribute("owner", bookDAO.getOwner(person.getId()));

        return "books/show";
    }

    @PatchMapping("/{id}/assign")
    public String assignPerson(@RequestParam("id") int personId, @PathVariable int id) {
        bookDAO.assignPerson(personId, id);

        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}/delete")
    public String deleteBook(@PathVariable int id) {
        bookDAO.deleteBook(id);

        return "redirect:/books";
    }

    @PatchMapping("/{id}/free")
    public String freeBook(@PathVariable int id) {
        bookDAO.freeBook(id);

        return "redirect:/books/{id}";
    }
}
