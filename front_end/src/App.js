import logo from './logo.svg';
import './App.css';
import Navigation from './customers/components/navigation/Navigation';
import HomePage from './customers/pages/homepage/HomePage';
import Product from './customers/components/product/Product';
import Footer from './customers/components/footer/Footer';
import ProductDetails from './customers/components/productDetails/ProductDetails';
import Cart from './customers/components/cart/Cart';
import Checkout from './customers/components/checkout/Checkout';
import Order from './customers/components/order/Order';
import OrderDetails from './customers/components/order/OrderDetails';
import { Route, Routes } from 'react-router-dom';
import CustomerRouters from './customers/routers/CustomerRouters';

function App() {
  return (
    <div>
      <Routes>
        <Route path='/*' element={<CustomerRouters />}></Route>
      </Routes>
    </div>
  );
}

export default App;
