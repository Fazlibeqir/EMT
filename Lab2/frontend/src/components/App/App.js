import './App.css';
import React, {Component} from 'react';
import {BrowserRouter as Routes, Redirect , Route, Switch} from "react-router-dom";
import Repository from "../../repository/repository";
import Authors from "../Authors/AuthorList/authors";
import Categories from "../Categories/categories";
import Countries from "../Countries/countries";
import Header from "../Header/header";
import Books from "../Books/BookList/BookList";
import BookEdit from "../Books/BookEdit/bookEdit";
import BookAdd from "../Books/BookAdd/bookAdd";


class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            books: [],
            authors: [],
            categories: [],
            countries: [],
            selectedBook: {},
            redirectFromAdd: false,
        }
    }
    loadBooks = () => {
        Repository.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }
    loadAuthors=()=>{
        Repository.fetchAuthors()
            .then((data)=>{
                this.setState({
                    authors:data.data
                })
            });
    }
    loadCountries=()=>{
        Repository.fetchCountries()
            .then((data)=>{
                this.setState({
                    countries:data.data
                })
            });
    }
    loadCategories=()=>{
        Repository.fetchCategories()
            .then((data)=>{
                this.setState({
                    categories:data.data
                })
            });
    }
    deleteBook = (id)=>{
        Repository.deleteBook(id)
            .then(()=>{
                this.loadBooks();
            })
    }
    addBook=(name,category,author,availableCopies)=>{
        Repository.addBook(name,category,author,availableCopies)
            .then(()=>{
                this.loadBooks();
                this.setState({redirectFromAdd:true});
            })
            .catch((err)=>{
                console.error('Error while adding book: ',err);
            })
    }
    editBook=(id,name,category,author,availableCopies)=>{
        Repository.editBook(id,name,category,author,availableCopies)
            .then(()=>{
                this.loadBooks();
            })
    }
    getBook=(id)=>{
        Repository.getBook(id)
            .then((data)=>{
                this.setState({
                    selectedBook:data.data
                })
            })
    }
    getBookCopy=(id)=>{
        Repository.getCopy(id)
            .then(()=>{
                this.loadBooks();
            })
    }
    addBookCopy=(id)=>{
        Repository.addCopy(id)
            .then(()=>{
                this.loadBooks();
            })
    }
    componentDidMount() {
        this.loadBooks();
        this.loadAuthors();
        this.loadCategories();
        this.loadCountries();
    }

    render() {
        const {redirectFromAdd} = this.state;
        return (
            <Routes>
                <Header/>
                <main>
                    <div className="container">
                        <Switch>
                        <Route path={"/categories"} exact render={() =>
                            <Categories
                                categories={this.state.categories}/>}/>
                        <Route path={"/authors"} exact render={() =>
                            <Authors
                                authors={this.state.authors}/>}/>
                        <Route path={"/countries"} exact render={() =>
                            <Countries
                                countries={this.state.countries}/>}/>
                        <Route path={"/books/add"} exact render={() =>
                            <BookAdd
                                authors={this.state.authors}
                                categories={this.state.categories}
                                onAddBook={this.addBook}/>}/>
                        <Route path={"/books/edit/:id"} exact render={() =>
                            <BookEdit
                                authors={this.state.authors}
                                categories={this.state.categories}
                                onEditBook={this.editBook}
                                book={this.state.selectedBook}/>}/>
                        <Route path={["/books", "/"]} exact render={() =>
                            <Books
                                books={this.state.books}
                                onDelete={this.deleteBook}
                                onGetCopy={this.getBookCopy}
                                onAddCopy={this.addBookCopy}
                                onEdit={this.getBook}/>}/>
                        <Redirect to={"/"}/>
                        </Switch>
                        {redirectFromAdd && <Redirect to={"/books"}/>}
                    </div>
                </main>
            </Routes>
        );
    }
}

export default App;
