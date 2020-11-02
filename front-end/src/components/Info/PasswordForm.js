import React from 'react';

class PasswordForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: ''};

        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }
    

    render() {
        return (
            <form onSubmit={(event)=> {event.preventDefault(); this.props.onSubmit(this.state.value)}}>
                <input disabled={!this.props.isCreator} type="text" value={this.state.value}
                       onChange={this.handleChange} placeHolder="Enter a password"/>
                {this.props.isCreator &&
                <input type="submit" value="Save"/>
                }
            </form>
        );
    }
}

export default PasswordForm;