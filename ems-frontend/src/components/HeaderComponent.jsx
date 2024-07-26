import React from "react";
import "../components/HeaderComponent.css";

function HeaderComponent() {
  return (
    <div>
      <header>
        <nav className="header__navbar">
          <a
            target="_blank"
            className="header__navbar--link"
            href="https://www.facebook.com"
          >
            Employee Management System
          </a>
        </nav>
      </header>
    </div>
  );
}
export default HeaderComponent;
