import React from 'react';
import AliceCarousel from 'react-alice-carousel';
import 'react-alice-carousel/lib/alice-carousel.css';
import { mainCarousalData } from './MainCarouselData';



const MainCarousel = () => {
    const items = mainCarousalData.map((item)=><img src={item.image} className='cursor-pointer z-10' role='presentation'/>)



    return(
        <AliceCarousel
        items={items}
        disableButtonsControls
        autoPlay
        autoPlayInterval={1000}
        infinite
    />
    )
    
    };

export default MainCarousel;