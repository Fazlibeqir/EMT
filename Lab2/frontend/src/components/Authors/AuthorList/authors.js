import React,{Component} from 'react';
import AuthorTerm from "../AuthorTerm/authorTerm";
import ReactPaginate from "react-paginate";

class Author extends Component{
    constructor(props) {
        super(props);
        this.state = {
            page:0,
            size:5
        }
    }

    getAuthorsPage(offset, nextPageOffset){
        return this.props.authors.map((term)=>{
            return (
                <AuthorTerm key={term.id} term={term}/>
            );
        }).filter((author, index)=>{
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
        const pageCount = Math.ceil(this.props.authors.length / this.state.size);
        const author = this.getAuthorsPage(offset, nextPageOffset);
        return (
            <div className={"container-fluid"}>
                <hr/>
                <h1 className={"text-center"}>Authors</h1>
                <hr/>
                <div>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Surname</th>
                            <th scope={"col"}>Country</th>
                        </tr>
                        </thead>
                        <tbody>
                        {author}
                        </tbody>
                    </table>
                    <ReactPaginate previousLabel={"back"}
                                      nextLabel={"next"}
                                      breakLabel={<a href="/#">...</a>}
                                      breakClassName={"break-me"}
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
export default Author;