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
            name: '',
            location: '',
            age: '',
        };

    }

    render() {

        return (

            <div>
                Name: {this.state.name} <br/>
                Location: {this.state.location} <br/>
                Age: {this.state.age} <br/>
            </div>

        )
    }
}
