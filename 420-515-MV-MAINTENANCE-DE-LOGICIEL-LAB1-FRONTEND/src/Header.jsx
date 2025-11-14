import React, { useState } from "react";
import './css/Header.css'

function Header() {
    const [isDarkMode, setIsDarkMode] = useState(false);

    // Boutton de dark mode
    const toggleDarkMode = () => {
        setIsDarkMode(!isDarkMode);
        console.log(isDarkMode);
    };
    return (
    <header>

{/* 
        <button onClick={toggleDarkMode} className="BouttonDark">Dark mode</button> */}


    </header>
    )
}

export default Header;