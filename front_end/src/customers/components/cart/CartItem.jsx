import React from 'react'
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import { Button, IconButton } from '@mui/material';

function CartItem() {
    return (
        <div className='border shadow-lg rounded-radius-3 p-5'>
            <div className='flex items-center'>
                <div className='h-[7rem] w-[7rem] lg:h-[9rem] lg:w-[9rem]'>
                    <img className='object-cover object-top h-full w-full' src='https://rukminim1.flixcart.com/image/612/612/l5h2xe80/kurta/x/6/n/xl-kast-tile-green-majestic-man-original-imagg4z33hu4kzpv.jpeg?q=70' />
                </div>
                <div className='ml-5 space-y-1'>
                    <h1 className='font-semibold '>Men Slim Mid Rise Black Jeans</h1>
                    <p className='text-sm font-semibold opacity-60'>Size : L White</p>
                    <p className='text-sm font-semibold opacity-60'>Selier : Cristaliyo 2 faction</p>
                    <div className='flex items-center space-x-2 pt-3'>
                        <p className='line-through font-semibold opacity-60'>₹1799</p>
                        <p className='font-bold'>₹499</p>
                        <p className='font-semibold text-green-600'>72% off</p>
                    </div>
                </div>
            </div>
            <div className='lg:flex items-center lg:space-x-10 pt-4'>
                <div className='flex items-center space-x-2'>
                    <IconButton>
                        <RemoveCircleOutlineIcon />
                    </IconButton>
                    <span className='px-7 py-1 border rounded-sm'>3</span>
                    <IconButton>
                        <AddCircleOutlineIcon />
                    </IconButton>
                </div>
                <div>
                    <Button sx={{ color: 'violet' }}>Remove</Button>
                </div>

            </div>

        </div>
    )
}

export default CartItem