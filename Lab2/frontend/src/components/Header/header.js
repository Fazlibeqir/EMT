import React from "react";
import {Link} from "react-router-dom";
const header = (props) => {
   return (
       <header>
           <nav className="navbar navbar-dark bg-dark">
               <div className="container">
                   <div className="d-flex ">
                          <Link className="nav-link text-white" to={"/books"}>Books</Link>
                          <Link className="nav-link text-white" to={"/authors"}>Authors</Link>
                          <Link className="nav-link text-white" to={"/categories"}>Categories</Link>
                          <Link className="nav-link text-white" to={"/countries"}>Countries</Link>
                   </div>
               </div>
           </nav>
       </header>
   )
}
export default header;