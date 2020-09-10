import React from 'react';
import PeopleList from '../people-list/PeopleList';

class People extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      peopleListData: [],
      peopleMetaData: null
    };
  }

  componentDidMount() {
    this.getAllPeople();
  }

  render() {
    const { error, isLoaded, peopleListData, peopleMetaData } = this.state;

    if (error) {
      return <div>Error loading people: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading people..</div>
    } else {
      return <PeopleList listOfPeople={peopleListData}></PeopleList>
    }
  }

  getAllPeople() {
    this.getPeopleFromPage(1);
  }

  getPeopleFromPage(page) {
    const apiUrl = "http://localhost:8080/people?page=" + page;

    fetch(apiUrl)
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            peopleListData: result.data,
            peopleMetaData: result.metadata
          });
        },
        (error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
  }

};

export default People;
