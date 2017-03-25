/**
 * Created by gary on 2017-03-24.
 */
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
            pref_bus_prof: false,
            pref_food_drink: false,
            pref_comm_culture: false,
            pref_perf_vis_art: false,
            pref_film_media_ent: false,
            pref_sports_fitness: false,
            pref_health_well: false,
            pref_sci_tech: false,
            pref_trav_outd: false,
            pref_char_games: false,
            pref_religion_spirit: false,
            pref_family_edu: false,
            pref_season_holi: false,
            pref_gov_poli: false,
            pref_fash_beaut: false,
            pref_home_life: false,
            pref_auto_boat_air: false,
            pref_hobbies_ints: false,
            pref_other: false
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
                <div className="profile-page">
                    <div className="form-group">
                        <label htmlFor="signin-name" className="col-sm-3 control-label">Username</label>
                        <div className="col-sm-9">
                            <input type="text"
                                   className="form-control" id="signin-name"
                                   placeholder="Username"
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
                        <div className="col-sm-3">
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
                </div>
               {/** Checkboxes for preferences **/}

                <hr/>

                <center><h4> Tell us what you are interested in: </h4></center>
                <br/>
                <div className="interests">
                <div className="option">
                        <div className="option-name"><label htmlFor="pref_music" className="">Music</label></div>
                        <div className="check-box">
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

                <div className="option">
                        <div className="option-name"><label htmlFor="pref_bus_prof" className="">Business & Professional</label></div>
                        <div className="check-box">
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

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_food_drink" className="">Food & Drink</label></div>
                    <div className="check-box">
                        <input
                            name="pref_food_drink"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_food_drink}
                            onChange={this.handleInputChange}
                            ref="pref_food_drink"
                        />
                    </div>
                </div>

                <div className="option">
                   <div className="option-name"> <label htmlFor="pref_comm_culture" className="">Community & Culture</label></div>
                    <div className="check-box">
                        <input
                            name="pref_comm_culture"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_comm_culture}
                            onChange={this.handleInputChange}
                            ref="pref_comm_culture"
                        />
                    </div>
                </div>

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_perf_vis_art" className="">Performing & Visual Arts</label></div>
                    <div className="check-box">
                        <input
                            name="pref_perf_vis_art"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_perf_vis_art}
                            onChange={this.handleInputChange}
                            ref="pref_perf_vis_art"
                        />
                    </div>
                </div>

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_film_media_ent" className="">Film, Media & Entertainment</label></div>
                    <div className="check-box">
                        <input
                            name="pref_film_media_ent"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_film_media_ent}
                            onChange={this.handleInputChange}
                            ref="pref_film_media_ent"
                        />
                    </div>
                </div>

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_sports_fitness" className="">Sports & Fitness</label></div>
                    <div className="check-box">
                        <input
                            name="pref_sports_fitness"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_sports_fitness}
                            onChange={this.handleInputChange}
                            ref="pref_sports_fitness"
                        />
                    </div>
                </div>

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_health_well" className="">Health & Wellness</label></div>
                    <div className="check-box">
                        <input
                            name="pref_health_well"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_health_well}
                            onChange={this.handleInputChange}
                            ref="pref_health_well"
                        />
                    </div>
                </div>

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_sci_tech" className="">Science & Technology</label></div>
                    <div className="check-box">
                        <input
                            name="pref_sci_tech"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_sci_tech}
                            onChange={this.handleInputChange}
                            ref="pref_sci_tech"
                        />
                    </div>
                </div>

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_trav_outd" className="">Travel & Outdoor</label></div>
                    <div className="check-box">
                        <input
                            name="pref_trav_outd"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_trav_outd}
                            onChange={this.handleInputChange}
                            ref="pref_trav_outd"
                        />
                    </div>
                </div>

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_char_games" className="">Charity & Games</label></div>
                    <div className="check-box">
                        <input
                            name="pref_char_games"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_char_games}
                            onChange={this.handleInputChange}
                            ref="pref_char_games"
                        />
                    </div>
                </div>

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_religion_spirit" className="">Religion & Spirituality</label></div>
                    <div className="check-box">
                        <input
                            name="pref_religion_spirit"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_religion_spirit}
                            onChange={this.handleInputChange}
                            ref="pref_religion_spirit"
                        />
                    </div>
                </div>

                <div className="option">
                   <div className="option-name"> <label htmlFor="pref_family_edu" className="">Family & Education</label></div>
                    <div className="check-box">
                        <input
                            name="pref_family_edu"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_family_edu}
                            onChange={this.handleInputChange}
                            ref="pref_family_edu"
                        />
                    </div>
                </div>

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_season_holi" className="">Seasonal & Holiday</label></div>
                    <div className="check-box">
                        <input
                            name="pref_season_holi"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_season_holi}
                            onChange={this.handleInputChange}
                            ref="pref_season_holi"
                        />
                    </div>
                </div>

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_gov_poli" className="">Government & Politics</label></div>
                    <div className="check-box">
                        <input
                            name="pref_gov_poli"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_gov_poli}
                            onChange={this.handleInputChange}
                            ref="pref_gov_poli"
                        />
                    </div>
                </div>

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_fash_beaut" className="">Fashion & Beauty</label></div>
                    <div className="check-box">
                        <input
                            name="pref_fash_beaut"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_fash_beaut}
                            onChange={this.handleInputChange}
                            ref="pref_fash_beaut"
                        />
                    </div>
                </div>

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_home_life" className="">Home & Lifestyle</label></div>
                    <div className="check-box">
                        <input
                            name="pref_home_life"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_home_life}
                            onChange={this.handleInputChange}
                            ref="pref_home_life"
                        />
                    </div>
                </div>

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_auto_boat_air" className="">Auto, Boat & Air</label></div>
                    <div className="check-box">
                        <input
                            name="pref_auto_boat_air"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_auto_boat_air}
                            onChange={this.handleInputChange}
                            ref="pref_auto_boat_air"
                        />
                    </div>
                </div>

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_hobbies_ints" className="">Hobbies & Special Interest</label></div>
                    <div className="check-box">
                        <input
                            name="pref_hobbies_ints"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_hobbies_ints}
                            onChange={this.handleInputChange}
                            ref="pref_hobbies_ints"
                        />
                    </div>
                </div>

                <div className="option">
                    <div className="option-name"><label htmlFor="pref_other" className="">Other</label> </div>
                    <div className="check-box">
                        <input
                            name="pref_other"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_other}
                            onChange={this.handleInputChange}
                            ref="pref_other"
                        />
                    </div>
                </div>


                {/** Submit button **/}

                <div className="form-group">
                    <div className="col-sm-offset-3 col-sm-9">
                        <button type="submit" className="btn btn-default">{this.props.submitLabel}</button>
                    </div>
                </div>
                </div>
            </form>
        )
    }


    data() {
        let name = "";
        if (ReactDOM.findDOMNode(this.refs.name).value.trim() == ""){
            name = this.props.username;
            console.log("blank username, using " + name);
        } else {
            name = ReactDOM.findDOMNode(this.refs.name).value.trim()
        }

        let password = "";
        if (ReactDOM.findDOMNode(this.refs.password).value.trim() == ""){
             password = this.props.password;
            console.log("blank password, using " + password);

        } else {
            password = ReactDOM.findDOMNode(this.refs.password).value.trim();
        }

        let age = "";
        if (ReactDOM.findDOMNode(this.refs.age).value.trim() == ""){
            age = this.props.age;
            console.log("blank age, using " + this.props.age);
        } else {
            age = ReactDOM.findDOMNode(this.refs.age).value.trim();
        }

        let location = "";
        if (ReactDOM.findDOMNode(this.refs.location).value.trim() == ""){
            location = this.props.location;
            console.log("blank location, using " + this.props.location);
        } else {
            location = ReactDOM.findDOMNode(this.refs.location).value.trim();
        }

        return {
            username: name,
            password: password,
            age: age,
            location: location,
            pref_music: this.state.pref_music,
            pref_bus_prof: this.state.pref_bus_prof,
            pref_food_drink: this.state.pref_food_drink,
            pref_comm_culture: this.state.pref_comm_culture,
            pref_perf_vis_art: this.state.pref_perf_vis_art,
            pref_film_media_ent: this.state.pref_film_media_ent,
            pref_sports_fitness: this.state.pref_sports_fitness,
            pref_health_well: this.state.pref_health_well,
            pref_sci_tech: this.state.pref_sci_tech,
            pref_trav_outd: this.state.pref_trav_outd,
            pref_char_games: this.state.pref_char_games,
            pref_religion_spirit: this.state.pref_religion_spirit,
            pref_family_edu: this.state.pref_family_edu,
            pref_season_holi: this.state.pref_season_holi,
            pref_gov_poli: this.state.pref_gov_poli,
            pref_fash_beaut: this.state.pref_fash_beaut,
            pref_home_life: this.state.pref_home_life,
            pref_auto_boat_air: this.state.pref_auto_boat_air,
            pref_hobbies_ints: this.state.pref_hobbies_ints,
            pref_other: this.state.pref_other,
        }
    }

}

export default UserForm;
