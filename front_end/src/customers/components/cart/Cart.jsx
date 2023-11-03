import React from 'react'
import CartItem from './CartItem'
import { Button } from '@mui/material'

function Cart() {
    return (
        <div className='pt-5'>
            <div className='lg:grid grid-cols-3 lg:px-16 relative'>
                <div className='col-span-2'>
                    {[1, 1, 1, 1].map((item) => <CartItem />)}
                </div>
                <div className='px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0'>
                    <div className='border p-5'>
                        <p className='uppercase font-bold opacity-60 pb-4'>Price details</p>
                        <hr />
                        <div className='space-y-3 font-semibold mb-10'>
                            <div className='flex justify-between'>
                                <p>Price(3 item)</p>
                                <p>₹4697</p>
                            </div>
                            <div className='flex justify-between'>
                                <p>Discount</p>
                                <p className='text-green-600'>₹-3419</p>
                            </div>
                            <div className='flex justify-between'>
                                <p>Delivery Charges</p>
                                <p className='text-green-600'>Free</p>
                            </div>
                            <div className='flex justify-between font-bold'>
                                <p>Total Amount</p>
                                <p className='text-green-600'>₹1278</p>
                            </div>
                        </div>
                        <Button className='w-full' variant='contained' sx={{ py: '.7rem', bgcolor: 'dodgerblue' }}>Check Out</Button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Cart