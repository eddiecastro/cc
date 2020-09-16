import React from 'react';
import { render, unmountComponentAtNode } from "react-dom";
import { act } from "react-dom/test-utils";

import PeopleList from './PeopleList';

let container = null;
beforeEach(() => {
  // setup a DOM element as a render target
  container = document.createElement("div");
  document.body.appendChild(container);
});

afterEach(() => {
  // cleanup on exiting
  unmountComponentAtNode(container);
  container.remove();
  container = null;
});

it("renders loading text", () => {
  act(() => {
    render(<PeopleList />, container);
  });
  expect(container.textContent).toBe('Loading people..');
});

it("renders table with people and pagination", async () => {
  var expectedDisplayName = "Test Name";
  var expectedEmailAddress = "test@gmail.com";
  var expectedTitle="Software Engineer";
  
  const fakePeopleListResponse = {
    metadata: {
      paging: {
        current_page: 1,
        next_page: 2,
        prev_page: 0,
        total_pages: 4,
        total_count: 351
      }
    },
    data: [
      {
        display_name: "Test Name",
        email_address: "test@gmail.com",
        title: "Software Engineer"
      },
    ]
  };

  jest.spyOn(global, "fetch").mockImplementation(() =>
    Promise.resolve({
      json: () => Promise.resolve(fakePeopleListResponse)
    })
  );

  await act(async () => {
    render(<PeopleList />, container);
  });

  expect(container.textContent).toMatch(expectedDisplayName);
  expect(container.textContent).toMatch(expectedEmailAddress);
  expect(container.textContent).toMatch(expectedTitle);
  expect(container.querySelector("[data-testid='PeopleList']"));
  expect(container.querySelector("[data-testid='Pagination']"));
});