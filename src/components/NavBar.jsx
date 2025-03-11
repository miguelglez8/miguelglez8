import { useState } from "react";
import { Link } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { BASE_PATH, ABOUT_PATH, PROJECTS_PATH, CONTACT_PATH } from "../routes/routes.js";

const NavBar = () => {
    const { t } = useTranslation();
    const [isMenuOpen, setIsMenuOpen] = useState(false);

    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen);
    };

    return (
        <nav className="navbar">
            <div className="navbar-container">
                <div
                    className={`menu-toggle ${isMenuOpen ? "open" : ""}`}
                    onClick={toggleMenu}
                    aria-expanded={isMenuOpen}
                >
                    <span className="bar"></span>
                    <span className="bar"></span>
                    <span className="bar"></span>
                </div>

                <div className={`menu ${isMenuOpen ? "active" : ""}`}>
                    <ul>
                        <li><Link to={BASE_PATH + "/"} onClick={() => setIsMenuOpen(false)}>{t("NavBar.init")}</Link></li>
                        <li><Link to={ABOUT_PATH} onClick={() => setIsMenuOpen(false)}>{t("NavBar.about")}</Link></li>
                        <li><Link to={PROJECTS_PATH} onClick={() => setIsMenuOpen(false)}>{t("NavBar.projects")}</Link></li>
                        <li><Link to={CONTACT_PATH} onClick={() => setIsMenuOpen(false)}>{t("NavBar.contact")}</Link></li>
                    </ul>
                </div>
            </div>
        </nav>
    );
};

export default NavBar;
