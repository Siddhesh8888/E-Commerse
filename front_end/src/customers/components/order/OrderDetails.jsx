import React from 'react'
import AddressCard from '../addressCard/AddressCard'
import OrderTracker from './OrderTracker'
import { Grid } from '@mui/material'
import StarBorderIcon from '@mui/icons-material/StarBorder';

function OrderDetails() {
    return (
        <div className='px-5 lg:px-20'>
            <div className='p-5 border rounded-md shadow-md'>
                <h1 className='text-xl font-bold opacity-80 pb-5'>Delivery Address : </h1>
                <AddressCard />
            </div>
            <div className='py-10'>
                <OrderTracker activeStep={3} />
            </div>
            <div>
                <Grid container className='space-y-5'>
                    {[1, 1, 1, 1, 1].map((item) => <Grid item container className='p-5 justify-between items-center border rounded-md shadow-xl'>
                        <Grid item sm={6}>
                            <div className='flex items-center'>
                                <img className='h-[7rem] w-[7rem] object-cover object-top' src='https://rukminim1.flixcart.com/image/612/612/xif0q/kurta/l/f/r/xl-k-spl668-yellow-sg-leman-original-imagznqcrahgq9rf.jpeg?q=70' />
                                <div className='pl-5 space-y-1.5'>
                                    <p className='text-lg font-semibold'>Men Slim Mid Rise Black Jeans</p>
                                    <p className='font-semibold space-x-5 opacity-60'><span>Color : pink</span><span>Size : M</span></p>
                                    <p className='font-semibold opacity-60'>Seller : lineria</p>
                                    <p className='text-lg font-semibold'>₹1039</p>
                                </div>
                            </div>
                        </Grid>
                        <Grid item>
                            <StarBorderIcon sx={{ color: 'gold' }} /><span className='pl-2 text-purple-600'>Rate & Review Product</span>
                        </Grid>
                    </Grid>)}
                </Grid>
            </div>
        </div>
    )
}

export default OrderDetails