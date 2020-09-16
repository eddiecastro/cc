import React from 'react';
import './PeopleDuplicate.css';

class PeopleDuplicate extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      buttonClicked: false,
      peopleDuplicates: []
    }

    this.getDuplicates = this.getDuplicates.bind(this);
  }

  render() {
    if (this.state.buttonClicked === false) {
      return (
        <button onClick={this.getDuplicates}>
          Show potential duplicates
        </button>
      );
    } else {
      return (
        <div>
          <button onClick={this.getDuplicates}>
            Hide potential duplicates
          </button>
          <div data-testid="PeopleDuplicate"></div>
            <div>
              {this.state.peopleDuplicates}
            </div>
        </div>
      );
    }
  }

  getDuplicates() {
    if(this.state.buttonClicked === false) {
      const apiUrl = "http://localhost:8080/people/duplicates"

      fetch(apiUrl)
        .then(res => res.json())
        .then(
          (result) => {
            this.handleDuplicateResult(result);
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
        peopleDuplicates: [],
        buttonClicked: false
      });
    }
  }

  handleDuplicateResult(result) {
    const duplicates = result.map((r, i) => {
      return (
        <div key={i} className="duplicate-container" data-testid="PeopleDuplicate">
          Duplicate {i}
          <div style={{ display: "grid", gridTemplateColumns: "repeat(2, 1fr)", gridGap: 20}}>
            {this.getDuplicate(r.duplicate1)}
            {this.getDuplicate(r.duplicate2)}
          </div>
        </div>
      )
    });

    this.setState({
      peopleDuplicates: duplicates,
      buttonClicked: true
    });
  }

  getDuplicate(duplicate) {
    return (
      <div>
        <div>Name:  {duplicate.display_name}</div>
        <div>Email: {duplicate.email_address}</div>
        <div>Title: {duplicate.title}</div>
      </div>
    )
  }
};

export default PeopleDuplicate;