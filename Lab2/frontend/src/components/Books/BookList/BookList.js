import React, {Component} from "react";
import BookTerm from "../BookTerm/bookTerm";
import {Link} from "react-router-dom";
import ReactPaginate  from "react-paginate";

class Books extends Component{
    constructor(props) {
        super(props);
        this.state = {
            page:0,
            size:5
        }
    }
    getBooksPage(offset, nextPageOffset){
        return this.props.books.map((term)=>{
            return (
                <BookTerm key={term.id}  term={term} onDelete={this.props.onDelete} onEdit={this.props.onEdit}
                onGetCopy={this.props.onGetCopy} onAddCopy={this.props.onAddCopy}/>
            );
        }).filter((book, index)=>{
            return index>=offset && index<nextPageOffset;
        });
    }
    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page:selected
        })
    }
    render() {
        const offset = this.state.page * this.state.size;
        const nextPageOffset =offset + this.state.size;
        const pageCount = Math.ceil(this.props.books.length / this.state.size);
        const books = this.getBooksPage(offset, nextPageOffset);
        return(
            <div className={"container-fluid"}>
                <hr/>
                <h1 className={"text-center"}>Books</h1>
                <hr/>
                <div>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Category</th>
                            <th scope={"col"}>Author</th>
                            <th scope={"col"}>Available Copies</th>
                            <th scope={"col"} className={"text-right"}>
                                <Link className={"btn btn-success"} to={"/books/add"}>Add new book</Link>
                            </th>
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
            </div>
        );
    }
}
export default Books;