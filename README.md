# [Lab1](https://github.com/Fazlibeqir/EMT/tree/main/Lab1/Library)
## Library Management System API

This project is a Spring Boot application that serves as an API for a library management system. It includes functionality for librarians to manage books in a library, such as adding new books, deleting books that are no longer in good condition, updating book records, same for authors and countries

## Additional request
Print on console Book created using Listeners.

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

# [Lab2](https://github.com/Fazlibeqir/EMT/tree/main/Lab2/frontend)


# React App with API Endpoints

This React application interacts with a backend API that provides endpoints for managing authors, books, and countries.

## API Endpoints

### Authors

- `GET /authors`: Retrieves all authors.

### Books

- `GET /books`: Retrieves all books.
- `GET /books/{id}`: Retrieves a book by ID.
- `POST /books/add`: Adds a new book.
- `PUT /books/edit/{id}`: Updates book details.
- `POST /books/getcopy/{id}`: Decrements available copies when getting a copy.
- `POST /books/addcopy/{id}`: Increments available copies when returning a copy.
- `DELETE /books/delete/{id}`: Deletes a book.

### Countries

- `GET /countries`: Retrieves all countries.

## Getting Started

1. Clone this repository.
2. Run Backend Java Spring app
3. Install dependencies with `npm install`.
4. Run the development server with `npm start`.


## Contribution

Contributions are welcome! If you have any suggestions or find issues, please open an issue or submit a pull request.
