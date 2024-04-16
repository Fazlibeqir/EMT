import React from 'react';

const countries=(props)=>{
    return(
        <div className={"container-fluid"}>
            <hr/>
            <h1 className={"text-center"}>Countries</h1>
            <hr/>
            <div>
                <table className={"table table-striped"}>
                    <thead>
                    <tr>
                        <th scope={"col"}>Name</th>
                        <th scope={"col"}>Continent</th>
                    </tr>
                    </thead>
                    <tbody>
                    {props.countries.map((term)=>{
                        return(
                            <tr key={term.id}>
                                <td>{term.name}</td>
                                <td>{term.continent}</td>
                            </tr>
                        );
                    })}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
export default countries;