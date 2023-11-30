import { Grid } from '@mui/material'
import React from 'react'
import AdjustIcon from '@mui/icons-material/Adjust';
import { useNavigate } from 'react-router-dom';

function OrderCard() {
    const navigate = useNavigate()
    return (
        <div onClick={() => navigate(`/account/order/${5}`)} className='p-5 border rounded-md shadow-md hover:shadow-xl'>
            <Grid container sx={{ justifyContent: 'space-between' }}>
                <Grid item xs={6}>
                    <div className='flex cursor-pointer items-center'>
                        <div className='h-[5rem] w-[5rem]'>
                            <img className='object-cover object-top h-full w-full' src='https://rukminim1.flixcart.com/image/612/612/l5h2xe80/kurta/x/6/n/xl-kast-tile-green-majestic-man-original-imagg4z33hu4kzpv.jpeg?q=70' />
                        </div>
                        <div className='pl-3 space-y-2'>
                            <p className='text-lg font-semibold'>Men Slim Mid Rise Black Jeans</p>
                            <p className='text-md font-semicold opacity-70'>Size: M</p>
                            <p className='text-md font-semicold opacity-70'>Color : Blue</p>
                        </div>
                    </div>
                </Grid>
                <Grid item xs={2}>
                    <p className='text-lg font-semibold opacity-70'>₹1039</p>
                </Grid>
                <Grid item xs={4}>
                    {true && <p><AdjustIcon sx={{ width: '15px', height: '15px' }} className='mr-2 text-green-600' /><span className='text-lg font-semibold'>Delivered On Mar 03</span><p>Your Item Has Been Deliverd</p></p>}
                    {false && <p className='text-lg font-semibold'>Expected Delivery On Mar 03</p>}
                </Grid>
            </Grid>
        </div>
    )
}

export default OrderCard