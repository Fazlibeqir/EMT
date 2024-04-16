import React from "react";
import {useHistory} from "react-router-dom";

const BookAdd = (props) => {
    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "NOVEL",
        author:1,
        availableCopies:0
    })
    const handleChanges = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }
    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const category = formData.category;
        const author = formData.author;
        const availableCopies = formData.availableCopies;

        props.onAddBook(name,category,author,availableCopies);
        history.push("/books/add");
    }
    return (
        <div className={"container-fluid"}>
            <hr/>
            <h1 className={"text-center"}>Add a new book</h1>
            <hr/>
            <div>
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter Book name"
                               onChange={handleChanges}
                        />
                    </div>

                    <div className="form-group">
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChanges}>
                            {props.authors.map((term) =>
                                <option value={term.id}>{term.name + " " + term.surname}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChanges}>
                            {props.categories.map((term) =>
                                <option value={term}>{term}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available Copies</label>
                        <input type="text"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder="Available Copies"
                               required
                               onChange={handleChanges}
                        />
                    </div>
                    <div className={"text-center "}>
                        <button id="submit" type="submit" className="btn btn-primary btn-block">Submit</button>
                    </div>

                </form>
            </div>
        </div>

    )

}
export default BookAdd;