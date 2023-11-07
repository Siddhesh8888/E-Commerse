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

function App() {
  return (
    <div>
      <Navigation />
      <div>
        {/* <HomePage/> */}
        {/* <Product/> */}
        {/* <ProductDetails/> */}
        {/* <Cart/> */}
        {/* <Checkout/> */}
        {/* <Order /> */}
        <OrderDetails />
      </div>
      <Footer />
    </div>
  );
}

export default App;
