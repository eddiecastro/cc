import React from 'react';
import './PeopleFrequency.css';
import PropTypes from 'prop-types';

class PeopleFrequency extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      buttonClicked: false,
      peopleFrequencies: []
    }

    this.getFrequencies = this.getFrequencies.bind(this);
  }

  render() {
    if (this.state.buttonClicked === false) {
      return (
        <button onClick={this.getFrequencies}>
          Show email character frequencies
        </button>
      );
    } else {
      return (
        <div>
          <button onClick={this.getFrequencies}>
            Hide email character frequencies
          </button>
          <div data-testid="PeopleFrequency"></div>
            <div>
              {this.state.peopleFrequencies}
            </div>
        </div>
      );
    }
  }

  getFrequencies() {
    if(this.state.buttonClicked === false) {
      const apiUrl = "http://localhost:8080/people/frequency"

      fetch(apiUrl)
        .then(res => res.json())
        .then(
          (result) => {
            this.handleFrequencyResult(result);
          },
          (error) => {
            this.setState({
              //TODO: should show an alert
              error
            });
          }
        )
    } else {
      this.setState({
        peopleFrequencies: [],
        buttonClicked: false
      });
    }
  }

  handleFrequencyResult(result) {
    const frequencyData = result;
    console.log('break');

    const frequencies = Object.entries(frequencyData).map(([key, value]) => {
      console.log(key);
      console.log(value);

      return (
        <div>{key}  -  {value}</div>
      )
    });

    this.setState({
      peopleFrequencies: frequencies,
      buttonClicked: true
    });
  }
};

PeopleFrequency.propTypes = {
}

PeopleFrequency.defaultProps = {
};

export default PeopleFrequency;