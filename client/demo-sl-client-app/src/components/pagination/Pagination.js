import React from 'react';
import PropTypes from 'prop-types';
import './Pagination.css';

class Pagination extends React.Component {
  
  constructor(props) {
    super(props);
    this.state = {
      totalPages: this.props.totalPages,
      pages: []
    };

  }

  componentDidMount() {
    this.setPages(1); //load page 1 
  }

  render() {
    return (
      <div className='pagination'>
        {this.state.pages}
      </div>
    );
  }

  onChange(key) {
    this.setPages(key); //load a different page depending on what the user clicked on
  }

  //create a new pages array with the correct page active
  setPages(activePage) {
    let newPages = Array.from({ length: this.state.totalPages }, (_, k) => (
      <button key={k} className={activePage === (k+1) ? 'active' : ''} onClick={() => this.onChange(k+1)}>
        {k+1}
      </button>
    ));

    this.setState({
      pages: newPages
    });

    //call the callback from the parent component that will load new items for the selected page
    this.props.pageChangeCallback(activePage, 100);
  }

};

Pagination.propTypes = {
  totalPages: PropTypes.number,
  pageChangeCallback: PropTypes.func,
}

Pagination.defaultProps = {
  totalPages: 1,
  pageChangeCallback: {}
};

export default Pagination;
