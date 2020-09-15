import React from 'react';
import PropTypes from 'prop-types';
import styles from './Pagination.module.css';

class Pagination extends React.Component {
  
  constructor(props) {
    super(props);
    this.state = {
      totalPages: this.props.totalPages
    };
  }

  componentDidMount() {
    // const pagesToDisplay = peopleListData.map((person) =>
    //   <tr align="left">
    //     <td>{person.display_name}</td>
    //     <td>{person.email_address}</td>
    //     <td>{person.title}</td>
    //   </tr>
  }

  render() {
    return (
      <div class="pagination">
        <a href="#">&laquo;</a>
        <a href="#">1</a>
        <a class="active" href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">6</a>
        <a href="#">&raquo;</a>
      </div>
    )
  }

};

Pagination.propTypes = {
  totalPages: PropTypes.number,
}

Pagination.defaultProps = {
  totalPages: 1,
};

export default Pagination;
