import React from 'react';
import PeopleList from '../people-list/PeopleList';
import Pagination from '../pagination/Pagination';
import './People.css';

class People extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      peopleListData: [],
      peopleMetaData: null
    };

    this.getPeople = this.getPeople.bind(this);
  }

  componentDidMount() {
    this.getPeople(1, 100); //TODO: constants
  }

  render() {
    const { error, isLoaded, peopleListData, peopleMetaData } = this.state;

    if (error) {
      return <div>Error loading people: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading people..</div>
    } else {
      /**
       * paging
       * we load one page at a time
       * we only need the pagination component to change numbers when clicked
       */
      return (
        <div>
          <PeopleList listOfPeople={peopleListData}></PeopleList>
          <Pagination totalPages={this.state.peopleMetaData.paging.total_pages} pageChangeCallback={this.getPeople} />
        </div>
      );
    }
  }

  //TODO: put in its own file ?
  getPeople(page, pageSize) {
    const apiUrl = "http://localhost:8080/people?page=" + page + "&page_size=" + pageSize;

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

  getFrequencies() {
    const apiUrl = "http://localhost:8080/people/frequency"
  }
};

export default People;
