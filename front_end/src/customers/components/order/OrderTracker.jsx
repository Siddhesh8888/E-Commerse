import { Step, StepLabel, Stepper } from "@mui/material";
import React from "react";

const step = ['Placed', 'Order Confirmed', 'Shipped', 'Out For Delivery', 'Delivered']
function OrderTracker({ activeStep }) {

    return (
        <div className="w-full">
            <Stepper activeStep={activeStep} alternativeLabel>
                {step.map((label) => <Step>
                    <StepLabel>{label}</StepLabel>
                </Step>)}
            </Stepper>
        </div>
    )
}

export default OrderTracker;