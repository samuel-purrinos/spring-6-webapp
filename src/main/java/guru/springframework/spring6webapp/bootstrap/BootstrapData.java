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

        Book ericBook = new Book();
        ericBook.setTitle("Domain driven design");
        ericBook.setIsbn("1234567890");

        Author savedEric = authorRepository.save(eric);
        Book savedEricBook = bookRepository.save(ericBook);

        Author rod = new Author();
        eric.setFirstName("Rod");
        eric.setLastName("Johnson");

        Book noEJB = new Book();
        ericBook.setTitle("J2EE development without EJB");
        ericBook.setIsbn("1234567890");

        Author savedRod = authorRepository.save(rod);
        Book savedNoEjb = bookRepository.save(noEJB);

        Publisher ericPublisher = new Publisher();
        ericPublisher.setPublisherName("PACKT");
        ericPublisher.setCity("New York");
        ericPublisher.setAddress("40 5th avenue");
        ericPublisher.setZip("12345");

        publisherRepository.save(ericPublisher);

        savedEricBook.setPublisher(ericPublisher);
        savedNoEjb.setPublisher(ericPublisher);

        savedEric.getBooks().add(savedEricBook);
        savedRod.getBooks().add(savedNoEjb);

        authorRepository.save(savedEric);
        authorRepository.save(savedRod);

        bookRepository.save(savedEricBook);
        bookRepository.save(savedNoEjb);

        logger.info("In bootstrap");
        logger.info("Author count: {}",authorRepository.count());
        logger.info("Book count: {}",bookRepository.count());
        logger.info("Publisher count: {}",publisherRepository.count());
    }
}
