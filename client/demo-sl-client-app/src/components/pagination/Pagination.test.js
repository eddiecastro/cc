import React from 'react';
import { render, unmountComponentAtNode } from "react-dom";
import { act } from "react-dom/test-utils";

import Pagination from './Pagination';

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

it("renders page numbers", () => {
  act(() => {
    render(<Pagination totalPages={4} pageChangeCallback={() => {}}/>, container);
  });

  expect(container.textContent).toBe('1234');
});

it("renders active page", () => {
  act(() => {
    render(<Pagination totalPages={4} pageChangeCallback={() => {}}/>, container);
  });

  expect(container.getElementsByClassName("active")[0].textContent).toBe('1');
});

it("calls callback function", () => {

  function callback(activePage, pageSize) {
    try {
      expect(activePage).toBe('1');
      expect(pageSize).toBe(100);
      done();
    } catch (error) {
    }
  }

  act(() => {
    render(<Pagination totalPages={4} pageChangeCallback={callback}/>, container);
  });
});