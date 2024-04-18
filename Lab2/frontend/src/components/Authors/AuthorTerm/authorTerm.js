import React from "react";

const AuthorTerm = (props) => {
    return(
        <tr key={props.term.id}>
            <td>{props.term.name}</td>
            <td>{props.term.surname}</td>
            <td>{props.term.country.name}</td>
        </tr>)
}
export default AuthorTerm;