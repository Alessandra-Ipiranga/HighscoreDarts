import styled from 'styled-components/macro'

export default styled.button`
  background-color: #fac800;
  border-color: #fac800;
  border-style: none;
  color: #fff;
  padding: 20px;
  margin-top: 20px;
  :focus-visible {
    outline-color: rgba(250, 200, 0, .2);
  }
  :hover {
    color: #000;
    transition: 2s;
  }
`