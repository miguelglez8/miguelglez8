import { Link } from "react-router-dom";
import { useTranslation } from "react-i18next";
import GitHubIcon from "../icons/GitHubIcon.jsx";
import data from "../data/data.js";

const Footer = () => {
    const { t } = useTranslation();

    return (
        <footer className="footer">
            <p>
                &copy; {new Date().getFullYear()} {data.personal.name}. {t("Footer.content")}.
                <Link to={data.personal.repo} style={{ marginLeft: '5px' }}>
                    <GitHubIcon size={20} color="#000000"/>
                </Link>
            </p>
        </footer>
    );
};

export default Footer;