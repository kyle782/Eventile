/**
 * Created by gary on 2017-03-22.
 */
import React from 'react';
import 'whatwg-fetch';
import {withRouter} from 'react-router';
import CreateEventForm from './create-event-form';

const checkStatus = (response) => {
    console.log("response = ", response);
    if(response.status >= 200 && response.status < 300) {
        return response.json();
    } else {
        var error = new Error(response.statusText);
        error.response = response;
        throw error;
    }
};

class CreateEvent extends React.Component {
    constructor() {
        super();
        this.createEvent = this.createEvent.bind(this);
        this.success = this.success.bind(this);
        this.fail = this.fail.bind(this);

        this.state = {
            new_event_name: 'default',
            new_event_description: '',
            new_event_location: '',
            created: false,
            create_success: true,
            new_event_id: '',
            error: '',
            auth: JSON.parse(localStorage.auth)
        };
    }

    createEvent(e){
        e.preventDefault();
        let form = this.form.data();
        let token = this.state.auth.access_token;

        fetch("/api/event/create_event?event_name=" + form.event_name + "&event_description=" + form.event_description +
        "&event_location=" + form.event_location +
        "&event_date=" + form.event_date, {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer ' + token // pass authentication token as a header to the REST API call
            }
        })
            .then(checkStatus)
            .then(this.success)
            .catch(this.fail)
    }

    success(event) {
        console.log("created event! ", event);
        this.setState({created: true, create_success: true, new_event_id: event.eventbrite_id});

    }


    fail(error) {
        console.log("errorrrrr");
        if (error){
            this.setState({created: true, create_success: false, error: error.error})
        }
    }


    render () {
        let Success = () => <p className="alert alert-success">
            <a href={"/event?q=" + this.state.new_event_id}>Success! Created the event!</a>
        </p>;
        let Failed = () => <p className="alert alert-danger">Failed: {this.state.error}</p>;
        return (

                <div className="col-sm-4 col-sm-offset-4">
                    <center><h2> Create an Event </h2></center>
                    <hr/>
                    <CreateEventForm onSubmit={this.createEvent} ref={ (ref) => this.form = ref }/>
                    {this.state.created ? this.state.create_success ? <Success/> : <Failed/> : null }
                </div>

        )
    }


}

export default withRouter(CreateEvent);