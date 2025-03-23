package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.*;
import guru.springframework.spring6webapp.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(BootstrapData.class);


    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book dddBook = new Book();
        dddBook.setTitle("Domain driven design");
        dddBook.setIsbn("1234567890");

        Author savedEric = authorRepository.save(eric);
        Book savedDddBook = bookRepository.save(dddBook);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE development without EJB");
        noEJB.setIsbn("1234567890");

        Author savedRod = authorRepository.save(rod);
        Book savedNoEjb = bookRepository.save(noEJB);

        Publisher ericPublisher = new Publisher();
        ericPublisher.setPublisherName("PACKT");
        ericPublisher.setCity("New York");
        ericPublisher.setState("New York");
        ericPublisher.setAddress("40 5th avenue");
        ericPublisher.setZip("12345");

        publisherRepository.save(ericPublisher);

        savedDddBook.setPublisher(ericPublisher);
        savedNoEjb.setPublisher(ericPublisher);

        savedEric.getBooks().add(savedDddBook);
        savedRod.getBooks().add(savedNoEjb);
        savedDddBook.getAuthors().add(savedEric);
        savedNoEjb.getAuthors().add(savedRod);

        authorRepository.save(savedEric);
        authorRepository.save(savedRod);

        bookRepository.save(savedDddBook);
        bookRepository.save(savedNoEjb);

        logger.info("In bootstrap");
        logger.info("Author count: {}",authorRepository.count());
        logger.info("Book count: {}",bookRepository.count());
        logger.info("Publisher count: {}",publisherRepository.count());
    }
}
