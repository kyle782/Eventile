import React from 'react';
import ReactDOM from 'react-dom';
import 'whatwg-fetch';
import auth from './auth';
import { withRouter } from 'react-router';


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
        this.getUser = this.getUser.bind(this);
        this.success = this.success.bind(this);
        this.fail = this.fail.bind(this);
        this.render = this.render.bind(this);

        this.state = {
            name: '',
            age: '',
            location: '',
            gotUser: false,
            auth: JSON.parse(localStorage.auth)
        }
    }

    getUser(){
        let token = this.state.auth.access_token; // authentication token to make sure user is signed in/authorized

        fetch("/api/user", {        // GET the user from the usercontroller, make REST call
            headers: {
                'Authorization': 'Bearer ' + token // pass authentication token as a header to the REST API call
            }
        })
            .then(checkStatus)
            .then(this.success)
            .catch(this.fail)

    }

    success(user) {
        // update the states with the user JSON object
        console.log("success: user = " + user);
        this.setState({name: user.username, age: user.age, location: user.location, gotUser: true});
    }

    fail(error) {
        console.error("Search has failed", error);
        this.setState({gotUser: true});
        if(error.response.status == 401) {
            auth.logOut();
            this.props.router.replace({
                pathname: "/signin",
                state: {nextPath: "/search"}
            })
        }
    }

    render() {

        // needed to stop the infinite looping
        if (this.state.gotUser == false){
            this.getUser();
        }

        return (

            <div>
                Name: {this.state.name} <br/>
                Location: {this.state.location} <br/>
                Age: {this.state.age} <br/>
            </div>

        )
    }
}

export default withRouter(UserPage);