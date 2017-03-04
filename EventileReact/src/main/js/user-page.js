import React from 'react';
import ReactDOM from 'react-dom';
import 'whatwg-fetch';
import auth from './auth';

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
