import React, { useState, useEffect } from 'react';

function ShoppingCartApp() {
  const [cart, setCart] = useState([]);

  const addToCart = (product) => {
    setCart([...cart, product]);
  };

  const removeFromCart = (id) => {
    setCart(cart.filter(item => item.id !== id));
  };

  const clearCart = () => {
    setCart([]);
  };

  useEffect(() => {
    const timeout = setTimeout(() => {
      fetch('/api/cart', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ cart }),
      });
    }, 500);
    return () => clearTimeout(timeout);
  }, [cart]);

  const products = [
    { id: 1, name: 'Apple' },
    { id: 2, name: 'Banana' },
  ];

  return (
    <div>
      <h2>My Store</h2>
      {products.map(product => (
        <div key={product.id}>
          <span>{product.name} </span>
          <button onClick={() => addToCart(product)}>Add to Cart</button>
        </div>
      ))}
      <h3>Cart</h3>
      {cart.map(item => (
        <div key={item.id}>
          <span>{item.name} </span>
          <button onClick={() => removeFromCart(item.id)}>Remove</button>
        </div>
      ))}
      {cart.length > 0 && <button onClick={clearCart}>Clear Cart</button>}
    </div>
  );
}

export default ShoppingCartApp;
