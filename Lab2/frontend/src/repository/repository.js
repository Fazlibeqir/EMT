import axios from "../custom-axios/axios";

const Repository = {
    fetchCountries:()=>{
        return axios.get("/countries");
    },
    fetchAuthors:()=>{
        return axios.get("/authors");
    },
    fetchBooks:()=>{
        return axios.get("/books");
    },
    fetchCategories:()=>{
        return axios.get("/categories");
    },
    deleteBook:(id)=>{
        return axios.delete(`/books/delete/${id}`);
    },
    addBook:(name,category,author,copies)=>{
        return axios.post("/books/add",{
            name:name,
            category:category,
            authorId:author,
            availableCopies:copies
        })
    },
    editBook:(id,name,category,author,copies)=>{
        return axios.put(`/books/edit/${id}`,{
            name:name,
            category:category,
            authorId:author,
            availableCopies:copies
        })
    },
    getCopy:(id)=>{
        return axios.post(`/books/getcopy/${id}`);
    },
    addCopy:(id)=>{
        return axios.post(`/books/addcopy/${id}`);
    },
    getBook:(id)=>{
        return axios.get(`/books/${id}`);
    },

}
export default Repository;