package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        System.out.println("Publisher Count: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");//1
        Book ddd = new Book("Domain Driven Design", "123123");//1
        eric.getBooks().add(ddd);//1
        ddd.getAuthors().add(eric);//1

        ddd.setPublisher(publisher);//2
        publisher.getBooks().add(ddd);//2

        authorRepository.save(eric);//1
        bookRepository.save(ddd);//1
        publisherRepository.save(publisher);//2

        Author rod = new Author("Rod", "Johnson");//1
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");//1
        rod.getBooks().add(noEJB);//1
        noEJB.getAuthors().add(rod);//1

        noEJB.setPublisher(publisher);//2
        publisher.getBooks().add(noEJB);//2

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);//2

        System.out.println("Number of Books: " + bookRepository.count());//1
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());//2
    }
}
