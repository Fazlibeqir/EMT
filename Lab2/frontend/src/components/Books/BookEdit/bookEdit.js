import React from "react";
import {useHistory} from "react-router-dom";

const BookEdit = (props)=>{
    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "NOVEL",
        author: 1,
        availableCopies: 0
    })
    const handleChanges = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }
    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.book.name;
        const availableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.book.availableCopies;
        const category = formData.category !== 0 ? formData.category : props.book.category;
        const author = formData.author !== 0 ? formData.author : props.book.author.id;

        props.onEditBook(props.book.id,name,category,author,availableCopies);
        history.push("/books");
    }
    return (
        <div className={"container-fluid"}>
            <hr/>
            <h1 className={"text-center"}>Edit an existing book</h1>
            <h2 className={"text-center"}>-{props.book.name}-</h2>
            <hr/>
            <div>
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               placeholder={props.book.name}
                               onChange={handleChanges}
                        />
                    </div>

                    <div className="form-group">
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChanges}>
                            {props.authors.map((term) => {
                                if (props.book.author !== undefined &&
                                    props.book.author.id === term.id)
                                    return <option selected={props.book.author.id}
                                                   value={term.id}>{term.name + " " + term.surname}</option>
                                else return <option value={term.id}>{term.name + " " + term.surname}</option>
                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChanges}>
                            {props.categories.map((term) => {
                                if (props.book.category !== undefined &&
                                    props.book.category === term)
                                    return <option selected={props.book.category} value={term}>{term}</option>
                                else return <option value={term}>{term}</option>
                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Available Copies</label>
                        <input type="text"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder={props.book.availableCopies}
                               onChange={handleChanges}
                        />
                    </div>
                    <div className={"text-center"}>
                        <button id="submit" type="submit" className="btn btn-block btn-primary">Submit</button>
                    </div>
                </form>
            </div>
        </div>

    )
}
export default BookEdit;
