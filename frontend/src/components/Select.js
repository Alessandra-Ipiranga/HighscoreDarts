import styled from 'styled-components/macro'

export default styled.select`
  -moz-appearance: none;
  -moz-padding-start: calc(10px - 3px);
  -webkit-appearance: none;
  border: none;
  width: 100%;
  height: 40px;
  padding-left: 10px;
  color: #666;
  font-family: 'Open Sans', sans-serif;
  font-size: 16px;
  box-shadow: 2px 2px 5px 1px rgba(0,0,0,0.3);
  border-radius: 3px;
  outline: none;

  select::-ms-expand {
    display: none;
  }
`