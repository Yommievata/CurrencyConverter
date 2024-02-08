import React, { useState } from 'react';
import axios from 'axios';
import './CurrencyConverter.css';

const CurrencyConverter = () => {
  const [fromCurrency, setFromCurrency] = useState('');
  const [toCurrency, setToCurrency] = useState('');
  const [amount, setAmount] = useState('');
  const [convertedAmount, setConvertedAmount] = useState(null);

  const convertCurrency = async () => {
    try {
      axios.defaults.xsrfHeaderName = "X-CSRFToken";

      const uppercasedFromCurrency = fromCurrency.toUpperCase();
      const uppercasedToCurrency = toCurrency.toUpperCase();

      const response = await axios.get(
        `http://localhost:8080/convertCurrency?from=${uppercasedFromCurrency}&to=${uppercasedToCurrency}&amount=${amount}`
      );
      setConvertedAmount(response.data);
    } catch (error) {
      console.error('Error converting currency:', error.message);
    }
  };

  return (
    <div className="container">
      <h1>Currency Converter</h1>
      <div>
        <label>From Currency:</label>
        <input type="text" value={fromCurrency} onChange={(e) => setFromCurrency(e.target.value)} />
      </div>
      <div>
        <label>To Currency:</label>
        <input type="text" value={toCurrency} onChange={(e) => setToCurrency(e.target.value)} />
      </div>
      <div>
        <label>Amount:</label>
        <input type="number" value={amount} onChange={(e) => setAmount(e.target.value)} />
      </div>
      <button onClick={convertCurrency}>Convert</button>
      {convertedAmount !== null && (
        <div className="result">
          <h3>Converted Amount:</h3>
          <p>{toCurrency} {convertedAmount.toFixed(2)}</p>
        </div>
      )}
    </div>
  );
};

export default CurrencyConverter;
