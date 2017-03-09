import React from 'react';
import ReactDOM from 'react-dom';
import 'whatwg-fetch';
import auth from './auth';

// Currently, this page does not functionally work since no value is being read from the
// user information. The path was create just for navigation.

class UserPage extends React.Component {

    constructor() {
        super();
        this.state = {
            name: "",
            location: "",
            age: ""
        }
    }

    componentDidMount() {
        fetch('http://localhost:8080/UserController/userPage?username')
            .then (result => {
            this.setState({name: result.json()})
            });

        fetch("http://localhost:8080/UserController/userPage?location")
            .then (result => {
            this.setState({location: result.json()})
            });

        fetch('http://localhost:8080/UserController/userPage?age')
            .then (result => {
                this.setState({age: result.json()})
            });
        }

    render() {
        return(
            <div>
                <h> {this.state.name} </h> <br/>
                Location:{this.state.location} <br/>
                Age: {this.state.age} <br/>
            </div>
        )
    }
}

export default UserPage;