import { Link } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { BASE_PATH, ABOUT_PATH, PROJECTS_PATH, CONTACT_PATH } from "../routes/routes.js";

const NavBar = () => {
    const { t } = useTranslation();

    return (
        <nav className="navbar">
            <ul>
                <li><Link to={BASE_PATH + "/"}>{t("NavBar.init")}</Link></li>
                <li><Link to={ABOUT_PATH}>{t("NavBar.about")}</Link></li>
                <li><Link to={PROJECTS_PATH}>{t("NavBar.projects")}</Link></li>
                <li><Link to={CONTACT_PATH}>{t("NavBar.contact")}</Link></li>
            </ul>
        </nav>
    );
};

export default NavBar;