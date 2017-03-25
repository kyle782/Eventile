/**
 * Created by gary on 2017-03-22.
 */
import React from 'react';
import ReactDOM from 'react-dom';
import 'whatwg-fetch';


class CreateEventForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            new_event_name: '',
            new_event_description: '',
            new_event_location: '',
            new_event_date: '',
            new_event_category: '',
            new_event_timezone: '',
            new_event_time: '',
            new_event_img: ''
        };

        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    }

    render() {
        return (

            <form className="form-horizontal" name="createEventForm" onSubmit={this.props.onSubmit} ref="createEventForm">
                <div className="form-group">
                    <label htmlFor="new_event_name" className="col-sm-4 control-label">Name of the Event:</label>
                    <div className="col-sm-8">
                        <input type="text"
                               className="form-control" id="new_event_name"
                               placeholder="Name for the event"
                               ref="new_event_name"
                        />
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="new_event_description" className="col-sm-4 control-label">Description:</label>
                    <div className="col-sm-8">
                        <input type="text"
                               className="form-control" id="new_event_description"
                               placeholder="Tell us about the event..."
                               ref="new_event_description"
                        />
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="new_event_location" className="col-sm-4 control-label">Location:</label>
                    <div className="col-sm-8">
                        <input type="text"
                               className="form-control" id="new_event_location"
                               placeholder="London, Ontario"
                               ref="new_event_location"
                        />
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="new_event_date" className="col-sm-4 control-label">Date:</label>
                    <div className="col-sm-8">
                        <input type="text"
                               className="form-control" id="new_event_date"
                               placeholder="When will the event take place?"
                               ref="new_event_date"
                        />
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="new_event_time" className="col-sm-4 control-label">Time:</label>
                    <div className="col-sm-8">
                        <input type="text"
                               className="form-control" id="new_event_time"
                               placeholder="What time will event start?"
                               ref="new_event_time"
                        />
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="new_event_timezone" className="col-sm-4 control-label">Timezone:</label>
                    <div className="col-sm-8">
                        <input type="text"
                               className="form-control" id="new_event_timezone"
                               placeholder="What is the time zone of event location?"
                               ref="new_event_timezone"
                        />
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="new_event_category" className="col-sm-4 control-label">Category:</label>
                    <div className="col-sm-8">
                        <input type="text"
                               className="form-control" id="new_event_category"
                               placeholder="What kind of event is it?"
                               ref="new_event_category"
                        />
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="new_event_img" className="col-sm-4 control-label">Image:</label>
                    <div className="col-sm-8">
                        <input type="text"
                               className="form-control" id="new_event_img"
                               placeholder="Include Image URL (Not Required)"
                               ref="new_event_img"
                        />
                    </div>
                </div>

                {/** Submit button **/}

                <div className="form-group">
                    <div className="col-sm-offset-3 col-sm-9">
                        <button type="submit" className="btn btn-default">Create Event!</button>
                    </div>
                </div>
            </form>

        )
    }

    data() {
        let form_event_name = ReactDOM.findDOMNode(this.refs.new_event_name).value.trim(),
            form_event_description = ReactDOM.findDOMNode(this.refs.new_event_description).value.trim(),
            form_event_location = ReactDOM.findDOMNode(this.refs.new_event_location).value.trim(),
            form_event_date = ReactDOM.findDOMNode(this.refs.new_event_date).value.trim(),
            form_event_time = ReactDOM.findDOMNode(this.refs.new_event_time).value.trim(),
            form_event_timezone = ReactDOM.findDOMNode(this.refs.new_event_timezone).value.trim(),
            form_event_category = ReactDOM.findDOMNode(this.refs.new_event_category).value.trim(),
            form_event_img = ReactDOM.findDOMNode(this.refs.new_event_img).value.trim();


        return {
            event_name: form_event_name,
            event_description: form_event_description,
            event_date: form_event_date,
            event_location: form_event_location,
            event_time: form_event_time,
            event_timezone: form_event_timezone,
            event_category: form_event_category,
            event_img: form_event_img
        }
    }

}

export default CreateEventForm;