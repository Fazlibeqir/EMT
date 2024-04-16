import React from "react";
import { Link } from "react-router-dom";

const BookTerm = (props) => {
    return (
        <tr key={props.term.id}>
            <td>{props.term.name}</td>
            <td>{props.term.category}</td>
            <td>{props.term.author.name + " " + props.term.author.surname}</td>
            <td>{props.term.availableCopies}</td>
            <td className="text-right">
                <button
                    type="button"
                    title="Delete"
                    className="btn btn-danger ml-2"
                    onClick={() => props.onDelete(props.term.id)}
                >
                    Delete
                </button>
                <Link
                    to={`/books/edit/${props.term.id}`}
                    className="btn btn-info ml-2"
                    onClick={() => props.onEdit(props.term.id)}>
                    Edit </Link>
                <button
                    type="button"
                    title="Get Copy"
                    className="btn btn-primary ml-2"
                    onClick={() => props.onGetCopy(props.term.id)}>
                    Mark as Taken</button>
                <button
                    type="button"
                    title="Add Copy"
                    className="btn btn-success ml-2"
                    onClick={() => props.onAddCopy(props.term.id)}>
                    Unmark as Taken</button>
            </td>
        </tr>
    );
};

export default BookTerm;
