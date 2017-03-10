import React from 'react';
import ReactDOM from 'react-dom';
import 'whatwg-fetch';
import auth from './auth';

// Currently, this page does not functionally work since no value is being read from the
// user information. The path was create just for navigation.

function checkStatus(response) {
    if(response.status >= 200 && response.status < 300) {
        return response.json()
    } else {
        var error = new Error(response.statusText);
        error.response = response;
        throw error;
    }
};

class UserPage extends React.Component {
    constructor() {
        super();
        this.state = {
            name: 'noname',
            age: '000',
            location: 'location',
            auth: JSON.parse(localStorage.auth)
        };
        this.getUser = this.getUser.bind(this);
        this.success = this.success.bind(this);
        this.fail = this.fail.bind(this);
    }

    getUser(){
        let token = this.state.auth.access_token;
        console.log("plss");
        fetch("/api/user", {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(checkStatus)
            .then(this.success)
            .catch(this.fail)

    }

    success(user) {
        console.log("Search result", user);
        this.setState({name: user.username, age: user.age, location: user.location});
    }

    fail(error) {
        console.error("Search has failed", error);
        if(error.response.status == 401) {
            auth.logOut();
            this.props.router.replace({
                pathname: "/signin",
                state: {nextPath: "/search"}
            })
        }
    }

    render() {

        return (

            <div onLoad={this.getUser()}>
                Name: {this.state.name} <br/>
                Location: {this.state.location} <br/>
                Age: {this.state.age} <br/>
            </div>

        )
    }
}

export default UserPage;