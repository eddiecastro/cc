import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import PeopleList from './PeopleList';

describe('<PeopleList />', () => {
  test('it should mount', () => {
    render(<PeopleList />);
    
    const peopleList = screen.getByTestId('PeopleList');

    expect(peopleList).toBeInTheDocument();
  });
});