"use client";

import { useState } from 'react';
import LoginPopup from '../components/LoginPopup';
import SignupPopup from '../components/SignupPopup';

export default function Register() {
    const [showLogin, setShowLogin] = useState(true);
    const [showSignup, setShowSignup] = useState(false);

    const togglePopup = () => {
        setShowLogin(!showLogin);
        setShowSignup(!showSignup);
    };

    return (
        <div className="flex justify-center items-center h-screen">
            {showLogin && <LoginPopup togglePopup={togglePopup} />}
            {showSignup && <SignupPopup togglePopup={togglePopup} />}
        </div>
    );
}
