import logo from './logo.svg';
import './App.css';
import Navigation from './customers/components/navigation/Navigation';
import HomePage from './customers/pages/homepage/HomePage';
import Product from './customers/components/product/Product';
import Footer from './customers/components/footer/Footer';

function App() {
  return (
    <div>
      <Navigation/>
      <div>
        {/* <HomePage/> */}
        <Product/>
      </div>
      <Footer/>
    </div>
  );
}

export default App;
