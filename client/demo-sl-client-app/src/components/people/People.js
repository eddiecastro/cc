import React from 'react';
import PeopleList from '../people-list/PeopleList';
import Pagination from '../pagination/Pagination';

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
          <Pagination totalPages={this.state.peopleMetaData.paging.total_pages} />
        </div>
      );
    }
  }

  //TODO: put in its own file 
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

  handleResult(result, page) {

    /**
    this.state.peopleMetaData.paging
    {current_page: 1, next_page: 2, prev_page: 0, total_pages: 4, total_count: 351}
     */
  }

};

export default People;
