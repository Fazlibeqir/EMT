import React, {Component} from "react";
import BookTerm from "../BookTerm/bookTerm";
import {Link} from "react-router-dom";
import ReactPaginate  from "react-paginate";

class Books extends Component{
    constructor(props) {
        super(props);
        this.state = {
            page:0,
            size:5,
            searchText:'',
        }
    }
    getBooksPage(books,offset, nextPageOffset){
        return books.slice(offset,nextPageOffset).map((term,index)=>{
            return (
                <BookTerm key={index}  term={term} onDelete={this.props.onDelete} onEdit={this.props.onEdit}
                          onGetCopy={this.props.onGetCopy} onAddCopy={this.props.onAddCopy}/>
            );
        });
    }
    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page:selected
        })
    }
    getFilteredBooks(){
        return this.props.books.filter((term)=>{
            return term.name.toLowerCase().includes(this.state.searchText.toLowerCase());
        });
    }
    handleSearch = (e) => {
        e.preventDefault();
        this.setState({
            page:0
        })
    }
    handleSearchInputChanges = (e) => {
        this.setState({
            searchText:e.target.value
        })
    }
    render() {
        const offset = this.state.page * this.state.size;
        const nextPageOffset =offset + this.state.size;
        const filteredBooks = this.getFilteredBooks();
        const pageCount = Math.ceil(filteredBooks.length / this.state.size);
        const books = this.getBooksPage(filteredBooks,offset, nextPageOffset);
        const allBooks = this.props.books;

        return(
                // this is the Table of Books
            <div className={"container-fluid"}>
                <hr/>
                <h1 className={"text-center"}>Books</h1>
                <hr/>
                <div className="row justify-content-between align-items-center">
                    <div className="col-sm-6">
                        <form onSubmit={this.handleSearch}>
                            <div className="input-group mb-3">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Search by name"
                                    value={this.state.searchText}
                                    onChange={this.handleSearchInputChanges}
                                />
                                <div className="input-group-append">
                                    <button type="submit" className="btn btn-primary">
                                        Search
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div className="col-sm-6 text-right">
                        <Link className={"btn btn-success"} to={"/books/add"}>
                            Add new book
                        </Link>
                    </div>
                </div>
                <table className={"table table-striped"}>
                    <thead>
                    <tr>
                        <th scope={"col"}>Name</th>
                        <th scope={"col"}>Category</th>
                        <th scope={"col"}>Author</th>
                        <th scope={"col"}>Available Copies</th>

                    </tr>
                    </thead>
                    <tbody>
                    {books}
                    </tbody>
                </table>
                <ReactPaginate previousLabel={"back"}
                               nextLabel={"next"}
                               breakLabel={<a href="/#">...</a>}
                               breakClassName={"break-me"}
                               pageClassName={"ml-1"}
                               pageCount={pageCount}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination m-4 justify-content-center"}
                               subContainerClassName={"pages pagination"}
                               activeClassName={"active"}/>
            </div>
        );
    }
}

export default Books;