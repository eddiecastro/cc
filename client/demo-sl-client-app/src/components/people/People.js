import React from 'react';
import PeopleList from '../people-list/PeopleList';
import './People.css';
import PeopleFrequency from '../people-frequency/PeopleFrequency';

class People extends React.Component {

  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div style={{ display: "grid", gridTemplateColumns: "repeat(3, 1fr)", gridGap: 20 }}>
        <div>
          <PeopleList/>
        </div>
        <div>
          <PeopleFrequency />
        </div>
      </div>
    );
  }
};

export default People;
