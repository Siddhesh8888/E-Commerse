import React from 'react'
import { Route, Routes } from 'react-router-dom'
import HomePage from '../pages/homepage/HomePage'
import Cart from '../components/cart/Cart'
import Navigation from '../components/navigation/Navigation';
import Footer from '../components/footer/Footer';
import Product from '../components/product/Product';
import ProductDetails from '../components/productDetails/ProductDetails';
import Checkout from '../components/checkout/Checkout';
import OrderDetails from '../components/order/OrderDetails';
import Order from '../components/order/Order';

function CustomerRouters() {
    return (
        <div>
            <div>
                <Navigation />
            </div>
            <Routes>
                <Route path='/' element={<HomePage />}></Route>
                <Route path='/login' element={<HomePage />}></Route>
                <Route path='/register' element={<HomePage />}></Route>
                <Route path='/cart' element={<Cart />}></Route>
                <Route path='/:levelOne/:levelTwo/:levelThree' element={<Product />}></Route>
                <Route path='/:product/:productId' element={<ProductDetails />}></Route>
                <Route path='/checkout' element={<Checkout />}></Route>
                <Route path='/account/order' element={<Order />}></Route>
                <Route path='/account/order/:orderId' element={<OrderDetails />}></Route>
            </Routes>

            {/* <HomePage/> */}
            {/* <Product/> */}
            {/* <ProductDetails/> */}
            {/* <Cart/> */}
            {/* <Checkout/> */}
            {/* <Order /> */}
            {/* <OrderDetails /> */}
            <div>
                <Footer />
            </div>
        </div>
    )
}

export default CustomerRouters