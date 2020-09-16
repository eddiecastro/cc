import React from 'react';
import './PeopleFrequency.css';

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
    result.sort((a, b) => b.frequency - a.frequency); //TODO: could be used by user to sort asc or desc in future. for now, desc

    const frequencies = result.map((r) => {
      return (
        <div key={r.emailCharacter}>{r.emailCharacter}  -  {r.frequency}</div>
      )
    });

    this.setState({
      peopleFrequencies: frequencies,
      buttonClicked: true
    });
  }
};

export default PeopleFrequency;