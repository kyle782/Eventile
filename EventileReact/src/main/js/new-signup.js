/**
 * Created by FrankJiao on 2017-03-02.
 */
import React from 'react';
import ReactDOM from 'react-dom';
import 'whatwg-fetch';


class UserForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            pref_music: false,
            pref_bus_prof: false
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

            <form className="form-horizontal" name="signInForm" onSubmit={this.props.onSubmit} ref="signInForm">
                <div className="form-group">
                    <label htmlFor="signin-name" className="col-sm-3 control-label">Username</label>
                    <div className="col-sm-9">
                        <input type="text"
                               className="form-control" id="signin-name"
                               placeholder="Name"
                               ref="name"
                        />
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="inputPassword3" className="col-sm-3 control-label">Password</label>
                    <div className="col-sm-9">
                        <input type="password"
                               className="form-control"
                               id="signin-password"
                               placeholder="Password"
                               ref="password"
                        />
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="signin-age" className="col-sm-3 control-label">Age</label>
                    <div className="col-sm-9">
                        <input type="age"
                               className="form-control"
                               id="signin-age"
                               placeholder="Age"
                               ref="age"
                        />
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="userlocation" className="col-sm-3 control-label">Location</label>
                    <div className="col-sm-9">
                        <input type="location"
                               className="form-control"
                               id="signin-location"
                               placeholder="Location"
                               ref="location"
                        />
                    </div>
                </div>

                {/** Checkboxes for preferences **/}

                <div className="form-group">
                    <label htmlFor="pref_music" className="col-sm-3 control-label">Music</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_music"
                            className="form-check" /** might have to change this to align horizontally **/
                            type="checkbox"
                            checked={this.state.pref_music}
                            onChange={this.handleInputChange}
                            ref="pref_music"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_bus_prof" className="col-sm-3 control-label">Business & Professional</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_bus_prof"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_bus_prof}
                            onChange={this.handleInputChange}
                            ref="pref_bus_prof"
                        />
                    </div>
                </div>




                {/** Submit button **/}

                <div className="form-group">
                    <div className="col-sm-offset-3 col-sm-9">
                        <button type="submit" className="btn btn-default">{this.props.submitLabel}</button>
                    </div>
                </div>
            </form>
        )
    }

    data() {
        let name = ReactDOM.findDOMNode(this.refs.name).value.trim(),
            password = ReactDOM.findDOMNode(this.refs.password).value.trim(),
            age = ReactDOM.findDOMNode(this.refs.age).value.trim(),
            location = ReactDOM.findDOMNode(this.refs.location).value.trim();
        console.log(ReactDOM.findDOMNode(this.refs.pref_music).checked);
        console.log(ReactDOM.findDOMNode(this.refs.pref_bus_prof).checked);

        return {
            username: name,
            password: password,
            age: age,
            location: location
        }
    }

}

export default UserForm;
