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
        this.getUserCreatedEvents = this.getUserCreatedEvents.bind(this);
        this.success_got_created_events = this.success_got_created_events.bind(this);

        this.state = {
            name: '',
            age: '',
            location: '',
            gotUser: false,
            user_preferences: [],
            user_created_events: [],
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
            .then(this.getUserCreatedEvents)
            .catch(this.fail)

    }

    getUserCreatedEvents(){
        let token = this.state.auth.access_token;

        fetch("/api/event/get_user_created_events", {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(checkStatus)
            .then(this.success_got_created_events)
            .catch(this.fail)
    }

    success_got_created_events(created_events){
        console.log("!!got user's created events = ", created_events);
        console.log("num of created events = ", created_events.length);
        this.setState({user_created_events: created_events});
    }

    success(user) {
        // update the states with the user JSON object
        console.log("success: user = ", user);
        this.setState({name: user.username, age: user.age, location: user.location,
            gotUser: true, user_preferences: user.preferences});
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

        let created_events = this.state.user_created_events.map( (created_event) => {
            return <div>
                <p>Name: {created_event.name}</p>
            </div>
        });

        let prefs = this.state.user_preferences.map( (preference) => {
            return <div>
                <p>{preference}</p>
            </div>
        });

        return (

            <div>
                <center><h2> Profile Page for {this.state.name} </h2></center>
                <hr/>
                <div className="container">
                    Name: {this.state.name} <br/>
                    Location: {this.state.location} <br/>
                    Age: {this.state.age} <br/>

                    <h2> Selected Preferences: </h2>
                    {prefs}
                    <br/>
                    <h2> Your Created Events: </h2>
                    {created_events}
                </div>
            </div>

        )
    }
}

export default withRouter(UserPage);