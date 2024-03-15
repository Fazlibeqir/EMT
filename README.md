# Lab1
## Library Management System API

This project is a Spring Boot application that serves as an API for a library management system. It includes functionality for librarians to manage books in a library, such as adding new books, deleting books that are no longer in good condition, updating book records, and marking books as rented, same for authors and countries

## Project Structure

The project follows a layered architecture with the following components:

- Controllers: Handle incoming HTTP requests and delegate to services.
- Services: Implement business logic and interact with repositories.
- Repositories: Interface with the database.

## Data Model

The application stores the following data for books:

- ID (Long)
- Name (String)
- Category (Enum: NOVEL, THRILLER, HISTORY, FANTASY, BIOGRAPHY, CLASSICS, DRAMA)
- Author (Author entity)
- Available Copies (Integer)

The Author entity includes:

- ID (Long)
- Name (String)
- Surname (String)
- Country (Country entity)

The Country entity includes:

- ID (Long)
- Name (String)
- Continent (String)

## API Endpoints
- Author
- `GET /authors/{id}`: Retrieve a author by id.
- `GET /authors`: Retrieve all authors.
- `POST /authors/add`: Add a new author.
- `POST /authors/{id}`: Update author details.
- `DELETE /authors/{id}`: Delete a author.

- Book
- `GET /books`: Retrieve all books.
- `GET /books/{id}`: Retrieve a book by id.
- `POST /books/add`: Add a new book.
- `POST /books/edit/{id}`: Update book details.
- `DELETE /books/delete/{id}`: Delete a book.

- Country
- `GET /countries`: Retrieve all countries.
- `GET /countries/{id}`: Retrieve a country by id.
- `POST /countries/add`: Add a new country.
- `POST /countries/{id}`: Update country details.
- `DELETE /countries/{id}`: Delete a contry.


## Contribution

Contributions are welcome! If you have any suggestions or find issues, please open an issue or submit a pull request.
